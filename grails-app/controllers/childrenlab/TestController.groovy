package childrenlab

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN'])
class TestController {
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
}
