package childrenlab

class Connect {

    String deviceModel
    String cordova
    String devicePlatform
    String phoneUuid
    String deviceVersion
    User user

    Date dateCreated
    Date lastUpdated

    static constraints = {

        deviceModel nullable: true
        cordova nullable: true
        devicePlatform nullable: true
        phoneUuid nullable: true
        deviceVersion nullable: true
        user nullable: true
    }
}
