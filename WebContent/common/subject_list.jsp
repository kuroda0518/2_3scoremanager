<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/common/header.jsp" />
<jsp:include page="/common/menu.jsp" />

<div style="margin-left:200px; padding: 20px; margin-bottom: 40px;">

        <h2>科目管理</h2>
        <div style="text-align: right; margin-bottom: 10px;">
        <a href="<%= request.getContextPath() %>/SubjectCreate.action">新規登録</a>
    </div>

<!-- この黄色いの気にしないで -->
        <table border="1" cellspacing="0" cellpadding="8" style="width: 100%; border-collapse: collapse;">
        <thead style="background-color: #f0f0f0;">
            <tr>
                <th>科目コード</th>
                <th>科目名</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
                        <c:forEach var="s" items="${subject}">
    <tr>
        <td>${s.cd}</td>         <td>${s.name}</td>
        <td>
            <a href="<%= request.getContextPath() %>/SubjectUpdate.action?cd=${s.cd}">変更</a>             &nbsp;
            <a href="<%= request.getContextPath() %>/SubjectDelete.action?cd=${s.cd}">削除</a>         </td>
    </tr>
</c:forEach>
        </tbody>
    </table>
</div>

<jsp:include page="/common/footer.jsp" />
