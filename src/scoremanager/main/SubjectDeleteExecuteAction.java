package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        String subcd = request.getParameter("subcd");       // 科目コード（変更不可）



        // ログイン中の教員から schoolCd を取得
        Teacher user = (Teacher) request.getSession().getAttribute("loginUser");
        String schoolCd = user.getSchoolCd();

        Subject subject = new Subject();
        subject.setCd(subcd);
        subject.setSchoolCd(schoolCd);

        SubjectDao dao = new SubjectDao();
        dao.delete(subject);

        return "subject_update_result.jsp";
    }
}