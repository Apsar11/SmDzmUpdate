package com.resource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.jws.WebService;

//import org.apache.axis.AxisFault;
//import org.apache.axis.MessageContext;

@WebService(endpointInterface = "com.resource.CarInterface")
public class Car extends ServerPublisher implements CarInterface  {

	private String result;

	public Message update(String unique_id, String sm, String dzm) {

		/*try {
			authenticate();
		} catch (AxisFault e1) {
			e1.printStackTrace();
			System.out.println(e1.getMessage());
		}*/
		Message m = new Message();

		Connection conn = DbConnection.getConnection();
		try {
			PreparedStatement up = conn.prepareStatement("UPDATE user_list set sm=?,dzm=? where unique_id=?");
			
			 LocalDateTime t1 = LocalDateTime.now();
			 DateTimeFormatter f1 = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");
			 String d1 = t1.format(f1);
	           // Timestamp d1 = Timestamp.valueOf(now);
			if(!(m.getSm()==null) && !m.getSm().isEmpty()) {
			 up.setString(1, sm);
			}else {
				up.setString(1, null);
			}
			up.setString(2, d1);//(2, d1.getDate());
			up.setString(3, unique_id);

			int RowAffected = up.executeUpdate ();
			if (RowAffected > 0) {
				result = "Updated Successfully";
			}else {
				result ="failed to update";
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			result = "Failed to update";
		}
		 LocalDateTime t1 = LocalDateTime.now();
		 DateTimeFormatter f1 = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");
		 String d1 = t1.format(f1);
		m.setSm(sm);
		m.setDzm(d1);
		m.setUniqueId(unique_id);
		m.setResult(result);
		System.out.println(d1);
  		return  m;

	}


 
	/*
	 * private void authenticate() throws AxisFault { MessageContext msgContext =
	 * MessageContext.getCurrentContext(); String username = (String)
	 * msgContext.getProperty("Username"); String password = (String)
	 * msgContext.getProperty("Password");
	 * 
	 * if (!"Apsar".equals(username) || !"Apsar@11".equals(password)) { throw new
	 * AxisFault("Authentication failed"); } }
	 */

}