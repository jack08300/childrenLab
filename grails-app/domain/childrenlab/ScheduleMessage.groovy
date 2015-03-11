package childrenlab

class ScheduleMessage {

    User user
    String message
    static belongsTo = [schedule: Schedule]

    Date dateCreated
    Date lastUpdated

    static constraints = {
        message nullable: false, blank: false
        dateCreated nullable: true
        lastUpdated nullable: true
    }

    static mapping = {
        message type: 'text'
    }
}
