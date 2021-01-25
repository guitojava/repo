/*
 * Copyright (c) 2018. LeonDev, SA.
 * This software is the proprietary information of George Leon.
 * Use is subject to license terms.
 */

package app.sys.health.database;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Repository
public class DBConnectionDaoImpl extends JdbcDaoSupport implements DBConnectionDao {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    public Boolean checkConnection() {
        try {
            String sql = "SELECT 1 ";
            int ret = getJdbcTemplate().queryForObject(sql, Integer.class);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }


}

