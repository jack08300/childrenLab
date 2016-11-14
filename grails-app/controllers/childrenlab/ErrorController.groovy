package childrenlab

import grails.converters.JSON

class ErrorController {
    def internalServerError(){
        withFormat {
            json {
                render([] as JSON)
            }
        }
    }
}
