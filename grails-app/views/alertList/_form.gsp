<%@ page import="childrenlab.AlertList" %>

<div class="fieldcontain ${hasErrors(bean: alertListInstance, field: 'alertId', 'error')} required">
	<label for="alertId">
		<g:message code="alertList.alertId.label" default="Alert Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="alertId" type="number" value="${alertListInstance.alertId}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: alertListInstance, field: 'english', 'error')} required">
	<label for="english">
		<g:message code="alertList.english.label" default="English" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="english" required="" value="${alertListInstance?.english}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: alertListInstance, field: 'spanish', 'error')} ">
	<label for="spanish">
		<g:message code="alertList.spanish.label" default="Spanish" />
		
	</label>
	<g:textField name="spanish" value="${alertListInstance?.spanish}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: alertListInstance, field: 'russian', 'error')} ">
	<label for="russian">
		<g:message code="alertList.russian.label" default="Russian" />
		
	</label>
	<g:textField name="russian" value="${alertListInstance?.russian}"/>

</div>



