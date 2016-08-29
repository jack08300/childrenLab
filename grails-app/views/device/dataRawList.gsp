<%@ page import="java.text.SimpleDateFormat" %>
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
        <li><g:link controller="device" action="exportRaw" class="export" params="[macId: macId]">Download CSV</g:link></li>
    </ul>
</div>

<table>
    <thead>
    <tr>
        <g:sortableColumn property="id" title="id" />
        <g:sortableColumn property="indoorActivity" title="Indoor" />
        <g:sortableColumn property="outdoorActivity" title="Outdoor" />
        <g:sortableColumn property="date" title="Time" />

    </tr>
    </thead>
    <tbody>
    <g:each in="${data}" status="i" var="listInstance">
        <%
            def testTime = listInstance.time;
            java.text.DateFormat formatter= new SimpleDateFormat("YYYY/MM/dd kk:mm:ss Z");
//            formatter.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
        %>
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
            <td>${fieldValue(bean: listInstance, field: "id")}</td>
            <td>${fieldValue(bean: listInstance, field: "indoorActivity")}</td>
            <td>${fieldValue(bean: listInstance, field: "outdoorActivity")}</td>
            <td>${formatter.format(testTime)}</td>
        </tr>
    </g:each>
    </tbody>
</table>
<div class="pagination">
    <g:paginate total="${deviceActivityInstanceCount ?: 0}" params="[macId: macId, userId: userId]" />
</div>
</body>
</html>