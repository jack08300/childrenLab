package childrenlab

import grails.converters.JSON

class ConnectController {

    def device(String model, String cordova, String platform, String uuid, String version){
        if(!uuid){
            log.error("UUID is missing. $model, $cordova, $platform, $uuid, $version")
            render([success: false, message: "uuid is missing"] as JSON)
        }

        def connect = Connect.findByUuid(uuid) ?: new Connect(uuid: uuid)

        connect.deviceModel = model
        connect.cordova = cordova
        connect.devicePlatform = platform
        connect.deviceVersion = version
        connect.save(failOnError: true)

        render([success: true] as JSON)
    }
}
