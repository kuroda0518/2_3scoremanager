<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/header.jsp" %>
<%@ include file="/common/menu.jsp" %>
<%@ page import="bean.Teacher" %>





<!-- 横並びの設定 -->
<div style="display: flex; gap: 20px; justify-content: center; margin-top: 30px;">
    <a href="../StudentList.action" style="background-color: #f8d7da; padding: 20px; text-align: center; border-radius: 10px; text-decoration: none;">学生管理</a>

    <div style="background-color: #d4edda; padding: 20px; border-radius: 10px;">
        <div><a>成績管理</a></div>
        <div><a href="#" style="text-decoration: none;">成績登録</a></div>
        <div><a href="#" style="text-decoration: none;">成績参照</a></div>
    </div>

    <a href="../scoremanager/SubjectList.action" style="background-color: #d6d8fb; padding: 20px; text-align: center; border-radius: 10px; text-decoration: none;">科目管理</a>
</div>

<%@ include file="/common/footer.jsp" %>


