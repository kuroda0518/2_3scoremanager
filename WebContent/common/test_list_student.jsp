<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp" %>
<jsp:include page="menu.jsp" />

<h2>成績一覧（科目）</h2>

<form action="${pageContext.request.contextPath}/TestListStudent.action" method="get">

    <label>入学年度</label>
    <select name="entYear">
        <option value="">-- 選択 --</option>
        <c:forEach var="year" items="${entYearList}">
            <option value="${year}" <c:if test="${param.entYear == year}">selected</c:if>>${year}</option>
        </c:forEach>
    </select>

    <label>クラス</label>
    <select name="classNum">
        <option value="">-- 選択 --</option>
        <c:forEach var="cls" items="${classNumList}">
            <option value="${cls}" <c:if test="${param.classNum == cls}">selected</c:if>>${cls}</option>
        </c:forEach>
    </select>

    <label>科目ID</label>
<select name="subject">
  <option value="">-- 選択 --</option>
  <c:forEach var="subj" items="${subjectList}">
    <option value="${subj['id']}"
      <c:if test="${param.subject == subj['id']}">selected</c:if>>
      ${subj['name']}
    </option>
  </c:forEach>
</select>

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
            <th>1回</th><th>2回</th>
        </tr>
        <c:forEach var="row" items="${studentList}">
            <tr>
                <td>${row.student.entYear}</td>
                <td>${row.student.classNum}</td>
                <td>${row.student.no}</td>
                <td>${row.student.name}</td>
                <td><c:choose>
                    <c:when test="${row.point1 != null}">${row.point1}</c:when>
                    <c:otherwise>-</c:otherwise>
                </c:choose></td>
                <td><c:choose>
                    <c:when test="${row.point2 != null}">${row.point2}</c:when>
                    <c:otherwise>-</c:otherwise>
                </c:choose></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<%@ include file="footer.jsp" %>