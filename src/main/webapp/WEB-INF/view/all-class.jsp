%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
<h3>Classes list:</h3>
<table border="2">
    <tr>
        <th>Class</th>
        <th>Number of students</th>
        <th>Classroom teacher</th>
        <th>Operations</th>
    </tr>

    <c:forEach var="emp" items="${allClass}">
        <c:url var="infoBatton" value="/classstudents">
            <c:param name="classID" value="${emp.id}"/>
        </c:url>
        <c:url var="updateBatton" value="/updateclass">
            <c:param name="classID" value="${emp.id}"/>
        </c:url>
        <c:url var="deleteBatton" value="/deleteclass">
            <c:param name="classID" value="${emp.id}"/>
        </c:url>
        <tr>
            <td>${emp.number}</td>
            <td>${emp.studentsCout}</td>
            <td>${emp.teacher.surname} ${emp.teacher.name}</td>
            <td>
                <input type="button" value="Students" onclick="window.location.href = '${infoBatton}'"/>
                <input type="button" value="Update" onclick="window.location.href = '${updateBatton}'"/>
                <input type="button" value="Delete" onclick="if (confirm('Are you sure?')) window.location.href = '${deleteBatton}'"/>
            </td>
        </tr>
    </c:forEach>
</table>
<input type="button" value="Add" onclick="window.location.href = 'addclass'"/>
<c:url var="exitBatton" value="/"/>
<input type="button" value="Exit" onclick="window.location.href = ${exitBatton}"/>
</body>
</html>