package childrenlab

import grails.converters.JSON
import org.hibernate.criterion.Order


class StripeController {

    def stripeService


    def preOrder(String email, int amount){
println params as JSON

        def stripe = stripeService.createCustomer(email, null, JSON.parse(params.card), JSON.parse(params.billingAddress))
        if(stripe instanceof Stripe){
            def order = new Orders(stripe: stripe, amount: amount, charge: params.charge, orderId: "test", phoneNumber: params.shippingAddress.phoneNumber)

            def address = new Address(
                    address1: params.shippingAddress.address.address1,
                    address2: params.shippingAddress.address.address2,
                    city: params.shippingAddress.address.city,
                    state: params.shippingAddress.address.state,
                    country: params.shippingAddress.address.country,
                    zipCode: params.shippingAddress.address.zipcode,
                    name: "${params.shippingAddress.firstName} ${params.shippingAddress.lastName}",
            ).save(failOnError: true)
            order.address = address
            order.save(failOnError: true)
        }else{
            render([success: false, message: "something wrong when create customer"] as JSON)
            return
        }

        render([success: true] as JSON)
    }

    def getCardToken(){
        def result = stripeService.getCardToken()

        println result
    }

    def createCustomer(){
        def result = stripeService.createCustomer(params.email, params.description)
        render result as JSON
    }

    def charge(String orderId){
        def result = stripeService.charge(orderId)

        if(result.success){
            flash.message = "Charged"
        }else{
            flash.message = "Something wrong when processing."
        }

        render(controller: "orders")

    }

    def testCreateCustomer(){
        def card = [
                number: "4242424242424242",
                month: "2",
                year: "17",
                cvc: "123",
                name: "Yen-Chieh Chen"
        ]

        def shipping = [
                firstName: "Yen-Chieh",
                lastName: "CHen",
                phone: "3473949504",
                address : [
                        address1: "8612 Elmhurst Ave",
                        city: "Flushing",
                        state: "NY",
                        country: "USA",
                        zipcode: "11375"
                ]
        ]

        def billing = [
                name: "Yen-Chieh Chen",
                        address1: "8612 Elmhurst Ave",
                        city: "Flushing",
                        state: "NY",
                        country: "USA",
                        zipcode: "11375"

        ]

        def result = stripeService.createCustomer("yen-chieh.chen@kapitall.com", "FOr test", card, shipping, billing)
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
