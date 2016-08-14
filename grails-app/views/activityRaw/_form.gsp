<%@ page import="childrenlab.ActivityRaw" %>



<div class="fieldcontain ${hasErrors(bean: activityRawInstance, field: 'device', 'error')} required">
	<label for="device">
		<g:message code="activityRaw.device.label" default="Device" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="device" name="device.id" from="${childrenlab.Device.list()}" optionKey="id" required="" value="${activityRawInstance?.device?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: activityRawInstance, field: 'indoorActivity', 'error')} required">
	<label for="indoorActivity">
		<g:message code="activityRaw.indoorActivity.label" default="Indoor Activity" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="indoorActivity" required="" value="${activityRawInstance?.indoorActivity}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: activityRawInstance, field: 'outdoorActivity', 'error')} required">
	<label for="outdoorActivity">
		<g:message code="activityRaw.outdoorActivity.label" default="Outdoor Activity" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="outdoorActivity" required="" value="${activityRawInstance?.outdoorActivity}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: activityRawInstance, field: 'time', 'error')} required">
	<label for="time">
		<g:message code="activityRaw.time.label" default="Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="time" type="number" value="${activityRawInstance.time}" required=""/>

</div>

