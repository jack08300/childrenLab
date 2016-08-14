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
        <g:sortableColumn property="options" title="Menu" />
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>
            <g:link controller="device" action="list">Swing Data</g:link>
        </td>
    </tr>
    <tr>
        <td>
            <g:link controller="user" action="feedbackList">Feedback Data</g:link>
        </td>
    </tr>
    <tr>
        <td>
            <g:link controller="survey" action="index">Survey</g:link>
        </td>
    </tr>
    <tr>
        <td>
            <g:link controller="calendarEvent" action="index">Calendar Event</g:link>
        </td>
    </tr>

    <tr>
        <td>
            <g:link controller="user" action="getPushList">Push Notification</g:link>
        </td>
    </tr>
    </tbody>
</table>

</body>
</html>
