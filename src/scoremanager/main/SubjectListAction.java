package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectListAction extends Action {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    request.setCharacterEncoding("UTF-8");

	    // セッションからログイン中の先生を取得
	    Teacher user = (Teacher) request.getSession().getAttribute("user");
	    if (user == null) {
	        response.sendRedirect("Login.action");
	        return null;
	    }

	    String schoolCd = user.getSchoolCd();  // ← ここで学校コード取得！

	    SubjectDao dao = new SubjectDao();
	    List<Subject> subject = dao.selectBySchool(schoolCd);  // ← こっちを使う！

	    request.setAttribute("subject", subject);

	    return "subject_list.jsp";
	}

}