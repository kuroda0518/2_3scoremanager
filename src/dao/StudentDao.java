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
    //学生登録のdao
    public int insert(Student student) throws Exception {
        Connection con = getConnection();  // ← Dao クラスのメソッドをそのまま使用！

        String sql = "INSERT INTO student (no, name, ent_year, class_num, is_attend, school_cd) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, student.getNo());
        st.setString(2, student.getName());
        st.setInt(3, student.getEntYear());
        st.setString(4, student.getClassNum());
        st.setBoolean(5, student.getIsAttend());
        st.setString(6, student.getSchoolCd());

        int rows = st.executeUpdate();

        st.close();
        con.close();

        return rows;
    }


    public Student find(String no) throws Exception {
        Connection con = getConnection();

        String sql = "SELECT * FROM student WHERE no = ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, no);

        ResultSet rs = st.executeQuery();
        Student student = null;

        if (rs.next()) {
            student = new Student();
            student.setNo(rs.getString("no"));
            student.setName(rs.getString("name"));
            student.setEntYear(rs.getInt("ent_year"));
            student.setClassNum(rs.getString("class_num"));
            student.setIsAttend(rs.getBoolean("is_attend"));
            student.setSchoolCd(rs.getString("school_cd"));
        }

        rs.close();
        st.close();
        con.close();

        return student;
    }

 // 学生情報を更新するメソッド（主キーは no）
    public int update(Student student) throws Exception {
        Connection con = getConnection();

        String sql = "UPDATE student SET name = ?, ent_year = ?, class_num = ?, is_attend = ?, school_cd = ? WHERE no = ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, student.getName());
        st.setInt(2, student.getEntYear());
        st.setString(3, student.getClassNum());
        st.setBoolean(4, student.getIsAttend());
        st.setString(5, student.getSchoolCd());
        st.setString(6, student.getNo());

        int rows = st.executeUpdate();

        st.close();
        con.close();

        return rows;
    }

    public List<Integer> getEntYearList(String schoolCd) throws Exception {
        List<Integer> list = new ArrayList<>();
        String sql = "SELECT DISTINCT ENT_YEAR FROM STUDENT WHERE SCHOOL_CD = ? ORDER BY ENT_YEAR";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, schoolCd);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                list.add(rs.getInt("ENT_YEAR"));
            }
        }
        return list;
    }


    public List<String> getClassNumList(String schoolCd) throws Exception {
        List<String> list = new ArrayList<>();
        String sql = "SELECT DISTINCT CLASS_NUM FROM STUDENT WHERE SCHOOL_CD = ? ORDER BY CLASS_NUM";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, schoolCd);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                list.add(rs.getString("CLASS_NUM"));
            }
        }
        return list;
    }

 // 入学年度を重複なく取得
    public List<String> findDistinctEntYears() throws Exception {
        List<String> list = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("SELECT DISTINCT ent_year FROM student ORDER BY ent_year");
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            list.add(rs.getString("ent_year"));
        }
        rs.close();
        st.close();
        con.close();
        return list;
    }

    public List<String> findDistinctClassNums() throws Exception {
        List<String> list = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("SELECT DISTINCT class_num FROM student ORDER BY class_num");
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            list.add(rs.getString("class_num"));
        }
        rs.close();
        st.close();
        con.close();
        return list;
    }

    public List<Integer> getNoList(String schoolCd) throws Exception {
        List<Integer> list = new ArrayList<>();
        String sql = "SELECT DISTINCT No FROM STUDENT WHERE SCHOOL_CD = ? ORDER BY No";

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, schoolCd);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                list.add(rs.getInt("No"));
            }
        }
        return list;
    }

    public Student findOne(String schoolCd, String studentNo) throws Exception {
        Student student = null;

        try (Connection con = getConnection()) {
            String sql = "SELECT * FROM student WHERE school_cd = ? AND no = ?";
            try (PreparedStatement st = con.prepareStatement(sql)) {
                st.setString(1, schoolCd);
                st.setString(2, studentNo);
                ResultSet rs = st.executeQuery();

                if (rs.next()) {
                    student = new Student();
                    student.setNo(rs.getString("no"));
                    student.setName(rs.getString("name"));
                    // 他にも必要に応じてセット
                }
            }
        }

        return student;
    }



}
