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
            // リクエストパスからアクション名取得（例：StudentList.action → StudentList）
            String path = request.getServletPath().replaceFirst("^/", "").replace(".action", "");

            // パッケージを固定（すべて scoremanager.main として処理）
            String packageName = "scoremanager.main";
            String className = packageName + "." + path + "Action";

            // 対応する Action クラスを読み込んで実行
            Action action = (Action) Class.forName(className).getDeclaredConstructor().newInstance();
            String view = action.execute(request, response);

            // null や 空文字なら何もフォワードしない（リダイレクト処理などを想定）
            if (view == null || view.isEmpty()) return;

            // 画面ファイルへのパス（常に /common 配下にあるとする）
            RequestDispatcher dispatcher = request.getRequestDispatcher("/common/" + view);
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace(); // コンソールに詳細表示
            throw new ServletException("FrontController Error: " + e.getMessage(), e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
