package electricity.billing.system;

import java.sql.*;

public class Database {

    Connection connection;
    Statement statement;

    public Database() {
        try {
            // 1️⃣ MySQL Driver load
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2️⃣ Connection
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bill_system",
                    "root",
                    "Saurabh@123"
            );

            // 3️⃣ Statement
            statement = connection.createStatement();

            System.out.println("Database Connected Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
