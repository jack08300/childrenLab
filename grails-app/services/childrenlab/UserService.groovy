package childrenlab


import grails.transaction.Transactional

@Transactional
class UserService {

    def springSecurityService
    def ftpService

    def register(String email, String password, String phoneNumber, String firstName, String lastName, String birthday, String nickName, String sex, String address, String city, int zipCode, String roleString){

        def user = User.findByEmail(email)

        Map result
        try{
            Date birthdayDate = birthday ? new Date().parse("yyyy-MM-dd", birthday) : null
            roleString = roleString ?: 'ROLE_USER'
            Role role = Role.findByAuthority(roleString)

            if(user){
                //User exists
                result = [success: false, message: "The email already registered."]
            } else {
                user = new User(email: email, password: password, phoneNumber: phoneNumber, firstName: firstName, lastName: lastName, birthday: birthdayDate, nickName: nickName, sex: sex, address: address, city: city, zipCode: zipCode).save(flush: true, failOnError: true)

                new UserRole(user: user, role: role).save(flush: true, failOnError: true)
                result = [success: true]
            }
        }catch(Exception e){
            log.error("Login or register error: ", e);
            result = [success: false, message: "The server incounter error stage, please try it again later."]
        }

        return result
    }

    def leaveFeedback(String typeString, String text){
        User user = springSecurityService.getCurrentUser() as User

        FeedbackType type = typeString as FeedbackType
        new Feedback(user: user, type: type, text: text).save(failOnError: true)

        return [success: true]
    }


    def updateProfile(String email, String phoneNumber, String firstName, String lastName, String gender){
        def user = springSecurityService.currentUser as User

        try{
            if(email && user.email != email){ user.email = email }
            if(phoneNumber && user.phoneNumber != phoneNumber){ user.phoneNumber = phoneNumber}
            if(firstName && user.firstName != firstName){ user.firstName = firstName }
            if(lastName && user.lastName != lastName){ user.lastName = lastName}
            if(gender && user.sex != gender){ user.sex = gender}

            user.save(failOnError: true, flash: true)

            return [success: true]
        }catch(Exception e){
            log.error("Error on updating user profile. $e.message")
            return[success: false]
        }
    }


}
