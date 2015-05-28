package childrenlab

import grails.transaction.Transactional

@Transactional
class DeviceService {

    def uploadData(String activityX, String activityY, String activityZ, String light, String sound, String uv, String macId, String email){

        User user = null
        if(email){
            user = User.findByEmail(email)
        }

        new DeviceActivity(activityX: activityX, activityY: activityY, activityZ: activityZ, light: light, sound: sound, uv: uv, macId: macId).save(failOnError: true)

        return true
    }
}
