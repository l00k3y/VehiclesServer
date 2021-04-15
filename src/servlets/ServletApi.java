package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import models.Vehicle;
import models.VehicleDAO;

public class ServletApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	VehicleDAO dao = new VehicleDAO();
	Gson gson = new Gson();
	PrintWriter writer;
	//for api keys, generate a key, check if the key is in the database/hardcoded, if so then pass, if not then reject
	//use java http servlet built in modules to set up this - bring across web pages from last coursework to set up admin page
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//populate allCon ArrayList with all vehicles using vehicle dao, convert to json & output the json
		ArrayList<Vehicle> allCon;
		try {
			allCon = dao.getAllVehicles();
			resp.setContentType("application/json");
			writer = resp.getWriter();
			String conJSON = gson.toJson(allCon);
			writer.write(conJSON);
			writer.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//retrieve vehicle details from vehicledata parameter, convert the json string to a vehicle object using gson
		//& insert the object using the vehicle dao
		writer = resp.getWriter();
		resp.setContentType("application/json");
		Vehicle insertVehicle = new Vehicle();
		String jsonString = req.getParameter("vehicledata");
		insertVehicle = gson.fromJson(jsonString, Vehicle.class);
		boolean inserted = false;
		try {
			inserted = dao.insertVehicle(insertVehicle);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (inserted) {
			writer.write("New vehicle added");
		} else {
			writer.close();
		}
	}

	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get vehicle details from request parameters and update vehicle details
		// create vehicle object from json data using GSON & pull vehicle id from json
		// data to pass to update vehicle function
		writer = resp.getWriter();
		resp.setContentType("application/json");
		Vehicle updateVehicle = new Vehicle();
		String jsonString = req.getParameter("vehicledata");
		
		boolean updated = false;
		try {
			updateVehicle = gson.fromJson(jsonString, Vehicle.class);
			updated = dao.updateVehicle(updateVehicle, updateVehicle.getVehicleID());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (updated) {
			writer.write("Vehicle updated");
		} else {
			writer.close();
		}
	}

	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//retrieve & parse vehicle_id from the request, delete the vehicle based off of the vehicle_id using the vehicle dao
		writer = resp.getWriter();
		System.out.println(req.getParameter("vehicle_id"));
		int vehicleID = Integer.parseInt(req.getParameter("vehicle_id"));
		boolean deleted = false;
		try {
				deleted = dao.deleteVehicle(vehicleID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (deleted) {
			writer.write("Vehicle deleted");
		} else {
			writer.close();
		}
	}
}