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

        String sql = "SELECT SCHOOL_CD AS schoolCd, CD AS cd, NAME AS name FROM SUBJECT";
;
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


  //科目登録のdao
    public int insert(Subject subject) throws Exception {
        Connection con = getConnection();  // ← Dao クラスのメソッドをそのまま使用！

        String sql = "INSERT INTO SUBJECT (SCHOOL_CD, CD, NAME) VALUES (?, ?, ?)";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, subject.getSchoolCd());
        st.setString(2, subject.getCd());
        st.setString(3, subject.getName());

        int rows = st.executeUpdate();

        st.close();
        con.close();

        return rows;
    }
}

