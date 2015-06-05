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
    </ul>
</div>
<table>
    <thead>
    <tr>
        <g:sortableColumn property="macId" title="Mac ID" />
        <g:sortableColumn property="user.email" title="User Email" />

    </tr>
    </thead>
    <tbody>
    <g:each in="${deviceList}" status="i" var="listInstance">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

            <td><g:link action="deviceDataList" params="[macId: listInstance.macId]">${fieldValue(bean: listInstance, field: "macId")}</g:link></td>
            <td>${fieldValue(bean: listInstance, field: "user.email")}</td>
        </tr>
    </g:each>
    </tbody>
</table>

</body>
</html>
