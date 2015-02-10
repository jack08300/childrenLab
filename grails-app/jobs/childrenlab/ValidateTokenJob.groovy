package childrenlab

class ValidateTokenJob {
    static triggers = {
        cron cronExpression: "0 0 0/4 * * ?"
    }

    def execute() {
        AuthenticationToken.executeUpdate("delete AuthenticationToken where lastUpdated < ?", [new Date()-1])
    }
}
