
<%@ page import="childrenlab.AlertList" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'alertList.label', default: 'AlertList')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-alertList" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-alertList" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
						<g:sortableColumn property="alertId" title="${message(code: 'alertList.alertId.label', default: 'Alert Id')}" />

						<g:sortableColumn property="english" title="${message(code: 'alertList.english.label', default: 'English')}" />

						<g:sortableColumn property="spanish" title="${message(code: 'alertList.spanish.label', default: 'Spanish')}" />
					
						<g:sortableColumn property="russian" title="${message(code: 'alertList.russian.label', default: 'Russian')}" />
					

					
					</tr>
				</thead>
				<tbody>
				<g:each in="${alertListInstanceList}" status="i" var="alertListInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

						<td><g:link action="show" id="${alertListInstance.id}">${fieldValue(bean: alertListInstance, field: "alertId")}</g:link></td>

						<td>${fieldValue(bean: alertListInstance, field: "english")}</td>
					
						<td>${fieldValue(bean: alertListInstance, field: "spanish")}</td>
					
						<td>${fieldValue(bean: alertListInstance, field: "russian")}</td>
					

					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${alertListInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
