package childrenlab

public enum KidsStatus {
    PRIVATE, PUBLIC, REMOVED
}

class Kids {

    String firstName
    String lastName
    String nickName
    Date birthday
    String note
    KidsStatus status = KidsStatus.PUBLIC
    String profile

    Date dateCreated
    Date lastUpdated

    static belongsTo = [parent: User]
//    static hasMany = [subHost: User]

    static constraints = {
        firstName nullable: false
        lastName nullable: false
        nickName nullable: true
        birthday nullable: true
        note nullable: true
        profile nullable: true
//        subHost nullable: true

        dateCreated nullable: true
        lastUpdated nullable: true
    }
}
