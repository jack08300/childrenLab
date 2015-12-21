package childrenlab

import grails.transaction.Transactional

@Transactional
class CalendarEventService {

    def springSecurityService

    def addEvent(String eventName, String startDate, String endDate, String color, String status, description){
        if(!eventName || !startDate || !endDate){
            return [success: false, message: "One of parameter doesn't fill"]
        }

        Date start = Date.parse("yyyy/MM/dd HH:mm:ss", startDate)
        Date end = Date.parse("yyyy/MM/dd HH:mm:ss", endDate)

        def newEvent = new CalendarEvent(eventName: eventName, startDate: start, endDate: end, color: color, status: status ? status as EventStatus : EventStatus.Open, description: description, user: springSecurityService.currentUser as User).save(failOnError: true)

        if(!newEvent){
            return [success: false, message: "Something wrong when save the event"]
        }

        return [success: true]

    }

    def getEventsByUser(def params){
        def user = springSecurityService.currentUser as User

        def events

        if(params.query == "month"){
            events = CalendarEvent.findAll("from CalendarEvent where user = ? and Month(startDate) = ? and Year(startDate) = ?", [user, params.month.toInteger(), params.year.toInteger()])
        }else if(params.query == "day"){
            events = CalendarEvent.findAll("from CalendarEvent where user = ? and Month(startDate) = ? and Year(startDate) and Day(startDate) = ?", [user, params.month, params.year, params.day])
        }else{
            events = CalendarEvent.findAll("from CalendarEvent where user = ?", [user])
        }

        return events
    }

    @Transactional
    def editEvent(int id, String eventName, String startDate, String endDate, String color, String description){
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
}
