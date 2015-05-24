package childrenlab

import grails.transaction.Transactional

@Transactional
class DeviceService {

    def uploadData(String activityData, String lightData, String soundData, String uvData, String uuid, String email){

        User user = null
        if(email){
            user = User.findByEmail(email)
        }

        new DeviceActivity(activityData: activityData, lightData: lightData, soundData: soundData, uvData: uvData, uuid: uuid, user: user).save(failOnError: true)

        return [success: true]
    }
}
