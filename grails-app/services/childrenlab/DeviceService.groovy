package childrenlab

import grails.transaction.Transactional

@Transactional
class DeviceService {

    def springSecurityService

    def uploadData(String x, String y, String z, String u, String v, String macId){


        User user = springSecurityService.getCurrentUser() as User

        if(!macId){
            macId = 'test'
        }


        def device = Device.findByMacId(macId) ?: new Device(macId: macId, user: user).save(failOnError: true)

        new DeviceActivity(activityX: x, activityY: y, activityZ: z, u: u, v: v, device: device).save(failOnError: true)

        return true
    }

    // Raw Data Example: |MacID,X,Y,Z,U,V,TIME|
    def uploadRawData(String rawData){

        User user = springSecurityService.getCurrentUser() as User

        def eachRaw = rawData.split("\\|");
        def eachData = []

        eachRaw.each(){
            if(it){
                eachData.add(it.split(","))
            }
        }

        // [0] -> MacId, [1] -> X, [2] -> Y, [3] -> Z, [4] -> U, [5] -> V, [6] -> Time
        eachData.each(){
            if(it.size() < 6){
                log.error("The data should be equals to 6. ${it}")
                return
            }
            String macId = it[0], x = it[1], y = it[2], z = it[3], u = it[4], v = it[5]
            long time = 0
            if(it[6]){
                time = Long.parseLong(it[6].toString())
            }


            if(!macId){
                macId = 'test'
            }


            def device = Device.findByMacId(macId) ?: new Device(macId: macId, user: user).save(failOnError: true)
            new DeviceActivity(activityX: x, activityY: y, activityZ: z, u: u, v: v, receivedTime: time, device: device).save(failOnError: true)
        }



        return true
    }


}
