package childrenlab

import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN', 'ROLE_TESTER'])
class HomeController {

    def index() {
        if(SpringSecurityUtils.ifAllGranted('ROLE_TESTER')) {
            redirect([controller: 'device', action:'list'])
        }else{
            render(view: '/mainMenu')
        }

    }
}
