package org.johnmusic.service.database.dao;

import org.johnmusic.service.response.ResponseData;

import java.util.List;

/**
 * Created by bankuru on 12/2/17.
 */
public interface IBatchDetailsDao<T> {
    public List<T> getAllBatchesForInstrumentAndDay(int instrumentId, int dayId);
    public int getCountOfStudentsInABatch(int batchId);
    public ResponseData getDetailedResponse();

}
