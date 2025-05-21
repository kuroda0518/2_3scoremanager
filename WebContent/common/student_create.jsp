<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/common/header.jsp" />
<jsp:include page="/common/menu.jsp" />

<div style="margin-left: 200px; padding: 20px;">
    <h2>学生情報登録</h2>

    <form action="${pageContext.request.contextPath}/StudentCreate.action" method="post"
          style="max-width: 500px; background-color: #f9f9f9; padding: 20px; border-radius: 8px;">

        <label>入学年度：</label><br>
        <select name="entYear" required style="width: 100%; padding: 6px; margin-bottom: 8px;">
            <option value="">--------</option>
            <c:forEach var="year" items="${entYearList}">
                <option value="${year}" <c:if test="${entYear == year}">selected</c:if>>${year}</option>
            </c:forEach>
        </select>
        <c:if test="${not empty entYearError}">
            <div style="color: orange;">${entYearError}</div>
        </c:if>

        <label>学生番号：</label><br>
        <input type="text" name="no" value="${no}" required style="width: 100%; padding: 6px; margin-bottom: 8px;"><br>
        <c:if test="${not empty noError}">
            <div style="color: orange;">${noError}</div>
        </c:if>

        <label>氏名：</label><br>
        <input type="text" name="name" value="${name}" required style="width: 100%; padding: 6px; margin-bottom: 8px;"><br>

        <label>クラス：</label><br>
        <input type="text" name="classNum" value="${classNum}" required style="width: 100%; padding: 6px; margin-bottom: 8px;"><br>

        <label>在籍中：</label>
        <input type="checkbox" name="isAttend" value="true" <c:if test="${isAttend == 'true'}">checked</c:if>><br><br>



        <input type="submit" value="登録して終了" style="padding: 8px 16px; background-color: #4a5568; color: white; border: none; border-radius: 4px;">
    </form>

    <br><a href="${pageContext.request.contextPath}/StudentList.action">戻る</a>
</div>

<jsp:include page="/common/footer.jsp" />
