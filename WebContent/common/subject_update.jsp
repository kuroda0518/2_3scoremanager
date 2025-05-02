<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<jsp:include page="/common/header.jsp" />
<jsp:include page="/common/menu.jsp" />


<div style="margin-left:200px; padding: 20px;">
    <h2>科目情報変更</h2>

    <form action="<%= request.getContextPath() %>/SubjectUpdateExecute.action" method="post" style="width: 400px;">
        <div style="margin-bottom: 15px;">
            <label for="subjectCode">科目コード</label><br>
            <input type="text" id="subjectCode" name="subcd" value="${subject.cd}" readonly style="width: 100%;" />
        </div>

        <div style="margin-bottom: 15px;">
            <label for="subjectName">科目名</label><br>
            <input type="text" id="subjectName" name="subname" value="${subject.name}" required style="width: 100%;" />
        </div>

        <div style="margin-bottom: 15px;">
            <button type="submit" style="background-color: #3182ce; color: white; border: none; padding: 8px 16px; border-radius: 4px;">
                変更
            </button>
        </div>

        <div>
            <a href="<%= request.getContextPath() %>/SubjectList.action">戻る</a>
        </div>
    </form>
</div>
<jsp:include page="/common/footer.jsp" />
