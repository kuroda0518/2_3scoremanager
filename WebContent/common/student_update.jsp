<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/common/header.jsp" />
<jsp:include page="/common/menu.jsp" />

<html>
<head>
    <title>学生情報変更</title>
    <style>
    
        body {
            font-family: sans-serif;
            background-color: #f9f9f9;
            padding: 20px;
        }
        .container {
            width: 500px;
            margin: auto;
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px #ccc;
        }
        h2 {
            border-bottom: 2px solid #ccc;
            padding-bottom: 10px;
        }
        label {
            display: inline-block;
            width: 100px;
            margin-top: 10px;
        }
        input[type="text"], select {
            width: 250px;
            padding: 6px;
            margin-top: 10px;
        }
        .radio-group {
            margin-top: 10px;
        }
        .button-group {
            margin-top: 20px;
        }
        .button-group input[type="submit"], .button-group a {
            padding: 8px 20px;
            text-decoration: none;
            border: none;
            border-radius: 5px;
            font-weight: bold;
        }
        .button-group input[type="submit"] {
            background-color: #007bff;
            color: white;
            cursor: pointer;
        }
        .button-group a {
            background-color: transparent;
            color: #007bff;
            margin-left: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>学生情報変更</h2>

        <form action="StudentUpdateExecute.action" method="post">
            <div>
                <label>入学年度：</label>
                <span>${student.entYear}</span>
            </div>
            <div>
                <label>学生番号：</label>
                <span>${student.no}</span>
                <input type="hidden" name="no" value="${student.no}">
            </div>
            <div>
                <label>氏名：</label>
                <input type="text" name="name" value="${student.name}">
            </div>
            <div>
                <label>クラス：</label>
                <select name="classCode">
                    <c:forEach var="cls" items="${classList}">
                        <option value="${cls.code}" ${cls.code == student.classCode ? 'selected' : ''}>
                            ${cls.name}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="radio-group">
                <label>在学状況：</label>
                <input type="radio" name="status" value="1" ${student.status == 1 ? 'checked' : ''}> 在学中
                <input type="radio" name="status" value="0" ${student.status == 0 ? 'checked' : ''}> 退学
            </div>
            <div class="button-group">
                <input type="submit" value="変更">
                <a href="StudentList.action">戻る</a>
            </div>
        </form>
    </div>
</body>
</html>

<jsp:include page="/common/footer.jsp" />
a

