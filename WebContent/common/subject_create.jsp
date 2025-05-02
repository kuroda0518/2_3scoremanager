<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/common/header.jsp" />
<jsp:include page="/common/menu.jsp" />

<div style="margin-left: 200px; padding: 20px;">
    <h2>科目情報登録</h2>

    <form action="${pageContext.request.contextPath}/SubjectCreate.action" method="post"
          style="max-width: 500px; background-color: #f9f9f9; padding: 20px; border-radius: 8px;">

        <label>科目コード：</label><br>
        <input type="text" name="cd" value="${cd}" required
               placeholder="科目コードを入力"
               style="width: 100%; padding: 6px; margin-bottom: 8px;"><br>
        <c:if test="${not empty cdError}">
            <div style="color: orange;">${cdError}</div>
        </c:if>

        <label>科目名：</label><br>
        <input type="text" name="name" value="${name}" required
               placeholder="科目名を入力"
               style="width: 100%; padding: 6px; margin-bottom: 8px;"><br>
        <c:if test="${not empty nameError}">
            <div style="color: orange;">${nameError}</div>
        </c:if>

        <label>学校コード：</label><br>
        <input type="text" name="schoolCd" value="${schoolCd}" required
               placeholder="学校コードを入力"
               style="width: 100%; padding: 6px; margin-bottom: 8px;"><br>
        <c:if test="${not empty schoolCdError}">
            <div style="color: orange;">${schoolCdError}</div>
        </c:if>

        <input type="submit" value="登録"
               style="padding: 8px 16px; background-color: #4a5568; color: white; border: none; border-radius: 4px;">
    </form>

    <br><a href="${pageContext.request.contextPath}/SubjectList.action">戻る</a>
</div>


<jsp:include page="/common/footer.jsp" />