package childrenlab

class Device {

    static belongsTo = [kid: Kids]
    List<User> subHost
    User user
    String swingVersion
    String macId
    String batteryStatus

    Date dateCreated
    Date lastUpdated

    static constraints = {
        swingVersion nullable: true
        macId nullable: false
        batteryStatus nullable: true
        user nullable: true
        dateCreated nullable: true
        lastUpdated nullable: true
    }
}
