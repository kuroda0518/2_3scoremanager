<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<jsp:include page="/common/header.jsp" />
<jsp:include page="/common/menu.jsp" />

<div style="margin-left:200px; padding: 20px;">
    <!-- ① タイトル -->
    <h2>科目情報削除</h2>

    <!-- ② 確認メッセージ -->
    <p>「${subject.name}(${subject.cd})」を削除してもよろしいですか？</p>

    <form action="<%= request.getContextPath() %>/SubjectDeleteExecute.action" method="post" style="margin-top: 20px;">
        <!-- 削除対象のコードをhiddenで送る -->
        <input type="hidden" name="subcd" value="${subject.cd}" />

        <!-- ③ 削除ボタン -->
        <button type="submit" style="background-color: #e53e3e; color: white; border: none; padding: 8px 16px; border-radius: 4px;">
            削除
        </button>
    </form>

    <!-- ④ 戻るリンク -->
    <div style="margin-top: 15px;">
        <a href="<%= request.getContextPath() %>/SubjectList.action">戻る</a>
    </div>
</div>

<jsp:include page="/common/footer.jsp" />