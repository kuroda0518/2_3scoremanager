<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/common/header.jsp" %>
<%@ include file="/common/menu.jsp" %>
<html>
<head>
  <title>学生情報変更</title>
  <style>
    body { font-family: sans-serif; background-color: #f9f9f9; padding: 30px; }
    .container { width: 500px; margin: auto; background: #fff; padding: 20px 40px; border-radius: 8px; box-shadow: 0 0 10px #ccc; }
    h2 { font-size: 20px; margin-bottom: 20px; }
    label { display: inline-block; width: 100px; margin-top: 12px; }
    input[type="text"], select {
      width: 250px; padding: 5px; margin-top: 10px;
    }
    .form-group { margin-bottom: 15px; }
    .checkbox-group { margin-top: 10px; }
    .button-group { margin-top: 20px; }
    .button-group input, .button-group a {
      padding: 6px 16px;
      font-weight: bold;
      border: none;
      border-radius: 5px;
      text-decoration: none;
    }
    .button-group input[type="submit"] {
      background-color: #007bff; color: white; cursor: pointer;
    }
    .button-group a {
      margin-left: 10px; color: #007bff;
    }
    .error { color: red; font-size: 13px; }
  </style>
</head>
<body>
  <div class="container">
    <h2>学生情報変更</h2>

    <c:if test="${not empty message}">
      <p class="error">${message}</p>
    </c:if>

    <form action="StudentUpdateExecute.action" method="post">
      <div class="form-group">
        <label>入学年度：</label>
        <input type="text" name="ent_year" value="${student.entYear}" readonly>
      </div>

      <div class="form-group">
        <label>学生番号：</label>
        <input type="text" name="no" value="${student.no}" readonly>
      </div>

      <div class="form-group">
        <label>氏名：</label>
        <input type="text" name="name" value="${student.name}" maxlength="30" required>
      </div>

      <label for="classNum">クラス：</label>
      <select name="classNum" id="classNum">
        <c:forEach var="classNum" items="${classNumList}">
            <option value="${classNum}">${classNum}</option>
        </c:forEach>
      </select>

      <div class="checkbox-group">
        <label>在学中：</label>
        <input type="checkbox" name="is_attend" value="true" ${student.isAttend ? 'checked' : ''}>
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
