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
        <li>
            <g:form controller="device" action="createSampleData">
                Email: <input type="email" name="email" />
                MacId: <input type="text" name="macId"/>
                <g:submitButton name="submit" value="Create Sample Data"/>
            </g:form>
        </li>
    </ul>
</div>
<table>
    <thead>
    <tr>
        <th>Data</th>
        <th>Raw Data</th>
        <g:sortableColumn property="macId" title="Mac ID" />
        <g:sortableColumn property="user.email" title="User Email" />
        <g:sortableColumn property="delete" title="Delete" />

    </tr>
    </thead>
    <tbody>
    <g:each in="${deviceList}" status="i" var="listInstance">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
            <td>
                <g:link action="deviceDataList" params="[macId: listInstance.macId, userId: listInstance.user ? listInstance.user.id : null]">Data List (${childrenlab.Activity.countByDevice(listInstance)})</g:link>

            </td>
            <td>
                <g:link action="deviceDataRawList" params="[macId: listInstance.macId, userId: listInstance.user ? listInstance.user.id : null]">Raw Data List (${childrenlab.ActivityRaw.countByDevice(listInstance)})</g:link>

            </td>
            <td>
                ${fieldValue(bean: listInstance, field: "macId")}

            </td>
            <td>${fieldValue(bean: listInstance, field: "user.email")}</td>
            <td><g:link action="deleteByDevice" params="[macId: listInstance.macId, email: listInstance.user ? listInstance.user.email : null]">Delete</g:link></td>
        </tr>
    </g:each>
    </tbody>
</table>

</body>
</html>
