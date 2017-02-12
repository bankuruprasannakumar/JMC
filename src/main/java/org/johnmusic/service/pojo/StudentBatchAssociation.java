package org.johnmusic.service.pojo;

/**
 * Created by bankuru on 12/2/17.
 */
public class StudentBatchAssociation {
    private int studentId;
    private int batchId;
    private boolean isPaymentDone;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public boolean isPaymentDone() {
        return isPaymentDone;
    }

    public void setPaymentDone(boolean paymentDone) {
        isPaymentDone = paymentDone;
    }
}
