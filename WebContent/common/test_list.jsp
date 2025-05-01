<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<jsp:include page="/common/header.jsp" />
<jsp:include page="/common/menu.jsp" />

<div style="margin-left:200px; padding:20px;">
    <h2>成績管理</h2>
<div style="text-align: right; margin-bottom: 10px;">
        <a href="${pageContext.request.contextPath}/TestCreate.action" style="background-color: #3182ce; color: white; padding: 6px 12px; border-radius: 4px; text-decoration: none;">
            新規登録
        </a>
    </div>


    <!-- 検索フォーム -->
<style>
  .filter-form {
    display: flex;
    gap: 12px;
    align-items: center;
    padding: 12px 16px;
    border: 1px solid #ccc;
    border-radius: 6px;
    background-color: #f9f9f9;
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
</style>

<form action="<%= request.getContextPath() %>/TestList.action" method="get" class="filter-form">
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

    <label>
        <input type="checkbox" name="isAttend" value="true"
            <c:if test="${param.isAttend == 'true'}">checked</c:if>>在学中
    </label>

    <button type="submit">絞り込み</button>
</form>


    <!-- 学生リスト -->
    <c:choose>
        <c:when test="${not empty studentList}">
            <table border="1" cellpadding="5" cellspacing="0" style="width:100%; text-align:center;">
                <tr>
                    <th>学生番号</th>
                    <th>科目番号</th>
                    <th>学校番号</th>
                    <th>在学中</th>
                    <th>操作</th>
                </tr>
                <c:forEach var="tes" items="${testList}">
                    <tr>
                        <td>${tes.entYear}</td>
                        <td>${tes.no}</td>
                        <td>${tes.name}</td>
                        <td>${tes.classNum}</td>
                        <td>
                            <c:choose>
                                <c:when test="${tes.isAttend}">
                                    ○
                                </c:when>
                                <c:otherwise>
                                    -
                                </c:otherwise>
                            </c:choose>
                        </td>
						<td><a href="${pageContext.request.contextPath}/StudentUpdate.action?no=${stu.no}">変更</a></td>
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
