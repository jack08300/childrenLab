<%@ page import="childrenlab.CalendarRepeatEvent" %>



<div class="fieldcontain ${hasErrors(bean: calendarRepeatEventInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="calendarRepeatEvent.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${calendarRepeatEventInstance?.description}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: calendarRepeatEventInstance, field: 'city', 'error')} ">
	<label for="city">
		<g:message code="calendarRepeatEvent.city.label" default="City" />
		
	</label>
	<g:textField name="city" value="${calendarRepeatEventInstance?.city}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: calendarRepeatEventInstance, field: 'state', 'error')} ">
	<label for="state">
		<g:message code="calendarRepeatEvent.state.label" default="State" />
		
	</label>
	<g:textField name="state" value="${calendarRepeatEventInstance?.state}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: calendarRepeatEventInstance, field: 'alert', 'error')} required">
	<label for="alert">
		<g:message code="calendarRepeatEvent.alert.label" default="Alert" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="alert" type="number" value="${calendarRepeatEventInstance.alert}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: calendarRepeatEventInstance, field: 'color', 'error')} required">
	<label for="color">
		<g:message code="calendarRepeatEvent.color.label" default="Color" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="color" required="" value="${calendarRepeatEventInstance?.color}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: calendarRepeatEventInstance, field: 'endDate', 'error')} required">
	<label for="endDate">
		<g:message code="calendarRepeatEvent.endDate.label" default="End Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="endDate" precision="day"  value="${calendarRepeatEventInstance?.endDate}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: calendarRepeatEventInstance, field: 'eventName', 'error')} required">
	<label for="eventName">
		<g:message code="calendarRepeatEvent.eventName.label" default="Event Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="eventName" required="" value="${calendarRepeatEventInstance?.eventName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: calendarRepeatEventInstance, field: 'startDate', 'error')} required">
	<label for="startDate">
		<g:message code="calendarRepeatEvent.startDate.label" default="Start Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="startDate" precision="day"  value="${calendarRepeatEventInstance?.startDate}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: calendarRepeatEventInstance, field: 'status', 'error')} required">
	<label for="status">
		<g:message code="calendarRepeatEvent.status.label" default="Status" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="status" from="${childrenlab.RepeatEventStatus?.values()}" keys="${childrenlab.RepeatEventStatus.values()*.name()}" required="" value="${calendarRepeatEventInstance?.status?.name()}" />

</div>

<div class="fieldcontain ${hasErrors(bean: calendarRepeatEventInstance, field: 'timezoneOffset', 'error')} required">
	<label for="timezoneOffset">
		<g:message code="calendarRepeatEvent.timezoneOffset.label" default="Timezone Offset" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="timezoneOffset" type="number" value="${calendarRepeatEventInstance.timezoneOffset}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: calendarRepeatEventInstance, field: 'todoList', 'error')} ">
	<label for="todoList">
		<g:message code="calendarRepeatEvent.todoList.label" default="Todo List" />
		
	</label>
	<g:select name="todoList" from="${childrenlab.TodoList.list()}" multiple="multiple" optionKey="id" size="5" value="${calendarRepeatEventInstance?.todoList*.id}" class="many-to-many"/>

</div>

<div class="fieldcontain ${hasErrors(bean: calendarRepeatEventInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="calendarRepeatEvent.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${childrenlab.User.list()}" optionKey="id" required="" value="${calendarRepeatEventInstance?.user?.id}" class="many-to-one"/>

</div>

