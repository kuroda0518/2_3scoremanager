package tool;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.action")
public class FrontController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // パスを取得（例：StudentRegister）
            String path = request.getServletPath().replaceFirst("^/", "").replace(".action", "");

            // パッケージ判定
            String packageName = "scoremanager.main";
            if (path.startsWith("Login") || path.startsWith("Logout")) {
                packageName = "scoremanager.login";
            }

            // クラス名を組み立て
            String className = packageName + "." + path + "Action";

            // Actionクラスを実行
            Action action = (Action) Class.forName(className).getDeclaredConstructor().newInstance();
            String view = action.execute(request, response);

            // JSPへフォワード
            if (view.startsWith("login")) {
                request.getRequestDispatcher("/login/" + view).forward(request, response);
            } else {
                request.getRequestDispatcher("/common/" + view).forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
