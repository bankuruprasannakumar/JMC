package org.johnmusic.service.request;

/**
 * Created by bankuru on 12/2/17.
 */
public interface IRequest {
    public boolean isValid();

    public String errorMessage();

}
