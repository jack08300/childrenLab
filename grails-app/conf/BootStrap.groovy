import childrenlab.Kids
import childrenlab.Role
import childrenlab.Schedule
import childrenlab.User
import childrenlab.UserRole
import grails.converters.JSON
import grails.plugin.springsecurity.SecurityFilterPosition
import grails.plugin.springsecurity.SpringSecurityUtils

import java.text.SimpleDateFormat

class BootStrap {

    def init = { servletContext ->
        def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
        new Role(authority: 'ROLE_USER').save(flush: true)
        new Role(authority: 'ROLE_NANNY').save(flush: true)


        def testUser = new User(email: 'admin', password: 'admin', firstName: "Jay", lastName: "Chen", phoneNumber: '12345656').save(flush: true)
        def testUser2 = new User(email: 'user', password: 'user', firstName: 'Jay', lastName: 'chen', phoneNumber: '12234214512').save(flush: true)

        new UserRole(role: Role.get(1), user: testUser).save(flush: true)
        new UserRole(role: Role.get(2), user: testUser2).save(flush: true)

        SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");

        JSON.registerObjectMarshaller(Schedule){
            def returnArray = [:]
            returnArray['id'] = it.id
            returnArray['dateCreated'] = dateFormat.format(it.dateCreated)
            returnArray['lastUpdated'] = dateFormat.format(it.lastUpdated)
            returnArray['startDate'] = dateFormat.format(it.startDate)
            returnArray['endDate'] = dateFormat.format(it.endDate)
            returnArray['status'] = it.status.name()
            returnArray['type'] = it.type.name()
            returnArray['user'] = it.user.firstName + " " + it.user.lastName
            returnArray['userId'] = it.user.id
            returnArray['note'] = it.note

            return returnArray
        }

        JSON.registerObjectMarshaller(Kids){
            def returnArray = [:]
            returnArray['id'] = it.id
            returnArray['firstName'] = it.firstName
            returnArray['lastName'] = it.lastName
            returnArray['nickName'] = it.nickName
            returnArray['birthday'] = dateFormat.format(it.birthday)
            returnArray['note'] = it.note

            return returnArray
        }
    }
    def destroy = {
    }
}
