package org.johnmusic.service.pojo;

/**
 * Created by bankuru on 12/2/17.
 */
public class Student {
    private int studentId;
    private long registeredTime;
    private boolean isAlumni;
    private String mobileNumber;
    private String studentName;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public long getRegisteredTime() {
        return registeredTime;
    }

    public void setRegisteredTime(long registeredTime) {
        this.registeredTime = registeredTime;
    }

    public boolean isAlumni() {
        return isAlumni;
    }

    public void setAlumni(boolean alumni) {
        isAlumni = alumni;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
