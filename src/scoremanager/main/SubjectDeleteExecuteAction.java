package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        String subcd = request.getParameter("subcd");

        Subject subject = new Subject();
        subject.setCd(subcd);

        SubjectDao dao = new SubjectDao();
        dao.delete(subject);

        return "subject_delete_done.jsp";
    }
}