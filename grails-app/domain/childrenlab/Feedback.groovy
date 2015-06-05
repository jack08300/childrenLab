package childrenlab

public enum FeedbackType {
    BUG, SUGGESTION
}

class Feedback {

    static belongsTo = [user: User]
    FeedbackType type = FeedbackType.SUGGESTION
    String text

    Date dateCreated

    static constraints = {
        user nullable: false
        type nullable: false
        text nullable: false
        dateCreated nullable: true
    }

    static mapping = {
        text type: 'text'
    }
}
