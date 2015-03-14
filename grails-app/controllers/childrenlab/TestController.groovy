package childrenlab

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER'])
class TestController {
    def token(){
        render([success: true] as JSON)
    }
}
