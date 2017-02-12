package org.johnmusic.service.request;

import org.johnmusic.service.Constants;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by bankuru on 12/2/17.
 */
@XmlRootElement
public class AddAttendanceRequest implements IRequest{
    private int userId;
    private int batchId;
    private long time;
    private List<StudentAttendance> studentAttendanceList;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public List<StudentAttendance> getStudentAttendanceList() {
        return studentAttendanceList;
    }

    public void setStudentAttendanceList(List<StudentAttendance> studentAttendanceList) {
        this.studentAttendanceList = studentAttendanceList;
    }

    @Override
    public boolean isValid() {
        if (userId == 0)
            return false;
        if (batchId == 0)
            return false;
        if (time == 0)
            return false;
        if (studentAttendanceList == null || studentAttendanceList.isEmpty())
            return false;
        return true;

    }

    @Override
    public String errorMessage() {
        StringBuilder message = new StringBuilder();
        if (userId == 0)
            message.append(Constants.INVALID_USER_ID);
        if (batchId == 0)
            message.append(Constants.INVALID_BATCH_ID);
        if (time == 0)
            message.append(Constants.INVALID_TIME);
        if (studentAttendanceList == null || studentAttendanceList.isEmpty())
            message.append(Constants.INVALID_STUDENT_ATTENDANCE_LIST);
        return message.toString();
    }
}
