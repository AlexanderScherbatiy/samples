package samples;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;
import java.util.Properties;

public class SQLTest {

    @Test
    public void testDerby() throws IOException, SQLException {
        testDB("derby.properties");
    }

    @Test
    public void testPostgreSQL() throws IOException, SQLException {
        testDB("postgresql.properties");
    }

    private void testDB(String propertyFile) throws IOException, SQLException {

        Properties properties = getProperties(propertyFile);
        String jdbcDriver = properties.getProperty("jdbc.driver");
        String jdbcURL = properties.getProperty("jdbc.url");
        String jdbcUser = properties.getProperty("jdbc.user");
        String jdbcPassword = properties.getProperty("jdbc.password");
        System.out.printf("jdbc.driver=%s%n", jdbcDriver);
        System.out.printf("jdbc.url=%s%n", jdbcURL);
        System.out.printf("jdbc.user=%s%n", jdbcUser);
        System.out.printf("jdbc.password=%s%n", jdbcPassword);

        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPassword)) {
            testDB(connection);
        }
    }

    private void testDB(Connection connection) throws SQLException {

        if (!tableExists(connection, "numbers")) {
            String createString = "CREATE TABLE numbers (number INT NOT NULL, value VARCHAR(32) NOT NULL)";
            connection.createStatement().execute(createString);
            connection.createStatement().executeUpdate("INSERT INTO numbers VALUES (5, 'five')");
            connection.createStatement().executeUpdate("INSERT INTO numbers VALUES (7, 'seven')");
        }

        try (ResultSet resultSet = connection.createStatement().executeQuery("SELECT number, value FROM numbers")) {
            int i = 0;
            Object[][] golden = {{5, "five"}, {7, "seven"}};
            while (resultSet.next()) {
                int key = resultSet.getInt("number");
                String value = resultSet.getString("value");
                System.out.printf("number: %d, value: %s%n", key, value);
                assertEquals(golden[i][0], key);
                assertEquals(golden[i][1], value);
                i++;
            }
        }

        connection.createStatement().execute("DROP TABLE numbers");
    }

    private static boolean tableExists(Connection connection, String tableName) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet rs = metaData.getTables(null, null, tableName, null)) {
            return rs.next();
        }
    }

    private static Properties getProperties(String propertyFile) throws IOException {
        Properties properties = new Properties();
        properties.load(SQLTest.class.getClassLoader().getResourceAsStream(propertyFile));
        return properties;
    }


}
