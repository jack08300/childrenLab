package childrenlab

class Orders {

    String orderId
    Stripe stripe
    int amount = 1
    String product = "Swing Watch"
    float charge
    boolean charged = false
    String phoneNumber

    static hasOne = [address: Address]

    Date dateCreated
    Date lastUpdated


    static constraints = {
        orderId nullable: false, unique: true
        stripe nullable: false
        amount: nullable: false
        product nullable: false
        charge nullable: true
        dateCreated nullable: true
        lastUpdated nullable: true
        address nullable: true
        phoneNumber nullable: true
    }

    def beforeInsert() {
        orderId = UUID.randomUUID().toString()
    }
}
