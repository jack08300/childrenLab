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
        <g:sortableColumn property="type" title="Type" />
        <g:sortableColumn property="text" title="Feedback" />
        <g:sortableColumn property="user.firstName" title="First Name" />
        <g:sortableColumn property="user.lastName" title="Last Name" />
        <g:sortableColumn property="user.email" title="Email" />
        <g:sortableColumn property="dateCreated" title="Date" />

    </tr>
    </thead>
    <tbody>
    <g:each in="${feedbackList}" status="i" var="listInstance">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

            <td>${fieldValue(bean: listInstance, field: "type")}</td>
            <td>${fieldValue(bean: listInstance, field: "text")}</td>
            <td>${fieldValue(bean: listInstance, field: "user.firstName")}</td>
            <td>${fieldValue(bean: listInstance, field: "user.lastName")}</td>
            <td>${fieldValue(bean: listInstance, field: "user.email")}</td>
            <td>${fieldValue(bean: listInstance, field: "dateCreated")}</td>
        </tr>
    </g:each>
    </tbody>
</table>

</body>
</html>
