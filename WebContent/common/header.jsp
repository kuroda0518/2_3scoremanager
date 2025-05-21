<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div style="background-color: #D7EEFF; color: white; padding: 10px;">
    <h1>
        <a href="http://localhost:8080/scoremanager/main/Menu.action" style="color: black; text-decoration: none;">
            得点管理システム
        </a>
    </h1>

    <div style="text-align: right;">
        <c:choose>
            <c:when test="${not empty sessionScope.loginUser}">
                <span style="color: black;">${sessionScope.loginUser.name} さん</span>　
                <a href="<%=request.getContextPath()%>/Logout.action" style="color: black;">ログアウト</a>
            </c:when>
            <c:otherwise>
                <a href="<%=request.getContextPath()%>/main/login-in.jsp" style="color: black;">ログイン</a>
            </c:otherwise>
        </c:choose>
    </div>
</div>
