package childrenlab

import grails.transaction.Transactional

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
}
