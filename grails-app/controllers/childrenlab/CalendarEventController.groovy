package childrenlab

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class CalendarEventController {

    def calendarEventService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond CalendarEvent.list(params), model:[calendarEventInstanceCount: CalendarEvent.count()]
    }

    def show(CalendarEvent calendarEventInstance) {
        respond calendarEventInstance
    }

    def create() {
        respond new CalendarEvent(params)
    }

    @Transactional
    def save(CalendarEvent calendarEventInstance) {
        if (calendarEventInstance == null) {
            notFound()
            return
        }

        if (calendarEventInstance.hasErrors()) {
            respond calendarEventInstance.errors, view:'create'
            return
        }

        calendarEventInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'calendarEvent.label', default: 'CalendarEvent'), calendarEventInstance.id])
                redirect calendarEventInstance
            }
            '*' { respond calendarEventInstance, [status: CREATED] }
        }
    }

    def edit(CalendarEvent calendarEventInstance) {
        respond calendarEventInstance
    }

    @Transactional
    def update(CalendarEvent calendarEventInstance) {
        if (calendarEventInstance == null) {
            notFound()
            return
        }

        if (calendarEventInstance.hasErrors()) {
            respond calendarEventInstance.errors, view:'edit'
            return
        }

        calendarEventInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'CalendarEvent.label', default: 'CalendarEvent'), calendarEventInstance.id])
                redirect calendarEventInstance
            }
            '*'{ respond calendarEventInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(CalendarEvent calendarEventInstance) {

        if (calendarEventInstance == null) {
            notFound()
            return
        }

        calendarEventInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'CalendarEvent.label', default: 'CalendarEvent'), calendarEventInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'calendarEvent.label', default: 'CalendarEvent'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    @Transactional
    @Secured(['ROLE_USER'])
    def addEvent(String eventName, String startDate, String endDate, String color, String status, String description){
        def result = calendarEventService.addEvent(eventName, startDate, endDate, color, status, description)

        render result as JSON
    }

    @Secured(['ROLE_USER'])
    def getEventsByUser(){
        def result = calendarEventService.getEventsByUser(params)

        render result as JSON
    }

    @Transactional
    @Secured(['ROLE_USER'])
    def editEvent(String eventName, String startDate, String endDate, String color, int id){
        def result = calendarEventService.editEvent(id, eventName, startDate, endDate, color)

        render result as JSON
    }

    @Transactional
    @Secured(['ROLE_USER'])
    def deleteEvent(int id){
        def result = calendarEventService.deleteEvent(id)

        render result as JSON
    }

}
