package childrenlab

public enum EventStatus {
    Open, Pass, Cancel
}

class CalendarEvent {

    String eventName
    Date startDate
    Date endDate
    String color
    EventStatus status = EventStatus.Open
    String description
    int alert

    static belongsTo = [user: User]


    Date dateCreated
    Date lastUpdated

    static mapping = {
        description type: 'text'
    }

    static constraints = {
        color nullable: true
        eventName nullable: false
        startDate nullable: false
        endDate nullable: false
        user nullable: false
        description nullable: true
        alert nullable: true
    }
}
