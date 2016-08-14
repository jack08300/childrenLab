
<%@ page import="childrenlab.ActivityRaw" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'activityRaw.label', default: 'ActivityRaw')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-activityRaw" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-activityRaw" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list activityRaw">
			
				<g:if test="${activityRawInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="activityRaw.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${activityRawInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${activityRawInstance?.device}">
				<li class="fieldcontain">
					<span id="device-label" class="property-label"><g:message code="activityRaw.device.label" default="Device" /></span>
					
						<span class="property-value" aria-labelledby="device-label"><g:link controller="device" action="show" id="${activityRawInstance?.device?.id}">${activityRawInstance?.device?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${activityRawInstance?.indoorActivity}">
				<li class="fieldcontain">
					<span id="indoorActivity-label" class="property-label"><g:message code="activityRaw.indoorActivity.label" default="Indoor Activity" /></span>
					
						<span class="property-value" aria-labelledby="indoorActivity-label"><g:fieldValue bean="${activityRawInstance}" field="indoorActivity"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${activityRawInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="activityRaw.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${activityRawInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${activityRawInstance?.outdoorActivity}">
				<li class="fieldcontain">
					<span id="outdoorActivity-label" class="property-label"><g:message code="activityRaw.outdoorActivity.label" default="Outdoor Activity" /></span>
					
						<span class="property-value" aria-labelledby="outdoorActivity-label"><g:fieldValue bean="${activityRawInstance}" field="outdoorActivity"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${activityRawInstance?.time}">
				<li class="fieldcontain">
					<span id="time-label" class="property-label"><g:message code="activityRaw.time.label" default="Time" /></span>
					
						<span class="property-value" aria-labelledby="time-label"><g:fieldValue bean="${activityRawInstance}" field="time"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:activityRawInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${activityRawInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
