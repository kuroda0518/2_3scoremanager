<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/common/header.jsp" />
<jsp:include page="/common/menu.jsp" />

<div style="margin-left: 200px; padding: 20px;">
    <h2>学生情報登録</h2>

    <div style="background-color: #d4edda; color: #155724; padding: 12px; border-radius: 6px; margin-bottom: 16px;">
        ${message}
    </div>

    <div style="margin-top: 20px;">
        <a href="${pageContext.request.contextPath}/StudentRegister.action" style="margin-right: 20px;">戻る</a>
        <a href="${pageContext.request.contextPath}/StudentList.action">学生一覧</a>
    </div>
</div>

<jsp:include page="/common/footer.jsp" />
