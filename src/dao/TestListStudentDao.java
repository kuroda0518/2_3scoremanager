package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.TestListStudent;

public class TestListStudentDao extends Dao {

    public List<TestListStudent> findByClassAndSubject(String entYear, String classNum, int subjectId) throws Exception {
        List<TestListStudent> list = new ArrayList<>();

        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(
            "SELECT s.ent_year, s.class_num, s.no, s.name, " +
            "MAX(CASE WHEN t.test_id = 1 THEN t.point END) AS first_score, " +
            "MAX(CASE WHEN t.test_id = 2 THEN t.point END) AS second_score " +
            "FROM student s " +
            "LEFT JOIN test t ON s.no = t.student_no AND t.subject_id = ? " +
            "WHERE s.ent_year = ? AND s.class_num = ? " +
            "GROUP BY s.ent_year, s.class_num, s.no, s.name " +
            "ORDER BY s.no"
        );
        st.setInt(1, subjectId);
        st.setString(2, entYear);
        st.setString(3, classNum);

        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            TestListStudent s = new TestListStudent();
            s.setEntYear(rs.getString("ent_year"));
            s.setClassNum(rs.getString("class_num"));
            s.setStudentNo(rs.getString("no"));
            s.setStudentName(rs.getString("name"));
            s.setFirstScore((Integer) rs.getObject("first_score"));
            s.setSecondScore((Integer) rs.getObject("second_score"));
            list.add(s);
        }

        rs.close();
        st.close();
        con.close();

        return list;
    }
}