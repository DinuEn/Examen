import java.sql.*;
import java.sql.DriverManager;

public class DataBase {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/examen_java1p";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void connectToDB(){
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

}
