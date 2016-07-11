<%@ page import="childrenlab.Activity" %>



<div class="fieldcontain ${hasErrors(bean: activityInstance, field: 'calories', 'error')} required">
	<label for="calories">
		<g:message code="activity.calories.label" default="Calories" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="calories" type="number" value="${activityInstance.calories}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: activityInstance, field: 'device', 'error')} required">
	<label for="device">
		<g:message code="activity.device.label" default="Device" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="device" name="device.id" from="${childrenlab.Device.list()}" optionKey="id" required="" value="${activityInstance?.device?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: activityInstance, field: 'distance', 'error')} required">
	<label for="distance">
		<g:message code="activity.distance.label" default="Distance" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="distance" type="number" value="${activityInstance.distance}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: activityInstance, field: 'receivedTime', 'error')} required">
	<label for="receivedTime">
		<g:message code="activity.receivedTime.label" default="Received Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="receivedTime" type="number" value="${activityInstance.receivedTime}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: activityInstance, field: 'steps', 'error')} required">
	<label for="steps">
		<g:message code="activity.steps.label" default="Steps" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="steps" type="number" value="${activityInstance.steps}" required=""/>

</div>

