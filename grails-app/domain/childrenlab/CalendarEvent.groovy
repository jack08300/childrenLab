package childrenlab

public enum EventStatus {
    Open, Pass, Cancel, ENABLED, DISABLED, REMOVED
}

public enum EventRepeat {
    DAILY, WEEKLY
}

class CalendarEvent {

    String eventName
    Date startDate
    Date endDate
    String color
    EventStatus status = EventStatus.Open
    String description
    int alert
    String city
    String state
    int timezoneOffset
    EventRepeat eventRepeat
    boolean pushNotification
    CalendarEvent createdFromEvent


    static belongsTo = [user: User]
    static hasMany = [todoList: TodoList]

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
        city nullable: true
        state nullable: true
        todoList nullable: true
        eventRepeat nullable: true
        createdFromEvent nullable: true
    }
}
