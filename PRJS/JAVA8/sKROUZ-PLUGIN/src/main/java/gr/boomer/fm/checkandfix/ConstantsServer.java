/*******************************************************************************
 * Copyright (c) 2020. της Primer Software	v.1.0
 *
 ******************************************************************************/

package gr.boomer.fm.checkandfix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConstantsServer {


    private static final Logger logger = LoggerFactory.getLogger(ConstantsServer.class);


    // BACKEND EVENTS
    public static String PRIMER_FILE_CHANGE_EVENT = "PRIMER_FILE_CHANGE_EVENT";
    // this is written currently into the log


    public static String VERSION;
    public static String BASE_URL;

    public static Integer SERVER_THREADS_MAX;
    public static Integer SERVER_THREADS_MIN;
    public static Integer SERVER_THREADS_IDLE_TIMEOUT;

    public static final int SYSTEM_USER_ID = Integer.MAX_VALUE;
    public static final int MAX_USER_ID = 1000000;

    public static final String PDF = "pdf";
    public static final String JPG = "jpg";
    public static final String JPEG = "jpeg";
    public static final String PNG = "png";

    public static final String COMPANY_ID_HEADER = "Company-ID";


    public static final int SESSION_EXPIRATION_LIMIT = 600; // 10 hours
    public static final int CONNECT_FILE_UPLOAD_DELAY = 12;


    public static String DB_HOSTNAME = "";
    public static String DB_DATABASE = "";
    public static String DB_USERNAME = "";
    public static String DB_PASSWORD = "";
    public static String DROPBOX_PATH = "";

    public static final String UTF8 = "UTF-8";
    public static final int CONTROLLER_CACHE_CAPACITY = 10000;
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BASIC_AUTHORIZATION_PREFIX = "Basic ";
    public static final String TOKEN_AUTHORIZATION_PREFIX = "Token ";
    public static final String SERVICE_TOKEN_AUTHORIZATION_PREFIX = "ServiceToken ";
    public static final String API_PING_SUCCESS_MESSAGE = "pong";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String HTTP_JSON_CONTENT_TYPE = "application/json; charset=utf-8";
    public static final String HTTP_OCTET_STREAM_CONTENT_TYPE = "application/octet-stream";
    public static boolean DEBUG_MODE = false;
    public static String P12_PATH = null;
    public static String P12_PASS = null;


    public static void loadConstants() {

        loadConstants(true);
    }


    public static void loadConstants(boolean logProps) {

        Properties properties = new Properties();

        //the base folder is ./, the root of the main.properties file
        String outsidePath = "./primer.properties";

        try {
            //load the file handle for main.properties
            FileInputStream file = new FileInputStream(outsidePath);
            //load all the properties from this file
            properties.load(file);
        } catch (Exception ex) {
            // ignore
            logger.info("outsidePath = ./primer.properties NOT_FOUND will use  primer.properties  from RESOURCE FOLDER in jar");
        }

        if (properties.isEmpty()) {

            try (InputStream inputStream = ConstantsServer.class.getClassLoader()
                    .getResourceAsStream("primer.properties")) {
                logger.info("Properties from RESOURCE Folder");
                properties.load(inputStream);
            } catch (Exception ex) {
                logger.error("Can't load   primer.properties from   RESOURCE FOLDER  very serious  ERROR");
                logger.error("Exception ", ex);
                System.exit(-9); // very serious
            }
        } else {
            logger.info("Properties from outsidePath = ./primer.properties  ");
        }


        try {
            if (logProps) {
                logPrintProperties(properties);
            }
            readAllProperties(properties);
        } catch (Exception ex) {
            logger.error("Check  primer.properties  ");
            logger.error("Exception ", ex);
        }

    }

    public static void logPrintProperties(Properties properties) {
        logger.info("primer.properties (key : value)");
        for (Object key : properties.keySet()) {
            logger.info(key + " : " + properties.getProperty(key.toString()));
        }
    }

    public static void readAllProperties(Properties properties) {

        VERSION = properties.getProperty("primer.version");
        BASE_URL = properties.getProperty("base.url");
        SERVER_THREADS_MAX = new Integer(properties.getProperty("maxThreads"));
        SERVER_THREADS_MIN = new Integer(properties.getProperty("minThreads"));
        SERVER_THREADS_IDLE_TIMEOUT = new Integer(properties.getProperty("idleTimeout"));
        DB_HOSTNAME = properties.getProperty("database.hostname");
        DB_DATABASE = properties.getProperty("database.db");
        DB_USERNAME = properties.getProperty("database.username");
        DB_PASSWORD = properties.getProperty("database.password");
        DROPBOX_PATH = properties.getProperty("dropbox.path");
    }


}
