<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ログイン | 得点管理システム</title>
    <style>
        body {
            font-family: sans-serif;
            background-color: #f8f8f8;
            text-align: center;
            margin-top: 100px;
        }
        .login-box {
            display: inline-block;
            padding: 30px;
            background-color: white;
            border: 1px solid #ccc;
            border-radius: 8px;
        }
        input {
            margin: 10px;
            padding: 8px;
            width: 200px;
        }
        .error-message {
            color: red;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>

<div class="login-box">
    <h2>ログイン</h2>

    <c:if test="${not empty error}">
        <div class="error-message">${error}</div>
    </c:if>

    <form action="<%= request.getContextPath() %>/Login.action" method="post">
        <input type="text" name="id" placeholder="ID"><br>
        <input type="password" name="password" placeholder="パスワード"><br>
        <input type="submit" value="ログイン">
    </form>
</div>

</body>
</html>
