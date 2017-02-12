package org.johnmusic.service;

import com.sun.xml.internal.ws.api.ha.StickyFeature;

/**
 * Created by bankuru on 11/2/17.
 */
public class Constants {
    //ec2-52-66-134-60.ap-south-1.compute.amazonaws.com
    public static final String DB_SELECT_ENDPOINT = "http://ec2-35-154-161-135.ap-south-1.compute.amazonaws.com:8983/solr/JohnMusicClasses/select";
    public static final String DB_UPDATE_ENDPOINT = "http://ec2-35-154-161-135.ap-south-1.compute.amazonaws.com:8983/solr/JohnMusicClasses/update?commit=true";

    //Solr params
    public static final String ID = "id";
    public static final String SUCCESS = "success";
    public static final String RESPONSE = "response";
    public static final String DOCS = "docs";
    public static final String WT_JSON = "wt=json";
    public static final String ALL = "*";
    public static final String AND = " AND ";
    public static final String SET = "set";
    public static final String DELETE_START = "{\"delete\":{\"query\":\"";
    public static final String DELETE_END = "\"}}";
    public static final String INSERT_START = "[";
    public static final String INSERT_END = "]";
    public static final String TRUE = "true";
    public static final String FALSE = "false";
    public static final String NUMFOUND = "numFound";
    public static final String START = "start";
    public static final String ROWS = "rows";

    //Solr schema params
    public static final String STUDENT_ID = "studentId";
    public static final String REGISTERED_TIME = "registeredTime";
    public static final String IS_ALUMNI = "isAlumni";
    public static final String MOBILE_NUMBER = "mobileNumber";
    public static final String BATCH_ID = "batchId";
    public static final String INSTRUMENT_ID = "instrumentId";
    public static final String LEVEL = "level";
    public static final String DAY_ID = "dayId";
    public static final String START_TIME = "startTime";
    public static final String END_TIME = "endTime";
    public static final String CLASS_COUNT = "classCount";
    public static final String PAYMENT_DATE = "paymentDate";
    public static final String IS_PAYMENT_DONE = "isPaymentDone";
    public static final String STUDENT_NAME = "studentName";
    public static final String TOTAL_STUDENTS = "totalStudents";

    //Network params
    public static final String CHARSET = java.nio.charset.StandardCharsets.UTF_8.name();
    public static final String ACCEPT_CHARSET = "Accept-Charset";

    //Response params
    public static final String BATCH_LIST = "batchList";
    public static final String STUDENT_LIST = "studentList";
    public static final String BATCH_ID_LIST = "batchIdList";
    public static final String DATE_OF_JOINING = "dateOfJoining";


    //Error messages
    public static final String INVALID_USER_ID = "Invalid UserId";
    public static final String INVALID_INSTRUMENT_ID = "Invalid InstrumentId";
    public static final String INVALID_DAY_ID = "Invalid dayId";
    public static final String INVALID_BATCH_ID = "Invalid batchId";
    public static final String INVALID_TIME = "Invalid time";
    public static final String INVALID_STUDENT_ATTENDANCE_LIST = "Invalid studentAttendanceList";
    public static final String IOEXCEPTION_ERRORMESSAGE = "IO Exception";
    public static final int ERRORCODE_IOEXCEPTION = 102;
    public static final int ERRORCODE_INVALID_INPUT = 103;
    public static final int ERRORCODE_JSON_EXCEPTION = 101;
    public static final int ERRORCODE_INVALID_AUTH = 104;
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String INVALID_REQUEST = "Invalid request body";


}
