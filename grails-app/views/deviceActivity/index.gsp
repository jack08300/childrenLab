
<%@ page import="childrenlab.DeviceActivity" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'deviceActivity.label', default: 'DeviceActivity')}" />
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
		<a href="#list-deviceActivity" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-deviceActivity" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="activityX" title="${message(code: 'deviceActivity.activityX.label', default: 'Activity X')}" />
					
						<g:sortableColumn property="activityY" title="${message(code: 'deviceActivity.activityY.label', default: 'Activity Y')}" />
					
						<g:sortableColumn property="activityZ" title="${message(code: 'deviceActivity.activityZ.label', default: 'Activity Z')}" />
					
						<g:sortableColumn property="light" title="${message(code: 'deviceActivity.light.label', default: 'Light')}" />
					
						<g:sortableColumn property="uv" title="${message(code: 'deviceActivity.uv.label', default: 'Uv')}" />

						<g:sortableColumn property="dateCreated" title="${message(code: 'deviceActivity.dateCreated.label', default: 'Date')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${deviceActivityInstanceList}" status="i" var="deviceActivityInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${deviceActivityInstance.id}">${fieldValue(bean: deviceActivityInstance, field: "activityX")}</g:link></td>
					
						<td>${fieldValue(bean: deviceActivityInstance, field: "activityY")}</td>
					
						<td>${fieldValue(bean: deviceActivityInstance, field: "activityZ")}</td>
					
						<td>${fieldValue(bean: deviceActivityInstance, field: "light")}</td>
					
						<td>${fieldValue(bean: deviceActivityInstance, field: "uv")}</td>

						<td>${fieldValue(bean: deviceActivityInstance, field: "dateCreated")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${deviceActivityInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
