package childrenlab



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ActivityRawController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ActivityRaw.list(params), model:[activityRawInstanceCount: ActivityRaw.count()]
    }

    def show(ActivityRaw activityRawInstance) {
        respond activityRawInstance
    }

    def create() {
        respond new ActivityRaw(params)
    }

    @Transactional
    def save(ActivityRaw activityRawInstance) {
        if (activityRawInstance == null) {
            notFound()
            return
        }

        if (activityRawInstance.hasErrors()) {
            respond activityRawInstance.errors, view:'create'
            return
        }

        activityRawInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'activityRaw.label', default: 'ActivityRaw'), activityRawInstance.id])
                redirect activityRawInstance
            }
            '*' { respond activityRawInstance, [status: CREATED] }
        }
    }

    def edit(ActivityRaw activityRawInstance) {
        respond activityRawInstance
    }

    @Transactional
    def update(ActivityRaw activityRawInstance) {
        if (activityRawInstance == null) {
            notFound()
            return
        }

        if (activityRawInstance.hasErrors()) {
            respond activityRawInstance.errors, view:'edit'
            return
        }

        activityRawInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ActivityRaw.label', default: 'ActivityRaw'), activityRawInstance.id])
                redirect activityRawInstance
            }
            '*'{ respond activityRawInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(ActivityRaw activityRawInstance) {

        if (activityRawInstance == null) {
            notFound()
            return
        }

        activityRawInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ActivityRaw.label', default: 'ActivityRaw'), activityRawInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'activityRaw.label', default: 'ActivityRaw'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
