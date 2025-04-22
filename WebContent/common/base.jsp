<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="/common/header.jsp" />

<!-- レイアウト全体をflex -->
<div style="display: flex; min-height: 100vh;">

    <!-- サイドメニュー -->
    <div style="width: 200px; background-color: #f0f0f0; padding: 10px; min-height: 100vh;">
        <jsp:include page="/common/menu.jsp" />
    </div>

    <!-- メインコンテンツ -->
    <div style="flex-grow: 1; padding: 20px;">
        <jsp:include page="${main}" />
    </div>
</div>

<jsp:include page="/common/footer.jsp" />



aaaa