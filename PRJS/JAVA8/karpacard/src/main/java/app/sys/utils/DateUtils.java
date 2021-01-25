/*
 * Copyright (c) 2018. LeonDev, SA.
 * This software is the proprietary information of George Leon.
 * Use is subject to license terms.
 */

package app.sys.utils;

import app.sys.errorhandling.ServiceException;

import javax.ws.rs.core.Response;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    /**
     * Do not allow object creation
     */
    private DateUtils() {
    }


    /**
     * addDays soem days to a date
     *
     * @param date Date
     * @param days int
     * @return Date
     */
    public static final Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    /**
     * @return today  String as  format  yyyy-MM-dd
     */
    public static final String today() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date now = Calendar.getInstance().getTime();
        String today = df.format(now);
        return today;
    }

    /**
     * @param date input
     * @return String as  format  yyyy-MM-dd
     */
    public static final String dateOnlyAsString(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String today = df.format(date);
        return today;
    }

    /**
     * @param date
     * @return
     */
    public static final String dateWithTimeAsString(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        String today = df.format(date);
        return today;
    }

    /**
     * toDate FROM TEXT
     *
     * @param value     String
     * @param errorMsg  String
     * @param errorCode ErrorMessageCodes
     * @param status    Response.Status
     * @return DATE
     * @throws ServiceException error
     */
    public static final Date toDate(String value, String errorMsg, Response.Status status, String errorCode) throws ServiceException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return df.parse(value);
        } catch (Exception ex) {
            throw new ServiceException(status.getStatusCode(), errorMsg, errorCode);
        }
    }
}
