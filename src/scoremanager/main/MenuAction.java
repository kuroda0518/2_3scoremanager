package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Teacher;
import tool.Action;

public class MenuAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	// 例: ログイン成功時の LoginAction
        Teacher user = (Teacher) request.getSession().getAttribute("loginUser");
        if (user == null) {
            response.sendRedirect("Login.action");
            return null;
        }

        return "main.jsp";  // /common/main_menu.jsp にあるならパスを調整
    }
}
