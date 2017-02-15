package org.johnmusic.service.request;

import com.sun.jersey.core.util.StringIgnoreCaseKeyComparator;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by bankuru on 12/2/17.
 */
public class StudentAttendance {
    private int studentId;
    private String isPresent;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getIsPresent() {
        return isPresent;
    }

    public boolean isPresent() {
        if ("True".equals(isPresent) || "true".equals(isPresent)) {
            return true;
        }
        return false;
    }

    public void setIsPresent(String isPresent) {
        this.isPresent = isPresent;
    }
}
