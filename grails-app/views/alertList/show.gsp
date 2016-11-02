
<%@ page import="childrenlab.AlertList" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'alertList.label', default: 'AlertList')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-alertList" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-alertList" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list alertList">


				<g:if test="${alertListInstance?.alertId}">
					<li class="fieldcontain">
						<span id="alertId-label" class="property-label"><g:message code="alertList.alertId.label" default="Alert Id" /></span>

						<span class="property-value" aria-labelledby="alertId-label"><g:fieldValue bean="${alertListInstance}" field="alertId"/></span>

					</li>
				</g:if>

				<g:if test="${alertListInstance?.english}">
					<li class="fieldcontain">
						<span id="english-label" class="property-label"><g:message code="alertList.english.label" default="English" /></span>

						<span class="property-value" aria-labelledby="english-label"><g:fieldValue bean="${alertListInstance}" field="english"/></span>

					</li>
				</g:if>

				<g:if test="${alertListInstance?.spanish}">
				<li class="fieldcontain">
					<span id="spanish-label" class="property-label"><g:message code="alertList.spanish.label" default="Spanish" /></span>
					
						<span class="property-value" aria-labelledby="spanish-label"><g:fieldValue bean="${alertListInstance}" field="spanish"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${alertListInstance?.russian}">
				<li class="fieldcontain">
					<span id="russian-label" class="property-label"><g:message code="alertList.russian.label" default="Russian" /></span>
					
						<span class="property-value" aria-labelledby="russian-label"><g:fieldValue bean="${alertListInstance}" field="russian"/></span>
					
				</li>
				</g:if>

			
			</ol>
			<g:form url="[resource:alertListInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${alertListInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
