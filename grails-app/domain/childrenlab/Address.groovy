package childrenlab

class Address {

    String address1
    String address2
    String city
    String state
    String country = "USA"
    String zipCode
    String name

    static belongsTo = [orders: Orders]

    Date dateCreated
    Date lastUpdated

    static constraints = {
        address1 nullable: false
        address2 nullable: true
        city nullable: false
        state nullable: false
        country nullable: false
        zipCode nullable: false
        orders nullable: true

    }
}
