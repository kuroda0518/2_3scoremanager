package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Test;
import dao.TestDao;
import tool.Action;

public class TestRegistAction extends Action {
    public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String[] studentNos = req.getParameterValues("studentNo");
        String[] points = req.getParameterValues("point");
        String subjectStr = req.getParameter("subject");
        String testStr = req.getParameter("test");

        if (subjectStr == null || testStr == null || subjectStr.isEmpty() || testStr.isEmpty()) {
            req.setAttribute("error", "科目または回数が指定されていません。");
            return "/common/regist.jsp";
        }

        int subjectId = Integer.parseInt(subjectStr);
        int testId = Integer.parseInt(testStr);

        TestDao dao = new TestDao();
        for (int i = 0; i < studentNos.length; i++) {
            if (points[i] == null || points[i].trim().equals("")) continue;

            int point;
            try {
                point = Integer.parseInt(points[i].trim());
                if (point < 0 || point > 100) throw new NumberFormatException();
            } catch (NumberFormatException e) {
                req.setAttribute("error", "0～100の範囲で入力してください");
                return "/common/regist.jsp";
            }

            Test test = new Test();
            test.setStudentNo(studentNos[i]);
            test.setSubjectId(subjectId);
            test.setTestId(testId);
            test.setPoint(point);
            dao.save(test);
        }
        return "/common/regist_done.jsp";
    }
}
