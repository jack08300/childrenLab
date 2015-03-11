package childrenlab

class UserAddress {

    String address
    String city
    int zipCode
    static belongsTo = [user: User]

    Date dateCreated
    Date lastUpdated

    static constraints = {
        address nullable: true
        city nullable: true
        zipCode nullable: true
        user nullable: false

        dateCreated nullable: true
        lastUpdated nullable: true
    }
}
