package childrenlab

class DeviceActivity {

    String activityX
    String activityY
    String activityZ
    String light
    String sound
    String uv

    static belongsTo = [device: Device]

    Date dateCreated
    Date lastUpdated

    static constraints = {
        activityX nullable: true
        activityY nullable: true
        activityZ nullable: true
        light nullable: true
        sound nullable: true
        uv nullable: true
        device nullable: false
    }
}
