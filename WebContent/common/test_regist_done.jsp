<jsp:include page="/common/header.jsp" />
<jsp:include page="/common/menu.jsp" />
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<div style="margin-left: 200px; padding: 20px;">

    <h2 style="color: green; margin-bottom: 20px;">
        ${message}
    </h2>

    <c:url var="backUrl" value="TestRegistExecute.action">
        <c:param name="entYear" value="${entYear}" />
        <c:param name="classNum" value="${classNum}" />
        <c:param name="subject" value="${subject}" />
        <c:param name="no" value="${no}" />
    </c:url>

    <a href="${backUrl}" style="padding: 8px 16px; background-color: #4CAF50; color: white; text-decoration: none; border-radius: 4px;">
        戻る
    </a>

</div>

<jsp:include page="/common/footer.jsp" />
