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

    def list(){
        def list = Schedule.list()

        def result = [success: true, list: list]

        render result as JSON
    }

    def retrieveUserSchedule(int scheduleId, int offset, int max){
        max = max ?: 30
        def result = scheduleService.retrieveUserSchedule(scheduleId, offset, max)

        render result as JSON
    }

    def edit(int scheduleId, String startDate, String endDate, int paymentPerHour, String note){
        def result = scheduleService.editSchedule(scheduleId, startDate, endDate, paymentPerHour, note)

        render result as JSON
    }

    def leaveMessage(String message, int scheduleId){
        def result = scheduleService.leaveMessage(scheduleId, message)

        render result as JSON
    }

    def search(String startDate, String endDate, int startPrice, int endPrice, String zipcode, String gender, int offset, int max){
        max = max ?: 30
        def result = scheduleService.search(startDate, endDate, startPrice, endPrice, zipcode, gender, offset, max)
        //JSON.use('deep')
        render result as JSON
    }
}
