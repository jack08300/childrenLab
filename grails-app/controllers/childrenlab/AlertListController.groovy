package childrenlab

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Secured(['ROLE_ADMIN'])
@Transactional(readOnly = true)
class AlertListController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond AlertList.list(params), model:[alertListInstanceCount: AlertList.count()]
    }

    def show(AlertList alertListInstance) {
        respond alertListInstance
    }

    def create() {
        respond new AlertList(params)
    }

    @Transactional
    def save(AlertList alertListInstance) {
        if (alertListInstance == null) {
            notFound()
            return
        }

        if (alertListInstance.hasErrors()) {
            respond alertListInstance.errors, view:'create'
            return
        }

        alertListInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'alertList.label', default: 'AlertList'), alertListInstance.id])
                redirect alertListInstance
            }
            '*' { respond alertListInstance, [status: CREATED] }
        }
    }

    def edit(AlertList alertListInstance) {
        respond alertListInstance
    }

    @Transactional
    def update(AlertList alertListInstance) {
        if (alertListInstance == null) {
            notFound()
            return
        }

        if (alertListInstance.hasErrors()) {
            respond alertListInstance.errors, view:'edit'
            return
        }

        alertListInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'AlertList.label', default: 'AlertList'), alertListInstance.id])
                redirect alertListInstance
            }
            '*'{ respond alertListInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(AlertList alertListInstance) {

        if (alertListInstance == null) {
            notFound()
            return
        }

        alertListInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'AlertList.label', default: 'AlertList'), alertListInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'alertList.label', default: 'AlertList'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
