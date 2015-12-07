
<%@ page import="childrenlab.DeviceActivity" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'deviceActivity.label', default: 'DeviceActivity')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-deviceActivity" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-deviceActivity" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list deviceActivity">
			
				<g:if test="${deviceActivityInstance?.activityX}">
				<li class="fieldcontain">
					<span id="activityX-label" class="property-label"><g:message code="deviceActivity.activityX.label" default="Activity X" /></span>
					
						<span class="property-value" aria-labelledby="activityX-label"><g:fieldValue bean="${deviceActivityInstance}" field="activityX"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${deviceActivityInstance?.activityY}">
				<li class="fieldcontain">
					<span id="activityY-label" class="property-label"><g:message code="deviceActivity.activityY.label" default="Activity Y" /></span>
					
						<span class="property-value" aria-labelledby="activityY-label"><g:fieldValue bean="${deviceActivityInstance}" field="activityY"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${deviceActivityInstance?.activityZ}">
				<li class="fieldcontain">
					<span id="activityZ-label" class="property-label"><g:message code="deviceActivity.activityZ.label" default="Activity Z" /></span>
					
						<span class="property-value" aria-labelledby="activityZ-label"><g:fieldValue bean="${deviceActivityInstance}" field="activityZ"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${deviceActivityInstance?.light}">
				<li class="fieldcontain">
					<span id="light-label" class="property-label"><g:message code="deviceActivity.light.label" default="Light" /></span>
					
						<span class="property-value" aria-labelledby="light-label"><g:fieldValue bean="${deviceActivityInstance}" field="light"/></span>
					
				</li>
				</g:if>

				<g:if test="${deviceActivityInstance?.audio}">
				<li class="fieldcontain">
					<span id="audio-label" class="property-label"><g:message code="deviceActivity.audio.label" default="Audio" /></span>

						<span class="property-value" aria-labelledby="audio-label"><g:fieldValue bean="${deviceActivityInstance}" field="audio"/></span>

				</li>
				</g:if>
			
				<g:if test="${deviceActivityInstance?.uv}">
				<li class="fieldcontain">
					<span id="uv-label" class="property-label"><g:message code="deviceActivity.uv.label" default="Uv" /></span>
					
						<span class="property-value" aria-labelledby="uv-label"><g:fieldValue bean="${deviceActivityInstance}" field="uv"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${deviceActivityInstance?.device}">
				<li class="fieldcontain">
					<span id="device-label" class="property-label"><g:message code="deviceActivity.device.label" default="Device" /></span>
					
						<span class="property-value" aria-labelledby="device-label"><g:link controller="device" action="show" id="${deviceActivityInstance?.device?.id}">${deviceActivityInstance?.device?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${deviceActivityInstance?.temperature}">
				<li class="fieldcontain">
					<span id="temperature-label" class="property-label"><g:message code="deviceActivity.temperature.label" default="Temperature" /></span>
					
						<span class="property-value" aria-labelledby="temperature-label"><g:fieldValue bean="${deviceActivityInstance}" field="temperature"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${deviceActivityInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="deviceActivity.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${deviceActivityInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${deviceActivityInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="deviceActivity.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${deviceActivityInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:deviceActivityInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${deviceActivityInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
