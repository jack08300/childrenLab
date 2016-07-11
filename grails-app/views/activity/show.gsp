
<%@ page import="childrenlab.Activity" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'activity.label', default: 'Activity')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-activity" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-activity" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list activity">
			
				<g:if test="${activityInstance?.calories}">
				<li class="fieldcontain">
					<span id="calories-label" class="property-label"><g:message code="activity.calories.label" default="Calories" /></span>
					
						<span class="property-value" aria-labelledby="calories-label"><g:fieldValue bean="${activityInstance}" field="calories"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${activityInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="activity.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${activityInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${activityInstance?.device}">
				<li class="fieldcontain">
					<span id="device-label" class="property-label"><g:message code="activity.device.label" default="Device" /></span>
					
						<span class="property-value" aria-labelledby="device-label"><g:link controller="device" action="show" id="${activityInstance?.device?.id}">${activityInstance?.device?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${activityInstance?.distance}">
				<li class="fieldcontain">
					<span id="distance-label" class="property-label"><g:message code="activity.distance.label" default="Distance" /></span>
					
						<span class="property-value" aria-labelledby="distance-label"><g:fieldValue bean="${activityInstance}" field="distance"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${activityInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="activity.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${activityInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${activityInstance?.receivedTime}">
				<li class="fieldcontain">
					<span id="receivedTime-label" class="property-label"><g:message code="activity.receivedTime.label" default="Received Time" /></span>
					
						<span class="property-value" aria-labelledby="receivedTime-label"><g:fieldValue bean="${activityInstance}" field="receivedTime"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${activityInstance?.steps}">
				<li class="fieldcontain">
					<span id="steps-label" class="property-label"><g:message code="activity.steps.label" default="Steps" /></span>
					
						<span class="property-value" aria-labelledby="steps-label"><g:fieldValue bean="${activityInstance}" field="steps"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:activityInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${activityInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
