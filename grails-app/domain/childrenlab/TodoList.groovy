package childrenlab

public enum TodoStatus {
    PENDING, DONE, OVERDUE, REMOVED
}

class TodoList {

    String text
    TodoStatus status = TodoStatus.PENDING

    Date dateCreated
    Date lastUpdated

    static constraints = {
        text nullable: false
        status nullable: false
    }
}
