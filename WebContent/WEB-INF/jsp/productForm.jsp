<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<style>
.error {
    color: #ff0000;
}
.errorblock{
    color: #000;
    background-color: #ffEEEE;
    border: 3px solid #ff0000;
    padding:8px;
    margin:16px;
}
</style>
</head>

<body>
<h1>Edit Product</h1>

<form:form method="POST" commandName="product">
    <form:errors path="*" cssClass="errorblock" element="div"/>
    <table>
    <tr>
        <td>Name : </td>
        <td><form:input path="name" /></td>
        <td><form:errors path="name" cssClass="error" /></td>
    </tr>
    <tr>
        <td>Description : </td>
        <td><form:textarea path="description" /></td>
        <td><form:errors path="description" cssClass="error" /></td>
    </tr>
    <tr>
        <td>price : </td>
        <td><form:input path="price" /></td>
        <td><form:errors path="price" cssClass="error" /></td>
    </tr>
    <tr>
	    <td>Type : </td>
	    <td>
	        <form:select path="type" multiple="false">
	            <form:option value="" label="--- Select ---" />
	            <form:options items="${productTypes}" />
	        </form:select>
	    </td>
	    <td><form:errors path="type" cssClass="error" /></td>
	</tr>
	<!--<tr>
	    <td>Code : </td>
	    <td>
	        <form:input path="code" />
	    </td>
	    <td><form:errors path="code" cssClass="error" /></td>
	</tr>-->
    <tr>
        <td colspan="3"><input type="submit" /></td>
    </tr>
    </table>
</form:form>

</body>
</html>