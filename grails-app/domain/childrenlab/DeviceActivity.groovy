package childrenlab

class DeviceActivity {

    String activityData
    String lightData
    String soundData
    String uvData
    String uuid
    User user
    String batteryStatus

    Date dateCreated
    Date lastUpdated

    static constraints = {
        activityData nullable: true
        lightData nullable: true
        soundData nullable: true
        uvData nullable: true
        user nullable: true
        uuid nullable: false
        batteryStatus nullable: true
    }
}
