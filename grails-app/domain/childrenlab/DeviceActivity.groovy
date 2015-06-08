package childrenlab

class DeviceActivity {

    String activityX
    String activityY
    String activityZ
    String light
    String audio
    String uv
    String temperature

    static belongsTo = [device: Device]

    Date dateCreated
    Date lastUpdated

    static constraints = {
        activityX nullable: true
        activityY nullable: true
        activityZ nullable: true
        light nullable: true
        audio nullable: true
        uv nullable: true
        device nullable: false
        temperature nullable: true
    }

    static mapping = {
        sort dateCreated: "desc"
    }
}
