package org.johnmusic.service.response;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by bankuru on 11/2/17.
 */
@XmlRootElement
public class ResponseData {
    private boolean success;
    private int errorCode;
    private String errorMessage;
    private String data;

    public ResponseData() {
        success = true;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
