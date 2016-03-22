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
        <g:sortableColumn property="id" title="User Id" />
        <g:sortableColumn property="email" title="User Email" />
        <g:sortableColumn property="message" title="Message" />
        <g:sortableColumn property="push" title="Push" />
    </tr>
    </thead>
    <tbody>
    <g:each in="${users}" status="i" var="userInstance">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

            <td>${fieldValue(bean: userInstance, field: "id")}</td>
            <td>${fieldValue(bean: userInstance, field: "email")}</td>
            <g:form controller="pushNotification" action="push">
                <input type="hidden" value="${userInstance.registrationId}" name="token"/>
                <td><g:textArea name="message"/> </td>
                <td><g:submitButton name="submit" /> </td>
            </g:form>

        </tr>
    </g:each>
    </tbody>
</table>


</body>
</html>
