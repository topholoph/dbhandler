package dbHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
//
//		String dbUrl = "jdbc:mysql://localhost:3306/tnsdb_1_12_5";
//		String user = "root";
//		String password = "feli1337";

		
		String dbUrl = "jdbc:mysql://192.168.0.87:3306/tnsdb_1_12_5";
		String user = "root";
		String password = "123";
		try {
			connection = Database.getConnection(dbUrl, user, password);
			System.out.println("Database connection successful");
			System.out.println("+++++++++++++++++++++++++++++");

			// Let's Make a Plan
			// 1. Create SQL for PreparedStatement using Parameters

			String sql = "SELECT name, guiDescription FROM EventType WHERE EventPrioType_ID > ?";

			// 2. Create a Prepared Statement

			preparedStatement = connection.prepareStatement(sql);

			// 3. Insert Parameter value(s) into PreparedStatement

			preparedStatement.setInt(1, 10);

			// 4. Execute the PreparedStatement

			resultSet = preparedStatement.executeQuery();

			// 5. Process the ResultSet (if applicable)

			while (resultSet.next()) {
				System.out.println(resultSet.getString("name") + " " + resultSet.getString("guiDescription")) ;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.closeResultSet(resultSet);
			Database.closePreparedStatement(preparedStatement);
			Database.closeConnection(connection);
		}
	}
}