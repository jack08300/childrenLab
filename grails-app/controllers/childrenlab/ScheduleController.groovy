package childrenlab

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER'])
class ScheduleController {
    def scheduleService

    def create(String startDate, String endDate, String status, String type, int paymentPerHour, String note){
        if(!startDate || !endDate){ render([success: false, message: "Start Date or End Date is missing."] as JSON)}

        def result = scheduleService.create(startDate, endDate, status, type, paymentPerHour, note)

        render result as JSON
    }

    def updateStatus(int scheduleId, String status){

        def result = scheduleService.updateStatus(scheduleId, status)
        render result as JSON
    }
}
