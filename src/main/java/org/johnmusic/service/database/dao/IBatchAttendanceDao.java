package org.johnmusic.service.database.dao;

import org.johnmusic.service.response.ResponseData;

/**
 * Created by bankuru on 12/2/17.
 */
public interface IBatchAttendanceDao<T> {
    public boolean setBatchAttendance(T batchAttendace);
    public ResponseData getDetailedResponse();
}
