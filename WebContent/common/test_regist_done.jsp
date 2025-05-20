<jsp:include page="/common/header.jsp" />
<jsp:include page="/common/menu.jsp" />
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div style="margin-left: 200px; padding: 20px;">

    <h2 style="color: green; margin-bottom: 20px;">
        ${message}
    </h2>

    <div style="margin-top: 20px;">
        <a href="${pageContext.request.contextPath}/TestRegist.action" style="margin-right: 20px;">戻る</a>
        <a href="${pageContext.request.contextPath}/TestList.action">成績参照</a>
    </div>

</div>

<jsp:include page="/common/footer.jsp" />
