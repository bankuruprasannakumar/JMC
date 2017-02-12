package org.johnmusic.service.database.dao;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.inject.Inject;
import org.johnmusic.service.Constants;
import org.johnmusic.service.database.adapter.IDataAccessAdapter;
import org.johnmusic.service.pojo.Batch;
import org.johnmusic.service.response.ResponseData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bankuru on 12/2/17.
 */
public class BatchDetailsDao implements IBatchDetailsDao<Batch> {
    private IDataAccessAdapter mSolrAdapter;
    private ResponseData mResponseData;

    @Inject
    public BatchDetailsDao(IDataAccessAdapter solrAdapter) {
        mSolrAdapter = solrAdapter;
        mResponseData = new ResponseData();
    }

    @Override
    public List<Batch> getAllBatchesForInstrumentAndDay(int instrumentId, int dayId) {
        String query = String.format("q=%s:%s AND %s:%s &%s&%s=%s&%s=%s", Constants.INSTRUMENT_ID, instrumentId, Constants.DAY_ID, dayId, Constants.WT_JSON, Constants.START, 0, Constants.ROWS, 500);
        ResponseData responseData = (ResponseData)mSolrAdapter.selectRequest(query);
        if(responseData.isSuccess()){
            try {
                JSONObject batchResponse = new JSONObject(responseData.getData());
                if (batchResponse.getJSONObject(Constants.RESPONSE).getInt(Constants.NUMFOUND) > 0) {
                    JSONArray BatchJsonArray = batchResponse.getJSONObject(Constants.RESPONSE).getJSONArray(Constants.DOCS);
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<Batch>>(){}.getType();
                    ArrayList<Batch> batchList = gson.fromJson(BatchJsonArray.toString(),listType);
                    return batchList;
                }
                else {
                    return new ArrayList<Batch>();
                }
            }catch (JSONException j){
                j.printStackTrace();
                mResponseData.setErrorMessage(j.toString());
                mResponseData.setErrorCode(Constants.ERRORCODE_JSON_EXCEPTION);
                mResponseData.setSuccess(false);
                return null;
            }
        }
        return null;
    }

    @Override
    public int getCountOfStudentsInABatch(int batchId) {
        String query = String.format("q=%s:%s AND %s:%s &%s&%s=%s&%s=%s", Constants.STUDENT_ID,Constants.ALL, Constants.BATCH_ID, batchId, Constants.WT_JSON, Constants.START, 0, Constants.ROWS, 500);
        ResponseData responseData = (ResponseData)mSolrAdapter.selectRequest(query);
        if(responseData.isSuccess()){
            try {
                JSONObject studentResponse = new JSONObject(responseData.getData());
                studentResponse.getJSONObject(Constants.RESPONSE).getInt(Constants.NUMFOUND);
            }catch (JSONException j){
                j.printStackTrace();
                mResponseData.setErrorMessage(j.toString());
                mResponseData.setErrorCode(Constants.ERRORCODE_JSON_EXCEPTION);
                mResponseData.setSuccess(false);
                return 0;
            }
        }
        return 0;
    }

    @Override
    public ResponseData getDetailedResponse() {
        return mResponseData;
    }
}
