/*
 * Copyright (c) 2018. LeonDev, SA.
 * This software is the proprietary information of George Leon.
 * Use is subject to license terms.
 */

package app.deals.dao;

import app.deals.pojo.DealQueryDTO;
import app.deals.dao.entity.Deal;
import app.sys.errorhandling.ErrorMessageCodes;
import app.sys.errorhandling.ServiceException;
import app.sys.utils.DateUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.core.Response;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Component
public class DealDaoServiceImpl implements DealDaoService {

    private static final Logger log = LoggerFactory.getLogger(DealDaoServiceImpl.class);
    private DealDao dealDao;

    @Autowired
    public DealDaoServiceImpl(DealDao dealDao ) {
        this.dealDao = dealDao;
    }

    public Stream<Deal> getAll() {
        return StreamSupport.stream(dealDao.findAll().spliterator(), true);
    }


    public Deal get(DealQueryDTO q) throws ServiceException {
        Date docDate = DateUtils.toDate(q.getDocumentDate(), "", Response.Status.INTERNAL_SERVER_ERROR, ErrorMessageCodes.SysError1002);

        List<Deal> results = dealDao.find(
                q.getAccountNumber(),
                q.getDocumentNumber(),
                q.getDocumentType(),
                docDate,
                q.getSenderEntityCode());


        if (CollectionUtils.isNotEmpty(results)) {
            return results.get(0);
        } else {
            return null;
        }
    }

    public Deal save(Deal deal) {
        return dealDao.save(deal);
    }



}