
<%@ page import="childrenlab.CalendarRepeatEvent" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'calendarRepeatEvent.label', default: 'CalendarRepeatEvent')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-calendarRepeatEvent" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-calendarRepeatEvent" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="description" title="${message(code: 'calendarRepeatEvent.description.label', default: 'Description')}" />
					
						<g:sortableColumn property="city" title="${message(code: 'calendarRepeatEvent.city.label', default: 'City')}" />
					
						<g:sortableColumn property="state" title="${message(code: 'calendarRepeatEvent.state.label', default: 'State')}" />
					
						<g:sortableColumn property="alert" title="${message(code: 'calendarRepeatEvent.alert.label', default: 'Alert')}" />
					
						<g:sortableColumn property="color" title="${message(code: 'calendarRepeatEvent.color.label', default: 'Color')}" />
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'calendarRepeatEvent.dateCreated.label', default: 'Date Created')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${calendarRepeatEventInstanceList}" status="i" var="calendarRepeatEventInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${calendarRepeatEventInstance.id}">${fieldValue(bean: calendarRepeatEventInstance, field: "description")}</g:link></td>
					
						<td>${fieldValue(bean: calendarRepeatEventInstance, field: "city")}</td>
					
						<td>${fieldValue(bean: calendarRepeatEventInstance, field: "state")}</td>
					
						<td>${fieldValue(bean: calendarRepeatEventInstance, field: "alert")}</td>
					
						<td>${fieldValue(bean: calendarRepeatEventInstance, field: "color")}</td>
					
						<td><g:formatDate date="${calendarRepeatEventInstance.dateCreated}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${calendarRepeatEventInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
