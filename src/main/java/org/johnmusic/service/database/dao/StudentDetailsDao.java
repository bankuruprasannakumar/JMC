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

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bankuru on 12/2/17.
 */
public class StudentDetailsDao implements IStudentDetailsDao<Student> {

    private IDataAccessAdapter mSolrAdapter;
    private ResponseData mResponseData;

    @Inject
    public StudentDetailsDao(IDataAccessAdapter solrAdapter) {
        mSolrAdapter = solrAdapter;
        mResponseData = new ResponseData();
    }

    @Override
    public List<Student> getAllStudentForBatchId(int batchId) {
        String query = String.format("q=%s:%s AND %s:%s &%s&%s=%s&%s=%s", Constants.STUDENT_ID,Constants.ALL, Constants.BATCH_ID, batchId, Constants.WT_JSON, Constants.START, 0, Constants.ROWS, 500);
        ResponseData responseData = (ResponseData)mSolrAdapter.selectRequest(query);
        if(responseData.isSuccess()){
            try {
                JSONObject studentResponse = new JSONObject(responseData.getData());
                if (studentResponse.getJSONObject(Constants.RESPONSE).getInt(Constants.NUMFOUND) > 0) {
                    JSONArray studentBatchAssociationJsonArray = studentResponse.getJSONObject(Constants.RESPONSE).getJSONArray(Constants.DOCS);
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<StudentBatchAssociation>>(){}.getType();
                    ArrayList<StudentBatchAssociation> studentBatchAssociationList = gson.fromJson(studentBatchAssociationJsonArray.toString(),listType);
                    ArrayList<Integer> studentIdList = new ArrayList<Integer>();
                    for (StudentBatchAssociation studentBatchAssociation : studentBatchAssociationList) {
                        studentIdList.add(studentBatchAssociation.getStudentId());
                    }
                    return getStudentsFromStudentIdList(studentIdList);
                }
                else {
                    return new ArrayList<Student>();
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

    private List<Student> getStudentsFromStudentIdList(ArrayList<Integer> studentIdList) {
        if(studentIdList == null || studentIdList.isEmpty()){
            mResponseData.setSuccess(false);
            return new ArrayList<Student>();
        }
        String subQuery = "";
        subQuery = "(";
        for (int index = 0; index < studentIdList.size(); index++) {
            subQuery += studentIdList.get(index) + " OR ";
        }
        subQuery = subQuery.substring(0, (subQuery.length() - 3));
        subQuery += ")";
        String query = String.format("q=%s:%s AND %s:%s &%s&%s=%s&%s=%s", Constants.STUDENT_ID, subQuery, Constants.STUDENT_NAME, Constants.ALL, Constants.WT_JSON, Constants.START, 0, Constants.ROWS, 500);
        ResponseData responseData = (ResponseData)mSolrAdapter.selectRequest(query);
        if(responseData.isSuccess()){
            try {
                JSONObject studentResponse = new JSONObject(responseData.getData());
                if (studentResponse.getJSONObject(Constants.RESPONSE).getInt(Constants.NUMFOUND) > 0) {
                    JSONArray studentJsonArray = studentResponse.getJSONObject(Constants.RESPONSE).getJSONArray(Constants.DOCS);
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<Student>>(){}.getType();
                    ArrayList<Student> studentList = gson.fromJson(studentJsonArray.toString(),listType);
                    return studentList;
                }
                else {
                    return new ArrayList<Student>();
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
    public List<Integer> getAllBatchIdsOfASudent(int studentId) {
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
                    ArrayList<Integer> batchIdList = new ArrayList<Integer>();
                    for (StudentBatchAssociation studentBatchAssociation : studentBatchAssociationList) {
                        batchIdList.add(studentBatchAssociation.getBatchId());
                    }
                    return batchIdList;
                }
                else {
                    return new ArrayList<Integer>();
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
    public Student getStudentForId(int studentId) {
        String query = String.format("q=%s:%s AND %s:%s&%s", Constants.STUDENT_ID,studentId, Constants.STUDENT_NAME,Constants.ALL,Constants.WT_JSON);
        ResponseData responseData = (ResponseData)mSolrAdapter.selectRequest(query);
        if(responseData.isSuccess()){
            try {
                JSONObject studentResponse = new JSONObject(responseData.getData());
                if (studentResponse.getJSONObject(Constants.RESPONSE).getInt(Constants.NUMFOUND) == 1) {
                    JSONObject studentJsonObject = studentResponse.getJSONObject(Constants.RESPONSE).getJSONArray(Constants.DOCS).getJSONObject(0);
                    Gson gson = new Gson();
                    Student student = gson.fromJson(studentJsonObject.toString(),Student.class);
                    return student;
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
    public ResponseData getDetailedResponse() {
        return mResponseData;

    }
}
