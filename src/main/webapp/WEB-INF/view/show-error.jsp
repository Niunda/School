<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
<h3>Error!</h3>
<br>
Error message: ${error.message}
<br>
<c:url var="exitBatton" value="/"/>
<input type="button" value="To menu" onclick="window.location.href = ${exitBatton}"/>
</body>
</html>