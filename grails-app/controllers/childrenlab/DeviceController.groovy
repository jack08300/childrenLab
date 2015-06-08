package childrenlab

import grails.plugin.springsecurity.annotation.Secured

@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
class DeviceController {
    def deviceService

    def uploadData(String activityX, String activityY, String activityZ, String light, String audio, String uv, String macId, String temperature){
        if(!macId){
            render false
            return
        }
        def result = deviceService.uploadData(activityX, activityY, activityZ, light, audio, uv, macId, temperature)

        render result
    }

    @Secured(['ROLE_ADMIN'])
    def list(){
        def device = Device.list();

        render(view: "list", model: [deviceList: device])
    }

    @Secured(['ROLE_ADMIN'])
    def deviceDataList(String macId){
        def device = Device.findByMacId(macId)
        def data = DeviceActivity.findAllByDevice(device)

        render(view: "dataList", model: [data: data])
    }


}
