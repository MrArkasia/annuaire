<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:url var="edit" value="/actions/product/edit" />

<html>
<head>
<title>Hello :: Spring Application</title>
</head>
<body>
    <h1>Products</h1>
    <table border='1'>
    <c:forEach items="${products}" var="prod">
        <tr>
        <td><a href="${edit}?id=${prod.number}"><c:out value="${prod.name}" /></a></td>
        <td><i>$<c:out value="${prod.price}" /></i></td>
        </tr>
    </c:forEach>
    </table>
    <p><a href="${edit}">Create new product</a></p>
</body>
</html>