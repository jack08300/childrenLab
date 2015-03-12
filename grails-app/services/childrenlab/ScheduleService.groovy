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

            if(start.compareTo(end) > 0 || start.compareTo(end) == 0){ return [success: false, message: "Start date must before end date."] }

            if(paymentPerHour <= 0){ return [success: false, message: "The payment per hour shouldn't be less than 1."] }

            if(checkHasScheduled(user, start, end)){ return [success: false, message: "The user already has schedule between the dates"] }

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

    def checkHasScheduled(def user, Date startDate, Date endDate, Schedule exceptSchedule = null){
        def schedule
        if(exceptSchedule){
            schedule = Schedule.executeQuery("from Schedule where user = :user and ((startDate between :startDate and :endDate) or (endDate between :startDate and :endDate)) and id != :id", [user: user, startDate: startDate, endDate: endDate, id: exceptSchedule.id])
        }else{
            schedule = Schedule.executeQuery("from Schedule where user = :user and ((startDate between :startDate and :endDate) or (endDate between :startDate and :endDate))", [user: user, startDate: startDate, endDate: endDate])
        }

        return (schedule && schedule.first())
    }

    def editSchedule(int scheduleId, String startDate, String endDate, int paymentPerHour, String note){
        try{
            User user = springSecurityService.getCurrentUser() as User

            Date start = toolsService.stringToDate(startDate)
            Date end = toolsService.stringToDate(endDate)

            def schedule = Schedule.findByUserAndId(user, scheduleId)
            if(!schedule){  return [success: false, message: "Can't find schedule to edit"] }

            if(start && end){
                if(checkHasScheduled(user, start, end, schedule)){ return [success: false, message: "You already has schedule between the dates"] }
                if(start.compareTo(end) > 0 || start.compareTo(end) == 0){ return [success: false, message: "Start date must before end date."] }
                schedule.startDate = start
                schedule.endDate = end
            }

            if(paymentPerHour != 0){
                if(paymentPerHour <= 0){ return [success: false, message: "The payment per hour shouldn't be less than 1."] }
                schedule.paymentPerHour = paymentPerHour
                schedule.discard()
            }
            schedule.note = note ?: schedule.note

            schedule.save(failOnError: true)

            return [success: true]

        }catch(Exception e){
            e.printStackTrace()
            return [success: false, message: "something wrong with server, please try again later"]
        }
    }

    def retrieveUserSchedule(int scheduleId){
        try {
            User user = springSecurityService.getCurrentUser() as User

            def schedule
            if(scheduleId > 0){
                schedule = Schedule.findByUserAndId(user, scheduleId)
            }else{
                schedule = Schedule.findAllByUser(user)
            }

            return [success: true, schedule: schedule]
        }catch(Exception e){
            e.printStackTrace()
            return [success: false, message: "something wrong with server, please try again later"]
        }
    }
}
