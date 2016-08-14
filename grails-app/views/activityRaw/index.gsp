
<%@ page import="childrenlab.ActivityRaw" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'activityRaw.label', default: 'ActivityRaw')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-activityRaw" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-activityRaw" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'activityRaw.dateCreated.label', default: 'Date Created')}" />
					
						<th><g:message code="activityRaw.device.label" default="Device" /></th>
					
						<g:sortableColumn property="indoorActivity" title="${message(code: 'activityRaw.indoorActivity.label', default: 'Indoor Activity')}" />
					
						<g:sortableColumn property="lastUpdated" title="${message(code: 'activityRaw.lastUpdated.label', default: 'Last Updated')}" />
					
						<g:sortableColumn property="outdoorActivity" title="${message(code: 'activityRaw.outdoorActivity.label', default: 'Outdoor Activity')}" />
					
						<g:sortableColumn property="time" title="${message(code: 'activityRaw.time.label', default: 'Time')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${activityRawInstanceList}" status="i" var="activityRawInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${activityRawInstance.id}">${fieldValue(bean: activityRawInstance, field: "dateCreated")}</g:link></td>
					
						<td>${fieldValue(bean: activityRawInstance, field: "device")}</td>
					
						<td>${fieldValue(bean: activityRawInstance, field: "indoorActivity")}</td>
					
						<td><g:formatDate date="${activityRawInstance.lastUpdated}" /></td>
					
						<td>${fieldValue(bean: activityRawInstance, field: "outdoorActivity")}</td>
					
						<td>${fieldValue(bean: activityRawInstance, field: "time")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${activityRawInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
