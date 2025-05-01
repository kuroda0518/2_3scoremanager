package scoremanager.main;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectCreateAction extends Action {

    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String schoolCd = request.getParameter("schoolCd");
        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        boolean hasError = false;

        // 入力値保持
        request.setAttribute("schoolCd", schoolCd);
        request.setAttribute("cd", cd);
        request.setAttribute("name", name);

        // 学校コードバリデーション
        if (schoolCd == null || schoolCd.isEmpty()) {
            hasError = true;
        }
        // 必要であれば、学校コードの形式チェックなどのバリデーションを追加

        // 科目コードバリデーション
        if (cd == null || cd.isEmpty()) {
            hasError = true;
        } else if (!cd.matches("[A-Z0-9]{3}")) {
            request.setAttribute("cdError", "科目コードは3文字の英数字で入力してください");
            hasError = true;
        } else {
            try {
                SubjectDao dao = new SubjectDao();
                List<Subject> existingSubjects = dao.selectAll();
                for (Subject s : existingSubjects) {
                    if (s.getSchoolCd() != null && s.getSchoolCd().equals(schoolCd) && s.getCd().equals(cd)) {
                        request.setAttribute("cdError", "その学校コードと科目コードの組み合わせは既に登録されています");
                        hasError = true;
                        break;
                    } else if (s.getSchoolCd() == null && schoolCd == null && s.getCd().equals(cd)) {
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

        // 科目名バリデーション
        if (name == null || name.isEmpty()) {
            hasError = true;
        } else {
            try {
                SubjectDao dao = new SubjectDao();
                List<Subject> existingSubjects = dao.selectAll();
                for (Subject s : existingSubjects) {
                    if (s.getSchoolCd() != null && s.getSchoolCd().equals(schoolCd) && s.getName().equals(name)) {
                        request.setAttribute("nameError", "その学校コードでは既に同じ科目名の科目が登録されています");
                        hasError = true;
                        break;
                    } else if (s.getSchoolCd() == null && schoolCd == null && s.getName().equals(name)) {
                        request.setAttribute("nameError", "既に同じ科目名の科目が登録されています");
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

        // 登録処理
        try {
            Subject subject = new Subject();
            subject.setSchoolCd(schoolCd);
            subject.setCd(cd);
            subject.setName(name);

            SubjectDao dao = new SubjectDao();
            dao.insert(subject);
            // 実際の登録処理に合わせて SubjectDao に insert メソッドを作成し、呼び出す必要があります。
            // 例: dao.insert(subject);

            request.setAttribute("message", "科目を登録しました。");
            return "subject_create_result.jsp";

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "登録中にエラーが発生しました。");
            return "subject_create_result.jsp";
        }
    }
}