<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div style="width:150px; float:left; border-right:1px solid #ccc; height:100vh; padding:10px; box-sizing:border-box;">

    <ul style="list-style:none; padding:0;">

        <li><a href="<%= request.getContextPath() %>/common/main.jsp">メニュー</a></li>
        <li><a href="<%= request.getContextPath() %>/StudentList.action">学生一覧</a></li>
        <li><label>成績管理</label></li>
        <li><a href="<%= request.getContextPath() %>/TestRegist.action">成績登録</a></li>
        <li><a href="<%= request.getContextPath() %>/TestList.action">成績参照</a></li><br>
        <li><a href="<%= request.getContextPath() %>/SubjectList.action">科目管理</a></li>
    </ul>
</div>
