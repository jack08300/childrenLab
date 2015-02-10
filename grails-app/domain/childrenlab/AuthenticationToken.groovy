package childrenlab

class AuthenticationToken {

    String token
    String email
    Date dateCreated
    Date lastUpdated
    Integer accessCount = 0

    static constraints = {
        token nullable: false
        email nullable: false
        dateCreated nullable: true
        lastUpdated nullable: true
    }

    def afterLoad() {
        accessCount++
    }
}
