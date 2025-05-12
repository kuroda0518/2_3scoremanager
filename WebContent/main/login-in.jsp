<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ログイン</title>
    <style>
        body {
            font-family: sans-serif;
            background-color: #ffffff;
            text-align: center;
            margin-top: 100px;
            font-size: 18px;
        }
        .login-box {
            display: inline-block;
            padding: 0;
            background-color: white;
            border: 1px solid #ccc;
            border-radius: 8px;
            overflow: hidden;
            width: 420px;
        }
        .login-title {
            background-color: #f0f0f0;
            padding: 22px;
            margin: 0;
            font-size: 30px;
            border-bottom: 1px solid #ccc;
        }
        .login-content {
            padding: 45px;
        }
        input[type="text"],
        input[type="password"] {
            margin: 15px;
            padding: 12px;
            width: 300px;
            background-color: #e0f7ff;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 18px;
        }
        .error-message {
            color: red;
            margin-bottom: 15px;
        }
        .show-password {
            margin-top: -7px;
            font-size: 18px;
        }
        input[type="submit"] {
            padding: 12px 25px;
            font-size: 18px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

<div class="login-box">
    <h2 class="login-title">ログイン</h2>

    <div class="login-content">
        <c:if test="${not empty error}">
            <div class="error-message">${error}</div>
        </c:if>

        <form action="Login.action" method="post">
            <p><input type="text" name="id" placeholder="ID" required></p><br>

            <input type="password" name="password" id="password" placeholder="パスワード" required><br>

            <div class="show-password">
                <input type="checkbox" id="showPassword" onclick="togglePassword()">
                <label for="showPassword">パスワードを表示する</label>
            </div><br>

            <input type="submit" value="ログイン">
        </form>
    </div>
</div>

<script>
    function togglePassword() {
        const passwordInput = document.getElementById("password");
        const checkbox = document.getElementById("showPassword");
        passwordInput.type = checkbox.checked ? "text" : "password";
    }
</script>

<%@ include file="/common/footer.jsp" %>