package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String no = request.getParameter("no");

        StudentDao sDao = new StudentDao();
        Student student = sDao.find(no);

        request.setAttribute("student", student);

        return "student_update.jsp";
    }
}
