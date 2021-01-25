/*
 * Copyright (c) 2018. LeonDev, SA.
 * This software is the proprietary information of George Leon.
 * Use is subject to license terms.
 */

package app.sys.health;


import app.sys.properties.AppProperties;
import app.sys.health.database.DBConnectionDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class AppHealthCheck implements HealthIndicator {

    private static final Logger log = LoggerFactory.getLogger(AppHealthCheck.class);

    @Autowired
    private AppProperties cmisProps;

    @Autowired
    private DBConnectionDao conDao;

    @Override
    public Health health() {
        if (!conDao.checkConnection()) {
            log.error("Can't get connection to DATABASE check if is DOWN");
            return Health.down().build();
        }
        return Health.up().build();
    }

}

