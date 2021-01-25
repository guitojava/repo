/*
 * Copyright (c) 2018. LeonDev, SA.
 * This software is the proprietary information of George Leon.
 * Use is subject to license terms.
 */

package app.deals.logic;

import app.deals.dao.DealDaoService;
import app.deals.dao.entity.Deal;
import app.deals.pojo.DealDTO;
import app.deals.pojo.DealQueryDTO;
import app.sys.errorhandling.ServiceException;
import app.sys.properties.AppProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DealServiceImpl implements DealService {

    private static final Logger log = LoggerFactory.getLogger(DealServiceImpl.class);
    private final AppProperties appProps;
    private final DealDaoService dealDaoService;

    @Autowired
    public DealServiceImpl(AppProperties appProps, DealDaoService dealDaoService) {
        this.appProps = appProps;
        this.dealDaoService = dealDaoService;
    }

    @Override
    public Deal create(DealDTO dealDTO) throws ServiceException {
        log.info("{}", dealDTO.toString());

        DealQueryDTO pq =  DealQueryDTO.mapper(dealDTO);
        Deal foundDeal = dealDaoService.get(pq);


        Deal entity =  Deal.mapper(dealDTO);
        if (foundDeal != null) {
            entity.setId(foundDeal.getId());
        }


        log.info("SAVE to DB   {} ", entity.toString());
       return dealDaoService.save(entity);
    }

    @Override
    public Deal query(final DealQueryDTO dealQueryDTO) throws ServiceException {
        return dealDaoService.get(dealQueryDTO);
    }

}
