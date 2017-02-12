package org.johnmusic.service.database.dao;

import org.johnmusic.service.response.ResponseData;

/**
 * Created by bankuru on 12/2/17.
 */
public interface IStudentBatchAssociationDetailsDao<T> {
    public T getStudentBatchAssociation(int studentId, int batchId);
    public ResponseData getDetailedResponse();
}
