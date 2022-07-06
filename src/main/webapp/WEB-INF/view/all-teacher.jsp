<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
<h3>Teachers list:</h3>
<table border="2">
    <tr>
        <th>Surname</th>
        <th>Name</th>
        <th>Subject</th>
        <th>Cabinet</th>
        <th>Operations</th>
    </tr>

    <c:forEach var="emp" items="${allTeacher}">
        <c:url var="updateBatton" value="/updateteacher">
            <c:param name="teacherID" value="${emp.id}"/>
        </c:url>
        <c:url var="deleteBatton" value="/deleteteacher">
            <c:param name="teacherID" value="${emp.id}"/>
        </c:url>
        <tr>
            <td>${emp.surname}</td>
            <td>${emp.name}</td>
            <td>${emp.subject}</td>
            <td>${emp.cabinet}</td>
            <td>
                <input type="button" value="Update" onclick="window.location.href = '${updateBatton}'"/>
                <input type="button" value="Delete"
                       onclick="if (confirm('Are you sure?')) window.location.href = '${deleteBatton}'"/>
            </td>
        </tr>
    </c:forEach>
</table>
<input type="button" value="Add" onclick="window.location.href = 'addteacher'"/>
<input type="button" value="Export report" onclick="window.location.href = 'report'"/>
<c:url var="exitBatton" value="/"/>
<input type="button" value="Exit" onclick="window.location.href = ${exitBatton}"/>
</body>
</html>