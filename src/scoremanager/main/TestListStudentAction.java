package scoremanager.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;
import dao.TestDao;
import tool.Action;

public class TestListStudentAction extends Action {
    public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // ▼▼ プルダウン用データを先に用意 ▼▼

    	List<String> entYearList = new ArrayList<>();
    	entYearList.add("2021");
    	entYearList.add("2022");
    	entYearList.add("2023");
    	req.setAttribute("entYearList", entYearList);

    	List<String> classNumList = new ArrayList<>();
    	classNumList.add("200");
    	classNumList.add("201");
    	classNumList.add("202");
    	req.setAttribute("classNumList", classNumList);

    	List<Map<String, Object>> subjectList = new ArrayList<>();
    	Map<String, Object> subj1 = new HashMap<>();
    	subj1.put("id", 1);
    	subj1.put("name", "情報処理基礎");
    	subjectList.add(subj1);

    	Map<String, Object> subj2 = new HashMap<>();
    	subj2.put("id", 2);
    	subj2.put("name", "情報処理応用");
    	subjectList.add(subj2);

    	Map<String, Object> subj3 = new HashMap<>();
    	subj3.put("id", 3);
    	subj3.put("name", "ゲーム");
    	subjectList.add(subj3);

    	req.setAttribute("subjectList", subjectList);



        // ▼▼ フォーム入力値取得 ▼▼
        String entYearStr = req.getParameter("entYear");
        String classNum = req.getParameter("classNum");
        String subjectIdStr = req.getParameter("subject");

        // ▼▼ 初期表示・未入力エラー処理 ▼▼
        if (entYearStr == null || classNum == null || subjectIdStr == null ||
            entYearStr.isEmpty() || classNum.isEmpty() || subjectIdStr.isEmpty()) {
            req.setAttribute("error", "入学年度とクラスと科目を選択してください");
            return "/common/test_list_student.jsp";
        }

        // ▼▼ 画面用データ取得処理 ▼▼
        int entYear = Integer.parseInt(entYearStr);
        int subjectId = Integer.parseInt(subjectIdStr);

        StudentDao studentDao = new StudentDao();
        List<Student> students = studentDao.filter("001", entYear, classNum, true); // schoolCd="001", isAttend=true

        if (students.isEmpty()) {
            req.setAttribute("error", "学生情報が存在しませんでした");
            return "/common/test_list_student.jsp";
        }

        TestDao testDao = new TestDao();
        List<Map<String, Object>> result = new ArrayList<>();

        for (Student s : students) {
            Map<String, Object> row = new HashMap<>();
            row.put("student", s);
            row.put("point1", testDao.findPoint(s.getNo(), subjectId, 1));
            row.put("point2", testDao.findPoint(s.getNo(), subjectId, 2));
            result.add(row);
        }

        req.setAttribute("studentList", result);
        return "/common/test_list_student.jsp";
    }
}

