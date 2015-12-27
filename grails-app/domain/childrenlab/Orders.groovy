package childrenlab

class Orders {

    String orderId
    Stripe stripe
    int amount = 1
    String product = "Swing Watch"
    float charge


    static constraints = {
        orderId nullable: false, unique: true
        stripe nullable: false
        amount: nullable: false
        product nullable: false
        charge nullable: false
    }

    def beforeInsert() {
        orderId = UUID.randomUUID().toString()
    }
}
