/*
 * Copyright (c) 2018. LeonDev, SA.
 * This software is the proprietary information of George Leon.
 * Use is subject to license terms.
 */

package app.sys.errorhandling;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ServiceExceptionMapper implements ExceptionMapper<ServiceException> {

    @Override
    public Response toResponse(ServiceException ex) {
        ErrorMessage errorMessage = new ErrorMessage(ex);
        return Response
                .status(errorMessage.getHttpStatus())
                .entity(errorMessage)
                .type(MediaType.APPLICATION_JSON).build();
    }
}
