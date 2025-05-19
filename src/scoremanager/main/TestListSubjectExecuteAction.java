package scoremanager.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // ログインユーザーから学校コード取得
        Teacher teacher = (Teacher) request.getSession().getAttribute("loginUser");
        String schoolCd = teacher.getSchoolCd();

        // セレクトボックス用データ取得
        StudentDao studentDao = new StudentDao();
        SubjectDao subjectDao = new SubjectDao();

        List<Integer> entYearList = studentDao.getEntYearList(schoolCd);
        List<String> classNumList = studentDao.getClassNumList(schoolCd);
        List<Subject> subjectList = subjectDao.filter(schoolCd);

        // JSPに渡す
        request.setAttribute("entYearList", entYearList);
        request.setAttribute("classNumList", classNumList);
        request.setAttribute("subjectList", subjectList);

        // パラメータ取得
        String entYearStr = request.getParameter("entYear");
        String classNum = request.getParameter("classNum");
        String subjectCd = request.getParameter("subject");

        // 入力値チェック
        if (entYearStr == null || entYearStr.isEmpty() ||
            classNum == null || classNum.isEmpty() ||
            subjectCd == null || subjectCd.isEmpty()) {
            request.setAttribute("error", "すべての項目を選択してください。");
            return "test_list.jsp"; // 元の画面に戻す
        }

        int entYear = Integer.parseInt(entYearStr);

        // DAOから取得
        TestDao dao = new TestDao();
        List<Test> tests = dao.getClassAndSubject(entYear, classNum, subjectCd);

        // 該当する成績情報がなかった場合のエラー処理
        if (tests.isEmpty()) {
            request.setAttribute("error", "指定された条件の成績情報は見つかりませんでした。");
            return "test_list.jsp"; // 元の画面に戻す
        }

        // 表示用リスト（Beanは使わずMap）
        Map<String, Map<String, Object>> studentMap = new LinkedHashMap<>();

        for (Test t : tests) {
            String studentNo = t.getStudentNo();
            Map<String, Object> data = studentMap.get(studentNo);

            if (data == null) {
                data = new HashMap<>();
                data.put("studentNo", t.getStudentNo());
                data.put("name", t.getName());
                data.put("entYear", t.getEntYear());
                data.put("classNum", t.getClassNum());
                data.put("point1", "-");
                data.put("point2", "-");
                studentMap.put(studentNo, data);
            }

            if (t.getNo() == 1) {
                data.put("point1", t.getPoint());
            } else if (t.getNo() == 2) {
                data.put("point2", t.getPoint());
            }
        }

        // Map → List に変換してJSPに渡す
        List<Map<String, Object>> displayList = new ArrayList<>(studentMap.values());
        request.setAttribute("testDisplayList", displayList);

        // 画面で再表示するためにセレクトボックスの値保持
        request.setAttribute("selectedEntYear", entYear);
        request.setAttribute("selectedClassNum", classNum);
        request.setAttribute("selectedSubjectCd", subjectCd);

        return "test_list_subject.jsp";
    }
}