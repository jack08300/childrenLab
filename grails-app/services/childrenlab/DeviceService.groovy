package childrenlab

import grails.transaction.Transactional
import org.joda.time.DateTime
import org.joda.time.DateTimeZone


@Transactional
class DeviceService {

    def springSecurityService

    def uploadData(String x, String y, String z, String u, String v, String macId){


        User user = springSecurityService.getCurrentUser() as User

        if(!macId){
            macId = 'test'
        }


        def device = Device.findByMacId(macId) ?: new Device(macId: macId, user: user).save(failOnError: true)

        new DeviceActivity(activityX: x, activityY: y, activityZ: z, u: u, v: v, device: device).save(failOnError: true)

        return true
    }

    def uploadRawData(String indoorActivity, String outdoorActivity, long time, String macId, int timezone = 0){

        User user = springSecurityService.getCurrentUser() as User

        if(!user){
            user = User.findByEmail("BleTester") ?: new User(email: "BleTester", password: "bleTester").save(failOnError: true)
        }
        def device = Device.findByMacIdAndUser(macId, user) ?: new Device(macId: macId, user: user).save(failOnError: true)

        //Insert into
        def indoorActivityArray = indoorActivity.split(",")

        def indoorTime = Long.parseLong(indoorActivityArray[0])*1000

        new ActivityRaw(indoorActivity: indoorActivity, outdoorActivity: outdoorActivity, time: indoorTime, device: device).save(failOnError: true)


        def indoorActivityStep = Integer.parseInt(indoorActivityArray[2])

        def dateTimezone = DateTimeZone.forOffsetHours(0)
        def indoorDateTime = new DateTime(indoorTime).withZone(dateTimezone)
        def today = new DateTime()

        def outdoorActivityArray = outdoorActivity.split(",")
        def outdoorTime = Long.parseLong(outdoorActivityArray[0])*1000
        def outdoorActivityStep = Integer.parseInt(outdoorActivityArray[2])
        def outdoorDatetime = new DateTime(outdoorTime).withZone(dateTimezone)

        println "--------------------------------------------\n" +
                "indoorTime Long: $indoorTime\n" +
                "indoorDateTime: $indoorDateTime\n" +
                "indoorActivityStep: $indoorActivityStep\n" +
                "\n" +
                "outdoorTime Long: $outdoorTime\n" +
                "outdoorDateTime: $outdoorDatetime\n" +
                "outdoorActivityStep: $outdoorActivityStep\n" +
                "----------------------------------------------"

        def todayActivity = Activity.findAll {
            device: device
            receivedDate >= today.toDateMidnight().toDate() && receivedDate <= today.plusDays(1).toDateMidnight().toDate()
        }

        if(todayActivity.size() > 0) {
            todayActivity.each() {
                println it.type
                if(it.type == ActivityType.INDOOR) {
                    it.steps += indoorActivityStep
                }else {
                    println outdoorActivityStep
                    it.steps += outdoorActivityStep
                }
            }
        }else{
            new Activity(steps: indoorActivityStep, receivedTime: indoorTime, type: ActivityType.INDOOR, device: device, receivedDate: indoorDateTime.toDate()).save(failOnError: true)
            new Activity(steps: outdoorActivityStep, receivedTime: outdoorTime, type: ActivityType.OUTDOOR, device: device, receivedDate: outdoorDatetime.toDate()).save(failOnError: true)
        }



        return [success: true]
    }

    def getYearlyActivity(String macId) {
        User user = springSecurityService.getCurrentUser() as User
        def device = Device.findByUserAndMacId(user, macId)

        def today = new DateTime()
        def begin = today.minusDays(today.getDayOfYear()-1)
        println begin


        def yearlyActivity = Activity.findAll {
            device: device
            receivedDate >= today.toDateMidnight().toDate() && receivedDate <= today.plusDays(1).toDateMidnight().toDate()
        }

        return [success: true, activity: yearlyActivity, query: "yearly"]
    }

    def getMonthlyActivity(String macId) {
        User user = springSecurityService.getCurrentUser() as User
        def device = Device.findByUserAndMacId(user, macId)

        def today = new DateTime()
        def begin = today.minusDays(today.getDayOfMonth()-1)
        println begin
        def end = today.dayOfWeek().dateTime

        def todayActivity = Activity.findAll {
            device: device
            receivedDate >= today.toDateMidnight().toDate() && receivedDate <= today.plusDays(1).toDateMidnight().toDate()
        }

        println todayActivity
        return [success: true, activity: todayActivity, query: "monthly"]
    }

    def getWeeklyActivity(String macId){
        def device = Device.findByMacId(macId)

        def today = new DateTime()
        def begin = today.minusDays(today.getDayOfWeek()-1)
        def end = today.dayOfWeek().dateTime


        def weeklyActivity = Activity.findAll {
            device: device
            receivedDate >= begin.toDateMidnight().toDate()
        }

        return [success: true, activity: weeklyActivity, query: "weekly"]
    }

    def getDailyActivity(String macId) {
        User user = springSecurityService.getCurrentUser() as User
        def device = Device.findByUserAndMacId(user, macId)

        def today = new DateTime()

        def todayActivity = Activity.findAll {
            device: device
            receivedDate >= today.toDateMidnight().toDate() && receivedDate <= today.plusDays(1).toDateMidnight().toDate()
        }

        println todayActivity
        return [success: true, activity: todayActivity, query: "daily"]
    }
}

/*
E = sqrt(x^2+y^2+z^2)
250 < E < 360
x average < 35
step = step+2 ;
 */