package childrenlab

import grails.converters.JSON
import org.hibernate.criterion.Order


class StripeController {

    def stripeService


    def preOrder(String email, int amount){
        def card = [
                number: "4242424242424242",
                month: "2",
                year: "17",
                cvc: "123",
                name: "Yen-Chieh Chen"
        ]


        def stripe = stripeService.createCustomer(email, null, card)
        if(stripe instanceof Stripe){
            def order = new Orders(stripe: stripe, amount: amount, charge: 49.99, orderId: "test").save(failOnError: true)

        }else{
            return [success: false, message: "something wrong when create customer"] as JSON
        }

        return [success: true] as JSON
    }

    def getCardToken(){
        def result = stripeService.getCardToken()

        println result
    }

    def createCustomer(){
        def result = stripeService.createCustomer(params.email, params.description)
        render result as JSON
    }

    def charge(){
        def result = stripeService.charge()

        render result as JSON

    }

    def testCreateCustomer(){
        def card = [
                number: "4242424242424242",
                month: "2",
                year: "17",
                cvc: "123",
                name: "Yen-Chieh Chen"
        ]

        def result = stripeService.createCustomer("yen-chieh.chen@kapitall.com", "FOr test", card)
        render result
    }

    def testCharge(){
        def card = [
                number: "4242424242424242",
                month: "2",
                year: "17",
                cvc: "123",
                name: "Yen-Chieh Chen"
        ]

        def result = stripeService.charge("yen-chieh.chen@kapitall.com", card)
        render result as JSON
    }


}
