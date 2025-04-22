<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/common/header.jsp" />
<jsp:include page="/common/menu.jsp" />

<div style="margin-left:200px; padding:20px;">
    <h2>学生情報登録</h2>

    <form action="<%= request.getContextPath() %>/StudentCreateExecute.action" method="post" style="display: flex; flex-direction: column; gap: 15px; width: 400px;">

        <label>入学年度</label>
        <select name="entYear" required>
            <option value="">--------</option>
            <c:forEach var="year" items="${entYearList}">
                <option value="${year}">${year}</option>
            </c:forEach>
        </select>

        <label>学生番号</label>
        <input type="text" name="no" placeholder="学生番号を入力してください" required />

        <label>氏名</label>
        <input type="text" name="name" placeholder="氏名を入力してください" required />

        <label>クラス</label>
        <select name="classNum" required>
            <c:forEach var="cls" items="${classNumList}">
                <option value="${cls}">${cls}</option>
            </c:forEach>
        </select>

        <label>
            <input type="checkbox" name="isAttend" value="true" checked />
            在学中
        </label>

        <button type="submit" style="padding: 8px 16px; background-color: #4a5568; color: white; border: none; border-radius: 4px;">
            登録して終了
        </button>

    </form>

    <br>
    <a href="<%= request.getContextPath() %>/StudentList.action">戻る</a>
</div>

<jsp:include page="/common/footer.jsp" />
