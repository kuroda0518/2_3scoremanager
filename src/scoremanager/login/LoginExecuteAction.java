package scoremanager.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDao;
import tool.Action;

public class LoginExecuteAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = (String) request.getAttribute("id");
        String password = (String) request.getAttribute("password");

        TeacherDao dao = new TeacherDao();
        Teacher teacher = dao.login(id, password);

        if (teacher == null) {
            request.setAttribute("error", "IDまたはパスワードが違います");
            return "login.jsp";
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", teacher);

        return "student_list.jsp";
    }
}
