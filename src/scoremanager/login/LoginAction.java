package scoremanager.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDao;
import tool.Action;

public class LoginAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();

        String id = request.getParameter("id");
        String password = request.getParameter("password");

        try {
            TeacherDao dao = new TeacherDao();
            Teacher teacher = dao.login(id, password);

            if (teacher != null) {
                session.setAttribute("loginUser", teacher);
                return "/common/main.jsp";
            } else {
                // ログイン失敗（IDかパスワードが間違っている）
                request.setAttribute("error", "IDまたはパスワードが間違っています。");
                return "/main/login.jsp";
            }

        } catch (Exception e) {
            // データベース接続エラーなどが起きた場合
            e.printStackTrace();  // ログに出す（開発用）
            request.setAttribute("errorMessage", "システムエラーが発生しました。管理者に連絡してください。");
            return "/common/error.jsp";  // エラー画面に遷移
        }
    }
}
