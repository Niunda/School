<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
<h3>Grades list:</h3>
<c:url var="addBatton" value="/addjournal">
    <c:param name="studID" value="${flag}"/>
</c:url>
<table border="2">
    <tr>
        <th>Date</th>
        <th>Subject</th>
        <th>Grade</th>
        <th>Teacher</th>
        <th>Operations</th>
    </tr>

    <c:forEach var="emp" items="${allJournal}">
        <c:url var="updateBatton" value="/updatejournal">
            <c:param name="journalID" value="${emp.id}"/>
        </c:url>
        <c:url var="deleteBatton" value="/deletejournal">
            <c:param name="journalID" value="${emp.id}"/>
            <c:param name="studID" value="${flag}"/>
        </c:url>
        <tr>
            <td>${emp.date}</td>
            <td>${emp.subject}</td>
            <td>${emp.grade}</td>
            <td>${emp.teacher.surname}  ${emp.teacher.name}</td>
            <td>
                <input type="button" value="Update" onclick="window.location.href = '${updateBatton}'"/>
                <input type="button" value="Delete" onclick="if (confirm('Are you sure?')) window.location.href = '${deleteBatton}'"/>
            </td>
        </tr>
    </c:forEach>
</table><br>
<input type="button" value="Add" onclick="window.location.href = '${addBatton}'"/>
<c:url var="exitBatton" value="/"/>
<input type="button" value="Exit" onclick="window.location.href = ${exitBatton}"/>
</body>
</html>