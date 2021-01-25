/*
 * Copyright (c) 2018. LeonDev, SA.
 * This software is the proprietary information of George Leon.
 * Use is subject to license terms.
 */

package app.deals.api;

import app.deals.logic.DealService;
import app.deals.pojo.DealDTO;
import app.deals.pojo.DealQueryDTO;
import app.deals.dao.entity.Deal;
import app.sys.ApiPaths;
import app.sys.errorhandling.ServiceException;
import app.sys.logging.LogExecutionTime;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Component
@Path(ApiPaths.DEAL_API_V1_BASE_URL)
@Produces(MediaType.APPLICATION_JSON)
@Api
public class DealAPI {

    private static final Logger log = LoggerFactory.getLogger(DealAPI.class);

    private final HttpServletRequest req;
    private final DealService dealService;


    @Autowired
    public DealAPI(HttpServletRequest req,
                   DealService dealService) {
        this.req = req;
        this.dealService = dealService;
    }


    //////////////////
    //////////////////
    @POST
    @Consumes(MediaType.APPLICATION_XML)

    @ApiOperation(
            tags = "1 Create a Deal",
            value = "Create a Deal",
            notes = DealAPIDocs.processDocs
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Resource not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @LogExecutionTime
    public Response create(


            @BeanParam DealDTO deal

    ) throws ServiceException {

        deal.validate();
        Deal savedDeal = dealService.create(deal);


        return Response
                .status(Response.Status.OK)
                .entity( savedDeal )
                .build();

    }

    @GET
    @ApiOperation(
            tags = "2 Get Deal"
            , value = "Get Deal"
            , notes = DealAPIDocs.statusDocs
            , response = Deal.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @LogExecutionTime
    public Response get(@BeanParam DealQueryDTO dealQueryDTO) throws ServiceException {

        dealQueryDTO.validate();
        log.debug("{}", dealQueryDTO.toString());
        Deal result = dealService.query(dealQueryDTO);

        if (result != null) {
            return Response
                    .status(Response.Status.OK)
                    .entity(result)
                    .build();

        } else {
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        }


    }


}


