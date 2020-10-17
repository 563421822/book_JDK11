<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>书籍展示页面</title>
</head>
<body>
<table border="1">
    <caption>所有书籍</caption>
    <tr>
        <td>编号</td>
        <td>书籍名称</td>
        <td>价格(元)</td>
        <td>作者</td>
        <td>操作</td>
    </tr>
    <c:forEach var="l" items="${requestScope.list}">
        <tr>
            <td>${l.id}</td>
            <td>${l.bookName}</td>
            <td>${l.price}</td>
            <td>${l.author}</td>
            <td><a href="BookServlet?method=doShowDetail&id=${l.id}">编辑</a>|<a
                    href="BookServlet?method=doChangeStatus&id=${l.id}">删除</a></td>
        </tr>
    </c:forEach>
    <tr>
        <c:if test="${requestScope.page.currentPage gt 1}">
            <td><a href="BookServlet?method=doShowAll&curr=1">首页</a></td>
        </c:if>
        <c:if test="${requestScope.page.currentPage gt 1}">
            <td><a href="BookServlet?method=doShowAll&curr=${requestScope.page.currentPage-1}">上一页</a></td>
        </c:if>
        <c:if test="${requestScope.page.currentPage lt requestScope.page.totalPage}">
            <td><a href="BookServlet?method=doShowAll&curr=${requestScope.page.currentPage+1}">下一页</a></td>
        </c:if>
        <c:if test="${requestScope.page.currentPage lt requestScope.page.totalPage}">
            <td><a href="BookServlet?method=doShowAll&curr=${requestScope.page.totalPage}">尾页</a></td>
        </c:if>
        <td>当前为第${requestScope.page.currentPage}/${requestScope.page.totalPage}页</td>
    </tr>
    <tr>
        <c:forEach begin="1" end="${requestScope.page.totalPage}" varStatus="status">
            <td><a href="BookServlet?method=doShowAll&curr=${status.index}">${status.index}</a></td>
        </c:forEach>
    </tr>
    <tr>
        <td colspan="5"><a href="add.jsp">添加书籍</a></td>
    </tr>
</table>
</body>
</html>
