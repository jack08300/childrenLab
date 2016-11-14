package childrenlab

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
@Transactional(readOnly = true)
class CalendarRepeatEventController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond CalendarRepeatEvent.list(params), model:[calendarRepeatEventInstanceCount: CalendarRepeatEvent.count()]
    }

    def show(CalendarRepeatEvent calendarRepeatEventInstance) {
        respond calendarRepeatEventInstance
    }

    def create() {
        respond new CalendarRepeatEvent(params)
    }


    @Transactional
    def save(CalendarRepeatEvent calendarRepeatEventInstance) {
        if (calendarRepeatEventInstance == null) {
            notFound()
            return
        }

        if (calendarRepeatEventInstance.hasErrors()) {
            respond calendarRepeatEventInstance.errors, view:'create'
            return
        }

        calendarRepeatEventInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'calendarRepeatEvent.label', default: 'CalendarRepeatEvent'), calendarRepeatEventInstance.id])
                redirect calendarRepeatEventInstance
            }
            '*' { respond calendarRepeatEventInstance, [status: CREATED] }
        }
    }

    def edit(CalendarRepeatEvent calendarRepeatEventInstance) {
        respond calendarRepeatEventInstance
    }

    @Transactional
    def update(CalendarRepeatEvent calendarRepeatEventInstance) {
        if (calendarRepeatEventInstance == null) {
            notFound()
            return
        }

        if (calendarRepeatEventInstance.hasErrors()) {
            respond calendarRepeatEventInstance.errors, view:'edit'
            return
        }

        calendarRepeatEventInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'CalendarRepeatEvent.label', default: 'CalendarRepeatEvent'), calendarRepeatEventInstance.id])
                redirect calendarRepeatEventInstance
            }
            '*'{ respond calendarRepeatEventInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(CalendarRepeatEvent calendarRepeatEventInstance) {

        if (calendarRepeatEventInstance == null) {
            notFound()
            return
        }

        calendarRepeatEventInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'CalendarRepeatEvent.label', default: 'CalendarRepeatEvent'), calendarRepeatEventInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'calendarRepeatEvent.label', default: 'CalendarRepeatEvent'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
