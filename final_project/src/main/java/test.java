import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class test {

    public static void main(String[] args) {
        Connection conn;
        Statement stmt;
        try {
            // load the JDBC driver
            Class.forName("org.postgresql.Driver");

            // establish connection
            String url = "jdbc:postgresql://localhost:5432/UFOReports";
            conn = DriverManager.getConnection(url, "postgres", "admin");

            // query the database
            String sql = "select id, ufo_shape, duration, Time  from Sightings";
            stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(sql);

            // print the result
            if (res != null) {
                while (res.next()) {
                    System.out.println("id: " + res.getString("id"));
                    System.out.println("Shape: " + res.getString("ufo_shape"));
                    System.out.println("Duration (Mins): " + res.getString("duration"));
                    System.out.println("Time of Day: " + res.getString("Time"));
                }
            }

            // clean up
            stmt.close();
            conn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
