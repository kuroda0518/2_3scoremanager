package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        String subcd = request.getParameter("subcd");       // 科目コード（変更不可）
        String subname = request.getParameter("subname");   // 科目名

        if (subname == null || subname.trim().isEmpty()) {
            request.setAttribute("message", "科目名を入力してください");
            SubjectDao dao = new SubjectDao();
            Subject subject = dao.cdSelect(subcd);
            request.setAttribute("subject", subject);
            return "subject_update.jsp";
        }

        // ログイン中の教員から schoolCd を取得
        Teacher user = (Teacher) request.getSession().getAttribute("loginUser");
        String schoolCd = user.getSchoolCd();

        Subject subject = new Subject();
        subject.setCd(subcd);
        subject.setName(subname);
        subject.setSchoolCd(schoolCd);

        SubjectDao dao = new SubjectDao();
        dao.update(subject);

        return "subject_update_done.jsp";
    }
}