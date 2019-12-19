package com.ayusha.job.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

/**
 * @author Finch
 * Date : 01-Aug-2019
 * Job Service Methods
 */
@Service
public class DateFormate {

	/**
	 * date and time formate
	 * 
	 * @return str
	 */
	public String dateFormateForAll() {
		// DateFormat dateFormat = new SimpleDateFormat("ddMMYY-");
		DateFormat dateFormat1 = new SimpleDateFormat("dd/MM/YY-HH:mm:ss");
		Calendar now = Calendar.getInstance();
		Date date = new Date();
		String str = dateFormat1.format(date);
		return str;

	}

	/**
	 * onlyDateFormate
	 * 
	 * @return str
	 */
	public String onlyDateFormate() {
		DateFormat dateFormat1 = new SimpleDateFormat("dd/MM/YY");
		Calendar now = Calendar.getInstance();
		Date date = new Date();
		String str = dateFormat1.format(date);
		return str;
	}

	/**
	 * onlyTimeFormate
	 * 
	 * @return str
	 */
	public String onlyTimeFormate() {
		DateFormat dateFormat1 = new SimpleDateFormat("HH:mm:ss");
		Calendar now = Calendar.getInstance();
		Date date = new Date();
		String str = dateFormat1.format(date);
		return str;
	}

}
