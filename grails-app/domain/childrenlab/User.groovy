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

    Date dateCreated
    Date lastUpdated

	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	static transients = ['springSecurityService']

	static constraints = {
		email blank: false, unique: true
        phoneNumber blank: false
		password blank: false

        firstName nullable: false
        lastName nullable: false
        birthday nullable: true
        nickName nullable: true
        sex nullable: true

        profile nullable: true

        dateCreated nullable: true
        lastUpdated nullable: true

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
