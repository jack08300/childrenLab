
<%@ page import="childrenlab.CalendarEvent" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'calendarEvent.label', default: 'CalendarEvent')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
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
		<a href="#list-calendarEvent" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-calendarEvent" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
						<g:sortableColumn property="user" title="User" />
						<g:sortableColumn property="eventName" title="Event Name" />
						<g:sortableColumn property="color" title="Color" />
						<g:sortableColumn property="startDate" title="Start Date" />
						<g:sortableColumn property="endDate" title="End Date" />
						<g:sortableColumn property="status" title="Status" />
						<g:sortableColumn property="description" title="Description" />
						<g:sortableColumn property="timezoneOffset" title="TimeZone Offset" />
					</tr>
				</thead>
				<tbody>
				<g:each in="${calendarEventInstanceList}" status="i" var="calendarEventInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td><g:link action="show" id="${calendarEventInstance.id}">${fieldValue(bean: calendarEventInstance, field: "id")}</g:link></td>

						<td>${fieldValue(bean: calendarEventInstance, field: "eventName")}</td>
						<td>${fieldValue(bean: calendarEventInstance, field: "color")}</td>
						<td>${fieldValue(bean: calendarEventInstance, field: "startDate")}</td>
						<td>${fieldValue(bean: calendarEventInstance, field: "endDate")}</td>
						<td>${fieldValue(bean: calendarEventInstance, field: "status")}</td>
						<td>${fieldValue(bean: calendarEventInstance, field: "description")}</td>
						<td>${fieldValue(bean: calendarEventInstance, field: "timezoneOffset")}</td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${calendarEventInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
