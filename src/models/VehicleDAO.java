package models;
import java.sql.*;
import java.util.ArrayList;
//16025536 - Luke Bretherton
public class VehicleDAO {
	Boolean completed = false;
	Connection dbConnection = null;
	ResultSet resultset = null;
	private static Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			String dbURL = "jdbc:sqlite:vehicles.sqlite";
			dbConnection = DriverManager.getConnection(dbURL);
			return dbConnection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dbConnection;
	}

	public ArrayList<Vehicle> getAllVehicles() throws SQLException {
		ArrayList<Vehicle> allVehicles = new ArrayList<Vehicle>();
		Statement statement = null;
		String query = "SELECT * FROM vehicles;";
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			resultset = statement.executeQuery(query);
			while (resultset.next()) {
				Vehicle readVehicle1 = new Vehicle(resultset.getInt(1), resultset.getString("make"),
						resultset.getString("model"), resultset.getInt(4), resultset.getByte(5),
						resultset.getString("license_number"), resultset.getString("colour"), resultset.getInt(8),
						resultset.getString("transmission"), resultset.getInt(10), resultset.getString("fuel_type"),
						resultset.getInt(12), resultset.getString("body_style"), resultset.getString("condition"),
						(resultset.getString("Notes")));
				allVehicles.add(readVehicle1);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (resultset != null) {
				resultset.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return allVehicles;
	}

	public Vehicle getVehicle(int vehicleID) throws SQLException {
		PreparedStatement statement = null;
		Vehicle readVehicle1 = null;
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.prepareStatement("SELECT * FROM vehicles WHERE vehicle_id = ?;");
			statement.setInt(1, vehicleID);
			resultset = statement.executeQuery();
			readVehicle1 = new Vehicle(resultset.getInt(1), resultset.getString("make"), resultset.getString("model"),
					resultset.getInt(4), resultset.getByte(5), resultset.getString("license_number"),
					resultset.getString("colour"), resultset.getInt(8), resultset.getString("transmission"),
					resultset.getInt(10), resultset.getString("fuel_type"), resultset.getInt(12),
					resultset.getString("body_style"), resultset.getString("condition"),
					(resultset.getString("Notes")));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (resultset != null) {
				resultset.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return readVehicle1;
	}

	public Boolean deleteVehicle(int vehicleID) throws SQLException {
		Boolean completed = false;
		PreparedStatement statement = null;
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.prepareStatement("DELETE FROM vehicles WHERE vehicle_id = ?;");
			statement.setInt(1, vehicleID);
			statement.execute();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return completed;
	}

	public Boolean updateVehicle(Vehicle newVehicle, int updateVehicleID) throws SQLException {
		Boolean completed = false;
		PreparedStatement statement = null;
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.prepareStatement(
					"UPDATE vehicles SET vehicle_id = ?, make = ?, model = ?, year = ?, price = ?, license_number = ?, colour = ?, number_doors = ?, transmission = ?, mileage = ?, fuel_type = ?, engine_size = ?, body_style = ?, condition = ?, Notes = ? WHERE vehicle_id = ?;");
			statement.setInt(1, newVehicle.getVehicleID());
			statement.setString(2, newVehicle.getMake());
			statement.setString(3, newVehicle.getModel());
			statement.setInt(4, newVehicle.getYear());
			statement.setInt(5, newVehicle.getPrice());
			statement.setString(6, newVehicle.getLicenseNumber());
			statement.setString(7, newVehicle.getColour());
			statement.setInt(8, newVehicle.getNumberDoors());
			statement.setString(9, newVehicle.getTransmission());
			statement.setInt(10, newVehicle.getMileage());
			statement.setString(11, newVehicle.getFuelType());
			statement.setInt(12, newVehicle.getEngineSize());
			statement.setString(13, newVehicle.getBodyStyle());
			statement.setString(14, newVehicle.getCondition());
			statement.setString(15, newVehicle.getNotes());
			statement.setInt(16, newVehicle.getVehicleID());
			statement.execute();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return completed;
	}

	public Boolean insertVehicle(Vehicle newVehicle) throws SQLException {
		Boolean completed = false;
		PreparedStatement statement = null;
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.prepareStatement(
					"INSERT INTO vehicles (vehicle_id,make,model,year,price,license_number,colour,number_doors,transmission,mileage,fuel_type,engine_size,body_style,condition,Notes) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
			statement.setInt(1, newVehicle.getVehicleID());
			statement.setString(2, newVehicle.getMake());
			statement.setString(3, newVehicle.getModel());
			statement.setInt(4, newVehicle.getYear());
			statement.setInt(5, newVehicle.getPrice());
			statement.setString(6, newVehicle.getLicenseNumber());
			statement.setString(7, newVehicle.getColour());
			statement.setInt(8, newVehicle.getNumberDoors());
			statement.setString(9, newVehicle.getTransmission());
			statement.setInt(10, newVehicle.getMileage());
			statement.setString(11, newVehicle.getFuelType());
			statement.setInt(12, newVehicle.getEngineSize());
			statement.setString(13, newVehicle.getBodyStyle());
			statement.setString(14, newVehicle.getCondition());
			statement.setString(15, newVehicle.getNotes());
			statement.execute();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return completed;
	}
}
