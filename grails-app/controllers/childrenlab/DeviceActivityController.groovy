package childrenlab

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN', 'ROLE_TESTER'])
class DeviceActivityController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        max=1000
        params.max = Math.min(max ?: 10, 100)
        respond DeviceActivity.list(params), model:[deviceActivityInstanceCount: DeviceActivity.count()]
    }

    def show(DeviceActivity deviceActivityInstance) {
        respond deviceActivityInstance
    }

    def create() {
        respond new DeviceActivity(params)
    }

    @Transactional
    def save(DeviceActivity deviceActivityInstance) {
        if (deviceActivityInstance == null) {
            notFound()
            return
        }

        if (deviceActivityInstance.hasErrors()) {
            respond deviceActivityInstance.errors, view:'create'
            return
        }

        deviceActivityInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'deviceActivity.label', default: 'DeviceActivity'), deviceActivityInstance.id])
                redirect deviceActivityInstance
            }
            '*' { respond deviceActivityInstance, [status: CREATED] }
        }
    }

    def edit(DeviceActivity deviceActivityInstance) {
        respond deviceActivityInstance
    }

    @Transactional
    def update(DeviceActivity deviceActivityInstance) {
        if (deviceActivityInstance == null) {
            notFound()
            return
        }

        if (deviceActivityInstance.hasErrors()) {
            respond deviceActivityInstance.errors, view:'edit'
            return
        }

        deviceActivityInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'DeviceActivity.label', default: 'DeviceActivity'), deviceActivityInstance.id])
                redirect deviceActivityInstance
            }
            '*'{ respond deviceActivityInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(DeviceActivity deviceActivityInstance) {

        if (deviceActivityInstance == null) {
            notFound()
            return
        }

        deviceActivityInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'DeviceActivity.label', default: 'DeviceActivity'), deviceActivityInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'deviceActivity.label', default: 'DeviceActivity'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
