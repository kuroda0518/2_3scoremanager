package scoremanager.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;
import tool.Action;

public class StudentRegisterAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String no = request.getParameter("no");
        String name = request.getParameter("name");
        String entYearStr = request.getParameter("entYear");
        String classNum = request.getParameter("classNum");
        String isAttendStr = request.getParameter("isAttend");
        String schoolCd = request.getParameter("schoolCd");

        boolean hasError = false;

        // 年度リスト生成
        List<Integer> entYearList = new ArrayList<>();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 0; i <= 10; i++) {
            entYearList.add(currentYear - i);
        }
        request.setAttribute("entYearList", entYearList);

        // 入力値保持
        request.setAttribute("entYear", entYearStr != null && !entYearStr.isEmpty() ? Integer.parseInt(entYearStr) : null);
        request.setAttribute("no", no);
        request.setAttribute("name", name);
        request.setAttribute("classNum", classNum);
        request.setAttribute("isAttend", isAttendStr);
        request.setAttribute("schoolCd", schoolCd);

        // 入学年度バリデーション
        if (entYearStr == null || entYearStr.isEmpty()) {
            request.setAttribute("entYearError", "入学年度を選択してください");
            hasError = true;
        }

        // 学生番号重複チェック
        try {
            StudentDao dao = new StudentDao();
            if (dao.find(no) != null) {
                request.setAttribute("noError", "学生番号が重複しています");
                hasError = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "エラーが発生しました");
            return "student_register_result.jsp";
        }
        if (hasError) {
            request.setAttribute("entYearList", entYearList); // ← これを追加
            return "student_register.jsp";
        }

        // 登録処理
        try {
            Student student = new Student();
            student.setNo(no);
            student.setName(name);
            student.setEntYear(Integer.parseInt(entYearStr));
            student.setClassNum(classNum);
            student.setIsAttend(isAttendStr != null);
            student.setSchoolCd(schoolCd);

            StudentDao dao = new StudentDao();
            dao.insert(student);

            request.setAttribute("message", "学生を登録しました。");
            return "student_register_result.jsp";

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "登録中にエラーが発生しました。");
            return "student_register_result.jsp";
        }
    }
}
