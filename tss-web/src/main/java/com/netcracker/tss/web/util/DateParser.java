package com.netcracker.tss.web.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

public class DateParser {

	public static Date parseDate(HttpServletRequest req) {
		String date = null;
		DateFormat format = new SimpleDateFormat("HH:mm, MMMM dd",
				Locale.ENGLISH);
		try {
			date = req.getParameter("ordertime");
			if (date != null && date != "")
				return format.parse(date.substring(0, date.length() - 2));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Date();
	}
}
