package org.johnmusic.service.database.dao;

import org.johnmusic.service.response.ResponseData;

import java.util.List;

/**
 * Created by bankuru on 12/2/17.
 */
public interface IStudentBatchAssociationDetailsDao<T> {
    public T getStudentBatchAssociation(int studentId, int batchId);
    public List<T> getAllStudentBatchAssociationsForStudentId(int studentId);
    public ResponseData getDetailedResponse();
}
