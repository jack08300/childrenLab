import childrenlab.Activity
import childrenlab.BootstrapFlag
import childrenlab.CalendarEvent
import childrenlab.Device
import childrenlab.FlagName
import childrenlab.Kids
import childrenlab.Role
import childrenlab.SubHost
import childrenlab.TodoList
import childrenlab.User
import childrenlab.UserRole
import grails.converters.JSON
import org.joda.time.DateTime
import org.joda.time.DateTimeZone

import java.text.SimpleDateFormat

class BootStrap {

    def init = { servletContext ->
        def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
        new Role(authority: 'ROLE_USER').save(flush: true)
        new Role(authority: 'ROLE_PARENT').save(flush: true)
        new Role(authority: 'ROLE_NANNY').save(flush: true)
        new Role(authority: 'ROLE_TESTER').save(flush: true)


        def testUser = new User(email: 'admin', password: 'sUPuteP6', firstName: "Jay", lastName: "Chen", phoneNumber: '12345656').save(flush: true)
        def tester = new User(email: 'tester', password: 'tester', firstName: "Tester For Data", lastName: "", phoneNumber: '').save(flush: true)
        def testUser2 = new User(email: 'user', password: 'user', firstName: 'Jay', lastName: 'chen', phoneNumber: '12234214512').save(flush: true)
        def userTester = new User(email: 'user', password: 'user', firstName: 'TESTER', lastName: 'TESTER', phoneNumber: '12234214512').save(flush: true)
        def userTester2 = new User(email: 'jack08300@gmail.com', password: 'aaaaaa', firstName: 'Jay', lastName: 'Chen', phoneNumber: '12234214512').save(flush: true)

        new UserRole(role: Role.get(1), user: testUser).save(flush: true)
        new UserRole(role: Role.get(2), user: testUser2).save(flush: true)
        new UserRole(role: Role.get(5), user: tester).save(flush: true)
        new UserRole(role: Role.get(2), user: userTester2).save(flush: true)

        def device = Device.findByMacId('test') ?: new Device(user: userTester, swingVersion: 'Test', macId: 'test').save(flush: true)

        SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss")

        JSON.registerObjectMarshaller(Kids){
            def returnArray = [:]
            returnArray['id'] = it.id
            returnArray['firstName'] = it.firstName
            returnArray['lastName'] = it.lastName
            returnArray['profile'] = it.profile

            return returnArray
        }

        JSON.registerObjectMarshaller(User){
            def returnArray = [:]
            returnArray['id'] = it.id
            returnArray['firstName'] = it.firstName
            returnArray['lastName'] = it.lastName
            returnArray['phoneNumber'] = it.phoneNumber
            returnArray['email'] = it.email
            returnArray['profile'] = it.profile
            returnArray['zipCode'] = it.zipCode


            return returnArray
        }

        JSON.registerObjectMarshaller(CalendarEvent){
            def returnArray = [:]
            returnArray['id'] = it.id
            returnArray['eventName'] = it.eventName


            def start = new DateTime(it.startDate).withZone(DateTimeZone.UTC)
//            start = start.plusMinutes(it.timezoneOffset)

            def end = new DateTime(it.endDate).withZone(DateTimeZone.UTC)
//            end = end.plusMinutes(it.timezoneOffset)


            returnArray['startDate'] = dateFormat.format(start.toDate())
            returnArray['endDate'] = dateFormat.format(end.toDate())
            returnArray['color'] = it.color
            returnArray['status'] = it.status?.name()
            returnArray['description'] = it.description ?: ''
            returnArray['alert'] = it.alert
            returnArray['city'] = it.city
            returnArray['state'] = it.state
            returnArray['timezoneOffset'] = it.timezoneOffset
            returnArray['todo'] = it.todoList
            returnArray['repeat'] = it.eventRepeat?.name()
            returnArray['createdFromRepeatEvent'] = it.createdFromEventId

            return returnArray
        }
/*
        JSON.registerObjectMarshaller(CalendarRepeatEvent){
            def returnArray = [:]
            returnArray['id'] = it.id
            returnArray['eventName'] = it.eventName


            def start = new DateTime(it.startDate)
            start = start.plusMinutes(it.timezoneOffset)

            def end = new DateTime(it.endDate)
            end = end.plusMinutes(it.timezoneOffset)


            returnArray['startDate'] = dateFormat.format(start.toDate())
            returnArray['endDate'] = dateFormat.format(end.toDate())
            returnArray['color'] = it.color
            returnArray['status'] = it.status?.name()
            returnArray['description'] = it.description ?: ''
            returnArray['alert'] = it.alert
            returnArray['city'] = it.city
            returnArray['state'] = it.state
            returnArray['timezoneOffset'] = it.timezoneOffset
            returnArray['todo'] = it.todoList

            return returnArray
        }*/
        JSON.registerObjectMarshaller(Activity){
            def returnArray = [:]
            returnArray['id'] = it.id
            returnArray['steps'] = it.steps
            returnArray['type'] = it.type.name()
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

        JSON.registerObjectMarshaller(SubHost){
            def returnArray = [:]
            returnArray['id'] = it.id
            returnArray['requestFrom'] = it.requestFrom
            returnArray['requestTo'] = it.requestTo
            returnArray['status'] = it.status.name()

            return returnArray
        }

        JSON.registerObjectMarshaller(Device){
            def returnArray = [:]
            returnArray['id'] = it.id
            returnArray['kid'] = it.kid
            returnArray['macId'] = it.macId
            returnArray['subHost'] = it.subHost

            return returnArray
        }
    }
    def destroy = {
    }

    def kidsRelationBootStrap = {
        def flag = BootstrapFlag.findByFlagName(FlagName.KIDS_RELATION)
        if(flag?.flag){ return false; }

        def kids = Kids.findAll()

        kids.each() {
            if(!it.parent) {
                it.sub = it.parent
                it.save(failOnError: true)
            }
        }
        new BootstrapFlag(flagName: FlagName.KIDS_RELATION, flag: true).save(failOnError: true)
    }
}
