/*
 * Copyright (c) 2018. LeonDev, SA.
 * This software is the proprietary information of George Leon.
 * Use is subject to license terms.
 */

package app.deals.dao;

import app.deals.pojo.DealQueryDTO;
import app.deals.dao.entity.Deal;
import app.sys.errorhandling.ServiceException;

import java.util.stream.Stream;


public interface DealDaoService {

    Stream<Deal> getAll();
    Deal get(DealQueryDTO q) throws ServiceException;
    Deal save(Deal deal);
}