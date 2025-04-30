<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/header.jsp" %>
<%@ include file="/common/menu.jsp" %>
<html>
<head>
  <title>変更完了</title>
  <style>
    body { font-family: sans-serif; background-color: #f9f9f9; padding: 30px; }
    .container {
      width: 500px;
      margin: auto;
      background-color: #fff;
      padding: 30px 40px;
      border-radius: 10px;
      box-shadow: 0 0 10px #ccc;
    }
    h2 {
      font-size: 20px;
      margin-bottom: 20px;
    }
    .message {
      background-color: #d4edda;
      color: #155724;
      padding: 10px;
      border-radius: 5px;
      font-weight: bold;
      margin-bottom: 20px;
    }
    a {
      color: #007bff;
      text-decoration: none;
    }
  </style>
</head>
<body>
  <div class="container">
    <h2>学生情報変更</h2>
    <div class="message">変更が完了しました</div>
    <a href="StudentList.action">学生一覧</a>
  </div>
</body>
</html>
<jsp:include page="/common/footer.jsp" />
