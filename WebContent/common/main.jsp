<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/header.jsp" %>
<%@ include file="/common/menu.jsp" %>
<%@ page import="bean.Teacher" %>




<div style="margin-left:200px; padding:20px;">
    <h2>メニュー</h2>
</div>
<div style="display: flex; gap: 20px; justify-content: center; margin-top: 30px;">
    <%-- 学生管理 --%>
    <div style="background-color: #f8d7da; padding: 20px; border-radius: 10px; text-align: center; flex-basis: 0; flex-grow: 1; min-width: 150px; display: flex; flex-direction: column; justify-content: center;">
        <a href="<%= request.getContextPath() %>/StudentList.action" style="text-decoration: none; color: inherit; font-size: 1.2em;">学生管理</a>
    </div>

    <%-- 成績管理 --%>
    <%-- ★★★ここが修正点：成績管理の枠内を中央寄せにする★★★ --%>
    <div style="background-color: #d4edda; padding: 20px; border-radius: 10px; flex-basis: 0; flex-grow: 1; min-width: 150px; text-align: center;"> <%-- text-align: center; を追加 --%>
        <div style="margin-bottom: 5px;"><a style="font-size: 1.2em; color: inherit; text-decoration: none;">成績管理</a></div>
        <div style="margin-bottom: 5px;"><a href="<%= request.getContextPath() %>/TestRegist.action" style="text-decoration: none; font-size: 1.2em;">成績登録</a></div>
        <div><a href="<%= request.getContextPath() %>/TestList.action" style="text-decoration: none; font-size: 1.2em;">成績参照</a></div>
    </div>
    <%-- ★★★修正点ここまで★★★ --%>

    <%-- 科目管理 --%>
    <div style="background-color: #d6d8fb; padding: 20px; border-radius: 10px; text-align: center; flex-basis: 0; flex-grow: 1; min-width: 150px; display: flex; flex-direction: column; justify-content: center;">
        <a href="<%= request.getContextPath() %>/scoremanager/SubjectList.action" style="text-decoration: none; color: inherit; font-size: 1.2em;">科目管理</a>
    </div>
</div>

<%@ include file="/common/footer.jsp" %>