package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;


public class SubjectDao extends Dao{

    // 科目一覧をすべて取得
    public List<Subject> selectAll() throws Exception {
        List<Subject> list = new ArrayList<>();
        Connection con = getConnection();

        String sql = "SELECT * FROM student";
        PreparedStatement st = con.prepareStatement(sql);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Subject subject = new Subject();
            subject.setSchoolCd(rs.getString("schoolCd"));
            subject.setCd(rs.getString("cd"));
            subject.setName(rs.getString("name"));
            list.add(subject);
        }

        rs.close();
        st.close();
        con.close();

        return list;
    }

}