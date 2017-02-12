package org.johnmusic.service.database.dao;

import com.google.gson.Gson;
import com.google.inject.Inject;
import org.johnmusic.service.Constants;
import org.johnmusic.service.database.adapter.IDataAccessAdapter;
import org.johnmusic.service.pojo.BatchAttendance;
import org.johnmusic.service.response.ResponseData;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

/**
 * Created by bankuru on 12/2/17.
 */
public class BatchAttendanceDao implements IBatchAttendanceDao<BatchAttendance> {

    private IDataAccessAdapter mSolrAdapter;
    private ResponseData mResponseData;

    @Inject
    public BatchAttendanceDao(IDataAccessAdapter solrAdapter) {
        mSolrAdapter = solrAdapter;
        mResponseData = new ResponseData();
    }

    @Override
    public boolean setBatchAttendance(BatchAttendance batchAttendace) {
        String query = "";
        try {
            Gson gson = new Gson();
            String jsonBatchAttendace = gson.toJson(batchAttendace);
            JSONObject batchAttendaceJSONObject = new JSONObject(jsonBatchAttendace);
            query = Constants.INSERT_START + batchAttendaceJSONObject.toString() + Constants.INSERT_END;
            mResponseData = (ResponseData)mSolrAdapter.updateRequest(query);
            if (mResponseData.isSuccess()) {
                return true;
            }
        }catch (JSONException j){
            j.printStackTrace();
            mResponseData.setErrorMessage(j.toString());
            mResponseData.setErrorCode(Constants.ERRORCODE_JSON_EXCEPTION);
            mResponseData.setSuccess(false);
            return false;
        }
        return false;
    }

    @Override
    public ResponseData getDetailedResponse() {
        return mResponseData;
    }
}
