package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 学生番号を取得
        String no = request.getParameter("no");

        // 学生情報を取得
        StudentDao sDao = new StudentDao();
        Student student = sDao.find(no);  // ← get 

        // 学校コードに一致するクラス一覧を取得
        ClassNumDao cDao = new ClassNumDao();
        List<Class> classList = cDao.getBySchool(student.getSchoolCd()); 

        // 画面に渡す
        request.setAttribute("student", student);
        request.setAttribute("classList", classList);

        return "student_update.jsp";
    }
}
