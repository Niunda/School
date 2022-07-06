<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
<h3>Enter class information</h3><br>
<form:form action="saveclass" modelAttribute="class">
    <form:hidden path="id"/>
    <form:hidden path="studentsCout"/>
    Number <form:input path="number"/><form:errors path="number"/> <br>
    Classroom teacher
    <form:select path="teacherID">
        <c:forEach var="emp" items="${allTeacher}">
            <form:option value="${emp.id}" label="${emp.surname} ${emp.name}"/>
        </c:forEach>
    </form:select>
    <br>
    <input type="submit" value="OK"/>
</form:form>
</body>
</html>