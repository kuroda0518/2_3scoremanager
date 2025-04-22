package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClassNumDao extends Dao {
    public List<String> findAll(String schoolCd) throws Exception {
        List<String> list = new ArrayList<>();

        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(
            "SELECT class_num FROM class_num WHERE school_cd = ? ORDER BY class_num"
        );
        st.setString(1, schoolCd);

        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            list.add(rs.getString("class_num"));
        }

        rs.close();
        st.close();
        con.close();

        return list;
    }
}
