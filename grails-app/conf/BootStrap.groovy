import childrenlab.Role
import childrenlab.User
import childrenlab.UserRole
import grails.plugin.springsecurity.SecurityFilterPosition
import grails.plugin.springsecurity.SpringSecurityUtils

class BootStrap {

    def init = { servletContext ->
        def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
        new Role(authority: 'ROLE_USER').save(flush: true)
        new Role(authority: 'ROLE_NANNY').save(flush: true)


        def testUser = new User(email: 'admin', password: 'admin', firstName: "Jay", lastName: "Chen", phoneNumber: '12345656').save(flush: true)
        def testUser2 = new User(email: 'user', password: 'user', firstName: 'Jay', lastName: 'chen', phoneNumber: '12234214512').save(flush: true)

        new UserRole(role: Role.get(1), user: testUser).save(flush: true)
        new UserRole(role: Role.get(2), user: testUser2).save(flush: true)

    }
    def destroy = {
    }
}
