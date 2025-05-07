<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp" %>
<jsp:include page="menu.jsp" />

<h2>成績一覧（科目）</h2>

<form action="main/TestListStudent.action" method="get">
    <label>入学年度</label>
    <input type="text" name="entYear" value="${param.entYear}" />

    <label>クラス</label>
    <input type="text" name="classNum" value="${param.classNum}" />

    <label>科目ID</label>
    <input type="text" name="subject" value="${param.subject}" />

    <input type="submit" value="検索" />
</form>

<c:if test="${not empty error}">
    <p style="color:red;">${error}</p>
</c:if>

<c:if test="${not empty studentList}">
    <div>科目：${param.subject}</div>
    <table border="1">
        <tr>
            <th>入学年度</th><th>クラス</th><th>学籍番号</th><th>氏名</th>
            <th>1回の点数</th><th>2回の点数</th>
        </tr>
        <c:forEach var="s" items="${studentList}">
            <tr>
                <td>${s.entYear}</td>
                <td>${s.classNum}</td>
                <td>${s.studentNo}</td>
                <td>${s.studentName}</td>
                <td><c:choose>
                    <c:when test="${s.firstScore != null}">${s.firstScore}</c:when>
                    <c:otherwise>-</c:otherwise>
                </c:choose></td>
                <td><c:choose>
                    <c:when test="${s.secondScore != null}">${s.secondScore}</c:when>
                    <c:otherwise>-</c:otherwise>
                </c:choose></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<%@ include file="footer.jsp" %>
