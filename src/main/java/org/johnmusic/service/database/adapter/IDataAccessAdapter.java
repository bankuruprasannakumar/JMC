package org.johnmusic.service.database.adapter;

/**
 * Created by bankuru on 11/2/17.
 */
public interface IDataAccessAdapter<T> {
    public T updateRequest(String query);
    public T selectRequest(String query);
}
