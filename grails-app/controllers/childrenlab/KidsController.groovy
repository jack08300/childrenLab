package childrenlab

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER'])
class KidsController {

    def kidsService

    def add(String firstName, String lastName, String nickName, String birthday, String note){
        if(!firstName || !lastName){
            render([success: false, message: "Please enter first name and last name."] as JSON)
        }
        def result = kidsService.add(firstName, lastName, nickName, birthday, note)

        render result as JSON
    }

    def remove(int kidId){
        def result = kidsService.remove(kidId)

        return result as JSON
    }

    def edit(int kidId, String firstName, String lastName, String nickName, String birthday, String note){
        def result = kidsService.edit(kidId, firstName, lastName, nickName, birthday, note)

        render result as JSON
    }

    def list(){
        def result = kidsService.list()
        render result as JSON
    }

    def getKid(int id) {
        def result = kidsService.getKid(id)
        render result as JSON
    }


}
