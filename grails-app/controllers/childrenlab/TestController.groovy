package childrenlab

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER'])
class TestController {
    def token(){
        return [success: true]
    }
}
