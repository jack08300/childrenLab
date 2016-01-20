
<%@ page import="childrenlab.Orders" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'orders.label', default: 'Orders')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-orders" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-orders" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="orderId" title="${message(code: 'orders.orderId.label', default: 'Order Id')}" />
					
						<th><g:message code="orders.stripe.label" default="Email" /></th>
					
						<g:sortableColumn property="product" title="${message(code: 'orders.product.label', default: 'Product')}" />
					
						<g:sortableColumn property="charge" title="${message(code: 'orders.charge.label', default: 'Estimate <br/> charge')}" />
					
						<g:sortableColumn property="amount" title="${message(code: 'orders.amount.label', default: 'Amount')}" />
					
						<g:sortableColumn property="charged" title="${message(code: 'orders.charged.label', default: 'Charged')}" />

						<g:sortableColumn property="dateCreated" title="${message(code: 'orders.dateCreated.label', default: 'Order Date')}" />

						<g:sortableColumn property="charged" title="${message(code: 'orders.charged.label', default: 'Place Order')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${ordersInstanceList}" status="i" var="ordersInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${ordersInstance.id}">${fieldValue(bean: ordersInstance, field: "orderId")}</g:link></td>
					
						<td>${fieldValue(bean: ordersInstance, field: "stripe.email")}</td>
					
						<td>${fieldValue(bean: ordersInstance, field: "product")}</td>
					
						<td>${fieldValue(bean: ordersInstance, field: "charge")}</td>
					
						<td>${fieldValue(bean: ordersInstance, field: "amount")}</td>


						<td><g:formatBoolean boolean="${ordersInstance.charged}" /></td>

						<td>${fieldValue(bean: ordersInstance, field: "dateCreated")}</td>

						<g:if test="${!ordersInstance.charged}">
							<td><g:link controller="stripe" action="charge" params="[orderId: ordersInstance.orderId]">Charge</g:link></td>
						</g:if>

					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${ordersInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
