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
        <g:sortableColumn property="id" title="id" />
        <g:sortableColumn property="activityX" title="Activity X" />
        <g:sortableColumn property="activityY" title="Activity Y" />
        <g:sortableColumn property="activityZ" title="Activity Z" />
        <g:sortableColumn property="u" title="U" />
        <g:sortableColumn property="v" title="V" />
        <g:sortableColumn property="date" title="Time" />

    </tr>
    </thead>
    <tbody>
    <g:each in="${data}" status="i" var="listInstance">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
            <td>${fieldValue(bean: listInstance, field: "id")}</td>
            <td>${fieldValue(bean: listInstance, field: "activityX")}</td>
            <td>${fieldValue(bean: listInstance, field: "activityY")}</td>
            <td>${fieldValue(bean: listInstance, field: "activityZ")}</td>
            <td>${fieldValue(bean: listInstance, field: "u")}</td>
            <td>${fieldValue(bean: listInstance, field: "v")}</td>
            <td>${new Date(listInstance.receivedTime*1000).format("YYYY/MM/dd HH:mm:ss")}</td>
        </tr>
    </g:each>
    </tbody>
</table>
<div class="pagination">
    <g:paginate total="${deviceActivityInstanceCount ?: 0}" params="[macId: macId]" />
</div>
</body>
</html>
