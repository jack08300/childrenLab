
<%@ page import="childrenlab.Survey" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'survey.label', default: 'Survey')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
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
<a href="#list-survey" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link action="export">Export CSV</g:link></li>
    </ul>
</div>
<div id="list-survey" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>
            <g:sortableColumn property="email" title="${message(code: 'survey.gender.label', default: 'ID')}" />
            <g:sortableColumn property="completed" title="${message(code: 'survey.gender.label', default: 'Completed')}" />
            <g:sortableColumn property="region" title="${message(code: 'survey.gender.label', default: 'Region')}" />
            <g:sortableColumn property="email" title="${message(code: 'survey.gender.label', default: 'Email')}" />
            <g:sortableColumn property="gender" title="${message(code: 'survey.gender.label', default: 'Gender')}" />


            <g:sortableColumn property="age" title="${message(code: 'survey.age.label', default: 'Age')}" />

            <g:sortableColumn property="zipcode" title="${message(code: 'survey.zipcode.label', default: 'Zipcode')}" />

        </tr>
        </thead>
        <tbody>
        <g:each in="${surveyInstanceList}" status="i" var="surveyInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="show" id="${surveyInstance.id}">${fieldValue(bean: surveyInstance, field: "id")}</g:link></td>

                <td>${fieldValue(bean: surveyInstance, field: "completed")}</td>
                <td>${fieldValue(bean: surveyInstance, field: "region")}</td>
                <td><g:link action="show" id="${surveyInstance.id}">${fieldValue(bean: surveyInstance, field: "email")}</g:link></td>

                <td>${fieldValue(bean: surveyInstance, field: "gender")}</td>

                <td>${fieldValue(bean: surveyInstance, field: "age")}</td>

                <td>${fieldValue(bean: surveyInstance, field: "zipcode")}</td>

            </tr>
        </g:each>
        </tbody>
    </table>
    <div class="pagination">
        <g:paginate total="${surveyInstanceCount ?: 0}" />
    </div>
</div>
</body>
</html>
