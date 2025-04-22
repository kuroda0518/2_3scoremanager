package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;

public class StudentDao extends Dao {

//条件（学校コード・入学年度・クラス・在籍）で学生一覧を絞り込んで取得するメソッド
    public List<Student> filter(String schoolCd, Integer entYear, String classNum, Boolean isAttend) throws Exception {
        List<Student> list = new ArrayList<>();
        //学校コードは必ず入力されるとしてそれぞれnullじゃなかったらSQLに付け加えていく
        StringBuilder sql = new StringBuilder("SELECT * FROM STUDENT WHERE SCHOOL_CD = ?");
        if (entYear != null) sql.append(" AND ENT_YEAR = ?");
        if (classNum != null && !classNum.isEmpty()) sql.append(" AND CLASS_NUM = ?");
        if (isAttend != null) sql.append(" AND IS_ATTEND = ?");

        //try (...) { ... }はtry-with-resources構文自動でclose()してくれる
        //普通に書いて最後にcloseで全然おけ
        //StringBuilderで作ったSQLをここで入れる
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql.toString())) {


        	//?の1にschoolCdをいれる
            int idx = 1;
            st.setString(idx++, schoolCd);
            //nullじゃなかったら?の2にentYearをいれる
            if (entYear != null) st.setInt(idx++, entYear);
            //nullじゃなく空文字でもない
            if (classNum != null && !classNum.isEmpty()) st.setString(idx++, classNum);

            if (isAttend != null) st.setBoolean(idx++, isAttend);

            //Studentに格納
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setNo(rs.getString("NO"));
                s.setName(rs.getString("NAME"));
                s.setEntYear(rs.getInt("ENT_YEAR"));
                s.setClassNum(rs.getString("CLASS_NUM"));
                s.setIsAttend(rs.getBoolean("IS_ATTEND"));
                s.setSchoolCd(rs.getString("SCHOOL_CD"));
                list.add(s);
            }
        }
        return list;
    }
}
