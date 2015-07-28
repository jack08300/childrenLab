<%@ page import="childrenlab.Survey" %>



<div class="fieldcontain ${hasErrors(bean: surveyInstance, field: 'gender', 'error')} ">
	<label for="gender">
		<g:message code="survey.gender.label" default="Gender" />
		
	</label>
	<g:textField name="gender" value="${surveyInstance?.gender}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: surveyInstance, field: 'age', 'error')} ">
	<label for="age">
		<g:message code="survey.age.label" default="Age" />
		
	</label>
	<g:textField name="age" value="${surveyInstance?.age}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: surveyInstance, field: 'income', 'error')} ">
	<label for="income">
		<g:message code="survey.income.label" default="Income" />
		
	</label>
	<g:textField name="income" value="${surveyInstance?.income}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: surveyInstance, field: 'job', 'error')} ">
	<label for="job">
		<g:message code="survey.job.label" default="Job" />
		
	</label>
	<g:textField name="job" value="${surveyInstance?.job}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: surveyInstance, field: 'zipcode', 'error')} ">
	<label for="zipcode">
		<g:message code="survey.zipcode.label" default="Zipcode" />
		
	</label>
	<g:textField name="zipcode" value="${surveyInstance?.zipcode}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: surveyInstance, field: 'kids', 'error')} ">
	<label for="kids">
		<g:message code="survey.kids.label" default="Kids" />
		
	</label>
	<g:textField name="kids" value="${surveyInstance?.kids}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: surveyInstance, field: 'kidsAge', 'error')} ">
	<label for="kidsAge">
		<g:message code="survey.kidsAge.label" default="Kids Age" />
		
	</label>
	<g:textField name="kidsAge" value="${surveyInstance?.kidsAge}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: surveyInstance, field: 'weeklySpend', 'error')} ">
	<label for="weeklySpend">
		<g:message code="survey.weeklySpend.label" default="Weekly Spend" />
		
	</label>
	<g:textField name="weeklySpend" value="${surveyInstance?.weeklySpend}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: surveyInstance, field: 'kidsSchedule', 'error')} ">
	<label for="kidsSchedule">
		<g:message code="survey.kidsSchedule.label" default="Kids Schedule" />
		
	</label>
	<g:textField name="kidsSchedule" value="${surveyInstance?.kidsSchedule}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: surveyInstance, field: 'outdoorActivity', 'error')} ">
	<label for="outdoorActivity">
		<g:message code="survey.outdoorActivity.label" default="Outdoor Activity" />
		
	</label>
	<g:textField name="outdoorActivity" value="${surveyInstance?.outdoorActivity}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: surveyInstance, field: 'percentSpendOnActivity', 'error')} ">
	<label for="percentSpendOnActivity">
		<g:message code="survey.percentSpendOnActivity.label" default="Percent Spend On Activity" />
		
	</label>
	<g:textField name="percentSpendOnActivity" value="${surveyInstance?.percentSpendOnActivity}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: surveyInstance, field: 'activityCategory', 'error')} ">
	<label for="activityCategory">
		<g:message code="survey.activityCategory.label" default="Activity Category" />
		
	</label>
	<g:textField name="activityCategory" value="${surveyInstance?.activityCategory}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: surveyInstance, field: 'activityFrom', 'error')} ">
	<label for="activityFrom">
		<g:message code="survey.activityFrom.label" default="Activity From" />
		
	</label>
	<g:textField name="activityFrom" value="${surveyInstance?.activityFrom}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: surveyInstance, field: 'usedSmartWatch', 'error')} ">
	<label for="usedSmartWatch">
		<g:message code="survey.usedSmartWatch.label" default="Used Smart Watch" />
		
	</label>
	<g:textField name="usedSmartWatch" value="${surveyInstance?.usedSmartWatch}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: surveyInstance, field: 'whatKindOfWatch', 'error')} ">
	<label for="whatKindOfWatch">
		<g:message code="survey.whatKindOfWatch.label" default="What Kind Of Watch" />
		
	</label>
	<g:textField name="whatKindOfWatch" value="${surveyInstance?.whatKindOfWatch}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: surveyInstance, field: 'smartWatchFunction', 'error')} ">
	<label for="smartWatchFunction">
		<g:message code="survey.smartWatchFunction.label" default="Smart Watch Function" />
		
	</label>
	<g:textField name="smartWatchFunction" value="${surveyInstance?.smartWatchFunction}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: surveyInstance, field: 'mustHaveFunction', 'error')} ">
	<label for="mustHaveFunction">
		<g:message code="survey.mustHaveFunction.label" default="Must Have Function" />
		
	</label>
	<g:textField name="mustHaveFunction" value="${surveyInstance?.mustHaveFunction}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: surveyInstance, field: 'smartWatchPrefer', 'error')} ">
	<label for="smartWatchPrefer">
		<g:message code="survey.smartWatchPrefer.label" default="Smart Watch Prefer" />
		
	</label>
	<g:textField name="smartWatchPrefer" value="${surveyInstance?.smartWatchPrefer}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: surveyInstance, field: 'whereToBuySmartWatchOther', 'error')} ">
	<label for="whereToBuySmartWatchOther">
		<g:message code="survey.whereToBuySmartWatchOther.label" default="Where To Buy Smart Watch Other" />
		
	</label>
	<g:textField name="whereToBuySmartWatchOther" value="${surveyInstance?.whereToBuySmartWatchOther}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: surveyInstance, field: 'buyOrNot', 'error')} ">
	<label for="buyOrNot">
		<g:message code="survey.buyOrNot.label" default="Buy Or Not" />
		
	</label>
	<g:textField name="buyOrNot" value="${surveyInstance?.buyOrNot}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: surveyInstance, field: 'wantToHaveFunction', 'error')} ">
	<label for="wantToHaveFunction">
		<g:message code="survey.wantToHaveFunction.label" default="Want To Have Function" />
		
	</label>
	<g:textField name="wantToHaveFunction" value="${surveyInstance?.wantToHaveFunction}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: surveyInstance, field: 'whereToBuySmartWatch', 'error')} ">
	<label for="whereToBuySmartWatch">
		<g:message code="survey.whereToBuySmartWatch.label" default="Where To Buy Smart Watch" />
		
	</label>
	<g:textField name="whereToBuySmartWatch" value="${surveyInstance?.whereToBuySmartWatch}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: surveyInstance, field: 'improve', 'error')} ">
	<label for="improve">
		<g:message code="survey.improve.label" default="Improve" />
		
	</label>
	<g:textField name="improve" value="${surveyInstance?.improve}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: surveyInstance, field: 'idea', 'error')} ">
	<label for="idea">
		<g:message code="survey.idea.label" default="Idea" />
		
	</label>
	<g:textField name="idea" value="${surveyInstance?.idea}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: surveyInstance, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="survey.email.label" default="Email" />
		
	</label>
	<g:textField name="email" value="${surveyInstance?.email}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: surveyInstance, field: 'ip', 'error')} ">
	<label for="ip">
		<g:message code="survey.ip.label" default="Ip" />
		
	</label>
	<g:textField name="ip" value="${surveyInstance?.ip}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: surveyInstance, field: 'smartWatchExpense', 'error')} required">
	<label for="smartWatchExpense">
		<g:message code="survey.smartWatchExpense.label" default="Smart Watch Expense" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="smartWatchExpense" required="" value="${surveyInstance?.smartWatchExpense}"/>

</div>

