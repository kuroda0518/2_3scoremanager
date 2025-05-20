package scoremanager.main;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestListStudentExecuteAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // ログインユーザーから学校コード取得
        Teacher teacher = (Teacher) request.getSession().getAttribute("loginUser");
        String schoolCd = teacher.getSchoolCd();

     // セレクトボックス用データ取得
        StudentDao studentDao = new StudentDao();
        SubjectDao subjectDao = new SubjectDao();

        List<Integer> noList = studentDao.getNoList(schoolCd);//これいらないかも
        List<Integer> entYearList = studentDao.getEntYearList(schoolCd);
        List<String> classNumList = studentDao.getClassNumList(schoolCd);
        List<Subject> subjectList = subjectDao.filter(schoolCd);

        // JSPに渡す
        request.setAttribute("noList", noList);//これいらないかも
        request.setAttribute("entYearList", entYearList);
        request.setAttribute("classNumList", classNumList);
        request.setAttribute("subjectList", subjectList);



        // 学生番号を取得
        String studentNo = request.getParameter("stu");

        // 学生情報取得（氏名のため）
        Student student = studentDao.findOne(schoolCd, studentNo);

        // 学生情報が存在しない場合 → 「入力して学生はいませんでした」エラーメッセージを設定
        if (student == null) {
            request.setAttribute("error", "入力された学生番号の学生はいませんでした");
            return "/common/test_list.jsp"; // 画像に合わせて遷移先を修正する可能性あり
        }

        request.setAttribute("student", student); // ← JSPに氏名表示するため

        // 成績情報取得
        TestDao testDao = new TestDao();
        List<Test> testList = testDao.findByStudentNo(studentNo);

        if (testList.isEmpty()) {
            request.setAttribute("error", "成績情報が存在しませんでした");
            return "/common/test_list.jsp";
        }

        request.setAttribute("testList", testList);
        return "/common/test_list_student.jsp"; // 画像に合わせて遷移先を修正する可能性あり
    }
}
