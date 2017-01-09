package com.cashflow.bean;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Utils {

	public static Utils instance;
	private Map<String, Integer> mapMeses;
	
	public static Utils getInstance() {
		if (instance == null) {
			instance = new Utils();
		}
		return instance;
	}
	
	public Utils() {
		mapMeses = new HashMap<String, Integer>();
		mapMeses.put("Jan", 0);
		mapMeses.put("Feb", 1);
		mapMeses.put("Mar", 2);
		mapMeses.put("Apr", 3);
		mapMeses.put("May", 4);
		mapMeses.put("Jun", 5);
		mapMeses.put("Jul", 6);
		mapMeses.put("Aug", 7);
		mapMeses.put("Sep", 8);
		mapMeses.put("Oct", 9);
		mapMeses.put("Nov", 10);
		mapMeses.put("Dec", 11);
	}

	public Date getDate(String fecha) {
		Calendar calendar = Calendar.getInstance();
		String args[] = fecha.split(" ");
		calendar.set(Calendar.YEAR, Integer.parseInt(args[2]));
		calendar.set(Calendar.MONTH, mapMeses.get(args[1]));
		calendar.set(Calendar.DATE, Integer.parseInt(args[0]));
		
		return calendar.getTime();
	}
}
