/*
 * Copyright (c) 2018. LeonDev, SA.
 * This software is the proprietary information of George Leon.
 * Use is subject to license terms.
 */

package app.sys.errorhandling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {
    private static final transient Logger logger = LoggerFactory.getLogger(GenericExceptionMapper.class);

    public Response toResponse(Throwable t) {


        if (t instanceof BadRequestException) {
            ServiceException sEx = new ServiceException(Response.Status.BAD_REQUEST.getStatusCode(),
                    "Http Bad Request",
                    ErrorMessageCodes.ErrorBadRequest);
            ErrorMessage errorMessage = new ErrorMessage(sEx);
            return Response
                    .status(errorMessage.getHttpStatus())
                    .entity(errorMessage)
                    .type(MediaType.APPLICATION_JSON).build();
        } else {


            ErrorMessage errorMessage = new ErrorMessage(t);
            return Response
                    .status(errorMessage.getHttpStatus())
                    .entity(errorMessage)
                    .type(MediaType.APPLICATION_JSON).build();
        }

    }
}
