package childrenlab

class SurveySourceConfig {

    String source
    boolean email = false

    static constraints = {
        source nullable: false
    }
}
