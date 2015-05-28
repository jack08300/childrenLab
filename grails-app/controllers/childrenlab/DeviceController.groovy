package childrenlab

import grails.plugin.springsecurity.annotation.Secured

@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
class DeviceController {
    def deviceService

    String uploadData(String activityX, String activityY, String activityZ, String light, String audio, String uv, String macId){
        if(!macId){
            render false
        }
        def result = deviceService.uploadData(activityX, activityY, activityZ, light, audio, uv, macId)

        render result
    }
}
