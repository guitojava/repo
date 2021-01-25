/*
 * Copyright (c) 2018. LeonDev, SA.
 * This software is the proprietary information of George Leon.
 * Use is subject to license terms.
 */

package app.deals.logic;

import app.deals.dao.entity.Deal;
import app.deals.pojo.DealDTO;
import app.deals.pojo.DealQueryDTO;
import app.sys.errorhandling.ServiceException;

public interface DealService {

    Deal create(DealDTO deal) throws ServiceException;

    Deal query(final DealQueryDTO dealQueryDTO) throws ServiceException;
}
