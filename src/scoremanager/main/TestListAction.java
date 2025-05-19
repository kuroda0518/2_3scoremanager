package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import bean.Teacher;
import dao.StudentDao;
import dao.SubjectDao;
import tool.Action;

public class TestListAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        // ログインユーザーから学校コード取得
        Teacher teacher = (Teacher) request.getSession().getAttribute("loginUser");
        String schoolCd = teacher.getSchoolCd();

        // セレクトボックス用データ取得
        StudentDao studentDao = new StudentDao();
        SubjectDao subjectDao = new SubjectDao();

        List<Integer> noList = studentDao.getNoList(schoolCd);
        List<Integer> entYearList = studentDao.getEntYearList(schoolCd);
        List<String> classNumList = studentDao.getClassNumList(schoolCd);
        List<Subject> subjectList = subjectDao.filter(schoolCd);

        // JSPに渡す
        request.setAttribute("noList", noList);
        request.setAttribute("entYearList", entYearList);
        request.setAttribute("classNumList", classNumList);
        request.setAttribute("subjectList", subjectList);

        return "test_list.jsp";
    }
}
