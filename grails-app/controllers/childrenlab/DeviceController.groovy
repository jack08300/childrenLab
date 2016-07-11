package childrenlab

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
class DeviceController {
    def deviceService

    def uploadData(String x, String y, String z, String u, String v, String macId){
        def result = deviceService.uploadData(x, y, z, u, v, macId)

        render result
    }

    def uploadRawData(String activityRawData){
        def result = deviceService.uploadRawData(activityRawData)

        render result
    }

    def uploadResultData(String macId, int activity, int calories, int distance, long receivedTime){
        def result = deviceService.uploadResultData(macId, activity, calories, distance, receivedTime)

        render result
    }

    def getWeeklyActivity(String macId){
        def result = deviceService.getWeeklyActivity(macId)

        render result
    }

    @Secured(['ROLE_ADMIN'])
    def swingData(){

    }

    @Secured(['ROLE_ADMIN'])
    def list(){
        def device = Device.list()

        render(view: "list", model: [deviceList: device, deviceInstanceCount: Device.count()])
    }

    @Secured(['ROLE_ADMIN'])
    def deviceDataList(String macId, int max){
        def device = Device.findByMacId(macId)

        params.max = Math.min(max ?: 50, 100)
        def c = DeviceActivity.createCriteria()
        def data = c.list(params) {
            eq('device', device)
        }

        render(view: "dataList", model: [data: data, deviceActivityInstanceCount: DeviceActivity.countByDevice(device), macId: macId])
    }

    def export(String macId){
        def device = Device.findByMacId(macId)
        def data = DeviceActivity.findAllByDevice(device)

        String csvFile = "Activity X, Activity Y, Activity Z, U, V, Date\n"
        data.each(){
            def date = new Date(it.receivedTime*1000).format("YYYY/MM/dd HH:mm:ss")
            csvFile += "${it.activityX}, ${it.activityY}, ${it.activityZ}, ${it.u}, ${it.v}, ${date}\n"
        }

        response.setHeader("content-disposition", "attachment; filename=Activity_${macId}.csv")
        render(contentType: "text/csv", text: csvFile)
    }

    @Secured(['ROLE_ADMIN'])
    @Transactional
    def deleteByDevice(String macId){
        if(!macId){
            render 'Wrong'
            return;
        }

        def device = Device.findByMacId(macId)
        def delete = DeviceActivity.executeUpdate("delete from DeviceActivity where device = ?", [device])

        redirect([action: 'list'])

    }
}
