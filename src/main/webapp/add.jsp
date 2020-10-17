<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<c:choose>
<c:when test="${requestScope.detail != null}">
<form action="BookServlet" method="get">
    <table>
        <caption>编辑详情</caption>
        <input type="hidden" name="method" value="doEdit"/>
        <input type="hidden" name="id" value="${requestScope.detail.id}"/>
        </c:when>
        <c:otherwise>
        <form action="BookServlet" method="get">
            <table>
                <caption>添加书籍</caption>
                <input type="hidden" name="method" value="doAddField"/>
                </c:otherwise>
                </c:choose>
                <tr>
                    <td>书籍名称:</td>
                    <td><label>
                        <input type="text" name="bookName" value="${requestScope.detail.bookName}"/>
                    </label></td>
                </tr>
                <tr>
                    <td>价格:</td>
                    <td><label>
                        <input type="text" name="price" value="${requestScope.detail.price}"/>
                    </label></td>
                </tr>
                <tr>
                    <td>作者:</td>
                    <td><label>
                        <input type="text" name="author" value="${requestScope.detail.author}"/>
                    </label>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="提交"/><input type="reset" value="重置"/></td>
                </tr>
            </table>
        </form>
</body>
</html>
