package childrenlab

class RepeatTodoList {

    String text
    TodoStatus status = TodoStatus.PENDING

    Date dateCreated
    Date lastUpdated

    static constraints = {
        text nullable: false
        status nullable: false
    }

}
