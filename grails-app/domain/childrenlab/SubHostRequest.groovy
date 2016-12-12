package childrenlab

public enum SubHostRequestStatus {
    PENDING, ACCEPTED, DENIED
}

class SubHostRequest {

    User requestFrom
    User requestTo
    Device device
    SubHostRequestStatus status = SubHostRequestStatus.PENDING

    Date dateCreated
    Date lastUpdated

    static constraints = {
        requestFrom nullable: false
        requestTo nullable: false
        device nullable: false
        status nullable: false
    }
}
