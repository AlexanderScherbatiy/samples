package sample;

import java.sql.*;

/**
 * Created by alexsch.
 */
public class SimpleJavaDBSample {

    private static final String DB_NAME = "TestDB";
    private static final String TABLE_NAME = "CONTACTS";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_FIRST_NAME = "FIRSTNAME";
    private static final String COLUMN_LAST_NAME = "LASTNAME";
    private static final String COLUMN_EMAIL = "EMAIL";
    private static final String DB_URL = String.format("jdbc:derby:%s;create=true", DB_NAME);
    private static final String QUERY_CREATE_TABLE = String.format(
            "create table %s(%s int, %s varchar(40), %s varchar(40), %s varchar(40))",
            TABLE_NAME, COLUMN_ID, COLUMN_FIRST_NAME, COLUMN_LAST_NAME, COLUMN_EMAIL);


    public static void main(String[] args) throws Exception {
        Connection connection = DriverManager.getConnection(DB_URL);
        Statement statement = connection.createStatement();

        DatabaseMetaData dbmd = connection.getMetaData();
        ResultSet rs = dbmd.getTables(null, null, TABLE_NAME, null);
        if (!rs.next()) {
            statement.execute(QUERY_CREATE_TABLE);
            statement.execute(String.format("insert into %s values (1,'James', 'Sullivan', 'james.p.sullivan@monsters.com')",
                    TABLE_NAME));
            statement.execute(String.format("insert into %s values (2,'Mike', 'Wazowski', 'mike.wazowski@monsters.com')",
                    TABLE_NAME));
        }

        ResultSet resultSet = statement.executeQuery(String.format("select * from %s", TABLE_NAME));

        while (resultSet.next()) {
            int id = resultSet.getInt(COLUMN_ID);
            String firstName = resultSet.getString(COLUMN_FIRST_NAME);
            String lastName = resultSet.getString(COLUMN_LAST_NAME);
            String email = resultSet.getString(COLUMN_EMAIL);

            System.out.printf(String.format("[%d] first name: %s, last name: %s, email: %s\n",
                    id, firstName, lastName, email));
        }
    }
}
