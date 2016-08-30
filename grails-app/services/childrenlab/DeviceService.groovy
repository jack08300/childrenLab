package childrenlab

import grails.transaction.Transactional
import org.joda.time.DateMidnight
import org.joda.time.DateTime
import org.joda.time.DateTimeZone


@Transactional
class DeviceService {

    def springSecurityService

    def uploadData(String x, String y, String z, String u, String v, String macId) {


        User user = springSecurityService.getCurrentUser() as User

        if (!macId) {
            macId = 'test'
        }


        def device = Device.findByMacId(macId) ?: new Device(macId: macId, user: user).save(failOnError: true)

        new DeviceActivity(activityX: x, activityY: y, activityZ: z, u: u, v: v, device: device).save(failOnError: true)

        return true
    }

    def uploadRawData(String indoorActivity, String outdoorActivity, long time, String macId, int timezone = 0) {

        User user = springSecurityService.getCurrentUser() as User

        if (!user) {
            user = User.findByEmail("BleTester") ?: new User(email: "BleTester", password: "bleTester").save(failOnError: true)
        }
        def device = Device.findByMacIdAndUser(macId, user) ?: new Device(macId: macId, user: user).save(failOnError: true)

        //Insert into
        def indoorActivityArray = indoorActivity.split(",")

        def indoorTime = Long.parseLong(indoorActivityArray[0]) * 1000

        new ActivityRaw(indoorActivity: indoorActivity, outdoorActivity: outdoorActivity, time: indoorTime, device: device, deviceTime: time).save(failOnError: true)


        def indoorActivityStep = Integer.parseInt(indoorActivityArray[2])

        def dateTimezone = DateTimeZone.forOffsetHours(0)
        def indoorDateTime = new DateTime(indoorTime).withZone(dateTimezone)
        def today = new DateTime()

        def outdoorActivityArray = outdoorActivity.split(",")
        def outdoorTime = Long.parseLong(outdoorActivityArray[0]) * 1000
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

        def todayActivity = Activity.findAllByDeviceAndReceivedDateBetween(device, indoorDateTime.toDateMidnight().toDate(), indoorDateTime.plusDays(1).toDateMidnight().toDate())

/*
        def todayActivity = Activity.findAll {
            device: device
            user: user
            receivedDate >= indoorDateTime.toDateMidnight().toDate() && receivedDate <= indoorDateTime.plusDays(1).toDateMidnight().toDate()
        }
*/

        println "TodayActivity: $todayActivity, Size: ${todayActivity.size()}"

        if (todayActivity.size() > 0) {
            todayActivity.each() {
                if (it.type == ActivityType.INDOOR) {
                    it.steps += indoorActivityStep
                } else {
                    it.steps += outdoorActivityStep
                }
            }
        } else {
            new Activity(steps: indoorActivityStep, receivedTime: indoorTime, type: ActivityType.INDOOR, device: device, receivedDate: indoorDateTime.toDate()).save(failOnError: true)
            new Activity(steps: outdoorActivityStep, receivedTime: outdoorTime, type: ActivityType.OUTDOOR, device: device, receivedDate: outdoorDatetime.toDate()).save(failOnError: true)
        }



        return [success: true]
    }

    def getYearlyActivity(String macId) {
        User user = springSecurityService.getCurrentUser() as User
        def device = Device.findByUserAndMacId(user, macId)

        def today = new DateTime()
        def begin = today.minusMonths(11).toDateMidnight()

        def todayActivity = Activity.executeQuery("SELECT SUM(a.steps) as Step, DATE_FORMAT(a.receivedDate, '%Y-%m') as Date, a.type as Type" +
                " from Activity a WHERE a.device = ? and a.device.user = ? and a.receivedDate > ? group by Year(receivedDate), Month(receivedDate), a.type order by receivedDate",
                [device, user, begin.toDate()])

        def activity = []
        todayActivity.each() {
            def map = [:]
            map.steps = it[0];
            map.date = it[1];
            map.type = (it[2] as ActivityType).name();
            activity.push(map);
        }

        def yearlyActivity = []
        for (int i = 0; i < 12; i++) {
            def map = [:]
            def day = begin.plusMonths(i)
            boolean found = false;
            activity.each() {
                if (isSameMonth(it.date, day)) {
                    yearlyActivity << it;
                    found = true
                }
            }

            if (!found) {
                yearlyActivity << generateEmptyActivity(day, "INDOOR", "YYYY-MM")
                yearlyActivity << generateEmptyActivity(day, "OUTDOOR", "YYYY-MM")
            }

        }

        println("Yearly Activity. Begin: ${begin.toString("YYYY-MM-DD")}")

        return [success: true, activity: yearlyActivity, query: "yearly"]
    }

    def getMonthlyActivity(String macId) {
        User user = springSecurityService.getCurrentUser() as User
        def device = Device.findByUserAndMacId(user, macId8)

        def today = new DateTime()
        def begin = today.minusDays(30).toDateMidnight()
        def end = today.plusDays(1).toDateMidnight()

        def todayActivity = Activity.executeQuery("SELECT SUM(a.steps) as Step, DATE_FORMAT(a.receivedDate, '%Y-%m-%d') as Date," +
                " a.type as Type from Activity a WHERE a.device = ? and a.device.user = ? and (a.receivedDate > ? and a.receivedDate < ?) group by  a.type, a.receivedDate order by receivedDate",
                [device, user, begin.toDate(), end.toDate()])

        def activity = []
        todayActivity.each() {
            def map = [:]
            map.steps = it[0];
            map.date = it[1];
            map.type = (it[2] as ActivityType).name();

            activity.push(map);
        }

        def monthlyActivity = []
        for (int i = 0; i < 30; i++) {
            def map = [:]
            def day = begin.plusDays(i)
            boolean found = false;
            activity.each() {
                if (isSameDay(it.date, day)) {
                    monthlyActivity << it;
                    found = true
                }
            }

            if (!found) {
                monthlyActivity << generateEmptyActivity(day, "INDOOR", "YYYY-MM-dd")
                monthlyActivity << generateEmptyActivity(day, "OUTDOOR", "YYYY-MM-dd")
            }

        }

        println("Monthly Activity. Begin: ${begin.toString("YYYY-MM-dd")} - End: ${end.toString("YYYY-MM-dd")} ")

        println todayActivity
        return [success: true, activity: monthlyActivity, query: "monthly"]
    }

    def getWeeklyActivity(String macId) {
        User user = springSecurityService.getCurrentUser() as User
        def device = Device.findByUserAndMacId(user, macId)

        def today = new DateTime()
        def begin = today.minusDays(7).toDateMidnight()
        def end = today.plusDays(1).toDateMidnight()

        def weeklyActivityFromData = Activity.executeQuery("SELECT SUM(a.steps) as Step, DATE_FORMAT(a.receivedDate, '%Y-%m-%d') " +
                "as Date, a.type as Type from Activity a WHERE a.device = ? and a.device.user = ? and (a.receivedDate > ? and a.receivedDate < ?) group by a.type, a.receivedDate order by receivedDate",
                [device, user, begin.toDate(), end.toDate()])

        def activity = []
        weeklyActivityFromData.each() {
            def map = [:]
            map.steps = it[0];
            map.date = it[1];
            map.type = (it[2] as ActivityType).name();

            activity.push(map);
        }

        def weeklyActivity = []
        for (int i = 0; i < 7; i++) {
            def map = [:]
            def day = begin.plusDays(i)
            boolean found = false;
            activity.each() {
                if (isSameDay(it.date, day)) {
                    weeklyActivity << it;
                    found = true
                }
            }

            if (!found) {
                weeklyActivity << generateEmptyActivity(day, "INDOOR", "YYYY-MM-dd")
                weeklyActivity << generateEmptyActivity(day, "OUTDOOR", "YYYY-MM-dd")
            }

        }

        println("Weekly Activity. Begin: ${begin.toString("YYYY-MM-dd")} - End: ${end.toString("YYYY-MM-dd")}")

        return [success: true, activity: weeklyActivity, query: "weekly"]
    }

    def getDailyActivity(String macId) {
        User user = springSecurityService.getCurrentUser() as User
        def device = Device.findByUserAndMacId(user, macId)

        def today = new DateTime()
        def begin = today.toDateMidnight()
        def end = today.plusDays(1).toDateMidnight()


        def todayActivity = Activity.executeQuery("SELECT SUM(a.steps) as Step, DATE_FORMAT(receivedDate, '%Y-%m-%d') as Date, a.type as Type from Activity a WHERE device = ? and device.user = ? and receivedDate > ? group by type, receivedDate order by receivedDate", [device, user, begin.toDate()])

        def activity = []
        todayActivity.each() {
            def map = [:]
            map.steps = it[0];
            map.date = it[1];
            map.type = (it[2] as ActivityType).name();

            activity.push(map);
        }
        println("Daily Activity. Begin: ${begin.toString("YYYY-MM-dd")} - End: ${end.toString("YYYY-MM-dd")} ")

        println activity
        return [success: true, activity: activity, query: "daily"]
    }

    public boolean isSameDay(String dateString, DateMidnight dateTime) {
        def date = new DateTime(dateString)
        return dateTime.dayOfMonth == date.dayOfMonth
    }

    public boolean isSameMonth(String dateString, DateMidnight dateTime) {
        def date = new DateTime(dateString)
        return dateTime.monthOfYear == date.monthOfYear
    }

    def generateEmptyActivity(def date, String type, String format) {
        return [
                steps: 0,
                date : date.toString(format),
                type : type
        ]
    }
}

/*
E = sqrt(x^2+y^2+z^2)
250 < E < 360
x average < 35
step = step+2 ;
 */