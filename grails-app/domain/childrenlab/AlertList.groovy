package childrenlab

class AlertList {

    int alertId
    String english
    String spanish
    String russian

    static constraints = {
        spanish nullable: true
        russian nullable: true
    }
}
