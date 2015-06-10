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

    def uploadUserProfile(def image){
        def user = springSecurityService.currentUser as User

        if(image && image.getSize() > 0){
            try{
                String fileName ="user_profile_${user.id}.png"
                String filePath = "userProfile"
                ftpService.save(image.getBytes(), fileName, filePath)
                fileName = "userUpload/$filePath/$fileName"
                user.profile = fileName
                user.save()

                return [success: true]
            }catch(Exception e){
                e.printStackTrace()
                return [success: false, message: "Erorr on uploading image null error: ${e.message}"]
            }
        }

    }
}
