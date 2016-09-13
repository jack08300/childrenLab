package childrenlab

public enum FlagName {
    KIDS_RELATION
}


class BootstrapFlag {

    FlagName flagName
    Boolean flag = false

    Date dateCreated
    Date lastUpdated

    static constraints = {
        flagName nullable: false
    }
}
