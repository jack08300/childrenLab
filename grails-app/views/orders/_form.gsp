<%@ page import="childrenlab.Orders" %>



<div class="fieldcontain ${hasErrors(bean: ordersInstance, field: 'orderId', 'error')} required">
	<label for="orderId">
		<g:message code="orders.orderId.label" default="Order Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="orderId" required="" value="${ordersInstance?.orderId}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: ordersInstance, field: 'stripe', 'error')} required">
	<label for="stripe">
		<g:message code="orders.stripe.label" default="Stripe" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="stripe" name="stripe.id" from="${childrenlab.Stripe.list()}" optionKey="id" required="" value="${ordersInstance?.stripe?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: ordersInstance, field: 'product', 'error')} required">
	<label for="product">
		<g:message code="orders.product.label" default="Product" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="product" required="" value="${ordersInstance?.product}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: ordersInstance, field: 'charge', 'error')} required">
	<label for="charge">
		<g:message code="orders.charge.label" default="Charge" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="charge" value="${fieldValue(bean: ordersInstance, field: 'charge')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: ordersInstance, field: 'amount', 'error')} required">
	<label for="amount">
		<g:message code="orders.amount.label" default="Amount" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="amount" type="number" value="${ordersInstance.amount}" required=""/>

</div>

