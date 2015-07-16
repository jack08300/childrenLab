package childrenlab

import grails.converters.JSON

class SurveyController {

    def storeIp(String ip){
        def survey = Survey.findByIp(ip) ?: new Survey(ip: ip)
        survey.save(flush: true, failOnError: true)

        render([success: true] as JSON)
    }

    def storeAnswer(){
        println params
        def survey = Survey.findByIp(params.ip) ?: new Survey(ip: params.ip)
        params.each(){ key, value ->
            if(key != "action" && key != "format" && key != "controller"){
                value = value.toString()
                survey["$key"] = value
            }
        }

        survey.save(flush: true, failOnError: true)

        render([success: true] as JSON)
    }
}
