<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<body>
<table border="2">
    <tr>
        <th>Menu</th>
    </tr>
    <tr>
        <td>
            <input type="button" style="height:50px;width:200px" value="Teachers"
                   onclick="window.location.href = '<c:url value="/teacher"/>'"/>
        </td>
    </tr>
    <tr>
        <td>
            <input type="button" style="height:50px;width:200px" value="Classes"
                   onclick="window.location.href = '<c:url value="/class"/>'"/>
        </td>
    </tr>
    <tr>
        <td>
            <input type="button" style="height:50px;width:200px" value="All Students"
                   onclick="window.location.href = '<c:url value="/student"/>'"/>
        </td>
    </tr>
</table>
</body>
</html>