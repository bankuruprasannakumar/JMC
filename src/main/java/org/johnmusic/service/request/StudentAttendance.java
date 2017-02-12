package org.johnmusic.service.request;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by bankuru on 12/2/17.
 */
public class StudentAttendance {
    private int studentId;
    private boolean isPresent;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }
}
