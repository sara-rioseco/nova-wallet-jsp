package com.novawallet.shared;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.DatabaseMetaData;

public class DB {

    public static Connection conn;
    public Statement stmt;
    private ResultSet rs;

    public void connect() {
        String schemaName = "nova_wallet";
        String user = "user";
        String pass = "password";
        String stringConnection = "jdbc:mysql://localhost:3306/" + schemaName;
        if (conn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(stringConnection, user, pass);
                stmt = conn.createStatement();
                System.out.println("DB connection: ON");
            } catch (ClassNotFoundException ex) {
                System.out.println("Driver not found");
            } catch (SQLException ex) {
                System.out.println("There was an error.");
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("ErrorCode: " + ex.getErrorCode());
            }
        }
    }

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

    public Connection getConnection() {
        if(conn == null) {
            connect();
        }
        return conn;
    }

    protected DatabaseMetaData getMetaData() throws SQLException {
        if(conn==null) {
            connect();
        }
            return conn.getMetaData();
    }

    protected ArrayList<String> getTableNames() throws SQLException {
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
    }

    protected ArrayList<String> getTableColumnNames(String tableName) throws SQLException {
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
    }

    public ResultSet query(String sql) {
        if(stmt == null) {
            try {
                this.stmt = conn.createStatement();
            } catch (SQLException e) {
                System.out.print("Error creating stmt: " + e.getMessage());
                return null;
            }
        }
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.print("Error connecting DB:" + e.getMessage());
        }
        return rs;
    }

    public int update(String sql) {
        if(stmt == null) {
            try {
                this.stmt = conn.createStatement();
            } catch (SQLException e) {
                System.out.print("Error creating stmt: " + e.getMessage());
                return 0;
            }
        }
        try {
            return stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.print("Error updating DB:" + e.getMessage());
            return 0;
        }
    }

    protected void close() {
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
    }
}
