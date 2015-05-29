package childrenlab

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
class ConnectController {

    def springSecurityService

    def device(String model, String cordova, String platform, String phoneUuid, String version){
        if(!uuid){
            log.error("UUID is missing. $model, $cordova, $platform, $phoneUuid, $version")
            render([success: false, message: "uuid is missing"] as JSON)
            return
        }

        def connect = Connect.findByPhoneUuid(phoneUuid) ?: new Connect(phoneUuid: phoneUuid)

        User user = springSecurityService.getCurrentUser() as User

        connect.deviceModel = model
        connect.cordova = cordova
        connect.devicePlatform = platform
        connect.deviceVersion = version
        connect.user = user
        connect.save(failOnError: true)

        render([success: true] as JSON)
    }
}
