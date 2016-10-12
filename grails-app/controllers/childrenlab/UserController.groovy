package childrenlab

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
class UserController {

    def userService
    def springSecurityService

    def register(String email, String password, String phoneNumber, String firstName, String lastName, String zipCode){
        def result = userService.register(email, password, phoneNumber, firstName, lastName, zipCode)

        render result as JSON
    }

/*    def updateProfile(String email, String password, String phoneNumber, String firstName, String lastName, String birthday, String nickName, String sex, String address, String city, int zipCode, String role){

        def result = userService.register(email, password, phoneNumber, firstName, lastName, birthday, nickName, sex, address, city, zipCode, role)

        render result as JSON
    }*/



    @Secured(['ROLE_USER'])
    def leaveFeedback(String type, String text){
        def result = userService.leaveFeedback(type, text)

        render result as JSON
    }

    def feedbackList(){
        def feedback = Feedback.list()
        render(view: 'feedbackList', model: [success: true, feedbackList: feedback])
    }

    def uploadUserProfile(){
        def result = userService.uploadUserProfile(params.image)

        render result as JSON
    }

    @Secured(['ROLE_USER'])
    def retrieveUserProfile(){
        def user = springSecurityService.currentUser as User
        def userKids = Kids.findAllByParent(user)

        def result = [success: true, user: user, kids: userKids]
        render result as JSON
    }

    @Secured(['ROLE_USER'])
    def updateProfile(String email, String phoneNumber, String firstName, String lastName, String address, String city, String state, String zipCode){
        def result = userService.updateProfile(email, phoneNumber, firstName, lastName, address, city, state, zipCode)

        render result as JSON
    }

    def isEmailRegistered(String email){
        boolean registered = User.findByEmail(email) != null

        render([registered: registered] as JSON)
    }

    @Secured(['ROLE_USER'])
    def updateUserRole(String role){
        if(!role){ render([success: false] as JSON)}

        def result = userService.updateUserRole(role)

        render result as JSON
    }

    @Secured(['ROLE_USER'])
    def updateIOSRegistrationId(String registrationId){
        def result = userService.updateRegistration(registrationId)

        render result as JSON
    }

    @Secured(['ROLE_ADMIN'])
    def getPushList(){
        def users = User.findAllByRegistrationIdIsNotNull()

        render(view: 'pushNotificationList', model: [users: users])
    }

}
