package childrenlab

class ActivityRaw {

    String indoorActivity
    String outdoorActivity
    long time
    Device device
    long deviceTime

    Date dateCreated
    Date lastUpdated

    static constraints = {
        deviceTime nullable: true
    }
}
