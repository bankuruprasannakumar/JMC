package org.johnmusic.service.database.dao;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.inject.Inject;
import org.johnmusic.service.Constants;
import org.johnmusic.service.database.adapter.IDataAccessAdapter;
import org.johnmusic.service.pojo.Student;
import org.johnmusic.service.pojo.StudentBatchAssociation;
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
public class StudentBatchAssociationDetailsDao implements IStudentBatchAssociationDetailsDao<StudentBatchAssociation> {

    private IDataAccessAdapter mSolrAdapter;
    private ResponseData mResponseData;

    @Inject
    public StudentBatchAssociationDetailsDao(IDataAccessAdapter solrAdapter) {
        mSolrAdapter = solrAdapter;
        mResponseData = new ResponseData();
    }

    @Override
    public StudentBatchAssociation getStudentBatchAssociation(int studentId, int batchId) {
        String query = String.format("q=%s:%s AND %s:%s&%s", Constants.STUDENT_ID,studentId, Constants.BATCH_ID, batchId,Constants.WT_JSON);
        ResponseData responseData = (ResponseData)mSolrAdapter.selectRequest(query);
        if(responseData.isSuccess()){
            try {
                JSONObject studentBatchAssociationResponse = new JSONObject(responseData.getData());
                if (studentBatchAssociationResponse.getJSONObject(Constants.RESPONSE).getInt(Constants.NUMFOUND) == 1) {
                    JSONObject studentBatchAssociationJsonObject = studentBatchAssociationResponse.getJSONObject(Constants.RESPONSE).getJSONArray(Constants.DOCS).getJSONObject(0);
                    Gson gson = new Gson();
                    StudentBatchAssociation studentBatchAssociation = gson.fromJson(studentBatchAssociationJsonObject.toString(),StudentBatchAssociation.class);
                    return studentBatchAssociation;
                }
                else {
                    mResponseData.setErrorMessage("Invalid userId");
                    mResponseData.setErrorCode(Constants.ERRORCODE_INVALID_INPUT);
                    mResponseData.setSuccess(false);
                    return null;
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
    public List<StudentBatchAssociation> getAllStudentBatchAssociationsForStudentId(int studentId) {
        String query = String.format("q=%s:%s AND %s:%s &%s&%s=%s&%s=%s", Constants.STUDENT_ID, studentId, Constants.BATCH_ID, Constants.ALL, Constants.WT_JSON, Constants.START, 0, Constants.ROWS, 500);
        ResponseData responseData = (ResponseData)mSolrAdapter.selectRequest(query);
        if(responseData.isSuccess()){
            try {
                JSONObject studentResponse = new JSONObject(responseData.getData());
                if (studentResponse.getJSONObject(Constants.RESPONSE).getInt(Constants.NUMFOUND) > 0) {
                    JSONArray studentBatchAssociationJsonArray = studentResponse.getJSONObject(Constants.RESPONSE).getJSONArray(Constants.DOCS);
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<StudentBatchAssociation>>(){}.getType();
                    ArrayList<StudentBatchAssociation> studentBatchAssociationList = gson.fromJson(studentBatchAssociationJsonArray.toString(),listType);
                    return studentBatchAssociationList;
                }
                else {
                    return new ArrayList<StudentBatchAssociation>();
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
    public ResponseData getDetailedResponse() {
        return mResponseData;
    }
}
