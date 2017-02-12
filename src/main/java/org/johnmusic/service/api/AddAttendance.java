package org.johnmusic.service.api;

import org.johnmusic.service.Constants;
import org.johnmusic.service.api.Utils.UnionAndIntersionHelper;
import org.johnmusic.service.api.Utils.listEqualsNoOrder;
import org.johnmusic.service.database.dao.IBatchAttendanceDao;
import org.johnmusic.service.database.dao.IStudentDetailsDao;
import org.johnmusic.service.pojo.Batch;
import org.johnmusic.service.pojo.BatchAttendance;
import org.johnmusic.service.request.AddAttendanceRequest;
import org.johnmusic.service.request.StudentAttendance;
import org.johnmusic.service.response.ResponseBuilder;
import org.json.JSONObject;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bankuru on 12/2/17.
 */
@Path("/addAttendance")
@Produces("application/json")
@Consumes("application/json")

public class AddAttendance {
    private final IBatchAttendanceDao mBatchAttendanceDao;
    private final IStudentDetailsDao mStudentDetailsDao;

    @Inject
    public AddAttendance(IBatchAttendanceDao batchAttendanceDao, IStudentDetailsDao studentDetailsDao) {
        mBatchAttendanceDao = batchAttendanceDao;
        mStudentDetailsDao = studentDetailsDao;
    }

    @POST
    public Response addAttendance(AddAttendanceRequest addAttendanceRequest) {
        try {
            if (addAttendanceRequest == null){
                return ResponseBuilder.error(Constants.ERRORCODE_INVALID_INPUT, Constants.INVALID_REQUEST);
            }
            if (!addAttendanceRequest.isValid()) {
                return ResponseBuilder.error(Constants.ERRORCODE_INVALID_INPUT, addAttendanceRequest.errorMessage());
            }

            int userId = addAttendanceRequest.getUserId();
/*
            if (null == mStudentDetailsDao.getStudentForId(userId)) {
                return ResponseBuilder.error(Constants.ERRORCODE_INVALID_INPUT, Constants.INVALID_USER_ID);
            }
*/
            int batchId = addAttendanceRequest.getBatchId();
            long time = addAttendanceRequest.getTime();
            List<StudentAttendance> studentAttendanceList = addAttendanceRequest.getStudentAttendanceList();
            List<Integer> presentStudentIds = new ArrayList<Integer>();
            List<Integer> absentStudentIds = new ArrayList<Integer>();
            for (StudentAttendance studentAttendance : studentAttendanceList) {
                System.out.println(studentAttendance.isPresent());
                System.out.println(studentAttendance.getStudentId());
                if (studentAttendance.isPresent()) {
                    presentStudentIds.add(studentAttendance.getStudentId());
                } else {
                    absentStudentIds.add(studentAttendance.getStudentId());
                }
            }
            System.out.println(presentStudentIds);
            boolean ifAllBatchStudentsPresent = listEqualsNoOrder.listEqualsNoOrder(mStudentDetailsDao.getAllStudentIdsForBatchId(batchId), UnionAndIntersionHelper.union(presentStudentIds, absentStudentIds));
            if (!ifAllBatchStudentsPresent) {
                return ResponseBuilder.error(Constants.ERRORCODE_INVALID_INPUT, "Invalid values of studentIds given");
            }
            BatchAttendance batchAttendance = new BatchAttendance();
            batchAttendance.setBatchId(batchId);
            batchAttendance.setAttendanceTime(time);
            batchAttendance.setPresentStudentIds(presentStudentIds);
            batchAttendance.setAbsentStudentIds(absentStudentIds);
            boolean status = mBatchAttendanceDao.setBatchAttendance(batchAttendance);
            if (status) {
                return ResponseBuilder.successResponse();
            } else {
                return ResponseBuilder.error(Constants.ERRORCODE_INVALID_INPUT, mBatchAttendanceDao.getDetailedResponse().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBuilder.error(Constants.ERRORCODE_IOEXCEPTION, "Internal Server Error");
        }

    }
}
