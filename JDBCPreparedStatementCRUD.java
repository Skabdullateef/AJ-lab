import java.sql.*;

public class JDBCPreparedStatementCRUD {

    // Database Details
    static final String URL = "jdbc:mysql://localhost:3306/college";
    static final String USER = "root";
    static final String PASSWORD = "root123";   // Replace with your MySQL password

    public static void main(String[] args) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            // Load JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to Database
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database Connected Successfully.\n");

            // -----------------------------
            // CREATE TABLE
            // -----------------------------
            String createTable =
                    "CREATE TABLE IF NOT EXISTS student(" +
                    "id INT PRIMARY KEY," +
                    "name VARCHAR(50)," +
                    "department VARCHAR(50))";

            ps = con.prepareStatement(createTable);
            ps.execute();
            ps.close();

            System.out.println("Table Created Successfully.\n");

            // -----------------------------
            // INSERT RECORD
            // -----------------------------
            String insert =
                    "INSERT INTO student(id,name,department) VALUES(?,?,?)";

            ps = con.prepareStatement(insert);

            ps.setInt(1,101);
            ps.setString(2,"Abdul");
            ps.setString(3,"IT");

            int rows = ps.executeUpdate();

            System.out.println(rows + " Record Inserted.\n");

            ps.close();

            // -----------------------------
            // DISPLAY RECORDS
            // -----------------------------
            String select = "SELECT * FROM student";

            ps = con.prepareStatement(select);

            rs = ps.executeQuery();

            System.out.println("----- STUDENT RECORDS -----");

            while(rs.next()) {

                System.out.println(
                        "ID : " + rs.getInt("id")
                        + "\tName : " + rs.getString("name")
                        + "\tDepartment : " + rs.getString("department")
                );

            }

            rs.close();
            ps.close();

            // -----------------------------
            // UPDATE RECORD
            // -----------------------------
            String update =
                    "UPDATE student SET department=? WHERE id=?";

            ps = con.prepareStatement(update);

            ps.setString(1,"CSE");
            ps.setInt(2,101);

            rows = ps.executeUpdate();

            System.out.println("\n" + rows + " Record Updated.\n");

            ps.close();

            // -----------------------------
            // DISPLAY AFTER UPDATE
            // -----------------------------
            ps = con.prepareStatement(select);

            rs = ps.executeQuery();

            System.out.println("----- AFTER UPDATE -----");

            while(rs.next()) {

                System.out.println(
                        "ID : " + rs.getInt("id")
                        + "\tName : " + rs.getString("name")
                        + "\tDepartment : " + rs.getString("department")
                );

            }

            rs.close();
            ps.close();

            // -----------------------------
            // DELETE RECORD
            // -----------------------------
            String delete =
                    "DELETE FROM student WHERE id=?";

            ps = con.prepareStatement(delete);

            ps.setInt(1,101);

            rows = ps.executeUpdate();

            System.out.println("\n" + rows + " Record Deleted.\n");

            ps.close();

            // -----------------------------
            // DISPLAY AFTER DELETE
            // -----------------------------
            ps = con.prepareStatement(select);

            rs = ps.executeQuery();

            System.out.println("----- AFTER DELETE -----");

            if(!rs.isBeforeFirst()) {

                System.out.println("No Records Found.");

            } else {

                while(rs.next()) {

                    System.out.println(
                            "ID : " + rs.getInt("id")
                            + "\tName : " + rs.getString("name")
                            + "\tDepartment : " + rs.getString("department")
                    );

                }

            }

            rs.close();
            ps.close();
            con.close();

            System.out.println("\nProgram Executed Successfully.");

        }
        catch(Exception e) {

            e.printStackTrace();

        }

    }
}
