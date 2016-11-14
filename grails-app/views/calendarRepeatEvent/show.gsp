
<%@ page import="childrenlab.CalendarRepeatEvent" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'calendarRepeatEvent.label', default: 'CalendarRepeatEvent')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-calendarRepeatEvent" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-calendarRepeatEvent" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list calendarRepeatEvent">
			
				<g:if test="${calendarRepeatEventInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="calendarRepeatEvent.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${calendarRepeatEventInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${calendarRepeatEventInstance?.city}">
				<li class="fieldcontain">
					<span id="city-label" class="property-label"><g:message code="calendarRepeatEvent.city.label" default="City" /></span>
					
						<span class="property-value" aria-labelledby="city-label"><g:fieldValue bean="${calendarRepeatEventInstance}" field="city"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${calendarRepeatEventInstance?.state}">
				<li class="fieldcontain">
					<span id="state-label" class="property-label"><g:message code="calendarRepeatEvent.state.label" default="State" /></span>
					
						<span class="property-value" aria-labelledby="state-label"><g:fieldValue bean="${calendarRepeatEventInstance}" field="state"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${calendarRepeatEventInstance?.alert}">
				<li class="fieldcontain">
					<span id="alert-label" class="property-label"><g:message code="calendarRepeatEvent.alert.label" default="Alert" /></span>
					
						<span class="property-value" aria-labelledby="alert-label"><g:fieldValue bean="${calendarRepeatEventInstance}" field="alert"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${calendarRepeatEventInstance?.color}">
				<li class="fieldcontain">
					<span id="color-label" class="property-label"><g:message code="calendarRepeatEvent.color.label" default="Color" /></span>
					
						<span class="property-value" aria-labelledby="color-label"><g:fieldValue bean="${calendarRepeatEventInstance}" field="color"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${calendarRepeatEventInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="calendarRepeatEvent.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${calendarRepeatEventInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${calendarRepeatEventInstance?.endDate}">
				<li class="fieldcontain">
					<span id="endDate-label" class="property-label"><g:message code="calendarRepeatEvent.endDate.label" default="End Date" /></span>
					
						<span class="property-value" aria-labelledby="endDate-label"><g:formatDate date="${calendarRepeatEventInstance?.endDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${calendarRepeatEventInstance?.eventName}">
				<li class="fieldcontain">
					<span id="eventName-label" class="property-label"><g:message code="calendarRepeatEvent.eventName.label" default="Event Name" /></span>
					
						<span class="property-value" aria-labelledby="eventName-label"><g:fieldValue bean="${calendarRepeatEventInstance}" field="eventName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${calendarRepeatEventInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="calendarRepeatEvent.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${calendarRepeatEventInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${calendarRepeatEventInstance?.startDate}">
				<li class="fieldcontain">
					<span id="startDate-label" class="property-label"><g:message code="calendarRepeatEvent.startDate.label" default="Start Date" /></span>
					
						<span class="property-value" aria-labelledby="startDate-label"><g:formatDate date="${calendarRepeatEventInstance?.startDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${calendarRepeatEventInstance?.status}">
				<li class="fieldcontain">
					<span id="status-label" class="property-label"><g:message code="calendarRepeatEvent.status.label" default="Status" /></span>
					
						<span class="property-value" aria-labelledby="status-label"><g:fieldValue bean="${calendarRepeatEventInstance}" field="status"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${calendarRepeatEventInstance?.timezoneOffset}">
				<li class="fieldcontain">
					<span id="timezoneOffset-label" class="property-label"><g:message code="calendarRepeatEvent.timezoneOffset.label" default="Timezone Offset" /></span>
					
						<span class="property-value" aria-labelledby="timezoneOffset-label"><g:fieldValue bean="${calendarRepeatEventInstance}" field="timezoneOffset"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${calendarRepeatEventInstance?.todoList}">
				<li class="fieldcontain">
					<span id="todoList-label" class="property-label"><g:message code="calendarRepeatEvent.todoList.label" default="Todo List" /></span>
					
						<g:each in="${calendarRepeatEventInstance.todoList}" var="t">
						<span class="property-value" aria-labelledby="todoList-label"><g:link controller="todoList" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${calendarRepeatEventInstance?.user}">
				<li class="fieldcontain">
					<span id="user-label" class="property-label"><g:message code="calendarRepeatEvent.user.label" default="User" /></span>
					
						<span class="property-value" aria-labelledby="user-label"><g:link controller="user" action="show" id="${calendarRepeatEventInstance?.user?.id}">${calendarRepeatEventInstance?.user?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:calendarRepeatEventInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${calendarRepeatEventInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
