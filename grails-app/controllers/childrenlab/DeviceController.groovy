package childrenlab

class DeviceController {
    def deviceService

    String uploadData(String activityX, String activityY, String activityZ, String light, String sound, String uv, String macId, String email){
        if(!macId){
            render false
        }
        def result = deviceService.uploadData(activityX, activityY, activityZ, light, sound, uv, macId, email)

        render result
    }
}
