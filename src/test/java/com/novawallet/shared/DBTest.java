package com.novawallet.shared;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DBTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private Statement mockStatement;

    @Mock
    private ResultSet mockResultSet;

    @Mock
    private DatabaseMetaData mockDatabaseMetaData;

    @Spy
    @InjectMocks
    private DB db;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        db.connect();
    }

    @Test
    void getConnectionTest() {
        assertEquals(mockConnection, db.getConnection());
    }

    @Test
    void connectTest() throws SQLException {
        assertNotNull(db.stmt);
        verify(mockConnection, times(1)).createStatement();
    }

    @Test
    void getStatementTest() {
        assertNotNull(db.getStatement());
    }

    @Test
    void getMetaDataTest() throws SQLException {
        when(mockConnection.getMetaData()).thenReturn(mockDatabaseMetaData);
        assertEquals(mockDatabaseMetaData, db.getMetaData());
    }

    /* @Test
    void getTableNamesTest() {
    }

    @Test
    void getTableColumnNamesTest() {
    } */

    @Test
    void queryTest() {
    }

    @Test
    void updateTest() {
    }

    /* @Test
    void closeTest() {
    } */
}