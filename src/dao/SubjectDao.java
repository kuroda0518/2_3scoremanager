package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Subject;


public class SubjectDao extends Dao{

	// schoolCdで絞り込むバージョン
	public List<Subject> selectBySchool(String schoolCd) throws Exception {
	    List<Subject> list = new ArrayList<>();
	    Connection con = getConnection();

	    String sql = "SELECT SCHOOL_CD AS schoolCd, CD AS cd, NAME AS name FROM SUBJECT WHERE SCHOOL_CD = ?";
	    PreparedStatement st = con.prepareStatement(sql);
	    st.setString(1, schoolCd);
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

    //選択したcdと一致したデータを取得
    //これはなぜかエラー出たりするので、selectBySchoolを主に使う
    public Subject cdSelect(String cd) throws Exception {
        Connection con = getConnection();

        String sql = "SELECT * FROM subject WHERE cd = ?";
        PreparedStatement sub = con.prepareStatement(sql);
        sub.setString(1, cd);

        ResultSet rs = sub.executeQuery();
        Subject subject = null;

        if (rs.next()) {
        	subject = new Subject();
            subject.setCd(rs.getString("cd"));
            subject.setName(rs.getString("name"));
            subject.setSchoolCd(rs.getString("school_cd"));
        }

        rs.close();
        sub.close();
        con.close();

        return subject;
    }

    //科目更新用
    public int update(Subject subject) throws Exception {
        Connection con = getConnection();

        String sql = "UPDATE subject SET name = ? WHERE cd = ?";
        PreparedStatement sub = con.prepareStatement(sql);
        sub.setString(1, subject.getName());
        sub.setString(2, subject.getCd());


        int subname = sub.executeUpdate();

        sub.close();
        con.close();

        return subname;
    }

    //科目削除用
    public int delete(Subject subject) throws Exception{
		//DBとの接続
		Connection con = getConnection();

		//SQL文の準備
		PreparedStatement st = con.prepareStatement("DELETE FROM SUBJECT WHERE CD=? ");
		st.setString(1,subject.getCd());

		int num = st.executeUpdate();

		st.close();
		con.close();

		return num;


	}


    //ログインユーザーの学校コードから科目全件を取得
    public List<Subject> filter(String schoolCd) throws Exception {
        List<Subject> list = new ArrayList<>();

        try (Connection con = getConnection()) {
            String sql = "SELECT * FROM SUBJECT WHERE school_cd = ? ORDER BY cd";

            try (PreparedStatement st = con.prepareStatement(sql)) {
                st.setString(1, schoolCd);

                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    Subject sub = new Subject();
                    sub.setSchoolCd(rs.getString("school_cd"));
                    sub.setCd(rs.getString("cd"));
                    sub.setName(rs.getString("name"));

                    list.add(sub);
                }
            }
        }

        return list;
    }

    public List<Map<String, Object>> findAll() throws Exception {
        List<Map<String, Object>> list = new ArrayList<>();
        Connection con = getConnection();


        PreparedStatement st = con.prepareStatement("SELECT cd, name FROM subject ORDER BY cd");
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Map<String, Object> subj = new HashMap<>();
            subj.put("id", rs.getString("cd"));
            subj.put("name", rs.getString("name"));
            list.add(subj);
        }

        rs.close();
        st.close();
        con.close();
        return list;
    }

  //学校コードと科目コードから科目コードと科目名を取得
    public Subject findOne(String schoolCd, String cd) throws Exception {
        Subject subject = null;

        try (Connection con = getConnection()) {
            String sql = "SELECT * FROM subject WHERE school_cd = ? AND cd = ?";
            try (PreparedStatement st = con.prepareStatement(sql)) {
                st.setString(1, schoolCd);
                st.setString(2, cd);
                ResultSet rs = st.executeQuery();

                if (rs.next()) {
                	subject = new Subject();
                	subject.setCd(rs.getString("cd"));
                	subject.setName(rs.getString("name"));
                }
            }
        }

        return subject;
    }

}
