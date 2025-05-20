package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistExecuteAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        String entYearStr = request.getParameter("entYear");
        String classNum = request.getParameter("classNum");
        String subjectCd = request.getParameter("subject");
        String testIdStr = request.getParameter("no");

        // ログイン中の教員情報
        Teacher teacher = (Teacher) request.getSession().getAttribute("loginUser");
        String schoolCd = teacher.getSchoolCd();

        // セレクトボックス表示用のリスト（失敗時も必要なので最初に取得）
        StudentDao studentDao = new StudentDao();
        SubjectDao subjectDao = new SubjectDao();
        List<Integer> entYearList = studentDao.getEntYearList(schoolCd);
        List<String> classNumList = studentDao.getClassNumList(schoolCd);
        List<Subject> subjectList = subjectDao.filter(schoolCd);

        request.setAttribute("entYearList", entYearList);
        request.setAttribute("classNumList", classNumList);
        request.setAttribute("subjectList", subjectList);

        // 入力値の保持（失敗時も表示させる）
        request.setAttribute("entYear", entYearStr);
        request.setAttribute("classNum", classNum);
        request.setAttribute("subject", subjectCd);
        request.setAttribute("no", testIdStr);

        // 入力チェック（未選択）
        if (entYearStr == null || entYearStr.isEmpty() ||
            classNum == null || classNum.isEmpty() ||
            subjectCd == null || subjectCd.isEmpty() ||
            testIdStr == null || testIdStr.isEmpty()) {

            request.setAttribute("message", "すべての項目を選択してください。");
            return "test_regist.jsp";
        }

        // 科目名の取得
        String subjectName = "";
        for (Subject sub : subjectList) {
            if (sub.getCd().equals(subjectCd)) {
                subjectName = sub.getName();
                break;
            }
        }
        request.setAttribute("subjectName", subjectName);

        // パース
        int entYear = Integer.parseInt(entYearStr);
        int testId = Integer.parseInt(testIdStr);

        // データ取得
        TestDao testDao = new TestDao();
        List<Test> studentList = testDao.filter(schoolCd, entYear, classNum, subjectCd, testId);

        request.setAttribute("studentList", studentList);

        return "test_regist.jsp";
    }
}