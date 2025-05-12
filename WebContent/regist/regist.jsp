<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*, bean.Student" %>
<%@ include file="header.jsp" %>
<jsp:include page="menu.jsp" />

<h2>成績管理</h2>
<form action="${pageContext.request.contextPath}/TestRegist.action" method="post">
    <input type="hidden" name="subject" value="${subjectId}">
    <input type="hidden" name="test" value="${testId}">

    <table>
        <tr><th>入学年度</th><th>クラス</th><th>学籍番号</th><th>氏名</th><th>点数</th></tr>
        <c:forEach var="s" items="${students}">
            <tr>
                <td>${s.entYear}</td>
                <td>${s.classNum}</td>
                <td>${s.no}</td>
                <td>${s.name}</td>
                <td>
                    <input type="text" name="point" value="${s.point}" size="3" />
                    <input type="hidden" name="studentNo" value="${s.no}" />
                </td>
            </tr>
        </c:forEach>
    </table>

    <input type="submit" value="登録して終了" />
</form>

<c:if test="${not empty error}">
    <p style="color:red;">${error}</p>
</c:if>

<%@ include file="footer.jsp" %>