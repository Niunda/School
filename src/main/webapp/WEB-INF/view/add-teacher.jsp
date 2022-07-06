<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<body>
<h3>Enter teacher information</h3><br>
<form:form action="saveteacher" modelAttribute="teacher">
    <form:hidden path="id"/>
    Surname <form:input path="surname"/><form:errors path="surname"/> <br>
    Name <form:input path="name"/><form:errors path="name"/><br>
    Subject <form:input path="subject"/><form:errors path="subject"/><br>
    Cabinet <form:input path="cabinet"/><br>
    <br>
    <input type="submit" value="OK"/>
</form:form>
</body>
</html>