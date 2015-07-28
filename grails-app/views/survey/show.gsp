
<%@ page import="childrenlab.Survey" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'survey.label', default: 'Survey')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
        <style type="text/css" media="screen">
        #swingLogo {
            font-size: 25px;
            padding: 15px;
            font-weight: bold;
            background-color: #ABBF78;
        }
        </style>
	</head>
	<body>
		<a href="#show-survey" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-survey" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list survey">
			
				<g:if test="${surveyInstance?.token}">
				<li class="fieldcontain">
					<span id="token-label" class="property-label"><g:message code="survey.token.label" default="Token" /></span>
					
						<span class="property-value" aria-labelledby="token-label"><g:fieldValue bean="${surveyInstance}" field="token"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${surveyInstance?.gender}">
				<li class="fieldcontain">
					<span id="gender-label" class="property-label"><g:message code="survey.gender.label" default="Gender" /></span>
					
						<span class="property-value" aria-labelledby="gender-label"><g:fieldValue bean="${surveyInstance}" field="gender"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${surveyInstance?.age}">
				<li class="fieldcontain">
					<span id="age-label" class="property-label"><g:message code="survey.age.label" default="Age" /></span>
					
						<span class="property-value" aria-labelledby="age-label"><g:fieldValue bean="${surveyInstance}" field="age"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${surveyInstance?.income}">
				<li class="fieldcontain">
					<span id="income-label" class="property-label"><g:message code="survey.income.label" default="Income" /></span>
					
						<span class="property-value" aria-labelledby="income-label"><g:fieldValue bean="${surveyInstance}" field="income"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${surveyInstance?.job}">
				<li class="fieldcontain">
					<span id="job-label" class="property-label"><g:message code="survey.job.label" default="Job" /></span>
					
						<span class="property-value" aria-labelledby="job-label"><g:fieldValue bean="${surveyInstance}" field="job"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${surveyInstance?.zipcode}">
				<li class="fieldcontain">
					<span id="zipcode-label" class="property-label"><g:message code="survey.zipcode.label" default="Zipcode" /></span>
					
						<span class="property-value" aria-labelledby="zipcode-label"><g:fieldValue bean="${surveyInstance}" field="zipcode"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${surveyInstance?.kids}">
				<li class="fieldcontain">
					<span id="kids-label" class="property-label"><g:message code="survey.kids.label" default="Kids" /></span>
					
						<span class="property-value" aria-labelledby="kids-label"><g:fieldValue bean="${surveyInstance}" field="kids"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${surveyInstance?.kidsAge}">
				<li class="fieldcontain">
					<span id="kidsAge-label" class="property-label"><g:message code="survey.kidsAge.label" default="Kids Age" /></span>
					
						<span class="property-value" aria-labelledby="kidsAge-label"><g:fieldValue bean="${surveyInstance}" field="kidsAge"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${surveyInstance?.weeklySpend}">
				<li class="fieldcontain">
					<span id="weeklySpend-label" class="property-label"><g:message code="survey.weeklySpend.label" default="Weekly Spend" /></span>
					
						<span class="property-value" aria-labelledby="weeklySpend-label"><g:fieldValue bean="${surveyInstance}" field="weeklySpend"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${surveyInstance?.kidsSchedule}">
				<li class="fieldcontain">
					<span id="kidsSchedule-label" class="property-label"><g:message code="survey.kidsSchedule.label" default="Kids Schedule" /></span>
					
						<span class="property-value" aria-labelledby="kidsSchedule-label"><g:fieldValue bean="${surveyInstance}" field="kidsSchedule"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${surveyInstance?.outdoorActivity}">
				<li class="fieldcontain">
					<span id="outdoorActivity-label" class="property-label"><g:message code="survey.outdoorActivity.label" default="Outdoor Activity" /></span>
					
						<span class="property-value" aria-labelledby="outdoorActivity-label"><g:fieldValue bean="${surveyInstance}" field="outdoorActivity"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${surveyInstance?.percentSpendOnActivity}">
				<li class="fieldcontain">
					<span id="percentSpendOnActivity-label" class="property-label"><g:message code="survey.percentSpendOnActivity.label" default="Percent Spend On Activity" /></span>
					
						<span class="property-value" aria-labelledby="percentSpendOnActivity-label"><g:fieldValue bean="${surveyInstance}" field="percentSpendOnActivity"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${surveyInstance?.activityCategory}">
				<li class="fieldcontain">
					<span id="activityCategory-label" class="property-label"><g:message code="survey.activityCategory.label" default="Activity Category" /></span>
					
						<span class="property-value" aria-labelledby="activityCategory-label"><g:fieldValue bean="${surveyInstance}" field="activityCategory"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${surveyInstance?.activityFrom}">
				<li class="fieldcontain">
					<span id="activityFrom-label" class="property-label"><g:message code="survey.activityFrom.label" default="Activity From" /></span>
					
						<span class="property-value" aria-labelledby="activityFrom-label"><g:fieldValue bean="${surveyInstance}" field="activityFrom"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${surveyInstance?.usedSmartWatch}">
				<li class="fieldcontain">
					<span id="usedSmartWatch-label" class="property-label"><g:message code="survey.usedSmartWatch.label" default="Used Smart Watch" /></span>
					
						<span class="property-value" aria-labelledby="usedSmartWatch-label"><g:fieldValue bean="${surveyInstance}" field="usedSmartWatch"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${surveyInstance?.whatKindOfWatch}">
				<li class="fieldcontain">
					<span id="whatKindOfWatch-label" class="property-label"><g:message code="survey.whatKindOfWatch.label" default="What Kind Of Watch" /></span>
					
						<span class="property-value" aria-labelledby="whatKindOfWatch-label"><g:fieldValue bean="${surveyInstance}" field="whatKindOfWatch"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${surveyInstance?.smartWatchFunction}">
				<li class="fieldcontain">
					<span id="smartWatchFunction-label" class="property-label"><g:message code="survey.smartWatchFunction.label" default="Smart Watch Function" /></span>
					
						<span class="property-value" aria-labelledby="smartWatchFunction-label"><g:fieldValue bean="${surveyInstance}" field="smartWatchFunction"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${surveyInstance?.mustHaveFunction}">
				<li class="fieldcontain">
					<span id="mustHaveFunction-label" class="property-label"><g:message code="survey.mustHaveFunction.label" default="Must Have Function" /></span>
					
						<span class="property-value" aria-labelledby="mustHaveFunction-label"><g:fieldValue bean="${surveyInstance}" field="mustHaveFunction"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${surveyInstance?.smartWatchPrefer}">
				<li class="fieldcontain">
					<span id="smartWatchPrefer-label" class="property-label"><g:message code="survey.smartWatchPrefer.label" default="Smart Watch Prefer" /></span>
					
						<span class="property-value" aria-labelledby="smartWatchPrefer-label"><g:fieldValue bean="${surveyInstance}" field="smartWatchPrefer"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${surveyInstance?.whereToBuySmartWatchOther}">
				<li class="fieldcontain">
					<span id="whereToBuySmartWatchOther-label" class="property-label"><g:message code="survey.whereToBuySmartWatchOther.label" default="Where To Buy Smart Watch Other" /></span>
					
						<span class="property-value" aria-labelledby="whereToBuySmartWatchOther-label">${ new String(org.apache.commons.codec.binary.Base64.decodeBase64(surveyInstance.whereToBuySmartWatchOther), "UTF-8")}</span>
					
				</li>
				</g:if>
			
				<g:if test="${surveyInstance?.smartWatchExpense}">
				<li class="fieldcontain">
					<span id="smartWatchExpense-label" class="property-label"><g:message code="survey.smartWatchExpense.label" default="Smart Watch Expense" /></span>
					
						<span class="property-value" aria-labelledby="smartWatchExpense-label"><g:fieldValue bean="${surveyInstance}" field="smartWatchExpense"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${surveyInstance?.region}">
				<li class="fieldcontain">
					<span id="region-label" class="property-label"><g:message code="survey.region.label" default="Region" /></span>
					
						<span class="property-value" aria-labelledby="region-label"><g:fieldValue bean="${surveyInstance}" field="region"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${surveyInstance?.buyOrNot}">
				<li class="fieldcontain">
					<span id="buyOrNot-label" class="property-label"><g:message code="survey.buyOrNot.label" default="Buy Or Not" /></span>
					
						<span class="property-value" aria-labelledby="buyOrNot-label"><g:fieldValue bean="${surveyInstance}" field="buyOrNot"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${surveyInstance?.wantToHaveFunction}">
				<li class="fieldcontain">
					<span id="wantToHaveFunction-label" class="property-label"><g:message code="survey.wantToHaveFunction.label" default="Want To Have Function" /></span>
					
						<span class="property-value" aria-labelledby="wantToHaveFunction-label"><g:fieldValue bean="${surveyInstance}" field="wantToHaveFunction"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${surveyInstance?.whereToBuySmartWatch}">
				<li class="fieldcontain">
					<span id="whereToBuySmartWatch-label" class="property-label"><g:message code="survey.whereToBuySmartWatch.label" default="Where To Buy Smart Watch" /></span>
					
						<span class="property-value" aria-labelledby="whereToBuySmartWatch-label"><g:fieldValue bean="${surveyInstance}" field="whereToBuySmartWatch"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${surveyInstance?.improve}">
				<li class="fieldcontain">
					<span id="improve-label" class="property-label"><g:message code="survey.improve.label" default="Improve" /></span>
					
						<span class="property-value" aria-labelledby="improve-label"><g:fieldValue bean="${surveyInstance}" field="improve"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${surveyInstance?.idea}">
				<li class="fieldcontain">
					<span id="idea-label" class="property-label"><g:message code="survey.idea.label" default="Idea" /></span>

						<span class="property-value" aria-labelledby="idea-label">${ new String(org.apache.commons.codec.binary.Base64.decodeBase64(surveyInstance.idea), "UTF-8")}</span>
					
				</li>
				</g:if>
			
				<g:if test="${surveyInstance?.email}">
				<li class="fieldcontain">
					<span id="email-label" class="property-label"><g:message code="survey.email.label" default="Email" /></span>
					
						<span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${surveyInstance}" field="email"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${surveyInstance?.ip}">
				<li class="fieldcontain">
					<span id="ip-label" class="property-label"><g:message code="survey.ip.label" default="Ip" /></span>
					
						<span class="property-value" aria-labelledby="ip-label"><g:fieldValue bean="${surveyInstance}" field="ip"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${surveyInstance?.resourcePage}">
				<li class="fieldcontain">
					<span id="resourcePage-label" class="property-label"><g:message code="survey.resourcePage.label" default="Resource Page" /></span>
					
						<span class="property-value" aria-labelledby="resourcePage-label"><g:fieldValue bean="${surveyInstance}" field="resourcePage"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${surveyInstance?.completed}">
				<li class="fieldcontain">
					<span id="completed-label" class="property-label"><g:message code="survey.completed.label" default="Completed" /></span>
					
						<span class="property-value" aria-labelledby="completed-label"><g:formatBoolean boolean="${surveyInstance?.completed}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${surveyInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="survey.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${surveyInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:surveyInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${surveyInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
