package childrenlab

import grails.transaction.Transactional

@Transactional
class ScheduleService {

    def toolsService
    def springSecurityService

    def create(String startDate, String endDate, String status, String type, int paymentPerHour, String note){
        try{
            User user = springSecurityService.getCurrentUser() as User

            Date start = toolsService.stringToDate(startDate)
            Date end = toolsService.stringToDate(endDate)

            if(start.compareTo(end) > 0 || start.compareTo(end) == 0){
                return [success: false, message: "Start date must before end date."]
            }

            if(paymentPerHour <= 0){
                return [success: false, message: "The payment per hour shouldn't be less than 1."]
            }

            if(checkHasScheduled(user, start, end)){
                return [success: false, message: "The user already has schedule between the dates"]
            }

            ScheduleStatus scheduleStatus = status ? status as ScheduleStatus : ScheduleStatus.PRIVATE
            ScheduleType scheduleType = type ? type as ScheduleType : ScheduleType.PARENT

            def saved = new Schedule(startDate: start, endDate: end, status: scheduleStatus, type: scheduleType, paymentPerHour: paymentPerHour, note: note, user: user).save(failOnError: true)

            return [success: true]

        }catch(Exception e){
            e.printStackTrace()
            return [success: false, message: "something wrong with server, please try again later"]
        }
    }

    def updateStatus(int scheduleId, String status){
        try{
            User user = springSecurityService.getCurrentUser() as User
            def schedule = Schedule.findByUserAndId(user, scheduleId)

            if(!schedule) { return [success: false, message: "Can't find schedule."]}

            def scheduleStatus = status ? status as ScheduleStatus : schedule.status

            schedule.status = scheduleStatus
            schedule.save(failOnError: true)

            return [success: true]
            
        }catch(Exception e){
            e.printStackTrace()
            return [success: false, message: "Error: $e.message"]
        }
    }

    def checkHasScheduled(def user, Date startDate, Date endDate){
        def schedule = Schedule.executeQuery("from Schedule where user = :user and ((startDate between :startDate and :endDate) or (endDate between :startDate and :endDate))", [user: user, startDate: startDate, endDate: endDate])
        println (schedule && schedule.first())
        return (schedule && schedule.first())
    }
}
