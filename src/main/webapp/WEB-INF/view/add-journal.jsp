<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
<h3>Enter grade information</h3><br>
<form:form action="savejournal" modelAttribute="journal" >
    <form:hidden path="id"/>
    <form:hidden path="studentID"/>
    Date (dd/MM/yyyy) <form:input path="date"/><form:errors path="date"/> <br>
    Subject <form:input path="subject"/><form:errors path="subject"/> <br>
    Grade <form:input path="grade"/><form:errors path="grade"/> <br>
    Teacher
    <form:select path="teacherID" >
        <c:forEach var="emp" items="${allTeacher}">
            <form:option value="${emp.id}" label="${emp.surname} ${emp.name}"/>
        </c:forEach>
    </form:select>
    <br>
    <input type="submit" value="OK"/>
</form:form>
</body>
</html>