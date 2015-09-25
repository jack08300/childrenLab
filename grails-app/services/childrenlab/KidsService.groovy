package childrenlab

import grails.transaction.Transactional

@Transactional
class KidsService {

    def toolsService
    def springSecurityService

    def add(String firstName, String lastName, String nickName, String birthdayString, String note){
        User user = springSecurityService.getCurrentUser() as User
        try{
            def birthday = toolsService.stringToDate(birthdayString, "yyyy-MM-dd")
//            if(!birthday){return [success: false, message: "Birthday format was not right."]}

            new Kids(firstName: firstName, lastName: lastName, nickName: nickName, birthday: birthday, note: note, parent: user).save(failOnError: true)

            return [success: true]
        }catch(Exception e){
            log.error("Error on adding kids for user $user", e)
            return [success: false, message: "Something wrong with system. Please try again later."]
        }
    }

    def remove(int kidId){
        User user = springSecurityService.getCurrentUser() as User
        try{
            def kid = Kids.findByIdAndParent(kidId, user)
            if(!kid){ return [success: false, message: "Can't find kids."]}

            kid.status = KidsStatus.REMOVED
            kid.save()

            return [success: true]

        }catch(Exception e){
            log.error("Error on removing Kid. $user. Kid ID: $kidId", e);
            return [success: false, message: "Something wrong with system, please try again later."]
        }
    }

    def list(){
        User user = springSecurityService.getCurrentUser() as User
        try{
            def kids = Kids.findAllByParentAndStatus(user, KidsStatus.PUBLIC, [sort: "birthday", order: "desc"])

            return [success: true, kids: kids]
        }catch(Exception e){
            log.error("Error on getting kids list. $user.", e);
            return [success: false, message: "Something wrong with system, please try again later."]
        }
    }

    def edit(int kidId, String firstName, String lastName, String nickName, String birthdayString, String note){
        User user = springSecurityService.getCurrentUser() as User
        try{
            def birthday = birthdayString ? toolsService.stringToDate(birthdayString, "yyyy-MM-dd") : null

            def kid = Kids.findByIdAndParent(kidId, user)
            if(!kid){ return [success: false, message: "Can't find kids."]}

            kid.firstName = firstName ?: kid.firstName
            kid.lastName = lastName ?: kid.lastName
            kid.nickName = nickName ?: kid.nickName
            kid.birthday = birthday ?: kid.birthday
            kid.note = note ?: kid.note

            kid.save(failOnError: true)
            return [success: true]
        }catch(Exception e){
            log.error("Error on editting kids for user $user", e)
            return [success: false, message: "Something wrong with system. Please try again later."]
        }
    }
}
