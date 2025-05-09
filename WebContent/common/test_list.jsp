<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<jsp:include page="/common/header.jsp" />
<jsp:include page="/common/menu.jsp" />

<div style="margin-left:200px; padding:20px;">
    <h2>成績管理</h2>
    <!-- 検索フォーム -->
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
  .filter-form2 button {
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
  }

  .filter-form2 label {
    font-weight: bold;
    font-size: 14px;
  }
</style>

<form action="<%= request.getContextPath() %>/TestList.action" method="get" class="filter-form">
<label>科目情報  </label>
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
            <option value="${sub}" <c:if test="${param.subject == sub}">selected</c:if>>${cls}</option>
        </c:forEach>
    </select>
    <label>回数：</label>
    <select name="no">
        <option value="">------</option>
        <c:forEach var="no" items="${classNumList}">
            <option value="${no}" <c:if test="${param.classNum == no}">selected</c:if>>${cls}</option>
        </c:forEach>
    </select>
    <button type="submit">検索</button>
</form>
<form action="<%= request.getContextPath() %>/TestList.action" method="get" class="filter-form2">
	<label>学生情報</labe>
    <label>学生番号：</label>
    <select name="stu">
        <option value="">------</option>
        <c:forEach var="stu" items="${studentList}">
            <option value="${stu}" <c:if test="${param.student == stu}">selected</c:if>>${cls}</option>
        </c:forEach>
    </select>
    <button type="submit">検索</button>
</form>


    <!-- 学生リスト -->
    <c:choose>
        <c:when test="${not empty studentList}">
            <table border="1" cellpadding="5" cellspacing="0" style="width:100%; text-align:center;">
                <tr>
                <div>科目名="$[subject]"
                    <th>入学年度</th>
                    <th>クラス</th>
                    <th>学生番号</th>
                    <th>氏名</th>
                    <th>点数</th>
                </tr>
                <c:forEach var="tes" items="${studentList}">
                    <tr>
                        <td>${tes.entYear}</td>
                        <td>${tes.no}</td>
                        <td>${tes.name}</td>
                        <td>${tes.classNum}</td>
                        <td>$[tes.point]</td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <p style="color:red;">成績情報が存在しませんでした。</p>
        </c:otherwise>
    </c:choose>
</div>

<jsp:include page="/common/footer.jsp" />