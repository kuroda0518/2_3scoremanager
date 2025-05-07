package scoremanager.main;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectCreateAction extends Action {

    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // ▼ ログイン中の先生から schoolCd を取得
        Teacher user = (Teacher) request.getSession().getAttribute("loginUser");
        String schoolCd = user.getSchoolCd();

        // ▼ 入力値取得
        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        boolean hasError = false;

        // ▼ 入力値保持（フォームに再表示用）
        request.setAttribute("cd", cd);
        request.setAttribute("name", name);

        // ▼ 科目コードバリデーション
        if (cd == null || cd.isEmpty()) {
            hasError = true;
        } else if (!cd.matches("[A-Z0-9]{3}")) {
            request.setAttribute("cdError", "科目コードは3文字の英数字で入力してください");
            hasError = true;
        } else {
            try {
                SubjectDao dao = new SubjectDao();
                List<Subject> existingSubjects = dao.selectBySchool(schoolCd);  // ← 学校ごとに限定
                for (Subject s : existingSubjects) {
                    if (s.getCd().equals(cd)) {
                        request.setAttribute("cdError", "その科目コードは既に登録されています");
                        hasError = true;
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("message", "エラーが発生しました。");
                return "subject_create_result.jsp";
            }
        }

        // ▼ 科目名バリデーション
        if (name == null || name.isEmpty()) {
            hasError = true;
        } else {
            try {
                SubjectDao dao = new SubjectDao();
                List<Subject> existingSubjects = dao.selectBySchool(schoolCd);  // ← 同じく限定
                for (Subject s : existingSubjects) {
                    if (s.getName().equals(name)) {
                        request.setAttribute("nameError", "その科目名は既に登録されています");
                        hasError = true;
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("message", "エラーが発生しました。");
                return "subject_create_result.jsp";
            }
        }

        if (hasError) {
            return "subject_create.jsp";
        }

        // ▼ 登録処理
        try {
            Subject subject = new Subject();
            subject.setSchoolCd(schoolCd);
            subject.setCd(cd);
            subject.setName(name);

            SubjectDao dao = new SubjectDao();
            dao.insert(subject);

            request.setAttribute("message", "科目を登録しました。");
            return "subject_create_result.jsp";

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "登録中にエラーが発生しました。");
            return "subject_create_result.jsp";
        }
    }
}
