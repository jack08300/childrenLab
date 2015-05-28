package childrenlab

import grails.transaction.Transactional

@Transactional
class DeviceService {

    def springSecurityService

    def uploadData(String activityX, String activityY, String activityZ, String light, String audio, String uv, String macId){


        User user = springSecurityService.getCurrentUser() as User

        def device = Device.findByMacId(macId) ?: new Device(macId: macId, user: user).save(failOnError: true)

        new DeviceActivity(activityX: activityX, activityY: activityY, activityZ: activityZ, light: light, audio: audio, uv: uv, device: device).save(failOnError: true)

        return true
    }
}
