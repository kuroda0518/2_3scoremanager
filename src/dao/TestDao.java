package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.Test;

public class TestDao extends Dao {

    public boolean save(Test test) throws Exception {
        if (test.getPoint() < 0 || test.getPoint() > 100) return false;

        Connection con = getConnection();

        PreparedStatement check = con.prepareStatement(
            "SELECT COUNT(*) FROM test WHERE student_no = ? AND test_id = ? AND subject_id = ?"
        );
        check.setString(1, test.getStudentNo());
        check.setInt(2, test.getTestId());
        check.setInt(3, test.getSubjectId());

        ResultSet rs = check.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        rs.close();
        check.close();

        PreparedStatement st;
        if (count > 0) {
            // UPDATE
            st = con.prepareStatement(
                "UPDATE test SET point = ? WHERE student_no = ? AND test_id = ? AND subject_id = ?"
            );
            st.setInt(1, test.getPoint());
            st.setString(2, test.getStudentNo());
            st.setInt(3, test.getTestId());
            st.setInt(4, test.getSubjectId());
        } else {

            st = con.prepareStatement(
                "INSERT INTO test (student_no, test_id, subject_id, point) VALUES (?, ?, ?, ?)"
            );
            st.setString(1, test.getStudentNo());
            st.setInt(2, test.getTestId());
            st.setInt(3, test.getSubjectId());
            st.setInt(4, test.getPoint());
        }

        int result = st.executeUpdate();
        st.close();
        con.close();
        return result > 0;
    }

	public Object findPoint(String no, int subjectId, int i) {
		// TODO 自動生成されたメソッド・スタブ
		return findPoint(null, 0, 0);
	}
}
