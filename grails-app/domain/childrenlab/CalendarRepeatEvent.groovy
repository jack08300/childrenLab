package childrenlab

public enum RepeatEventStatus {
    ENABLED, DISABLED, REMOVED
}

public enum EventRepeat {
    DAILY, WEEKLY
}


class CalendarRepeatEvent {

    String eventName
    Date startDate
    Date endDate
    String color
    RepeatEventStatus status = RepeatEventStatus.ENABLED
    String description
    int alert
    String city
    String state
    int timezoneOffset
    EventRepeat eventRepeat

    static belongsTo = [user: User];
    static hasMany = [todoList: TodoList]

    Date dateCreated
    Date lastUpdated

    static mapping = {
        description type: 'text'
    }

    static constraints = {
        description nullable: true
        city nullable: true
        state nullable: true
        eventRepeat nullable: false
    }
}
