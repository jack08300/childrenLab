package childrenlab

public enum RequestStatus{
    PENDING, REJECT, ACCEPTED, ENDED
}

class Requester {

    User user
    RequestStatus status = RequestStatus.PENDING
    String note

    static belongsTo = [schedule: Schedule]

    Date dateCreated
    Date lastUpdated

    static constraints = {
        user nullable: false
        note nullable: true
        dateCreated nullable: true
        lastUpdated nullable: true
    }

    static mapping = {
        note type: 'text'
    }
}
