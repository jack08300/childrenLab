package childrenlab

class Activity {

    int steps = 0
    int calories = 0
    int distance = 0
    long receivedTime = 0

    static belongsTo = [device: Device]

    Date dateCreated
    Date lastUpdated

    static constraints = {
        device nullable: false
        steps nullable: false
    }
}
