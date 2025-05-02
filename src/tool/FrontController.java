package tool;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
            // リクエストされたURLのパスからアクション名を抽出（例：/main/Login.action → Login）
            String path = request.getServletPath()
                    .replaceFirst("^/", "")       // 先頭のスラッシュ削除
                    .replace(".action", "");      // 拡張子を除去

            // スラッシュ区切りがある場合は最後の部分だけ取り出す
            int lastSlash = path.lastIndexOf('/');
            if (lastSlash != -1) {
                path = path.substring(lastSlash + 1);
            }

            // デフォルトパッケージ
            String packageName = "scoremanager.main";

            // ログイン・ログアウトは login パッケージで処理
            if (path.startsWith("Login") || path.startsWith("Logout")) {
                packageName = "scoremanager.login";
            }

            // 完全なクラス名を組み立て（例：scoremanager.login.LoginAction）
            String className = packageName + "." + path + "Action";

            // Actionクラスを動的に呼び出して実行
            Action action = (Action) Class.forName(className).getDeclaredConstructor().newInstance();
            String view = action.execute(request, response);

            // JSPのパスが返ってきた場合はフォワード（nullや空文字なら何もしない）
            if (view != null && !view.isEmpty()) {
                String jspPath = view.startsWith("/") ? view : "/common/" + view;
                RequestDispatcher dispatcher = request.getRequestDispatcher(jspPath);
                dispatcher.forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace(); // ログ出力
            throw new ServletException("FrontController Error: " + e.getMessage(), e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
