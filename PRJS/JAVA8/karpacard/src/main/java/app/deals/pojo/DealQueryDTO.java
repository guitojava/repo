/*
 * Copyright (c) 2018. LeonDev, SA.
 * This software is the proprietary information of George Leon.
 * Use is subject to license terms.
 */

package app.deals.pojo;

import app.sys.errorhandling.ErrorMessageCodes;
import app.sys.errorhandling.ServiceException;
import app.sys.utils.DateUtils;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.Arrays;

@Data
public class DealQueryDTO {

    @ApiParam(value = "Account number", required = true)
    @QueryParam("accountNumber")
    String accountNumber;

    @ApiParam(value = "Document number", required = true)
    @QueryParam("documentNumber")
    String documentNumber;

    @ApiParam(value = "Document Type Format: 'I' for Invoice  or  'C' for  CREDIT NOTE ", required = true)
    @QueryParam("documentType")
    String documentType;

    @ApiParam(value = "Document Date  Format: YYYY-MM-DD, e.g. 2018-01-31")
    @QueryParam("documentDate")
    String documentDate;

    @ApiParam(value = "senderEntityCode ", required = true)
    @QueryParam("senderEntityCode")
    String senderEntityCode;

    public boolean validate() throws ServiceException {

        if (StringUtils.isBlank(accountNumber)) {
            String msg = "accountNumber is required";
            throw new ServiceException(Response.Status.BAD_REQUEST.getStatusCode(), msg, ErrorMessageCodes.Error2000);
        }

        if (StringUtils.isBlank(documentNumber)) {
            String msg = "documentNumber is required";
            throw new ServiceException(Response.Status.BAD_REQUEST.getStatusCode(), msg, ErrorMessageCodes.Error2000);
        }

        if (StringUtils.isBlank(documentType)) {
            String msg = "documentType is required";
            throw new ServiceException(Response.Status.BAD_REQUEST.getStatusCode(), msg, ErrorMessageCodes.Error2000);
        } else {
            try {
                DocumentType.valueOf(documentType);
            } catch (Exception ex) {
                String msg = "Incorrect documentType value = " + documentType + " ,valid values are " + Arrays.asList(DocumentType.values());
                throw new ServiceException(Response.Status.BAD_REQUEST.getStatusCode(), msg, ErrorMessageCodes.Error2000);
            }
        }

        if (StringUtils.isBlank(documentDate)) {
            String msg = "documentDate is required";
            throw new ServiceException(Response.Status.BAD_REQUEST.getStatusCode(), msg, ErrorMessageCodes.Error2000);
        } else {
            String msg = "Incorrect documentDate  value = " + documentDate + " ,valid values are in the format YYYY-MM-DD e.g. 2018-01-01  ";
            DateUtils.toDate(documentDate, msg, Response.Status.BAD_REQUEST, ErrorMessageCodes.Error2000);
        }

        if (StringUtils.isBlank(senderEntityCode)) {
            String msg = "senderEntityCode is required";
            throw new ServiceException(Response.Status.BAD_REQUEST.getStatusCode(), msg, ErrorMessageCodes.Error2000);
        }
        return true;
    }

    public static DealQueryDTO mapper(DealDTO dealDTO) throws ServiceException {
        ModelMapper modelMapper = new ModelMapper();
        DealQueryDTO dealQueryDTO = modelMapper.map(dealDTO, DealQueryDTO.class);
        return dealQueryDTO;
    }
}



