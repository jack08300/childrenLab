import childrenlab.Activity
import childrenlab.CalendarEvent
import childrenlab.Device
import childrenlab.Kids
import childrenlab.Role
import childrenlab.Schedule
import childrenlab.ScheduleMessage
import childrenlab.TodoList
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
        new Role(authority: 'ROLE_PARENT').save(flush: true)
        new Role(authority: 'ROLE_NANNY').save(flush: true)
        new Role(authority: 'ROLE_TESTER').save(flush: true)


        def testUser = new User(email: 'admin', password: 'admin', firstName: "Jay", lastName: "Chen", phoneNumber: '12345656').save(flush: true)
        def tester = new User(email: 'tester', password: 'tester', firstName: "Tester For Data", lastName: "", phoneNumber: '').save(flush: true)
        def testUser2 = new User(email: 'user', password: 'user', firstName: 'Jay', lastName: 'chen', phoneNumber: '12234214512').save(flush: true)
        def userTester = new User(email: 'user', password: 'user', firstName: 'TESTER', lastName: 'TESTER', phoneNumber: '12234214512').save(flush: true)
        def userTester2 = new User(email: 'jack08300@gmail.com', password: 'aaaaa', firstName: 'Jay', lastName: 'Chen', phoneNumber: '12234214512').save(flush: true)

        new UserRole(role: Role.get(1), user: testUser).save(flush: true)
        new UserRole(role: Role.get(2), user: testUser2).save(flush: true)
        new UserRole(role: Role.get(5), user: tester).save(flush: true)
        new UserRole(role: Role.get(2), user: userTester2).save(flush: true)

        def device = Device.findByMacId('test') ?: new Device(user: userTester, swingVersion: 'Test', macId: 'test').save(flush: true)

        SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");

        JSON.registerObjectMarshaller(Schedule){
            def returnArray = [:]
            returnArray['id'] = it.id
            returnArray['dateCreated'] = dateFormat.format(it.dateCreated)
            returnArray['lastUpdated'] = dateFormat.format(it.lastUpdated)
            returnArray['startDate'] = dateFormat.format(it.startDate)
            returnArray['endDate'] = dateFormat.format(it.endDate)
            returnArray['status'] = it.status.name()
            returnArray['type'] = it.type.name()
            returnArray['price'] = it.paymentPerHour
            returnArray['note'] = it.note
            returnArray['user'] = [
                    id: it.user.id,
                    firstName: it.user.firstName,
                    lastName: it.user.lastName,
                    name: "$it.user.firstName  $it.user.lastName",
                    nickName: it.user.nickName,
                    birthday: it.user.birthday,
                    phoneNumber: it.user.phoneNumber,
                    gender: it.user.sex,
                    email: it.user.email

            ]
            returnArray['note'] = it.note

            return returnArray
        }

        JSON.registerObjectMarshaller(ScheduleMessage){
            def returnArray = [:]

            returnArray['id'] = it.id
            returnArray['user'] = [
                    id: it.user.id,
                    email: it.user.email
            ]
            returnArray['message'] = it.message
            returnArray['owner'] = [
                    id: it.schedule.user.id,
                    email: it.schedule.user.email
            ]
            returnArray['date'] = it.dateCreated

            return returnArray
        }

        JSON.registerObjectMarshaller(Kids){
            def returnArray = [:]
            returnArray['id'] = it.id
            returnArray['firstName'] = it.firstName
            returnArray['lastName'] = it.lastName
            returnArray['nickName'] = it.nickName
            returnArray['note'] = it.note
            returnArray['profile'] = it.profile

            return returnArray
        }

        JSON.registerObjectMarshaller(User){
            def returnArray = [:]
            returnArray['id'] = it.id
            returnArray['firstName'] = it.firstName
            returnArray['lastName'] = it.lastName
            returnArray['nickName'] = it.nickName
            returnArray['birthday'] = it.birthday ? dateFormat.format(it.birthday) : null
            returnArray['phoneNumber'] = it.phoneNumber
            returnArray['email'] = it.email
            returnArray['profile'] = it.profile
            returnArray['address'] = it.address
            returnArray['city'] = it.city
            returnArray['state'] = it.state
            returnArray['zipCode'] = it.zipCode


            return returnArray
        }

        JSON.registerObjectMarshaller(CalendarEvent){
            def returnArray = [:]
            returnArray['id'] = it.id
            returnArray['eventName'] = it.eventName
            returnArray['startDate'] = dateFormat.format(it.startDate)
            returnArray['endDate'] = dateFormat.format(it.endDate)
            returnArray['color'] = it.color
            returnArray['status'] = it.status?.name()
            returnArray['description'] = it.description ?: ''
            returnArray['alert'] = it.alert
            returnArray['city'] = it.city
            returnArray['state'] = it.state
            returnArray['todo'] = it.todoList

            return returnArray
        }

        JSON.registerObjectMarshaller(Activity){
            def returnArray = [:]
            returnArray['id'] = it.id
            returnArray['steps'] = it.steps
            returnArray['type'] = it.type.name()
            returnArray['distance'] = it.distance
            returnArray['calories'] = it.steps
            returnArray['receivedTime'] = it.receivedTime
            returnArray['receivedDate'] = it.receivedDate

            return returnArray
        }

        JSON.registerObjectMarshaller(TodoList){
            def returnArray = [:]
            returnArray['id'] = it.id
            returnArray['text'] = it.text
            returnArray['status'] = it.status.name()

            return returnArray
        }
    }
    def destroy = {
    }
}
