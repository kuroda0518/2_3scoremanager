package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import bean.Teacher;
import bean.Test;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistDoneAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        // パラメータ取得（null/空チェック）
        String entYearStr = request.getParameter("entYear");
        String classNum = request.getParameter("classNum");
        String subjectCd = request.getParameter("subject");
        String testNoStr = request.getParameter("no");

        if (entYearStr == null || entYearStr.isEmpty() ||
            classNum == null || classNum.isEmpty() ||
            subjectCd == null || subjectCd.isEmpty() ||
            testNoStr == null || testNoStr.isEmpty()) {
            request.setAttribute("message", "入力が不足しています（再度検索してください）");
            return "test_regist.jsp";
        }

        int entYear = Integer.parseInt(entYearStr);
        int testNo = Integer.parseInt(testNoStr);

        // ログイン教員情報
        Teacher teacher = (Teacher) request.getSession().getAttribute("loginUser");
        String schoolCd = teacher.getSchoolCd();

        // 対象学生リスト
        StudentDao studentDao = new StudentDao();
        List<Student> students = studentDao.filter(schoolCd, entYear, classNum, true);

        // 更新・削除処理
        TestDao testDao = new TestDao();
        for (Student stu : students) {
            String studentNo = stu.getNo();
            String pointParam = request.getParameter("point_" + studentNo);

            int point = -1;
            if (pointParam != null && !pointParam.trim().isEmpty()) {
                try {
                    point = Integer.parseInt(pointParam);
                    if (point < 0 || point > 100) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e) {
                    request.setAttribute("message", "点数は0〜100の間で入力してください");
                    return "test_regist.jsp";
                }
            }

            Test test = new Test();
            test.setStudentNo(studentNo);
            test.setSubjectCd(subjectCd);
            test.setNo(testNo);
            test.setPoint(point);
            test.setSchoolCd(schoolCd);
            test.setClassNum(classNum);

            testDao.updateOrDelete(test);
        }

        // 完了画面表示
        request.setAttribute("message", "登録が完了しました");
        request.setAttribute("entYear", entYear);
        request.setAttribute("classNum", classNum);
        request.setAttribute("subject", subjectCd);
        request.setAttribute("no", testNo);

     // セレクトボックス用リストを取得
        request.setAttribute("entYearList", studentDao.getEntYearList(schoolCd));      // 追加
        request.setAttribute("classNumList", studentDao.getClassNumList(schoolCd));    // 追加
        request.setAttribute("subjectList", new SubjectDao().filter(schoolCd));        // 追加


        return "test_regist_done.jsp";
    }
}
