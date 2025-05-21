package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Test;

public class TestDao extends Dao {
    public List<Test> filter(String schoolCd, int entYear, String classNum, String subjectCd, int no) throws Exception {
        List<Test> list = new ArrayList<>();

        try (Connection con = getConnection()) {
            String sql =
                "SELECT s.no, s.name, s.ent_year, s.class_num, t.point " +
                "FROM STUDENT s " +
                "LEFT JOIN TEST t ON s.no = t.student_no AND t.subject_cd = ? AND t.no = ? " +
                "WHERE s.school_cd = ? AND s.ent_year = ? AND s.class_num = ? AND s.is_attend = TRUE " +
                "ORDER BY s.no";

            try (PreparedStatement st = con.prepareStatement(sql)) {
                st.setString(1, subjectCd);
                st.setInt(2, no);
                st.setString(3, schoolCd);
                st.setInt(4, entYear);
                st.setString(5, classNum);

                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    Test test = new Test();
                    test.setStudentNo(rs.getString("no"));
                    test.setName(rs.getString("name"));
                    test.setEntYear(rs.getInt("ent_year"));
                    test.setClassNum(rs.getString("class_num"));
                    Integer point = (Integer) rs.getObject("point");
                    test.setPoint(point);
                    test.setNo(no);
                    test.setSubjectCd(subjectCd);
                    test.setSchoolCd(schoolCd);
                    list.add(test);
                }
            }
        }

        return list;
    }

    public void updateOrDelete(Test test) throws Exception {
        try (Connection con = getConnection()) {
            if (test.getPoint() != null && test.getPoint() >= 0) {
                // 点数がある → UPDATE or INSERT
                String sql = "MERGE INTO TEST (STUDENT_NO, SUBJECT_CD, NO, POINT, SCHOOL_CD, CLASS_NUM) "
                           + "KEY(STUDENT_NO, SUBJECT_CD, NO) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement st = con.prepareStatement(sql)) {
                    st.setString(1, test.getStudentNo());
                    st.setString(2, test.getSubjectCd());
                    st.setInt(3, test.getNo());
                    st.setObject(4, test.getPoint(), java.sql.Types.INTEGER); // ← ここが重要
                    st.setString(5, test.getSchoolCd());
                    st.setString(6, test.getClassNum());
                    st.executeUpdate();
                }
            } else {
                // 点数なし → DELETE
                String sql = "DELETE FROM TEST WHERE STUDENT_NO = ? AND SUBJECT_CD = ? AND NO = ?";
                try (PreparedStatement st = con.prepareStatement(sql)) {
                    st.setString(1, test.getStudentNo());
                    st.setString(2, test.getSubjectCd());
                    st.setInt(3, test.getNo());
                    st.executeUpdate();
                }
            }
        }
    }





    public Integer findPoint(String studentNo, int subjectId, int testNo) {
        Integer point = null;

        try (Connection con = getConnection()) {
            String sql = "SELECT POINT FROM TEST WHERE STUDENT_NO = ? AND SUBJECT_CD = ? AND NO = ?";
            try (PreparedStatement st = con.prepareStatement(sql)) {
                st.setString(1, studentNo);
                st.setString(2, String.format("%03d", subjectId)); // subjectIdは"001"のような文字列
                st.setInt(3, testNo);

                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    point = rs.getInt("POINT");
                    if (rs.wasNull()) point = null; // DB上がNULLだった場合
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // ログ出力
        }

        return point;
    }

    //学生別で使用する
    public List<Test> findByStudentNo(String studentNo) throws Exception {
        List<Test> list = new ArrayList<>();

        try (Connection con = getConnection()) {
            String sql =
                "SELECT t.student_no, t.subject_cd, s.name AS subject_name, t.no, t.point " +
                "FROM TEST t " +
                "JOIN SUBJECT s ON t.subject_cd = s.cd " +
                "WHERE t.student_no = ? " +
                "ORDER BY t.no";

            try (PreparedStatement st = con.prepareStatement(sql)) {
                st.setString(1, studentNo);
                System.out.println("実行されるSQL: " + st.toString()); // ★ログ出力

                ResultSet rs = st.executeQuery();

                while (rs.next()) {
                    Test test = new Test();
                    test.setStudentNo(rs.getString("student_no"));
                    test.setSubjectCd(rs.getString("subject_cd"));
                    test.setName(rs.getString("subject_name"));
                    test.setNo(rs.getInt("no"));
                    test.setPoint(rs.getInt("point"));
                    list.add(test);
                }
            }
        }


        return list;
    }

    //科目別で使用する
    public List<Test> getClassAndSubject(int entYear, String classNum, String subjectCd) throws Exception {
        List<Test> list = new ArrayList<>();

        String sql = "SELECT " +
                     "s.ent_year, s.class_num, s.no AS student_no, s.name AS student_name, " +
                     "t.no AS test_no, t.point " +
                     "FROM STUDENT s " +
                     "LEFT JOIN TEST t ON s.no = t.student_no AND t.subject_cd = ? " +
                     "WHERE s.ent_year = ? AND s.class_num = ? " +
                     "ORDER BY s.no, t.no";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, subjectCd);
            ps.setInt(2, entYear);
            ps.setString(3, classNum);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Test t = new Test();
                t.setEntYear(rs.getInt("ent_year"));
                t.setClassNum(rs.getString("class_num"));
                t.setStudentNo(rs.getString("student_no"));
                t.setName(rs.getString("student_name"));
                t.setNo(rs.getInt("test_no"));
                t.setPoint(rs.getInt("point"));
                list.add(t);
            }
        }

        return list;
    }



}
