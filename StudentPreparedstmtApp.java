import java.sql.*;
public class StudentPreparedstmtApp{
public static void main(String args[])
{
 String url="jdbc:mysql://localhost:3306/college";
 String USER = "12l4";
 String PASSWORD = "vitit"; 
 
 try{
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection con=DriverManager.getConnection(url,USER,PASSWORD);
    
              String createTable =
    "CREATE TABLE IF NOT EXISTS student (" +
    "RollNo INT PRIMARY KEY," +
    "name VARCHAR(50)," +
    "Address VARCHAR(30))";
    
    con.createStatement().executeUpdate(createTable);
    System.out.println("Table created successfully");
    
    Statement stmt=con.createStatement();
stmt.executeUpdate("INSERT IGNORE INTO student VALUES(1,'ravi','hyderabad')");
stmt.executeUpdate("INSERT IGNORE INTO student VALUES(2,'sita','chennai')");
stmt.executeUpdate("INSERT IGNORE INTO student VALUES(3,'kiran','bangalore')");
    System.out.println("Initial records inserted");
    
    System.out.println("\n initial records:");
    displayRecords(con);
    
    String insertSQL="INSERT INTO student(RollNo,name,Address)VALUES(?,?,?)";
    PreparedStatement insertStmt=con.prepareStatement(insertSQL);
    insertStmt.setInt(1,4);
    insertStmt.setString(2,"Meena");
    insertStmt.setString(3,"pune");
    insertStmt.executeUpdate();
        insertStmt.setInt(1,5);
    insertStmt.setString(2,"ramesh");
    insertStmt.setString(3,"mumbai");
    insertStmt.executeUpdate();
    System.out.println("Two new records insert3ed");
    
   

            // d. Update one record using PreparedStatement
            String updateSQL = "UPDATE student SET Address = ? WHERE RollNo = ?";
            PreparedStatement updateStmt = con.prepareStatement(updateSQL);
            updateStmt.setString(1, "Delhi");
            updateStmt.setInt(2, 2);
            updateStmt.executeUpdate();
            System.out.println("One record updated.");

            // e. Delete one record using PreparedStatement
            String deleteSQL = "DELETE FROM student WHERE RollNo = ?";
            PreparedStatement deleteStmt = con.prepareStatement(deleteSQL);
            deleteStmt.setInt(1, 3);
            deleteStmt.executeUpdate();
            System.out.println("One record deleted.");

            // f. Display updated content using PreparedStatement
            System.out.println("\nFinal Records:");
            displayRecords(con);

            // Close connection
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Function to display all records using PreparedStatement
    public static void displayRecords(Connection con) throws SQLException {
        String selectSQL = "SELECT * FROM student";
        PreparedStatement selectStmt = con.prepareStatement(selectSQL);
        ResultSet rs = selectStmt.executeQuery();

        System.out.println("RollNo\tname\tAddress");
        while (rs.next()) {
            int roll = rs.getInt("RollNo");
            String name = rs.getString("name");
            String address = rs.getString("Address");
            System.out.println(roll + "\t" + name + "\t" + address);
        }
    }
}
