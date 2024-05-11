package com.novawallet.shared;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.DatabaseMetaData;

public abstract class DB {

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    protected void connect() {
        try {
            // CREDENCIALES
            String user = "root";
            String pass = "admin";

            Class.forName("com.mysql.jdbc.Driver");
            String stringConnection = "jdbc:mysql://localhost:3306/";
            conn = DriverManager.getConnection(stringConnection, user, pass);
            stmt = conn.createStatement();
            System.out.println("DB connection: ON");
        } catch (ClassNotFoundException ex)  {
            System.out.println("Driver not found");
        } catch (SQLException ex) {
            System.out.println("There was an error.");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("ErrorCode: " + ex.getErrorCode());
        }
    }

    protected Statement getStatement() throws SQLException {
        return stmt;
    }

    protected Connection getConnection() {
        return conn;
    }

    protected DatabaseMetaData getMetaData() throws SQLException {
        if (conn==null || conn.isClosed()) {
            System.out.println("No DBConnection instance exists");
            return null;
        }
        else {
            return conn.getMetaData();
        }
    }

    protected ArrayList<String> getTableNames() throws SQLException {
        if (conn==null || conn.isClosed()) {
            System.out.println("No DBConnection instance exists");
            return null;
        }
        else {
            ArrayList<String> tableNames = new ArrayList<String>();
            try {
                DatabaseMetaData md = this.getMetaData();
                ResultSet rs = md.getTables("nova_wallet", null, "%", null);

                while (rs.next()) {
                    tableNames.add(rs.getString(3));
                }
            } catch (SQLException e) {
                System.out.println("Error getting DB tables names");
            }
            return tableNames;
        }
    }

    protected ArrayList<String> getTableColumnNames(String tableName) throws SQLException {
        if (conn==null || conn.isClosed()) {
            System.out.println("No DBConnection instance exists");
            return null;
        }
        else {
            ArrayList<String> tableColumnNames = new ArrayList<String>();
            String sql = "SELECT * FROM nova_wallet."+ tableName;
            try {
                ResultSet rs = stmt.executeQuery(sql);
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                for (int i = 1; i <= columnCount; i++ ) {
                    tableColumnNames.add(rsmd.getColumnName(i));
                }
            } catch (SQLException e) {
                System.out.println("Error getting DB table column names");
            }
            return tableColumnNames;
        }
    }

    protected ResultSet query(String sql) {
        try {
            connect();
            this.stmt= conn.createStatement();
            rs = stmt.executeQuery(sql);
            close();
            return rs;
        } catch (SQLException e) {
            System.out.print(e.getMessage());
            return null;
        }
    }

    protected int update(String sql) {
        try {
            connect();
            this.stmt= conn.createStatement();
            int updated = stmt.executeUpdate(sql);
            close();
            return updated;
        } catch (SQLException e) {
            System.out.print(e.getMessage());
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
