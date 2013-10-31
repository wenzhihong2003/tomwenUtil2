package com.tomwen.tomwenUtil;

import com.alibaba.druid.sql.SQLUtils;
import com.mysql.jdbc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.Properties;

/**
 * 用于出完整的mysql执行的sql语句
 * User: wenzhihong
 * Date: 10/18/13
 * Time: 10:23 AM
 */
public class MySqlLogIntecepter implements StatementInterceptor {
    SqlFormat sf = new SqlFormat();
    Logger logger = LoggerFactory.getLogger(MySqlLogIntecepter.class);

    @Override
    public void init(Connection connection, Properties properties) throws SQLException {

    }

    @Override
    public ResultSetInternalMethods preProcess(String sql, Statement statement, Connection connection) throws SQLException {
        String sqlText = "";
        if (sql == null) {
            if (statement instanceof StatementImpl) {
                StatementImpl st = (StatementImpl) statement;
                sqlText = st.toString();
                if (sqlText.indexOf(':') > 0) { //去掉前面的 com.mysql.jdbc.JDBC4PreparedStatement@1354d59:
                    sqlText = sqlText.substring(sqlText.indexOf(':') + 1).trim();
                }
            }
        } else {
            //if(sql.startsWith("SHOW FULL TABLES FROM") == false){
            sqlText = sql;
            //}
        }

        if (sqlText.length() < 1024) { //没有超过1k的话,就用hibernate的格式化
            sqlText = sf.format(sqlText).trim();
        } else {
            sqlText = SQLUtils.formatMySql(sqlText);
        }


        if ("".equals(sqlText.trim()) == false) {
            StringBuilder sb = new StringBuilder("\n----------执行的sql--------------\n");
            sb.append(sqlText);
            sb.append("\n---------------------------------------\n");

            logger.info(sb.toString());
        }

        return null;
    }

    @Override
    public ResultSetInternalMethods postProcess(String s, Statement statement, ResultSetInternalMethods resultSetInternalMethods, Connection connection) throws SQLException {
        return null;
    }

    @Override
    public boolean executeTopLevelOnly() {
        return false;
    }

    @Override
    public void destroy() {
    }
}
