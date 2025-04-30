package scoremanager.main;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        String no = request.getParameter("no");
        String name = request.getParameter("name");
        String classNum = request.getParameter("class_num");
        String entYearStr = request.getParameter("ent_year");
        String isAttendParam = request.getParameter("is_attend");

        if (name == null || name.trim().isEmpty()) {
            request.setAttribute("message", "氏名を入力してください");
            StudentDao dao = new StudentDao();
            Student student = dao.find(no);
            request.setAttribute("student", student);
            return "student_update.jsp";
        }

        // ログイン中の教員から schoolCd を取得
        Teacher user = (Teacher) request.getSession().getAttribute("user");
        String schoolCd = user.getSchoolCd();

        Student student = new Student();
        student.setNo(no);
        student.setName(name);
        student.setClassNum(classNum);
        student.setEntYear(Integer.parseInt(entYearStr));
        student.setIsAttend(isAttendParam != null);
        student.setSchoolCd(schoolCd);

        StudentDao dao = new StudentDao();
        dao.update(student);

        return "student_update_done.jsp";
    }
}
