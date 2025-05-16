package scoremanager.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;
import dao.SubjectDao; // 追加
import dao.TestDao;
import tool.Action;

public class TestListStudentAction extends Action {
    public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // ▼▼ プルダウン用データをDBから取得 ▼▼
        StudentDao studentDao = new StudentDao();
        SubjectDao subjectDao = new SubjectDao();

        List<String> entYearList = studentDao.findDistinctEntYears();
        List<String> classNumList = studentDao.findDistinctClassNums();
        List<Map<String, Object>> subjectList = subjectDao.findAll();

        req.setAttribute("entYearList", entYearList);
        req.setAttribute("classNumList", classNumList);
        req.setAttribute("subjectList", subjectList);

        // ▼▼ フォーム入力値取得 ▼▼
        String entYearStr = req.getParameter("entYear");
        String classNum = req.getParameter("classNum");
        String subjectIdStr = req.getParameter("subject");

        if (entYearStr == null || classNum == null || subjectIdStr == null ||
            entYearStr.isEmpty() || classNum.isEmpty() || subjectIdStr.isEmpty()) {
            req.setAttribute("error", "入学年度とクラスと科目を選択してください");
            return "/common/test_list_student.jsp";
        }

        int entYear = Integer.parseInt(entYearStr);
        int subjectId = Integer.parseInt(subjectIdStr);

        List<Student> students = studentDao.filter("001", entYear, classNum, true);

        if (students.isEmpty()) {
            req.setAttribute("error", "学生情報が存在しませんでした");
            return "/common/test_list_student.jsp";
        }

        if (subjectList == null || subjectList.isEmpty()) {
            req.setAttribute("error", "科目情報が存在しません");
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

