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

<table>
    <thead>
    <tr>
        <g:sortableColumn property="activityX" title="Activity X" />
        <g:sortableColumn property="activityY" title="Activity Y" />
        <g:sortableColumn property="activityZ" title="Activity Z" />
        <g:sortableColumn property="audio" title="Audio" />
        <g:sortableColumn property="light" title="Light" />
        <g:sortableColumn property="uv" title="UV" />
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
            <td>${fieldValue(bean: listInstance, field: "dateCreated")}</td>
        </tr>
    </g:each>
    </tbody>
</table>

</body>
</html>
