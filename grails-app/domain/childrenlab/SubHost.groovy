package childrenlab

enum SubHostRequestStatus {
    PENDING, ACCEPTED, DENIED
}


class SubHost {

    User requestFrom
    User requestTo
    SubHostRequestStatus status = SubHostRequestStatus.PENDING

    static hasMany = [kid: Kids]
    Date dateCreated
    Date lastUpdated

    static constraints = {
        requestFrom nullable: false
        requestTo nullable: false
        status nullable: false
        kid nullable: true
    }

}
