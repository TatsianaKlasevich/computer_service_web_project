package com.klasevich.cms.model.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final Logger logger = LogManager.getLogger();
    private static ConnectionPool instance = new ConnectionPool();
    private static final ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean connectionCreated = new AtomicBoolean();

    private static final String DRIVER_NAME = "driverName";
    private static final String URL = "url";
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static final String POOL_SIZE = "poolsize";
    private static final Integer DEFAULT_POOL_SIZE = 5;

    private BlockingQueue<Connection> freeConnections;
    private Queue<Connection> givenAwayConnections;

    private String driverName;
    private String url;
    private String user;
    private String password;
    private int poolSize;

    private ConnectionPool() {
        ResourceBundle bundle = ResourceBundle.getBundle("res.database");

        driverName = bundle.getString(DRIVER_NAME);
        url = bundle.getString(URL);
        user = bundle.getString(USER);
        password = bundle.getString(PASSWORD);

        try {
            poolSize = Integer.parseInt(bundle.getString(POOL_SIZE));
        } catch (NumberFormatException e) {
            logger.warn("An incorrect pool size {} in properties file. Use default size.", POOL_SIZE, e);
            poolSize = DEFAULT_POOL_SIZE;
        }
        try {
            initPoolData();
        } catch (ClassNotFoundException e) {
            logger.error("Can't find database driver", e);
        } catch (SQLException e) {
            logger.fatal("fatal exception, pool is unable to creat connection", e);
            throw new RuntimeException("Fatal exception during creating new connection", e);
        }
    }

    public static ConnectionPool getInstance() {//todo
        if (!connectionCreated.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                }
                connectionCreated.set(true);

            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    private void initPoolData() throws SQLException, ClassNotFoundException {
        Class.forName(driverName);
        freeConnections = new LinkedBlockingDeque<>(poolSize);
        givenAwayConnections = new ArrayDeque<>(poolSize);

        for (int i = 0; i < poolSize; i++) {
            Connection connection = DriverManager.getConnection(url, user, password);
            ProxyConnection proxyConnection = new ProxyConnection(connection);
            freeConnections.add(proxyConnection);
        }
    }

    public Connection takeConnection() {
        Connection connection = null;
        try {
            connection = freeConnections.take();
            givenAwayConnections.offer(connection);
        } catch (InterruptedException e) {
            logger.error("Error connecting to the data source.", e);
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection) {
            if (givenAwayConnections.remove(connection)) {
                freeConnections.offer(connection);
            }
        } else {
            logger.error("Connection to release is not proxy");
        }
    }

    public void destroyPool() throws ConnectionPoolException {
        try {
            for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
                ((ProxyConnection) freeConnections.take()).reallyClose();
            }
        } catch (SQLException e) {
            throw new ConnectionPoolException("Exception during closing free connection", e);
        } catch (InterruptedException e) {
            logger.error("Closing connection has been interrupted.", e);
            Thread.currentThread().interrupt();
        } finally {
            deregisterDrivers();
        }
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.error(e);
            }
        });
    }

    public void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.error("Connection hasn't returned to the pool");
        }
    }

    public void closeConnection (Connection connection, Statement statement, ResultSet... resultSets) {
        try {
            if (resultSets.length != 0) {
                for (int i = 0; i < resultSets.length; i++) {
                    resultSets[i].close();
                }
            }
        } catch (SQLException e) {
            logger.error("ResultSet hasn't closed", e);
        }
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            logger.error("Statement hasn't closed", e);
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.error("Connection hasn't returned to the pool", e);
        }
    }

    private class ProxyConnection implements Connection {
        private Connection connection;

        ProxyConnection(Connection connection) throws SQLException {
            this.connection = connection;
        }

         void reallyClose() throws SQLException {
            connection.close();
        }

        @Override
        public void clearWarnings() throws SQLException {
            connection.clearWarnings();
        }

        @Override
        public void close() {
            ConnectionPool.getInstance().releaseConnection(this);
        }

        @Override
        public void commit() throws SQLException {
            connection.commit();
        }

        @Override
        public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
            return connection.createArrayOf(typeName, elements);
        }

        @Override
        public Blob createBlob() throws SQLException {
            return connection.createBlob();
        }

        @Override
        public Clob createClob() throws SQLException {
            return connection.createClob();
        }

        @Override
        public NClob createNClob() throws SQLException {
            return connection.createNClob();
        }

        @Override
        public SQLXML createSQLXML() throws SQLException {
            return connection.createSQLXML();
        }

        @Override
        public Statement createStatement() throws SQLException {
            return connection.createStatement();
        }

        @Override
        public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
            return connection.createStatement(resultSetType, resultSetConcurrency);
        }

        @Override
        public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
                throws SQLException {
            return connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
            return connection.createStruct(typeName, attributes);
        }

        @Override
        public boolean getAutoCommit() throws SQLException {
            return connection.getAutoCommit();
        }

        @Override
        public String getCatalog() throws SQLException {
            return connection.getCatalog();
        }

        @Override
        public Properties getClientInfo() throws SQLException {
            return connection.getClientInfo();
        }

        @Override
        public String getClientInfo(String name) throws SQLException {
            return connection.getClientInfo(name);
        }

        @Override
        public int getHoldability() throws SQLException {
            return connection.getHoldability();
        }

        @Override
        public DatabaseMetaData getMetaData() throws SQLException {
            return connection.getMetaData();
        }

        @Override
        public int getTransactionIsolation() throws SQLException {
            return connection.getTransactionIsolation();
        }

        @Override
        public Map<String, Class<?>> getTypeMap() throws SQLException {
            return connection.getTypeMap();
        }

        @Override
        public SQLWarning getWarnings() throws SQLException {
            return connection.getWarnings();
        }

        @Override
        public boolean isClosed() throws SQLException {
            return connection.isClosed();
        }

        @Override
        public boolean isReadOnly() throws SQLException {
            return connection.isReadOnly();
        }

        @Override
        public boolean isValid(int timeout) throws SQLException {
            return connection.isValid(timeout);
        }

        @Override
        public String nativeSQL(String sql) throws SQLException {
            return connection.nativeSQL(sql);
        }

        @Override
        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency)
                throws SQLException {
            return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
        }

        @Override
        public CallableStatement prepareCall(String sql) throws SQLException {
            return connection.prepareCall(sql);
        }

        @Override
        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
                                             int resultSetHoldability) throws SQLException {
            return connection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public PreparedStatement prepareStatement(String sql) throws SQLException {
            return connection.prepareStatement(sql);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
                throws SQLException {
            return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
                                                  int resultSetHoldability) throws SQLException {
            return connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetConcurrency);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
            return connection.prepareStatement(sql, autoGeneratedKeys);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
            return connection.prepareStatement(sql, columnIndexes);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
            return connection.prepareStatement(sql, columnNames);
        }

        @Override
        public void rollback() throws SQLException {
            connection.rollback();
        }

        @Override
        public void setAutoCommit(boolean autoCommit) throws SQLException {
            connection.setAutoCommit(autoCommit);
        }

        @Override
        public void setCatalog(String catalog) throws SQLException {
            connection.setCatalog(catalog);
        }

        @Override
        public void setClientInfo(String name, String value) throws SQLClientInfoException {
            connection.setClientInfo(name, value);
        }

        @Override
        public void setHoldability(int holdability) throws SQLException {
            connection.setHoldability(holdability);
        }

        @Override
        public void setReadOnly(boolean readOnly) throws SQLException {
            connection.setReadOnly(readOnly);
        }

        @Override
        public Savepoint setSavepoint() throws SQLException {
            return connection.setSavepoint();
        }

        @Override
        public Savepoint setSavepoint(String name) throws SQLException {
            return connection.setSavepoint(name);
        }

        @Override
        public void setTransactionIsolation(int level) throws SQLException {
            connection.setTransactionIsolation(level);
        }

        @Override
        public boolean isWrapperFor(Class<?> iface) throws SQLException {
            return connection.isWrapperFor(iface);
        }

        @Override
        public <T> T unwrap(Class<T> iface) throws SQLException {
            return connection.unwrap(iface);
        }

        @Override
        public void abort(Executor executor) throws SQLException {
            connection.abort(executor);
        }

        @Override
        public int getNetworkTimeout() throws SQLException {
            return connection.getNetworkTimeout();
        }

        @Override
        public String getSchema() throws SQLException {
            return connection.getSchema();
        }

        @Override
        public void releaseSavepoint(Savepoint savepoint) throws SQLException {
            connection.releaseSavepoint(savepoint);
        }

        @Override
        public void rollback(Savepoint savepoint) throws SQLException {
            connection.rollback(savepoint);
        }

        @Override
        public void setClientInfo(Properties properties) throws SQLClientInfoException {
            connection.setClientInfo(properties);
        }

        @Override
        public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
            connection.setNetworkTimeout(executor, milliseconds);
        }

        @Override
        public void setSchema(String schema) throws SQLException {
            connection.setSchema(schema);
        }

        @Override
        public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
            connection.setTypeMap(map);
        }
    }
}
