<%@ page import="java.text.SimpleDateFormat; java.text.DateFormat" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Swing Backend</title>
    <style type="text/css" media="screen">

    #swingLogo {
        font-size: 25px;
        padding: 15px;
        font-weight: bold;
        background-color: #ABBF78;
    }
    </style>
</head>

<body>

<div class="nav" role="navigation">
    <ul>
        <li><g:link controller="home" class="home">Menu</g:link></li>
        <li><g:link controller="device" action="list" class="back">list</g:link></li>
        <li><g:link controller="device" action="export" class="export" params="[macId: macId]">Download CSV</g:link></li>
    </ul>
</div>

<table>
    <thead>
    <tr>

        <g:sortableColumn property="steps" title="${message(code: 'activity.calories.label', default: 'Steps')}" />

        <g:sortableColumn property="type" title="${message(code: 'activity.receivedTime.label', default: 'Type')}" />

        <g:sortableColumn property="receivedTime" title="${message(code: 'activity.receivedTime.label', default: 'Received Time')}" />

    </tr>
    </thead>
    <tbody>


    <g:each in="${data}" status="i" var="activityInstance">
        <%
            def testTime = activityInstance.receivedTime;
            java.text.DateFormat formatter= new SimpleDateFormat("YYYY/MM/dd kk:mm:ss Z");
//            formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        %>

        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

            <td>${fieldValue(bean: activityInstance, field: "steps")}</td>
            <td>${activityInstance.type.name()}</td>
            <td>${formatter.format(testTime)}</td>

        </tr>
    </g:each>
    </tbody>
</table>
<div class="pagination">
    <g:paginate total="${deviceActivityInstance ?: 0}" params="[macId: macId, userId: userId]" />
</div>
</body>
</html>
