<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
<h3>Students list:</h3>
<c:url var="addBatton" value="/addstudent">
    <c:param name="classID" value="${flag}"/>
</c:url>
<table border="2">
    <tr>
        <th>Surname</th>
        <th>Name</th>
        <th>Class</th>
        <th>Operations</th>
    </tr>

    <c:forEach var="emp" items="${allStudent}">
        <c:url var="infoBatton" value="/journal">
            <c:param name="studID" value="${emp.id}"/>
        </c:url>
        <c:url var="updateBatton" value="/updatestudent">
            <c:param name="studentID" value="${emp.id}"/>
        </c:url>
        <c:url var="deleteBatton" value="/deletestudent">
            <c:param name="studentID" value="${emp.id}"/>
        </c:url>
        <tr>
            <td>${emp.surname}</td>
            <td>${emp.name}</td>
            <td>${emp.cl.number}</td>
            <td>
                <input type="button" value="Grades" onclick="window.location.href = '${infoBatton}'"/>
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