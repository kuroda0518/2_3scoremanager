package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectListAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        SubjectDao dao = new SubjectDao();

        // 科目一覧
        List<Subject> subject = dao.selectAll();

        // ▼ リクエストに格納
        request.setAttribute("subject", subject);

        return "subject_list.jsp";
    }
}