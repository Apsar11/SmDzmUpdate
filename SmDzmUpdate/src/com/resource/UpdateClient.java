package com.resource;

public class UpdateClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Car cr = new Car();
		Message msg;
		
		msg = cr.update("1", "7930", null);
		System.out.println("Unique_id" + msg.getUniqueId());
		System.out.println("Sm" + msg.getSm());
		System.out.println("Dzm" + msg.getDzm());
		System.out.println("Result" + msg.getResult());
		
		
		

	}

}
