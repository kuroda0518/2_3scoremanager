package bean;

import java.io.Serializable;

public class Student implements Serializable {
    private String no;
    private String name;
    private int entYear;
    private String classNum;
    private boolean isAttend;
    private String schoolCd;

    public String getNo() { return no; }
    public void setNo(String no) { this.no = no; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getEntYear() { return entYear; }
    public void setEntYear(int entYear) { this.entYear = entYear; }

    public String getClassNum() { return classNum; }
    public void setClassNum(String classNum) { this.classNum = classNum; }

    public boolean getIsAttend() { return isAttend; }
    public void setIsAttend(boolean isAttend) { this.isAttend = isAttend; }

    public String getSchoolCd() { return schoolCd; }
    public void setSchoolCd(String schoolCd) { this.schoolCd = schoolCd; }
}
