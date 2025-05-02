package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String cd = request.getParameter("cd");

        SubjectDao sDao = new SubjectDao();
        Subject subject = sDao.cdSelect(cd);

        request.setAttribute("subject", subject);

        return "subject_update.jsp";
    }
}
