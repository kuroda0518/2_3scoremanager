package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.Teacher;

public class TeacherDao extends Dao {

    public Teacher login(String id, String password) throws Exception {
        Teacher teacher = null;

        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(
            "SELECT * FROM teacher WHERE id = ? AND password = ?"
        );
        st.setString(1, id);
        st.setString(2, password);

        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            teacher = new Teacher();
            teacher.setId(rs.getString("id"));
            teacher.setPassword(rs.getString("password"));
            teacher.setName(rs.getString("name"));
            teacher.setSchoolCd(rs.getString("school_cd"));
        }

        rs.close();
        st.close();
        con.close();

        return teacher;
    }
}
