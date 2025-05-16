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
                    test.setPoint(rs.getInt("point"));
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
            if (test.getPoint() >= 0) {
                // 点数がある → UPDATE or INSERT
                String sql = "MERGE INTO TEST (STUDENT_NO, SUBJECT_CD, NO, POINT, SCHOOL_CD, CLASS_NUM) "
                           + "KEY(STUDENT_NO, SUBJECT_CD, NO) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement st = con.prepareStatement(sql)) {
                    st.setString(1, test.getStudentNo());
                    st.setString(2, test.getSubjectCd());
                    st.setInt(3, test.getNo());
                    st.setInt(4, test.getPoint());
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



}
