package childrenlab

class User {

	transient springSecurityService

	String email
	String password
    String phoneNumber
    String firstName
    String lastName
    Date birthday
    String nickName
    String sex
    String profile
	String address
	String city
	String state

	static hasMany = [calendarEvent: CalendarEvent]

    Date dateCreated
    Date lastUpdated
	String registrationId

	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	String zipCode
    boolean completed = false
	static transients = ['springSecurityService']

	static constraints = {
		email blank: false, unique: true
        password blank: false

        phoneNumber nullable: true, blank: true
        firstName nullable: true
        lastName nullable: true
        birthday nullable: true
        nickName nullable: true
        sex nullable: true
		zipCode nullable: true
		address nullable: true
		city nullable: true
		state nullable: true


        profile nullable: true

        dateCreated nullable: true
        lastUpdated nullable: true
		registrationId nullable: true
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role }
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}
}
