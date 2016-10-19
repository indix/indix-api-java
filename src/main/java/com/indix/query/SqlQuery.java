package com.indix.query;

public class SqlQuery extends QueryBase {
    String sql;

    public SqlQuery() {
        super();
        sql = "";
    }

    /**
     * provide the sql query
     * @param sql input sql string
     * @return sql query
     */
    public SqlQuery withSql(String sql) {
        this.sql = sql;
        return this;
    }

    /**
     * retrieve content
     * @return string content
     */
    public String getContent() {
        return sql;
    }
}
