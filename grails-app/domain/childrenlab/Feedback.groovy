package childrenlab

public enum FeedbackType {
    BUG, SUGGESTION
}

class Feedback {

    static belongsTo = [user: User]
    FeedbackType type = FeedbackType.SUGGESTION
    String text

    static constraints = {
        user nullable: false
        type nullable: false
        text nullable: false
    }

    static mapping = {
        text type: 'text'
    }
}
