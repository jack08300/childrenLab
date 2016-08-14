package childrenlab

enum ActivityType {
    INDOOR, OUTDOOR
}
class Activity {

    int steps = 0
    int calories = 0
    int distance = 0
    int uv = 0
    ActivityType type

    
    long receivedTime = 0
    Date receivedDate

    static belongsTo = [device: Device]

    Date dateCreated
    Date lastUpdated

    static constraints = {
        device nullable: false
        steps nullable: false
        type nullable: false
        receivedDate nullable: true
    }
}

//Indoor
//	200,38,175,87 - Time, 0 - Type,124,0,0,0 - Activity,2,0,0,0 - Activity,3,0,0,0 - Activity,4,0,0,0 - Activity
//Outdoor
//	200,38,175,87,1,20,0,0,0,2,0,0,0,3,0,0,0,4,0,0,0