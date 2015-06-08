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
    </ul>
</div>

<table>
    <thead>
    <tr>
        <g:sortableColumn property="activityX" title="Activity X" />
        <g:sortableColumn property="activityY" title="Activity Y" />
        <g:sortableColumn property="activityZ" title="Activity Z" />
        <g:sortableColumn property="audio" title="Audio" />
        <g:sortableColumn property="light" title="Light" />
        <g:sortableColumn property="uv" title="UV" />
        <g:sortableColumn property="temperature" title="Temperature" />
        <g:sortableColumn property="date" title="Upload Date" />


    </tr>
    </thead>
    <tbody>
    <g:each in="${data}" status="i" var="listInstance">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

            <td>${fieldValue(bean: listInstance, field: "activityX")}</td>
            <td>${fieldValue(bean: listInstance, field: "activityY")}</td>
            <td>${fieldValue(bean: listInstance, field: "activityZ")}</td>
            <td>${fieldValue(bean: listInstance, field: "audio")}</td>
            <td>${fieldValue(bean: listInstance, field: "light")}</td>
            <td>${fieldValue(bean: listInstance, field: "uv")}</td>
            <td>${fieldValue(bean: listInstance, field: "temperature")}</td>
            <td>${fieldValue(bean: listInstance, field: "dateCreated")}</td>
        </tr>
    </g:each>
    </tbody>
</table>

</body>
</html>
