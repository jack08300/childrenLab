package childrenlab


import grails.transaction.Transactional

@Transactional
class UserService {

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
}
