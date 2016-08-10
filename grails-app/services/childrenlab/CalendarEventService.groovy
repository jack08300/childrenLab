package childrenlab

import grails.converters.JSON
import grails.transaction.Transactional

@Transactional
class CalendarEventService {

    def springSecurityService

    def addEvent(String eventName, String startDate, String endDate, String color, String status, String description, int alert, String city, String state){
        if(!eventName || !startDate || !endDate){
            return [success: false, message: "One of parameter doesn't fill"]
        }

        Date start = Date.parse("yyyy/MM/dd HH:mm:ss", startDate)
        Date end = Date.parse("yyyy/MM/dd HH:mm:ss", endDate)

        def newEvent = new CalendarEvent(eventName: eventName, startDate: start, endDate: end, color: color,
                status: status ? status as EventStatus : EventStatus.Open, description: description,
                user: springSecurityService.currentUser as User, alert: alert, city: city, state: state).save(failOnError: true)

        if(!newEvent){
            return [success: false, message: "Something wrong when save the event"]
        }

        return [success: true, newEvent: newEvent]

    }

    def getEventsByUser(String query, int month, int year, int day){
        def user = springSecurityService.currentUser as User

        def events

        if(query == "month"){
            events = CalendarEvent.findAll("from CalendarEvent where user = ? and Month(startDate) = ? and Year(startDate) = ? order by startDate", [user, month, year])
        }else if(query == "day"){
            events = CalendarEvent.findAll("from CalendarEvent where user = ? and Month(startDate) = ? and Year(startDate) = ? and Day(startDate) = ?  order by startDate", [user, month, year, day])
        }else{
            events = CalendarEvent.findAll("from CalendarEvent where user = ?  order by startDate", [user])
        }

        return [success: true, events: events, totalCount: events.size()]
    }

    @Transactional
    def editEvent(int id, String eventName, String startDate, String endDate, String color, String description, int alert){
        def user = springSecurityService.currentUser as User
        def event = CalendarEvent.findByUserAndId(user, id)

        if(!event){
            return [success: false, message: "Can't find the event"]
        }

        Date start = startDate ? Date.parse("yyyy/MM/dd HH:mm:ss", startDate) : null
        Date end = endDate ? Date.parse("yyyy/MM/dd HH:mm:ss", endDate) : null

        if(eventName) event.eventName = eventName
        if(start) event.startDate = start
        if(end) event.endDate = end
        if(color) event.color = color
        if(description) event.description = description
        if(alert > 0) event.alert = alert

        event.save(failOnError: true, flush: true)

        return [success: true]
    }

    @Transactional
    def deleteEvent(long id){
        def user = springSecurityService.currentUser as User
        def event = CalendarEvent.executeUpdate("delete from CalendarEvent where user = ? and id = ?", [user, id])

        if(!event){
            return [success: false, message: "Can't find the event"]
        }

        return [success: true]

    }


    @Transactional
    def addTodoList(int eventId, String todoList) {
        def user = springSecurityService.currentUser as User
        def event = CalendarEvent.get(eventId)



//        def event = CalendarEvent.findByIdAndUser(eventId, user)
        if(!event) {
            return [success: false, message: "can't find event"]
        }

        def todos = todoList.split("\\|")
        println todos

        todos.each() {
            event.addToTodoList(new TodoList(text: it).save(failOnError: true))
        }

        event.save(failOnError: true)

        return [success: true, event: event, todo: event.todoList]
    }

    @Transactional
    def todoDone(int todoId) {
        def user = springSecurityService.currentUser as User

        def todo = TodoList.findById(todoId)

        if(!todo) {
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

        return [success: true]
    }

    def getEvent(int eventId){
        def user = springSecurityService.currentUser as User
        def event = CalendarEvent.findById(eventId)

        if(!event){
            return [success: false, message: "The event is not exist."]
        }

        return [success: true, event: event]
    }
}
