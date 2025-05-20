<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<jsp:include page="/common/header.jsp" />
<jsp:include page="/common/menu.jsp" />

<div style="margin-left:200px; padding:20px;">
    <h2>成績管理</h2>
    <style>
        .filter-form {
            display: flex;
            gap: 12px;
            align-items: center;
            padding: 12px 16px;
            border: 1px solid #ccc;
            border-radius: 6px;
            background-color: #ffffff;
            margin-bottom: 10px;
        }

        .filter-form select,
        .filter-form input[type="text"],
        .filter-form button {
            padding: 6px 10px;
            font-size: 14px;
            border-radius: 4px;
            border: 1px solid #ccc;
        }

        .filter-form button {
            background-color: #4a5568;
            color: white;
            border: none;
            cursor: pointer;
        }

        .filter-form label {
            font-weight: bold;
            font-size: 14px;
        }

        .error-message {
            color: red;
            margin-top: 5px;
            margin-bottom: 10px;
            font-size: 14px;
            font-weight: bold;
        }

        .info-message {
            color: gray;
            margin-bottom: 10px;
            font-size: 14px;
        }
    </style>

    <h3>成績参照</h3>
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
    <c:if test="${empty param.entYear or empty param.classNum or empty param.subject}">

    </c:if>

    <form action="<%= request.getContextPath() %>/TestListStudentExecute.action" method="get" class="filter-form">
        <label>学生情報</label>
        <label>学生番号：</label>
        <input type="text" name="stu" value="${param.stu}" placeholder="学生番号を入力してください" required />

        <button type="submit">検索</button>
    </form>

    <c:if test="${not empty error}">
    <p>氏名：${student.name}（${student.no}）</p>
        <p class="error-message">${error}</p>
    </c:if>

    <%-- 成績一覧の表示 (必要に応じて追加) --%>

</div>


<jsp:include page="/common/footer.jsp" />