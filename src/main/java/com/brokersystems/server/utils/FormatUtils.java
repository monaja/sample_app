package com.brokersystems.server.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.brokersystems.utilities.service.UtilitiesService;

public class FormatUtils {
	
	@Autowired
	private UtilitiesService utilitiesService;
	
	public static String formatCurrency(BigDecimal amount){
		DecimalFormat df = new DecimalFormat("#,###.00");
		return df.format(amount);
	}
	
	
	public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }


}
