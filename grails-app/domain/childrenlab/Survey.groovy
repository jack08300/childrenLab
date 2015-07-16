package childrenlab

class Survey {

    String gender
    String age
    String income
    String job
    String zipcode
    String kids
    String kidsAge
    String weeklySpend
    String kidsSchedule
    String outdoorActivity
    String percentSpendOnActivity
    String activityCategory
    String activityFrom
    String usedSmartWatch
    String whatKindOfWatch
    String smartWatchFunction
    String mustHaveFunction
    String smartWatchPrefer
    String smartWatchExpense
    String buyOrNot
    String wantToHaveFunction
    String whereToBuySmartWatch
    String whereToBuySmartWatchOther
    String improve
    String idea
    String email
    String ip

    Date dateCreated


    static constraints = {
        gender nullable: true
        age nullable: true
        income nullable: true
        job nullable: true
        zipcode nullable: true
        kids nullable: true
        kidsAge nullable: true
        weeklySpend nullable: true
        kidsSchedule nullable: true
        outdoorActivity nullable: true
        percentSpendOnActivity nullable: true
        activityCategory nullable: true
        activityFrom nullable: true
        usedSmartWatch nullable: true
        whatKindOfWatch nullable: true
        smartWatchFunction nullable: true
        mustHaveFunction nullable: true
        smartWatchPrefer nullable: true
        whereToBuySmartWatchOther nullable: true

        buyOrNot nullable: true
        wantToHaveFunction nullable: true
        whereToBuySmartWatch nullable: true
        improve nullable: true
        idea nullable: true
        email nullable: true
        ip nullable: true
    }
}
