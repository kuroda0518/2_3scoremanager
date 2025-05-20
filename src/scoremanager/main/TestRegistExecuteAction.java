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

        int entYear = Integer.parseInt(request.getParameter("entYear"));
        String classNum = request.getParameter("classNum");
        String subjectCd = request.getParameter("subject");
        int no = Integer.parseInt(request.getParameter("no"));

        Teacher teacher = (Teacher) request.getSession().getAttribute("loginUser");
        String schoolCd = teacher.getSchoolCd();

        SubjectDao subjectDao = new SubjectDao();
        TestDao testDao = new TestDao();
        StudentDao studentDao = new StudentDao();

        List<Test> studentList = testDao.filter(schoolCd, entYear, classNum, subjectCd, no);
        List<Subject> subjectList = subjectDao.filter(schoolCd);
        List<Integer> entYearList = studentDao.getEntYearList(schoolCd);
        List<String> classNumList = studentDao.getClassNumList(schoolCd);

        request.setAttribute("studentList", studentList);
        request.setAttribute("subjectList", subjectList);
        request.setAttribute("entYearList", entYearList);
        request.setAttribute("classNumList",classNumList);

        request.setAttribute("entYear", entYear);
        request.setAttribute("classNum", classNum);
        request.setAttribute("subject", subjectCd);
        request.setAttribute("no", no);

        return "test_regist.jsp";
    }
}
