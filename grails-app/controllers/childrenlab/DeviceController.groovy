package childrenlab

import grails.converters.JSON

class DeviceController {
    def deviceService

    String uploadData(String activity, String lightData, String soundData, String uvData, String uuid, String email){
        if(!uuid){
            render([success: false, message: "Missing device UUID"] as JSON)
        }
        def result = deviceService.uploadData(activity, lightData, soundData, uvData, uuid, email)

        render result as JSON
    }
}
