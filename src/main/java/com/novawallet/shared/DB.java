package com.novawallet.shared;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.DatabaseMetaData;

/**
 * The type Db.
 */
public class DB {

    /**
     * The Conn.
     */
    public Connection conn;
    /**
     * The Stmt.
     */
    public Statement stmt;
    private ResultSet rs;

    /**
     * Instantiates a new Db.
     */
    public DB() {
        conn = getConnection();
    }

    /**
     * Gets connection.
     *
     * @return the connection
     */
    protected Connection getConnection() {
        if (conn != null) {
            return conn;
        }
        else {
            String schemaName = "nova_wallet";
            String user = "user";
            String pass = "password";
            String stringConnection = "jdbc:mysql://localhost:3306/" + schemaName;
            try {
                return DriverManager.getConnection(stringConnection, user, pass);
            } catch (SQLException e) {
                System.out.println("Error connecting to " + stringConnection);
                System.out.println(e.getMessage());
                return null;
            }
        }
    }

    /**
     * Connect.
     */
    public void connect() {
        if (this.conn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                this.conn = getConnection();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        try {
            stmt = this.conn.createStatement();
            System.out.println("DB connection: ON");
        } catch (SQLException ex) {
            System.out.println("There was an error.");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("ErrorCode: " + ex.getErrorCode());
        }
    }

    /**
     * Gets statement.
     *
     * @return the statement
     */
    public Statement getStatement() {
        if(stmt == null) {
            try {
                this.stmt = conn.createStatement();
            } catch (SQLException e) {
                System.out.print("Error creating stmt: " + e.getMessage());
                return null;
            }
        }
        return stmt;
    }

    /**
     * Gets meta data.
     *
     * @return the meta data
     */
    public DatabaseMetaData getMetaData() {
        if(this.conn==null) {
            this.connect();
        }
        try {
            return conn.getMetaData();
        } catch (SQLException e) {
            System.out.println("Error getting metadata: " + e.getMessage());
            return null;
        }
    }

    /* protected ArrayList<String> getTableNames() {
        if (conn==null) {
            connect();
        }
        ArrayList<String> tableNames = new ArrayList<String>();
        try {
            DatabaseMetaData md = this.getMetaData();
            ResultSet rs = md.getTables(null, null, "%", null);
            while (rs.next()) {
                tableNames.add(rs.getString(3));
            }
        } catch (SQLException e) {
            System.out.println("Error getting DB tables names");
        }
        return tableNames;
    } */

    /* protected ArrayList<String> getTableColumnNames(String tableName) {
        if (conn==null) {
            System.out.println("No DBConnection instance exists");
            return null;
        }
        else {
            ArrayList<String> tableColumnNames = new ArrayList<String>();
            String sql = "SELECT * FROM "+ tableName;
            try {
                ResultSet rs = stmt.executeQuery(sql);
                ResultSetMetaData resultSetMetaData = rs.getMetaData();
                int columnCount = resultSetMetaData.getColumnCount();
                for (int i = 1; i <= columnCount; i++ ) {
                    tableColumnNames.add(resultSetMetaData.getColumnName(i));
                }
            } catch (SQLException e) {
                System.out.println("Error getting DB table column names");
            }
            return tableColumnNames;
        }
    } */

    /**
     * Query result set.
     *
     * @param sql the sql
     * @return the result set
     */
    public ResultSet query(String sql) {
        if(stmt == null) {
            try {
                this.stmt = conn.createStatement();
            } catch (SQLException e) {
                System.out.print("Error creating stmt: " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
        try {
            rs = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            System.out.print("Error connecting DB:" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Update int.
     *
     * @param sql the sql
     * @return the int
     */
    public int update(String sql) {
        if(stmt == null) {
            try {
                this.stmt = conn.createStatement();
            } catch (SQLException e) {
                System.out.print("Error creating stmt: " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
        try {
            return stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.print("Error updating DB:" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /* public void close() {
        try {
            if(conn!=null) {
                conn.close();
                System.out.println("DB connection: CLOSED");
            } if(stmt!=null) {
                stmt.close();
                System.out.println("Statement: CLOSED");
            } if(rs!=null) {
                rs.close();
                System.out.println("ResultSet: CLOSED");
            }
        } catch (SQLException e) {
            System.out.println("Error closing DB");
        }
    } */
}
