package scoremanager.main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentListAction extends Action {

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // フォームからの入力値取得
        String entYearStr = request.getParameter("entYear");
        String classNum = request.getParameter("classNum");
        String isAttendStr = request.getParameter("isAttend");

        // ログイン中の教員から school_cd を取得（仮に固定なら "tes"）
        String schoolCd = "tes";

        // 型変換
        Integer entYear = (entYearStr != null && !entYearStr.isEmpty()) ? Integer.parseInt(entYearStr) : null;
        Boolean isAttend = (isAttendStr != null) ? true : null;

        // 学生データ取得
        StudentDao studentDao = new StudentDao();
        List<Student> studentList = studentDao.filter(schoolCd, entYear, classNum, isAttend);
        request.setAttribute("studentList", studentList);

        // 入学年度リスト（例：2015〜現在+1年）
        List<Integer> entYearList = new ArrayList<>();
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = thisYear - 10; i <= thisYear + 1; i++) {
            entYearList.add(i);
        }
        request.setAttribute("entYearList", entYearList);

        // クラス番号リスト（DBから取得）
        ClassNumDao classNumDao = new ClassNumDao();
        List<String> classNumList = classNumDao.findAll(schoolCd);
        request.setAttribute("classNumList", classNumList);

        // 表示するJSPへフォワード
        return "student_list.jsp";
    }
}
