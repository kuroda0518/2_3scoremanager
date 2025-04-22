package tool;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();

        // 未ログインかつログイン関連画面以外にアクセスしたらリダイレクト
        if ((session == null || session.getAttribute("loginUser") == null)
                && !uri.contains("Login") && !uri.contains("login.jsp")) {

            response.sendRedirect(request.getContextPath() + "/login/login.jsp");
            return;
        }

        chain.doFilter(req, res); // 通過
    }

    public void init(FilterConfig config) throws ServletException {}
    public void destroy() {}
}
