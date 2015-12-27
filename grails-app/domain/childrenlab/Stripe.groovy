package childrenlab

class Stripe {

    String customerId
    String cardId
    String email
    Date dateCreated
    Date lastUpdated


    static constraints = {
        customerId nullable: false
        cardId nullable: true
        email nullabele: false, unique: true
    }
}
