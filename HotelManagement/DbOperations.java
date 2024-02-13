
package HotelManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;



public class DbOperations {
	private static String host = "jdbc:mysql://localhost:3306/hotelmanagement";
	private static String userName = "root";
	private static String userPassword = "Sai@7702";

    private static Connection toCreateConnection()  {
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		
        }try {
        	Connection con = DriverManager.getConnection(host,userName,userPassword);
			return con;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
        
    }        

    public static void toCreateAHotelData(Hotel obj) {
        try {
            Connection con = toCreateConnection();
            String query = "INSERT INTO hotel (hotelName, hotelLocation, NoOfRoom, CostPerRoom) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(query);
            
            stmt.setString(1, obj.getHotelName());
            stmt.setString(2, obj.getHotelLocation());
            stmt.setInt(3, obj.getNoOfRoom());
            stmt.setFloat(4, obj.getCostPerRoom());

            stmt.executeUpdate();
            con.close();
            System.out.println("Thank you - Hotel creation successful\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public static HashMap<Integer, Hotel> toDisplayHotel() {
    HashMap<Integer, Hotel> hoteldetails = new HashMap<>();
    try {
        Connection con = toCreateConnection();
        PreparedStatement stmt = con.prepareStatement("Select * from hotel");

        ResultSet rowData = stmt.executeQuery();
        while (rowData.next()) {
            Hotel obj = new Hotel();
            obj.setHotelId(rowData.getInt(1));
            obj.setHotelName(rowData.getString(2));
            obj.setHotelLocation(rowData.getString(3));
            obj.setNoOfRoom(rowData.getInt(4));
            obj.setCostPerRoom(rowData.getFloat(5));
            hoteldetails.put(obj.getHotelId(), obj);
        }
        con.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return hoteldetails;
}

	


public static void toRemoveAHotelData(int id) {

	try {
		if (hotelExists( id)) {
			Connection con = toCreateConnection();
			PreparedStatement stmt = con.prepareStatement("Delete from hotel where HotelId = ?");
			stmt.setInt(1, id);
			
			stmt.executeUpdate();
			con.close();
			System.out.println("Hotel is Removed Successfully ");
		} else {
			System.out.println("Hotel does not exist with this id !!!!!!!");
		}
	} catch (SQLException e) {
	
		e.printStackTrace();
	}
}

public static boolean hotelExists(int id) {
	try {
		Connection con = toCreateConnection();
		PreparedStatement stmt = con.prepareStatement("Select * from hotel where HotelId = ?");
		stmt.setInt(1, id);
		ResultSet rowData = stmt.executeQuery();
		return rowData.next();
	} catch (SQLException e) {
				e.printStackTrace();
	}
	return false;
}

public static void toUpdatehotelName(String name, int id) {
	try {
		Connection con = toCreateConnection();
		PreparedStatement stmt = con.prepareStatement("Update hotel set hotelName = ? where HotelId = ?");
		stmt.setString(1, name);
		stmt.setInt(2, id);
		stmt.executeUpdate();
		con.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}

public static void toUpdatehotellocation(String location, int id) {
	try {
		Connection con = toCreateConnection();
		PreparedStatement stmt = con.prepareStatement("Update hotel set hotelLocation = ? where HotelId = ?");
		stmt.setString(1, location);
		stmt.setInt(2, id);
		stmt.executeUpdate();
		con.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}

public static void toUpdateNoOfRoom( int id) {
	try {
		Connection con = toCreateConnection();
		PreparedStatement stmt = con.prepareStatement("Update hotel set NoOfRoom = ? where HotelId = ?");
		
		stmt.setInt(1, id);
		stmt.executeUpdate();
		con.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}

public static void toUpdateCostPerRoom(float number, int id) {
	try {
		Connection con = toCreateConnection();
		PreparedStatement stmt = con.prepareStatement("Update hotel set CostPerRoom = ? where HotelId = ?");
		stmt.setFloat(1, number);
		stmt.setInt(2, id);
		stmt.executeUpdate();
		con.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}

public static Hotel toGethoteldata(int id) {
	try {
		Connection con = toCreateConnection();
		PreparedStatement stmt = con.prepareStatement("Select * from hotel where HotelId = ?");
		stmt.setInt(1, id);
		ResultSet rowData = stmt.executeQuery();
		rowData.next();
		Hotel obj = new Hotel();
		obj.setHotelId(rowData.getInt(1));
		obj.setHotelName(rowData.getString(2));
		obj.setHotelLocation(rowData.getString(3));
		obj.setNoOfRoom(rowData.getInt(4));
		obj.setCostPerRoom(rowData.getFloat(5));
		con.close();
		return obj;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
}


}

  

