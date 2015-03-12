package childrenlab

public enum ScheduleStatus{
    PRIVATE, PUBLIC, COMPLETED, REMOVED
}

public enum ScheduleType {
    NANNY, PARENT
}

class Schedule {

    Date startDate
    Date endDate
    String note
    ScheduleStatus status = ScheduleStatus.PRIVATE
    ScheduleType type
    int paymentPerHour

    static belongsTo = [user: User]

    Date dateCreated
    Date lastUpdated

    static constraints = {
        startDate nullable: false
        endDate nullable: false
        dateCreated nullable: true
        lastUpdated nullable: true
        type nullable: false
    }

    static mapping = {
        note type: 'text'
    }
}
