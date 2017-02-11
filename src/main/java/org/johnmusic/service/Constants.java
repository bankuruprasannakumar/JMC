package org.johnmusic.service;

/**
 * Created by bankuru on 11/2/17.
 */
public class Constants {
    //ec2-52-66-134-60.ap-south-1.compute.amazonaws.com
    public static final String DB_SELECT_ENDPOINT = "http://ec2-52-66-134-60.ap-south-1.compute.amazonaws.com:8983/solr/strandsContent/select";
    public static final String DB_UPDATE_ENDPOINT = "http://ec2-52-66-134-60.ap-south-1.compute.amazonaws.com:8983/solr/strandsContent/update?commit=true";

    //Solr params
    public static final String SUCCESS = "success";
    public static final String REGISTERED_TIME = "registeredTime";
    public static final String RESPONSE = "response";
    public static final String DOCS = "docs";
    public static final String WT_JSON = "wt=json";
    public static final String GCMID = "gcmId";
    public static final String APNSID = "apNsId";
    public static final String FB_ID = "fbId";
    public static final String ALL = "*";
    public static final String AND = " AND ";
    public static final String SET = "set";
    public static final String DELETE_START = "{\"delete\":{\"query\":\"";
    public static final String DELETE_END = "\"}}";
    public static final String INSERT_START = "[";
    public static final String INSERT_END = "]";
    public static final String TRUE = "true";
    public static final String FALSE = "false";
    public static final String IOEXCEPTION_ERRORMESSAGE = "IO Exception";
    public static final int ERRORCODE_IOEXCEPTION = 102;
    public static final int ERRORCODE_INVALID_INPUT = 103;
    public static final int ERRORCODE_JSON_EXCEPTION = 101;
    public static final int ERRORCODE_INVALID_AUTH = 104;
    public static final String ACCEPT_CHARSET = "Accept-Charset";
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String NUMFOUND = "numFound";
    public static final String START = "start";
    public static final String ROWS = "rows";

    //Network params
    public static final String CHARSET = java.nio.charset.StandardCharsets.UTF_8.name();

    //Request params


    //Error messages


}
