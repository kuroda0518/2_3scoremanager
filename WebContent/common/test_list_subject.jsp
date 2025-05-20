<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/common/header.jsp" />
<jsp:include page="/common/menu.jsp" />

<style>
  .filter-form {
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
  .filter-form input[type="checkbox"],
  .filter-form button {
    padding: 6px 10px;
    font-size: 14px;
  }

  .filter-form select {
    border: 1px solid #ccc;
    border-radius: 4px;
  }

  .filter-form button {
    background-color: #4a5568;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
  }

  .filter-form label {
    font-weight: bold;
    font-size: 14px;
  }

  .filter-form2 {
    display: flex;
    gap: 12px;
    align-items: center;
    padding: 12px 16px;
    border: 1px solid #ccc;
    border-radius: 6px;
    background-color: #ffffff;
    margin-bottom: 20px;
  }

  .filter-form2 select,
  .filter-form2 input[type="checkbox"],
  .filter-form2 button,
  .filter-form2 input[type="text"] {
    padding: 6px 10px;
    font-size: 14px;
  }

  .filter-form2 select {
    border: 1px solid #ccc;
    border-radius: 4px;
  }

  .filter-form2 button {
    background-color: #4a5568;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
  }

  .filter-form2 label {
    font-weight: bold;
    font-size: 14px;
  }
</style>

<div style="margin-left:200px; padding:20px;">
    <h2>成績管理</h2>

    <!-- 検索フォーム1 -->
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

    <!-- 検索フォーム2 -->
    <form action="<%= request.getContextPath() %>/TestListStudentExecute.action" method="get" class="filter-form2">
        <label>学生情報</label>
        <label>学生番号：</label>
        <input type="text" name="stu" value="${param.stu}" placeholder="学生番号を入力してください" />
        <button type="submit">検索</button>
    </form>

    <!-- 成績一覧 -->
	<h2>成績一覧（科目別）</h2>
	<c:if test="${not empty subject}">
	   <p>科目：${subject.name}</p>
	</c:if>
	<c:if test="${not empty testDisplayList}">
	  <table border="1" style="width:100%; text-align:center; margin-top:20px;">
	    <tr>
	      <th>入学年度</th>
	      <th>クラス</th>
	      <th>学籍番号</th>
	      <th>氏名</th>
	      <th>1回目</th>
	      <th>2回目</th>
	    </tr>
	    <c:forEach var="td" items="${testDisplayList}">
	      <tr>
	        <td>${td.entYear}</td>
	        <td>${td.classNum}</td>
	        <td>${td.studentNo}</td>
	        <td>${td.name}</td>
	        <td>${td.point1}</td>
	        <td>${td.point2}</td>
	      </tr>
	    </c:forEach>
	  </table>
	</c:if>



</div>

<jsp:include page="/common/footer.jsp" />

