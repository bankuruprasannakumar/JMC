package org.johnmusic.service.database.dao;

import org.johnmusic.service.response.ResponseData;

import java.util.List;

/**
 * Created by bankuru on 12/2/17.
 */
public interface IStudentDetailsDao<T> {
    public List<T> getAllStudentForBatchId(int batchId);
    public List<Integer> getAllBatchIdsOfASudent(int studentId);
    public T getStudentForId(int studentId);
    public ResponseData getDetailedResponse();

}
