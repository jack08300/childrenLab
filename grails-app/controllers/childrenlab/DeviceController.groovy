package childrenlab

import grails.plugin.springsecurity.annotation.Secured

@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
class DeviceController {
    def deviceService

    def uploadData(String x, String y, String z, String u, String v, String macId){
        def result = deviceService.uploadData(x, y, z, u, v, macId)

        render result
    }

    @Secured(['ROLE_ADMIN'])
    def swingData(){

    }

    @Secured(['ROLE_ADMIN'])
    def list(){
        def device = Device.list()

        render(view: "list", model: [deviceList: device])
    }

    @Secured(['ROLE_ADMIN'])
    def deviceDataList(String macId){
        def device = Device.findByMacId(macId)
        def data = DeviceActivity.findAllByDevice(device)

        render(view: "dataList", model: [data: data])
    }


}
