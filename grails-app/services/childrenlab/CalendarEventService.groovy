package childrenlab

import grails.transaction.Transactional
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter


@Transactional
class CalendarEventService {

    def springSecurityService

    def addEvent(String eventName, String startDate, String endDate, String color, String status, String description, int alert, String city, String state, int timezoneOffset, String repeat, String repeatEventId) {
        if (!eventName || !startDate || !endDate) {
            return [success: false, message: "One of parameter doesn't fill"]
        }

        def user = springSecurityService.currentUser as User

        if(!user) {
            return [status: 400, message: "Error occurred", error: "Please login first"]
        }

        EventRepeat eventRepeat
        try {
            Date start = Date.parse("yyyy/MM/dd HH:mm:ss", startDate)
            Date end = Date.parse("yyyy/MM/dd HH:mm:ss", endDate)

            if (timezoneOffset != 0) {
                timezoneOffset = (timezoneOffset / 60).toInteger()
            }


            DateTime startDateTime = new DateTime(start).withZone(DateTimeZone.forOffsetHours(0))
            DateTime endDateTime = new DateTime(end).withZone(DateTimeZone.forOffsetHours(0))


            eventRepeat = repeat ? repeat.toUpperCase() as EventRepeat : null

            def eventStatus = eventRepeat != null ? EventStatus.ENABLED : EventStatus.Open

            def createdFromEvent = null
            if(repeatEventId != 0) {
                createdFromEvent = CalendarEvent.get(repeatEventId) ?: null
                eventRepeat = null
            }


            def newEvent = new CalendarEvent(eventName: eventName, startDate: startDateTime.toDate(), endDate: endDateTime.toDate(), color: color,
                    status: eventStatus, description: description,
                    user: user, alert: alert, city: city, state: state, timezoneOffset: timezoneOffset, eventRepeat: eventRepeat, createdFromEvent: createdFromEvent).save(failOnError: true)

            if (!newEvent) {
                return [success: false, message: "Something wrong when save the event"]
            }
            println newEvent.startDate
            return [success: true, newEvent: newEvent, status: 200]
        } catch (Exception e) {
            return [status: 400, message: "Error occurred", error: e.toString()]
        }

    }

    def getEventsByUser(String query, int month, int year, int day) {
        try {
            def user = springSecurityService.currentUser as User
            def events
            if (query == "month") {

                events = CalendarEvent.findAll("from CalendarEvent where user = ? and Month(startDate) = ? and Year(startDate) = ? order by startDate", [user, month, year])
            } else if (query == "day") {
                events = CalendarEvent.findAll("from CalendarEvent where user = ? and Month(startDate) = ? and Year(startDate) = ? and Day(startDate) = ?  order by startDate", [user, month, year, day])
            } else {
                events = CalendarEvent.findAll("from CalendarEvent where user = ?  order by startDate", [user])
            }

            DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
            def date = formatter.parseDateTime("${year}-${month}-${day}").withZone(DateTimeZone.forOffsetHours(0))


            def dailyRepeat = CalendarEvent.findAll("from CalendarEvent where user = ? and eventRepeat = ? and status = ? and startDate < ? order by startDate "
                    , [user, EventRepeat.DAILY, EventStatus.ENABLED, date.toDate()])
            def weeklyRepeat = CalendarEvent.findAll("from CalendarEvent where user = ? and eventRepeat = ? and status = ?", [user, EventRepeat.WEEKLY, EventStatus.ENABLED])

            dailyRepeat.each(){ event ->
                if(events.find {it.id == event.id} == null && events.find {it.createdFromEvent.id == event.id} == null) {
                    events.add(event)
                }
            }
            weeklyRepeat.each() { event ->
                def weeklyDate = new DateTime(event.startDate).withZone(DateTimeZone.forOffsetHours(0))
                if(date.getDayOfWeek() == weeklyDate.getDayOfWeek() && !events.find {it.createdFromEvent.id == event.id}) {
                    if(!events.find {it.id == event.id}) {
                        events.add(event)
                    }
                }

            }

            events.sort({it.startDate})



            return [success: true, events: events, totalCount: events.size(), status: 200]
        } catch (Exception e) {
            e.printStackTrace()
            return [status: 400, error: e.toString()]
        }

    }

    @Transactional
    def editEvent(int id, String eventName, String startDate, String endDate, String color, String description, int alert, String repeat) {
        try {
            def user = springSecurityService.currentUser as User
            def event = CalendarEvent.findByUserAndId(user, id)

            if (!event) {
                return [success: false, message: "Can't find the event"]
            }

            Date start = startDate ? Date.parse("yyyy/MM/dd HH:mm:ss", startDate) : null
            Date end = endDate ? Date.parse("yyyy/MM/dd HH:mm:ss", endDate) : null


            if (eventName) event.eventName = eventName
            if (start) {
                DateTime startDateTime = new DateTime(start)
                startDateTime.plusMinutes(event.timezoneOffset)
                event.startDate = startDateTime
            }
            if (end) {
                DateTime endDateTime = new DateTime(end)
                endDateTime.plusMinutes(event.timezoneOffset)
                event.endDate = endDateTime
            }
            if (color) event.color = color
            if (description) event.description = description
            if (alert > 0) event.alert = alert

            event.save(failOnError: true, flush: true)

            return [success: true, status: 200]
        } catch (Exception e) {
            return [status: 400, error: e.toString()]
        }


    }

    @Transactional
    def deleteEvent(long id) {
        def user = springSecurityService.currentUser as User
        def event = CalendarEvent.executeUpdate("delete from CalendarEvent where user = ? and id = ?", [user, id])

        if (!event) {
            return [success: false, message: "Can't find the event"]
        }

        return [success: true]

    }


    @Transactional
    def addTodoList(int eventId, String todoList) {
        try {
            def user = springSecurityService.currentUser as User
            def event = CalendarEvent.get(eventId)

            if (!event && event.user != user) {
                return [success: false, message: "can't find event"]
            }

            def todos = todoList.split("\\|")
            println todos

            todos.each() {
                event.addToTodoList(new TodoList(text: it).save(failOnError: true))
            }

            event.save(failOnError: true)

            return [success: true, event: event, todo: event.todoList, status: 200]
        } catch (Exception e) {
            return [status: 400, error: e.toString()]
        }

    }

    @Transactional
    def addEventWithTodoList(int id
                             , String eventName
                             , String startDate
                             , String endDate
                             , String color
                             , String description
                             , int alert
                             , String city
                             , String state
                             , String todoList) {

        def user = springSecurityService.currentUser as User
        def event = CalendarEvent.findByUserAndId(user, id)

        if (!event) {
            return [success: false, message: "Can't find the event"]
        }

        Date start = startDate ? Date.parse("yyyy/MM/dd HH:mm:ss", startDate) : null
        Date end = endDate ? Date.parse("yyyy/MM/dd HH:mm:ss", endDate) : null

        if (eventName) event.eventName = eventName
        if (start) event.startDate = start
        if (end) event.endDate = end
        if (color) event.color = color
        if (description) event.description = description
        if (alert > 0) event.alert = alert
        if (city) event.city = city
        if (state) event.state = state

        if (todoList) {
            def todos = todoList.split("\\|")
            def todoHistory = event.todoList
            if (todoHistory.size() > 0) {
                todoHistory.toList().each() {
                    event.removeFromTodoList(it)
                }
            }

            event.save(failOnError: true)

            todos.each() {
                event.addToTodoList(new TodoList(text: it).save(failOnError: true))
            }
        }

        event.save(failOnError: true, flush: true)

        return [success: true, event: event]

    }

    @Transactional
    def todoDone(int todoId) {
        def user = springSecurityService.currentUser as User

        def todo = TodoList.findById(todoId)

        if (!todo) {
            return [success: false, message: "can't find todo"]
        }

        todo.status = TodoStatus.DONE
        todo.save()

        return [success: true]
    }

    @Transactional
    def deleteTodo(int todoId, int eventId) {
        def user = springSecurityService.currentUser as User

        def event = CalendarEvent.get(eventId)
        def todo = event.removeFromTodoList(TodoList.findById(todoId))
        event.save();

        return [success: true, status: 200]
    }

    def getEvent(int eventId) {
        def user = springSecurityService.currentUser as User
        def event = CalendarEvent.findById(eventId)

        if (!event) {
            return [success: false, message: "The event is not exist."]
        }

        return [success: true, event: event, status: 200]
    }
}
