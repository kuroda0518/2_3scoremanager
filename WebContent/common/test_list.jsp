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
            flex-direction: column;
            gap: 12px;
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
    </style>

    <form action="<%= request.getContextPath() %>/TestList.action" method="get" class="filter-form">
        <div>
            <label>入学年度：</label>
            <select name="entYear">
                <option value="">------</option>
                <c:forEach var="year" items="${entYearList}">
                    <option value="${year}" <c:if test="${param.entYear == year}">selected</c:if>>${year}</option>
                </c:forEach>
            </select>
        </div>
        <div>
            <label>クラス：</label>
            <select name="classNum">
                <option value="">------</option>
                <c:forEach var="cls" items="${classNumList}">
                    <option value="${cls}" <c:if test="${param.classNum == cls}">selected</c:if>>${cls}</option>
                </c:forEach>
            </select>
        </div>
