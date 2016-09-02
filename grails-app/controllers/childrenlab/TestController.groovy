package childrenlab

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import org.joda.time.DateMidnight
import org.joda.time.DateTime
import org.joda.time.DateTimeZone

@Secured(['ROLE_ADMIN'])
class TestController {
    DeviceService deviceService

    def token(){
        render([success: true] as JSON)
    }

    def cleanDatabase(){
        def vip = ["spenceractivitytest@kd.com", "jabad@imaginarium.es"]

        def users = [];

        vip.each() {
            users << User.findByEmail(it)
        }

        def c = Device.createCriteria();
        def device = c.list {
            not {'in' ("user", users)}
        }

        def activity = Activity.findAllByDeviceInList(device)
        Activity.deleteAll(activity)
//        activity.each() {
//            Activity.del
//        }

        println device
        println ""
        println activity
    }

    def createYearToDateTestData(String macId, String email){
        println "-------------------- Creating Year To Date Data"
        def thisMonth = new DateTime().monthOfYear
        println thisMonth
        def dateTime = new DateTime().minusMonths(thisMonth).withZone(DateTimeZone.UTC)
        dateTime = dateTime.toDateMidnight()

        1.upto(thisMonth) {
            println "+++++++++++++++++++++++++++++++++++++"
            dateTime = dateTime.plusMonths(1);
            createMonthData(macId, email, dateTime)
            println "+++++++++++++++++++++++++++++++++++++"
        }
        println "-------------------- Finished"
    }

    private void createMonthData(String macId, String email, DateMidnight dateTime) {
        def dayDataCount = getRandomNumber(30)
        println "-------------------- Creating $dayDataCount rows for test"
        dateTime = dateTime.minusDays(dateTime.dayOfMonth - 1)

        1.upto(dayDataCount) {
            println "+++++++++++++++++++++++++++++++++++++"
            dateTime = dateTime.plusDays(1);
            createRow(macId, email, dateTime)
            println "+++++++++++++++++++++++++++++++++++++"
        }
        println "-------------------- Finished"
    }

    private void createRow(String macId, String email, DateMidnight dateTime){

        println "Date: ${dateTime.toString("YYYY-MM-dd")}"

        int indoorSteps = getRandomNumber(1500)
        int outdoorSteps = getRandomNumber(10000)

        println "Indoor: $indoorSteps, Outdoor: $outdoorSteps"
        def indoorTime = String.valueOf(dateTime.getMillis())

        String indoorData = "${indoorTime.substring(0, indoorTime.length() - 3)},0,$indoorSteps,2,3,4"
        String outdoorData = "${indoorTime.substring(0, indoorTime.length() - 3)},0,$outdoorSteps,2,3,4"

        println "IndoorData: $indoorData"
        println "OutdoorData: $outdoorData"

        deviceService.uploadRawData(indoorData, outdoorData, dateTime.getMillisOfSecond(), macId, 0, email)
    }

    public static int getRandomNumber(int maxNumber){
        return Math.abs(new Random().nextInt() % maxNumber) + 1

    }

    def cleanTestData(String email, String macId){
        User user = User.findByEmail(email)

        def device = Device.findByUserAndMacId(user, macId)

        Activity.executeUpdate("DELETE FROM Activity where device = ?", [device])
        ActivityRaw.executeUpdate("DELETE FROM ActivityRaw where device = ?", [device])
        Device.executeUpdate("DELETE FROM Device where id = ?", [device.id])


    }
}
