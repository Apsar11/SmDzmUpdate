package com.resource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.jws.WebService;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.axis.AxisFault;

@XmlRootElement
@WebService(endpointInterface = "com.resource.CarInterface")
public class Car implements CarInterface {

    private String result;

    public Message update(String unique_id, String sm, String dzm) {
        // Perform WS-Security authentication
     

        // Proceed with business logic if authentication succeeds
        Message m = new Message();
        Connection conn = DbConnection.getConnection();
        try {
            PreparedStatement up = conn.prepareStatement("UPDATE user_list SET sm=?, dzm=? WHERE unique_id=?");

            LocalDateTime t1 = LocalDateTime.now();
            DateTimeFormatter f1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String d1 = t1.format(f1);

            up.setString(1, sm);
            up.setString(2, d1);
            up.setString(3, unique_id);

            int rowsAffected = up.executeUpdate();
            if (rowsAffected > 0) {
                result = "Updated Successfully";
            } else {
                result = "Failed to update";
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "Failed to update";
        }

        LocalDateTime t2 = LocalDateTime.now();
        DateTimeFormatter f2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String d2 = t2.format(f2);

        m.setSm(sm);
        m.setDzm(d2);
        m.setUniqueId(unique_id);
        m.setResult(result);
        System.out.println(d2);

        return m;
    }

    // Authenticate using the WS-Security headers


    private Message createErrorMessage(String errorMsg) {
        Message m = new Message();
        m.setResult(errorMsg);
        return m;
    }
}
