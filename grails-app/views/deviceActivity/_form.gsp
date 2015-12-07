<%@ page import="childrenlab.DeviceActivity" %>



<div class="fieldcontain ${hasErrors(bean: deviceActivityInstance, field: 'activityX', 'error')} ">
	<label for="activityX">
		<g:message code="deviceActivity.activityX.label" default="Activity X" />
		
	</label>
	<g:textField name="activityX" value="${deviceActivityInstance?.activityX}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: deviceActivityInstance, field: 'activityY', 'error')} ">
	<label for="activityY">
		<g:message code="deviceActivity.activityY.label" default="Activity Y" />
		
	</label>
	<g:textField name="activityY" value="${deviceActivityInstance?.activityY}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: deviceActivityInstance, field: 'activityZ', 'error')} ">
	<label for="activityZ">
		<g:message code="deviceActivity.activityZ.label" default="Activity Z" />
		
	</label>
	<g:textField name="activityZ" value="${deviceActivityInstance?.activityZ}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: deviceActivityInstance, field: 'light', 'error')} ">
	<label for="light">
		<g:message code="deviceActivity.light.label" default="Light" />
		
	</label>
	<g:textField name="light" value="${deviceActivityInstance?.light}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: deviceActivityInstance, field: 'audio', 'error')} ">
	<label for="audio">
		<g:message code="deviceActivity.audio.label" default="Audio" />
		
	</label>
	<g:textField name="audio" value="${deviceActivityInstance?.audio}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: deviceActivityInstance, field: 'uv', 'error')} ">
	<label for="uv">
		<g:message code="deviceActivity.uv.label" default="Uv" />
		
	</label>
	<g:textField name="uv" value="${deviceActivityInstance?.uv}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: deviceActivityInstance, field: 'device', 'error')} required">
	<label for="device">
		<g:message code="deviceActivity.device.label" default="Device" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="device" name="device.id" from="${childrenlab.Device.list()}" optionKey="id" required="" value="${deviceActivityInstance?.device?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: deviceActivityInstance, field: 'temperature', 'error')} ">
	<label for="temperature">
		<g:message code="deviceActivity.temperature.label" default="Temperature" />
		
	</label>
	<g:textField name="temperature" value="${deviceActivityInstance?.temperature}"/>

</div>

