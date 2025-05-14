package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import tool.Action;

public class TestRegistAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        // ログイン中の教員から学校コードを取得
        Teacher teacher = (Teacher) request.getSession().getAttribute("loginUser");
        String schoolCd = teacher.getSchoolCd();

        // クラス一覧と科目一覧を取得
        ClassNumDao classDao = new ClassNumDao();
        SubjectDao subjectDao = new SubjectDao();

        List<String> classNumList = classDao.filterClassNumOnly(schoolCd); // クラス番号のみのリストを想定
        List<Subject> subjectList = subjectDao.filter(schoolCd);

        // セレクトボックス用に属性をセット（セッション or リクエスト）
        request.setAttribute("entYearList", new int[] {2021, 2022, 2023, 2024, 2025});
        request.setAttribute("classNumList", classNumList);
        request.setAttribute("subjectList", subjectList);

        return "test_regist.jsp";
    }
}
