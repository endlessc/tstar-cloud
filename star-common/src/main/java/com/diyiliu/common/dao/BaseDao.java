package com.diyiliu.common.dao;

import com.diyiliu.common.util.JacksonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Description: BaseDao
 * Author: DIYILIU
 * Update: 2017-08-07 16:55
 */

public class BaseDao {
    protected static Logger logger = LoggerFactory.getLogger(BaseDao.class);

    protected static JdbcTemplate jdbcTemplate;

    public static void initDataSource(DataSource dataSource) {
        if (jdbcTemplate == null) {
            logger.info("装载数据源...");
            jdbcTemplate = new JdbcTemplate(dataSource);
        }
    }

    public boolean update(String sql, Object[] values) {
        if (jdbcTemplate == null) {
            logger.info("未装载数据源，无法连接数据库!");
            return false;
        }

        int result = 0;
        try {
            result = jdbcTemplate.update(sql, values);
        } catch (Exception e) {
            logger.error("msg:[{}], sql:[{}], 值:[{}]", e.getMessage(), sql, JacksonUtil.toJson(values));
            e.printStackTrace();
        }

        if (result > 0) {

            return true;
        }

        return false;
    }

    public boolean update(String sql) {
        if (jdbcTemplate == null) {
            logger.info("未装载数据源，无法连接数据库!");
            return false;
        }

        int result = jdbcTemplate.update(sql);

        if (result > 0) {

            return true;
        }

        return false;
    }
}
