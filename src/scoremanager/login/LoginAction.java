package scoremanager.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class LoginAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        if (id == null || id.isEmpty() || password == null || password.isEmpty()) {
            request.setAttribute("error", "IDまたはパスワードが未入力です");
            return "login.jsp";
        }

        // 認証処理に渡す
        request.setAttribute("id", id);
        request.setAttribute("password", password);
        return "LoginExecute.action";
    }
}

