package org.johnmusic.service.pojo;

import java.util.List;

/**
 * Created by bankuru on 12/2/17.
 */
public class BatchAttendance {
    private int batchId;
    private long attendanceTime;
    private List<Integer> presentStudentIds;
    private List<Integer> absentStudentIds;

    public List<Integer> getPresentStudentIds() {
        return presentStudentIds;
    }

    public void setPresentStudentIds(List<Integer> presentStudentIds) {
        this.presentStudentIds = presentStudentIds;
    }

    public List<Integer> getAbsentStudentIds() {
        return absentStudentIds;
    }

    public void setAbsentStudentIds(List<Integer> absentStudentIds) {
        this.absentStudentIds = absentStudentIds;
    }

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public long getAttendanceTime() {
        return attendanceTime;
    }

    public void setAttendanceTime(long attendanceTime) {
        this.attendanceTime = attendanceTime;
    }
}
