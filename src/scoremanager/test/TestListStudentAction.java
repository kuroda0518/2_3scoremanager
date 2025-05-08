package scoremanager.test;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.TestListStudent;
import dao.TestListStudentDao;
import tool.Action;

public class TestListStudentAction extends Action {
    public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String entYear = req.getParameter("entYear");
        String classNum = req.getParameter("classNum");
        String subjectIdStr = req.getParameter("subject");

        if (entYear == null || classNum == null || subjectIdStr == null ||
            entYear.isEmpty() || classNum.isEmpty() || subjectIdStr.isEmpty()) {
            req.setAttribute("error", "入学年度とクラスと科目を選択してください");
            return "jsp/test_list_student.jsp";
        }

        int subjectId = Integer.parseInt(subjectIdStr);

        TestListStudentDao dao = new TestListStudentDao();
        List<TestListStudent> students = dao.findByClassAndSubject(entYear, classNum, subjectId);

        if (students.isEmpty()) {
            req.setAttribute("error", "学生情報が存在しませんでした");
        }

        req.setAttribute("studentList", students);
        return "jsp/test_list_student.jsp";
    }
}