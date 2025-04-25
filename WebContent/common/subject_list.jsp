<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/common/header.jsp" />
<jsp:include page="/common/menu.jsp" />

<div style="margin-left:200px; padding: 20px;">

    <!-- タイトル -->
    <h2>科目管理</h2>

    <!-- 新規登録リンク -->
    <div style="text-align: right; margin-bottom: 10px;">
        <a href="<%= request.getContextPath() %>/SubjectAdd.action">新規登録</a>
    </div>

    <!-- 一覧テーブル -->
    <table border="1" cellspacing="0" cellpadding="8" style="width: 100%; border-collapse: collapse;">
        <thead style="background-color: #f0f0f0;">
            <tr>
                <th>科目コード</th>
                <th>科目名</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
            <!-- ここで受け取る属性名を subject に修正 -->
            <c:forEach var="s" items="${subject}">
                <tr>
                    <td>${s.code}</td>
                    <td>${s.name}</td>
                    <td>
                        <a href="<%= request.getContextPath() %>/SubjectUpdate.action?cd=${s.code}">変更</a>
                        &nbsp;
                        <a href="<%= request.getContextPath() %>/SubjectDeleteConfirm.action?cd=${s.code}">削除</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<jsp:include page="/common/footer.jsp" />
