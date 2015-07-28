package childrenlab

import grails.converters.JSON


class SurveyController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    def storeToken(String token, String resourcePage){
        def survey = Survey.findByToken(token) ?: new Survey(token: token, resourcePage: resourcePage)
        if(resourcePage) survey.resourcePage = resourcePage
        survey.save(flush: true, failOnError: true)

        render([success: true] as JSON)
    }

    def storeAnswer(){
        println params
        def survey = Survey.findByToken(params.token) ?: new Survey(token: params.token)
        params.each(){ key, value ->
            if(key != "action" && key != "format" && key != "controller" && key != "token"){
                value = value.toString()
                survey["$key"] = "${value.replace("[", "").replace("]", "").replaceAll(",", " ")}"
            }
        }

        survey.save(flush: true, failOnError: true)

        render([success: true] as JSON)
    }


    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Survey.list(params), model:[surveyInstanceCount: Survey.count()]
    }

    def show(Survey surveyInstance) {
        respond surveyInstance
    }

    def create() {
        respond new Survey(params)
    }

    def edit(Survey surveyInstance) {
        respond surveyInstance
    }

    def export(){
        def survey = Survey.list()

        String csvFile = "Completed, Region, Email, Gender, Age, Income, job, ZipCode, Kids, Kids_Age, Weekly_Spend_On_Kids, Kids_Schedule, Outdoor_Activity, Percent_Spend_On_Activity, Activity_Category, Activity_From, Used_Smart_Watch, What_Kind_Of_Watch, Must_Have_Function, Smart_Watch_Prefer, Smart_Watch_Expense, Buy_Or_Not, Wish_Function, Where_To_Buy_Watch, Where_To_Buy_Watch_Others, Suggestion, Idea, IP, source\n"

        survey.each(){
            csvFile+= "$it.completed, $it.region, $it.email, $it.gender, $it.age, $it.income, $it.job, $it.zipcode, $it.kids, $it.kidsAge, $it.weeklySpend, $it.kidsSchedule, $it.outdoorActivity,  $it.percentSpendOnActivity, $it.activityCategory, $it.activityFrom, $it.usedSmartWatch, $it.whatKindOfWatch, $it.mustHaveFunction, $it.smartWatchPrefer, $it.smartWatchExpense, $it.buyOrNot, $it.wantToHaveFunction, $it.whereToBuySmartWatch, $it.whereToBuySmartWatchOther, $it.improve, $it.idea, $it.ip, $it.resourcePage\n"
        }

        csvFile.replaceAll(",", "\",\"")

        response.setHeader("content-disposition", "attachment; filename=SurveyReport.csv")
        render(contentType: "text/csv", text: csvFile)
    }
}
