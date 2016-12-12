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
    def addEvent(String eventName, String startDate, String endDate, String color, String status, String description, int alert, String city, String state, int timezoneOffset, String repeat){
        def result = calendarEventService.addEvent(eventName, startDate, endDate, color, status, description, alert, city, state, timezoneOffset, repeat, params.repeatEventId)

        render(status: result.status, text: result as JSON, contentType: "application/json")

    }

    @Transactional
    @Secured(['ROLE_USER'])
    def addEventUnderDevice(String eventName, String startDate, String endDate, String color, String status, String description, int alert, String city, String state, int timezoneOffset, String repeat, String macId){
        def result = calendarEventService.addEventUnderDevice(eventName, startDate, endDate, color, status, description, alert, city, state, timezoneOffset, repeat, params.repeatEventId, macId)

        render(status: result.status, text: result as JSON, contentType: "application/json")

    }


    @Transactional
    @Secured(['ROLE_USER'])
    def editEventWithTodo(int id
                         , String eventName
                         , String startDate
                         , String endDate
                         , String color
                         , String description
                         , int alert
                         , String city
                         , String state
                         , String todoList
    ){
        def result = calendarEventService.addEventWithTodoList(id, eventName, startDate, endDate, color, description, alert, city, state, todoList)

        render result as JSON
    }

    @Transactional
    @Secured(['ROLE_USER'])
    def addTodo(int eventId, String todoList){
        def result = calendarEventService.addTodoList(eventId, todoList)

        render result as JSON
    }

    @Transactional
    @Secured(['ROLE_USER'])
    def editEvent(int id, String eventName, String startDate, String endDate, String color, String description, int alert, String repeat){
        def result = calendarEventService.editEvent(id, eventName, startDate, endDate, color, description, alert, repeat)

        render result as JSON
    }


    @Secured(['ROLE_USER'])
    def getEventsByUser(String query, int month, int year, int day){
        def result = calendarEventService.getEventsByUser(query, month , year, day)

        render(status: result.status, text: result as JSON, contentType: "application/json")
    }

    @Secured(['ROLE_USER'])
    def getEvent(int eventId){
        def result = calendarEventService.getEvent(eventId)

        render result as JSON
    }

    @Transactional
    @Secured(['ROLE_USER'])
    def todoDone(int eventId, int todoId){
        def result = calendarEventService.todoDone(todoId)

        render result as JSON
    }

    @Transactional
    @Secured(['ROLE_USER'])
    def deleteTodo(int todoId, int eventId){
        def result = calendarEventService.deleteTodo(todoId, eventId)

        render result as JSON
    }


    @Transactional
    @Secured(['ROLE_USER'])
    def deleteEvent(int id){
        def result = calendarEventService.deleteEvent(id)

        render result as JSON
    }

    @Secured(['ROLE_USER'])
    def getCities(String country){
        switch(country) {
            case 'spain':
                render spain as JSON
                break;
        }

    }


    def spain = [
            [
                city: "Álava",
                utf: "%C3%81lava"
            ],
            [
                city: "Ávila",
                utf: "%C3%81vila"
            ],
            [
                city: "Albacete",
                utf: "Albacete"
            ],
            [
                city: "Alicante",
                utf:"Alicante"
            ],
            [
                city: "Almería",
                utf:"Almer%C3%ADa"
            ],
            [
                city: "Asturias",
                utf:"Asturias"
            ],
            [
                city: "Badajoz",
                utf:"Badajoz"
            ],
            [
                city: "Baleares",
                utf:"Baleares"
            ],
            [
                city: "Barcelona",
                utf:"Barcelona"
            ],
            [
                city: "Bilbao",
                utf:"Bilbao"
            ],
            [
                city: "Burgos",
                utf:"Burgos"
            ],
            [
                city: "Cantabria",
                utf:"Cantabria"
            ],
            [
                city: "Castellón",
                utf:"Castell%C3%B3n"
            ],
            [
                city: "Ceuta",
                utf:"Ceuta"
            ],
            [
                city: "Ciudad Real",
                utf:"Ciudad%20Real"
            ],
            [
                city: "Coruña",
                utf:"Coru%C3%B1a"
            ],
            [
                city: "Cuenca",
                utf:"Cuenca"
            ],
            [
                city: "Cáceres",
                utf:"C%C3%A1ceres"
            ],
            [
                city: "Cádiz",
                utf:"C%C3%A1diz"
            ],
            [
                city: "Córdoba",
                utf:"C%C3%B3rdoba"
            ],
            [
                city: "Donostia",
                utf:"Donostia"
            ],
            [
                city: "Gerona",
                utf:"Gerona"
            ],
            [
                city: "Granada",
                utf:"Granada"
            ],
            [
                city: "Huelva",
                utf:"Huelva"
            ],
            [
                city: "Huesca",
                utf:"Huesca"
            ],
            [
                city: "Jaén",
                utf:"Ja%C3%A9n"
            ],
            [
                city: "Las Palmas",
                utf:"Las%20Palmas"

            ],
            [
                city: "León",
                utf:"Le%C3%B3n"
            ],
            [
                city: "Lleida",
                utf:"Lleida"
            ],
            [
                city: "Lugo",
                utf:"Lugo"
            ],
            [
                city: "Madrid",
                utf:"Madrid"
            ],
            [
                city: "Melilla",
                utf:"Melilla"
            ],
            [
                city: "Murcia",
                utf:"Murcia"
            ],
            [
                city: "Málaga",
                utf:"M%C3%A1laga"
            ],
            [
                city: "Navarra",
                utf:"Navarra"
            ],
            [
                city: "Orense",
                utf:"Orense"
            ],
            [
                city: "Palencia",
                utf:"Palencia"
            ],
            [
                city: "Pontevedra",
                utf:"Pontevedra"
            ],
            [
                city: "Rioja",
                utf:"Rioja"
            ],
            [
                city: "Salamanca",
                utf:"Salamanca"
            ],
            [
                city: "Segovia",
                utf:"Segovia"
            ],
            [
                city: "Sevilla",
                utf:"Sevilla"
            ],
            [
                city: "Soria",
                utf:"Soria"
            ],
            [
                city: "Tarragona",
                utf:"Tarragona"
            ],
            [
                city: "Tenerife",
                utf:"Tenerife"
            ],
            [
                city: "Teruel",
                utf:"Teruel"
            ],
            [
                city: "Toledo",
                utf:"Toledo"
            ],
            [
                city: "Valencia",
                utf:"Valencia"
            ],
            [
                city: "Valladolid",
                utf:"Valladolid"
            ],
            [
                city: "Zamora",
                utf:"Zamora"
            ],
            [
                city: "Zaragoza",
                utf:"Zaragoza"
            ]
    ]

}
