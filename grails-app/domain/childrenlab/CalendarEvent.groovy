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
    String city
    String state
    int timezoneOffset
    boolean pushNotification


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
    }
}
