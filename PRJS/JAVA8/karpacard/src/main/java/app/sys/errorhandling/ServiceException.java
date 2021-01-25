/*
 * Copyright (c) 2018. LeonDev, SA.
 * This software is the proprietary information of George Leon.
 * Use is subject to license terms.
 */

package app.sys.errorhandling;

public class ServiceException extends Exception {

    private Integer httpStatus;
    private String message;
    private String code;

    private static final long serialVersionUID = -528134378438377740L;

    public ServiceException(Integer httpStatus, String message, String code) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.code = code;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}