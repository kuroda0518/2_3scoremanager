package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String no = request.getParameter("no");

        StudentDao sDao = new StudentDao();
        Student student = sDao.find(no);

        Teacher user = (Teacher) request.getSession().getAttribute("loginUser");
        if (user == null) {
            response.sendRedirect("Login.action");
            return null;
        }

        String schoolCd = user.getSchoolCd();

        request.setAttribute("student", student);
        ClassNumDao classNumDao = new ClassNumDao();
        List<String> classNumList = classNumDao.findAll(schoolCd);
        request.setAttribute("classNumList", classNumList);

        return "student_update.jsp";
    }
}
