package org.johnmusic.service.api;

import org.johnmusic.service.Constants;
import org.johnmusic.service.database.dao.IBatchDetailsDao;
import org.johnmusic.service.database.dao.IStudentBatchAssociationDetailsDao;
import org.johnmusic.service.database.dao.IStudentDetailsDao;
import org.johnmusic.service.pojo.Batch;
import org.johnmusic.service.pojo.Student;
import org.johnmusic.service.pojo.StudentBatchAssociation;
import org.johnmusic.service.request.GetBatchesRequest;
import org.johnmusic.service.response.ResponseBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by bankuru on 12/2/17.
 */

@Path("/getBatches")
@Produces("application/json")
@Consumes("application/json")

public class GetBatches {
    private final IBatchDetailsDao mBatchDetailsDao;
    private final IStudentDetailsDao mStudentDetailsDao;
    private final IStudentBatchAssociationDetailsDao mStudentBatchAssociationDetailsDao;

    @Inject
    public GetBatches(IBatchDetailsDao batchDetailsDao, IStudentDetailsDao studentDetailsDao,
                      IStudentBatchAssociationDetailsDao studentBatchAssociationDetailsDao){
        mBatchDetailsDao = batchDetailsDao;
        mStudentDetailsDao = studentDetailsDao;
        mStudentBatchAssociationDetailsDao = studentBatchAssociationDetailsDao;
    }

    @POST
    public Response getBatches(GetBatchesRequest getBatchesRequest){
        try {
            if (getBatchesRequest == null){
                return ResponseBuilder.error(Constants.ERRORCODE_INVALID_INPUT, Constants.INVALID_REQUEST);
            }
            if (!getBatchesRequest.isValid()) {
                return ResponseBuilder.error(Constants.ERRORCODE_INVALID_INPUT, getBatchesRequest.errorMessage());
            }

            int userId = getBatchesRequest.getUserId();
/*
            if (null == mStudentDetailsDao.getStudentForId(userId)) {
                return ResponseBuilder.error(Constants.ERRORCODE_INVALID_INPUT, Constants.INVALID_USER_ID);
            }
*/
            int dayId = getBatchesRequest.getDayId();
            int instrumnentId = getBatchesRequest.getInstrumentId();
            List<Batch> batchList = mBatchDetailsDao.getAllBatchesForInstrumentAndDay(instrumnentId, dayId);
            if (batchList != null) {
                JSONObject responseObj = composeResponse(batchList);
                return ResponseBuilder.successResponse(responseObj.toString());
            }
            else {
                return ResponseBuilder.error(Constants.ERRORCODE_INVALID_INPUT, mBatchDetailsDao.getDetailedResponse().getErrorMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBuilder.error(Constants.ERRORCODE_IOEXCEPTION, "Internal Server Error");
        }
    }

    private JSONObject composeResponse(List<Batch> batchList) {
        JSONObject response = new JSONObject();
        try {
            response.put(Constants.SUCCESS, true);
            JSONArray batchJSONArray = new JSONArray();
            for (Batch batch : batchList) {
                JSONObject batchJSON = new JSONObject();
                batchJSON.put(Constants.ID, batch.getBatchId());
                batchJSON.put(Constants.INSTRUMENT_ID, batch.getInstrumentId());
                batchJSON.put(Constants.LEVEL, batch.getLevel());
                batchJSON.put(Constants.DAY_ID, batch.getDayId());
                batchJSON.put(Constants.START_TIME, batch.getStartTime());
                batchJSON.put(Constants.END_TIME, batch.getEndTime());
                batchJSON.put(Constants.CLASS_COUNT, batch.getClassCount());
                batchJSON.put(Constants.TOTAL_STUDENTS, mBatchDetailsDao.getCountOfStudentsInABatch(batch.getBatchId()));
                batchJSON.put(Constants.PAYMENT_DATE, batch.getPaymentDate());
                JSONArray batchStudentJSONArray = new JSONArray();
                List<Student> studentList = mStudentDetailsDao.getAllStudentForBatchId(batch.getBatchId());
                for (Student student : studentList) {
                    JSONObject studentJSON = new JSONObject();
                    studentJSON.put(Constants.ID, student.getStudentId());
                    studentJSON.put(Constants.STUDENT_NAME, student.getStudentName());
                    studentJSON.put(Constants.BATCH_ID_LIST, mStudentDetailsDao.getAllBatchIdsOfASudent(student.getStudentId()));
                    StudentBatchAssociation studentBatchAssociation = (StudentBatchAssociation) mStudentBatchAssociationDetailsDao.getStudentBatchAssociation(student.getStudentId(), batch.getBatchId());
                    studentJSON.put(Constants.IS_PAYMENT_DONE, studentBatchAssociation.isPaymentDone());
                    studentJSON.put(Constants.IS_ALUMNI, student.isAlumni());
                    studentJSON.put(Constants.DATE_OF_JOINING, student.getRegisteredTime());
                    studentJSON.put(Constants.MOBILE_NUMBER, student.getMobileNumber());
                    batchStudentJSONArray.put(studentJSON);
                }
                batchJSON.put(Constants.STUDENT_LIST, batchStudentJSONArray);
                batchJSONArray.put(batchJSON);
            }
            response.put(Constants.BATCH_LIST, batchJSONArray);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return response;
    }

}
