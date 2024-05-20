package com.novawallet.shared;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@Disabled
@ExtendWith(MockitoExtension.class)
class DBTest extends DB {

    @Mock
    private Connection mockConnection = mock(Connection.class);

    @Mock
    private Statement mockStatement=mock(Statement.class);

    @Mock
    private ResultSet mockResultSet;

    private DB db;

    @BeforeEach
    void setUp() throws SQLException, ClassNotFoundException {
        // Initialize the DB instance (anonymous subclass for testing purposes)
        db = new DB() {};

        // Mock DriverManager to return the mock connection
        when(DriverManager.getConnection(anyString(), anyString(), anyString())).thenReturn(mockConnection);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
    }

    @Test
    void connectTest() throws SQLException, ClassNotFoundException {
        db.connect();
        verify(mockConnection, times(1)).createStatement();
    }

    @Test
    void getStatementTest() {
    }

    @Test
    void getConnectionTest() {
    }

    @Test
    void getMetaDataTest() {
    }

    @Test
    void getTableNamesTest() {
    }

    @Test
    void getTableColumnNamesTest() {
    }

    @Test
    void queryTest() {
    }

    @Test
    void updateTest() {
    }

    @Test
    void closeTest() {
    }
}