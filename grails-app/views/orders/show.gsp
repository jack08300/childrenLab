
<%@ page import="childrenlab.Orders" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'orders.label', default: 'Orders')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-orders" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-orders" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list orders">
			
				<g:if test="${ordersInstance?.orderId}">
				<li class="fieldcontain">
					<span id="orderId-label" class="property-label"><g:message code="orders.orderId.label" default="Order Id" /></span>
					
						<span class="property-value" aria-labelledby="orderId-label"><g:fieldValue bean="${ordersInstance}" field="orderId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ordersInstance?.stripe}">
				<li class="fieldcontain">
					<span id="stripe-label" class="property-label"><g:message code="orders.stripe.label" default="Stripe" /></span>
					
						<span class="property-value" aria-labelledby="stripe-label"><g:link controller="stripe" action="show" id="${ordersInstance?.stripe?.id}">${ordersInstance?.stripe?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${ordersInstance?.product}">
				<li class="fieldcontain">
					<span id="product-label" class="property-label"><g:message code="orders.product.label" default="Product" /></span>
					
						<span class="property-value" aria-labelledby="product-label"><g:fieldValue bean="${ordersInstance}" field="product"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ordersInstance?.charge}">
				<li class="fieldcontain">
					<span id="charge-label" class="property-label"><g:message code="orders.charge.label" default="Charge" /></span>
					
						<span class="property-value" aria-labelledby="charge-label"><g:fieldValue bean="${ordersInstance}" field="charge"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ordersInstance?.amount}">
				<li class="fieldcontain">
					<span id="amount-label" class="property-label"><g:message code="orders.amount.label" default="Amount" /></span>
					
						<span class="property-value" aria-labelledby="amount-label"><g:fieldValue bean="${ordersInstance}" field="amount"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${ordersInstance?.charged}">
				<li class="fieldcontain">
					<span id="charged-label" class="property-label"><g:message code="orders.charged.label" default="Charged" /></span>
					
						<span class="property-value" aria-labelledby="charged-label"><g:formatBoolean boolean="${ordersInstance?.charged}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:ordersInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${ordersInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
