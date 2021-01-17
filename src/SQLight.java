import java.sql.*;

public class SQLight {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:sqlite:/C:\\sqlite\\sqlite-tools-win32-x86-3340000\\foxdb.db";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            String sql = "SELECT * FROM users";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                String name = result.getString("name");
                int score = result.getInt("score");

                System.out.println(name + " | " + score);
            }

        } catch (SQLException throwables) {
            System.out.println("Error connecting to SQlite database!");
            throwables.printStackTrace();
        }
    }
}
