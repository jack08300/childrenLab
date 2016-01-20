package childrenlab

import grails.converters.JSON
import grails.transaction.Transactional
import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method
import net.minidev.json.JSONObject
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap

public enum ErrorType {
    api_connection_error, api_error, authentication_error, card_error, invalid_request_error, rate_limit_error
}

@Transactional
class StripeService {

    def apiRequest(String api, def params, boolean useRequestForm = false){
        println "params:-  $params"

println "path:- $api"
        def http = new HTTPBuilder("https://api.stripe.com/")
        def result = [success: false]
        http.request(Method.POST, ContentType.URLENC){
            headers.'Accept' = ContentType.JSON
            headers.'Authorization' = "Bearer sk_test_Owf8vzeSEzWuMsQPvvC8SoIS"
            uri.path = api
            uri.query = params
            response.success = { resp, json ->
                println json
                println json.keySet();
                return JSON.parse(json.keySet().toString())
            }

            response.failure = { resp, message ->
                println "Request failed with status ${resp.status} ${message}"
                return JSON.parse(message.keySet().toString());
            }
        }


//        return result
    }

    def createCustomer(String email, String description = null, def card = [:], def billingAddress = [:]){

        def user = Stripe.findByEmail(email)

        def body = [
                "description": description,
                "email": email
        ]

        if(card){
            def token = getCardToken(card, billingAddress)
            if(token.error[0]){
                return [success: false, message: token.error.message]
            }
            body["source"] = token.id
        }


        def result = apiRequest("v1/customers", body, false)

        if(result){
            println "Create new customer Result: - $result"

            user = user ?: new Stripe(email: result.email)

            user.cardId = result?.sources?.data[0]?.id
            user.customerId = result.id
            user.save(failOnError: true)
        }

        return user

    }

    def charge(String orderId){
        def order = Orders.findByOrderIdAndCharged(orderId, false)
        def user = order.stripe
        def body = [
                "amount": String.valueOf(Math.round(order.charge * 100)),
                "currency": "usd",
                "customer": user.customerId
        ]

        def result = apiRequest("v1/charges", body)
        println result

        if(result.status == "succeeded"){
            order.charged = true

            return [success: true]
        }else{
            log.error("Error on charging orderId: $orderId")

            return [success: false]
        }
    }

    def getCardToken(def card, def billingAddress){
        def body = [
                "card[number]": card.number,
                "card[exp_month]": card.month,
                "card[exp_year]": card.year,
                "card[cvc]": card.cvc,
                "card[name]": card.name,
                "card[address_line1]": billingAddress?.address1,
                "card[address_line2]": billingAddress?.address2,
                "card[address_city]": billingAddress?.city,
                "card[address_country]": billingAddress?.country,
                "card[address_state]": billingAddress?.state,
                "card[address_zip]": billingAddress?.zipcode,
        ]

        def result = apiRequest("v1/tokens", body, true)

        println "Card Token - $result"

        return result
    }

    def getRequestForm(Map body){
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<String, Object>()

        form = childrenForm(body, form)
        println form

        return form
    }

    def childrenForm(def body, MultiValueMap form = new LinkedMultiValueMap<String, Object>()){
            body.each(){ key, value ->
                if(value instanceof Map){
                    form.add(key, value.toString())
                }else{
                    form.add(key, value)
                }

            }
        return form
    }
}
