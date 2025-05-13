<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="header.jsp" %>
<jsp:include page="menu.jsp" />

<h2>成績管理</h2>
<p>登録が完了しました</p>

<a href="${pageContext.request.contextPath}/regist/regist.jsp">戻る</a>　
<a href="${pageContext.request.contextPath}/main/StudentList.action">成績参照へ</a>

<%@ include file="footer.jsp" %>
