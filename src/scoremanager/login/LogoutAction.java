package scoremanager.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Action;

public class LogoutAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();

        // loginUser を使うように修正
        if (session.getAttribute("loginUser") != null) {
            session.removeAttribute("loginUser");
            return "/main/logout-out.jsp"; // ← /common/ にある前提
        }

        return "logout-error.jsp";
    }
}
