package childrenlab

import grails.plugins.rest.client.RestBuilder
import grails.transaction.Transactional
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap

public enum ErrorType {
    api_connection_error, api_error, authentication_error, card_error, invalid_request_error, rate_limit_error
}

@Transactional
class StripeService {

    def apiRequest(String api, Map params){
        def rest = new RestBuilder(connectTimeout:1000, readTimeout:20000)

        MultiValueMap form = getRequestForm(params)

        def resp = rest.post("https://api.stripe.com/$api") {
            header "Authorization", "Bearer sk_test_Owf8vzeSEzWuMsQPvvC8SoIS"
            contentType "application/x-www-form-urlencoded"
            accept "application/json"
            body form
        }

        if(resp.status == 200){

            return resp.json

        }else {
            log.error("Error on calling Strip -  $resp.status - $resp.json")
        }

        return [success: false]
    }

    def createCustomer(String email, String description = null, def card = [:]){

        def user = Stripe.findByEmail(email)
        if(user){
            return user
        }

        def body = [
                "description": description,
                "email": email
        ]

        if(card){
            def token = getCardToken(card)
            body["source"] = token.id
        }

        def result = apiRequest("v1/customers", body)

        if(result){
            user = new Stripe(customerId: result.id, cardId: result?.sources?.data[0]?.id, email: result.email).save(failOnError: true)
        }

        return user

    }

    def charge(def email){
        def user = createCustomer(email)
        def body = [
                "amount": "4999",
                "currency": "usd",
                "customer": user.customerId
        ]

        def result = apiRequest("v1/charges", body)
        println result
        return result
    }

    def getCardToken(def card){
        def body = [
                "card[number]": card.number,
                "card[exp_month]": card.month,
                "card[exp_year]": card.year,
                "card[cvc]": card.cvc,
                "card[name]": card.name
        ]

        def result = apiRequest("v1/tokens", body)

        println "Card Token - $result"

        return result
    }

    def getRequestForm(Map body){
        MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>()

        body.each(){ key, value ->

            form.add(key.toString(), value.toString())
        }

        return form
    }
}
