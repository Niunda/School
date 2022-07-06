<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
<h3>Enter student information</h3><br>
<form:form action="savestudent" modelAttribute="student" >
    <form:hidden path="id"/>
    Surname <form:input path="surname"/><form:errors path="surname"/> <br>
    Name <form:input path="name"/><form:errors path="name"/> <br>
    Class
    <form:select path="classID" >
        <c:forEach var="emp" items="${allClass}">
            <form:option value="${emp.id}" label="${emp.number}"/>
        </c:forEach>
    </form:select>
    <br>
    <input type="submit" value="OK"/>
</form:form>
</body>
</html>