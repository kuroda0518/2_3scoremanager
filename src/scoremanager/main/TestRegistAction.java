package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Test;
import dao.TestDao;
import tool.Action;

public class TestRegistAction extends Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        if ("POST".equalsIgnoreCase(req.getMethod())) {
            String[] studentNos = req.getParameterValues("studentNo");
            String[] points = req.getParameterValues("point");
            String subjectCd = req.getParameter("subject");
            String testStr = req.getParameter("test");

            if (subjectCd == null || testStr == null || subjectCd.isEmpty() || testStr.isEmpty()) {
                req.setAttribute("error", "科目または回数が指定されていません。");
                return "/common/test_regist.jsp";
            }

            int testNo = Integer.parseInt(testStr);
            TestDao testDao = new TestDao();

            for (int i = 0; i < studentNos.length; i++) {
                String studentNo = studentNos[i];
                String pointStr = points[i];

                if (pointStr == null || pointStr.trim().isEmpty()) continue;

                int point;
                try {
                    point = Integer.parseInt(pointStr.trim());
                    if (point < 0 || point > 100) throw new NumberFormatException();
                } catch (NumberFormatException e) {
                    req.setAttribute("error", "0～100の範囲で入力してください");
                    return "/common/test_regist.jsp";
                }

                Test test = new Test();
                test.setStudentNo(studentNo);
                test.setSubjectCd(subjectCd);
                test.setNo(testNo);
                test.setPoint(point);
                test.setSchoolCd("001");
                test.setClassNum(req.getParameter("classNum"));

                testDao.updateOrDelete(test);
            }

            return "/common/regist_done.jsp";
        }

        String entYearStr = req.getParameter("entYear");
        String classNum = req.getParameter("classNum");
        String subjectCd = req.getParameter("subject");
        String testStr = req.getParameter("test");

        if (entYearStr == null || classNum == null || subjectCd == null || testStr == null ||
            entYearStr.isEmpty() || classNum.isEmpty() || subjectCd.isEmpty() || testStr.isEmpty()) {
            req.setAttribute("error", "すべての項目を選択してください");
            return "/common/test_regist.jsp";
        }

        int entYear = Integer.parseInt(entYearStr);
        int testNo = Integer.parseInt(testStr);

        TestDao testDao = new TestDao();
        List<Test> students = testDao.filter("001", entYear, classNum, subjectCd, testNo);

        if (students.isEmpty()) {
            req.setAttribute("error", "学生情報が存在しませんでした");
            return "/common/test_regist.jsp";
        }

        req.setAttribute("students", students);
        req.setAttribute("subjectId", subjectCd);
        req.setAttribute("testId", testNo);
        req.setAttribute("classNum", classNum);

        return "/common/regist_done.jsp";
    }
}

