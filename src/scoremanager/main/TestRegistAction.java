package scoremanager.main;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import tool.Action;

public class TestRegistAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // フォームからの入力取得
        String entYearStr = request.getParameter("entYear");
        String classNum = request.getParameter("classNum");
        String isAttendStr = request.getParameter("isAttend");

        // セッションからログイン中のユーザー取得

        Teacher user = (Teacher) request.getSession().getAttribute("loginUser");
        if (user == null) {
            response.sendRedirect("Login.action");
            return null;
        }
        String schoolCd = user.getSchoolCd();


        // 型変換
        Integer entYear = (entYearStr != null && !entYearStr.isEmpty()) ? Integer.parseInt(entYearStr) : null;
        Boolean isAttend = (isAttendStr != null) ? true : null;

        // 学生リスト取得
        StudentDao studentDao = new StudentDao();
        List<Student> studentList = studentDao.filter(schoolCd, entYear, classNum, isAttend);
        request.setAttribute("studentList", studentList);

        // 入学年度リスト
        List<Integer> entYearList = new ArrayList<>();
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = thisYear - 10; i <= thisYear + 1; i++) {
            entYearList.add(i);
        }
        request.setAttribute("entYearList", entYearList);

        // 科目リスト取得
        SubjectDao subjectDao = new SubjectDao();
        List<String> subjectList =new ArrayList<>();
        request.setAttribute("subjectList", subjectList);

        // クラス番号リスト
        ClassNumDao classNumDao = new ClassNumDao();
        List<String> classNumList = classNumDao.findAll(schoolCd);
        request.setAttribute("classNumList", classNumList);

        return "test_list.jsp";
    }
}
