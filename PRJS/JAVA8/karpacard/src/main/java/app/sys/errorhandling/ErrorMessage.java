/*
 * Copyright (c) 2018. LeonDev, SA.
 * This software is the proprietary information of George Leon.
 * Use is subject to license terms.
 */

package app.sys.errorhandling;

import app.sys.filters.RequestFilter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@JsonInclude(Include.NON_NULL)
public class ErrorMessage implements Serializable {
    private Integer httpStatus;
    private String message;
    private String code;
    private String requestId;
    private static final long serialVersionUID = 5318063708359922770L;
    private static final String SYSTEM_ERROR_MSG = "Unexpected Error:  Contact support with requestId.";
    private static final transient Logger logger = LoggerFactory.getLogger(ErrorMessage.class);

    public ErrorMessage(ServiceException ex) {
        logger.warn("{}", ex.toString() );
        this.httpStatus = ex.getHttpStatus();
        this.message = ex.getMessage();
        this.code = ex.getCode();
        this.requestId = MDC.get(RequestFilter.REQUEST_ID_PARAM_NAME);
    }

    public ErrorMessage(Throwable t) {
        logger.error("INTERNAL_SERVER_ERROR", t);
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.message = SYSTEM_ERROR_MSG;
        this.code = ErrorMessageCodes.SysError1000;
        this.requestId = MDC.get(RequestFilter.REQUEST_ID_PARAM_NAME);
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

    public String getRequestId() {
        return requestId;
    }
}