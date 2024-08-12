package com.resource;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Response;

@WebService
public interface CarInterface {
	
	@WebMethod
	public   Message update(String unique_id,String sd,String dzm) ;
		
	

}
