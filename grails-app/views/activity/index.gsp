
<%@ page import="childrenlab.Activity" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'activity.label', default: 'Activity')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-activity" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-activity" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="calories" title="${message(code: 'activity.calories.label', default: 'Calories')}" />
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'activity.dateCreated.label', default: 'Date Created')}" />
					
						<th><g:message code="activity.device.label" default="Device" /></th>
					
						<g:sortableColumn property="distance" title="${message(code: 'activity.distance.label', default: 'Distance')}" />
					
						<g:sortableColumn property="lastUpdated" title="${message(code: 'activity.lastUpdated.label', default: 'Last Updated')}" />
					
						<g:sortableColumn property="receivedTime" title="${message(code: 'activity.receivedTime.label', default: 'Received Time')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${activityInstanceList}" status="i" var="activityInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${activityInstance.id}">${fieldValue(bean: activityInstance, field: "calories")}</g:link></td>
					
						<td><g:formatDate date="${activityInstance.dateCreated}" /></td>
					
						<td>${fieldValue(bean: activityInstance, field: "device")}</td>
					
						<td>${fieldValue(bean: activityInstance, field: "distance")}</td>
					
						<td><g:formatDate date="${activityInstance.lastUpdated}" /></td>
					
						<td>${fieldValue(bean: activityInstance, field: "receivedTime")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${activityInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
