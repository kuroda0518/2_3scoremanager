<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/common/header.jsp" />
<jsp:include page="/common/menu.jsp" />

<style>
  .filter-form, .filter-form2 {
    display: flex;
    gap: 12px;
    align-items: center;
    padding: 12px 16px;
    border: 1px solid #ccc;
    border-radius: 6px;
    background-color: #ffffff;
    margin-bottom: 20px;
  }

  .filter-form select,
  .filter-form2 select,
  .filter-form2 input[type="text"],
  .filter-form button,
  .filter-form2 button {
    padding: 6px 10px;
    font-size: 14px;
    border-radius: 4px;
    border: 1px solid #ccc;
  }

  .filter-form button,
  .filter-form2 button {
    background-color: #4a5568;
    color: white;
    border: none;
    cursor: pointer;
  }

  .filter-form label,
  .filter-form2 label {
    font-weight: bold;
    font-size: 14px;
  }

  .info-text {
    color: #3182ce; /* 水色 */
    font-size: 14px;
    margin: 6px 0 10px 10px;
  }

  .error-message {
    color: red;
    font-size: 14px;
    margin: 10px 0;
  }
</style>

<div style="margin-left:200px; padding:20px;">
  <h2>成績管理</h2>

  <!-- 科目別検索フォーム -->
  <form action="<%= request.getContextPath() %>/TestListSubjectExecute.action" method="post" class="filter-form">
    <label>入学年度：</label>
    <select name="entYear">
      <option value="">------</option>
      <c:forEach var="year" items="${entYearList}">
        <option value="${year}" <c:if test="${param.entYear == year}">selected</c:if>>${year}</option>
      </c:forEach>
    </select>

    <label>クラス：</label>
    <select name="classNum">
      <option value="">------</option>
      <c:forEach var="cls" items="${classNumList}">
        <option value="${cls}" <c:if test="${param.classNum == cls}">selected</c:if>>${cls}</option>
      </c:forEach>
    </select>

    <label>科目：</label>
    <select name="subject">
      <option value="">------</option>
      <c:forEach var="sub" items="${subjectList}">
        <option value="${sub.cd}" <c:if test="${param.subject == sub.cd}">selected</c:if>>${sub.name}</option>
      </c:forEach>
    </select>

    <button type="submit">検索</button>
  </form>

  <!-- 学生別検索フォーム -->
  <form action="<%= request.getContextPath() %>/TestListStudentExecute.action" method="get" class="filter-form2">
    <label>学生情報</label>
    <label>学生番号：</label>
    <input type="text" name="stu" value="${param.stu}" placeholder="学生番号を入力してください" />
    <button type="submit">検索</button>
  </form>

  <!-- 学生別成績一覧 -->
  <c:if test="${not empty student}">
    <h3>成績一覧（学生）</h3>
    <p>氏名：${student.name}（${student.no}）</p>
  </c:if>

  <c:if test="${not empty testList}">
    <table border="1" style="width:100%; text-align:center; margin-top:20px;">
      <thead>
        <tr>
          <th>科目名</th>
          <th>科目コード</th>
          <th>回数</th>
          <th>点数</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="test" items="${testList}">
          <tr>
            <td>${test.name}</td>
            <td>${test.subjectCd}</td>
            <td>${test.no}</td>
            <td>${test.point}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </c:if>

  <!-- エラーメッセージ -->
  <c:if test="${empty testList && not empty error}">
    <p class="error-message">${error}</p>
  </c:if>
</div>

<jsp:include page="/common/footer.jsp" />
