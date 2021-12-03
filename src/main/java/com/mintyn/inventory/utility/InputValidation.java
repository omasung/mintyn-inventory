package com.mintyn.inventory.utility;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class InputValidation {

	public Boolean validateNumeric(String input) {

		Boolean bool = false;

		if (input != null && input.matches("[0-9]+") == true) {

			bool = true;

		}

		return bool;
	}
	
	public Boolean validateAlphanumerice(String input) {

		Boolean bool = true;

		if (input == null || input.isEmpty()) {

			bool = false;

		}

		return bool;
	}

	public Boolean validateDate(String date) {
		
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        boolean bool = false;
        
	    try {
	    	
    		Date validate = sdf.parse(date);
    		
    		if (validate != null) {
    			
        		bool = true;
    			
    		}
    		
	    } catch (Exception ex) {
	    	
	        System.out.println(date + " is invalid ");
	        
	    }
	    
	    
	    return bool;
		
	}	

	public LocalDate formatDate(String date) {
		
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now(ZoneOffset.of("+1"));
        
	    try {
	    	
	    	date = dateFormat.format(localDate);
	    	localDate = LocalDate.parse(date);;
    		
	    } catch (Exception ex) {
	    	
	        System.out.println(date + " could not format " + ex.getMessage());
	        
	    }
	    	    
	    return localDate;
		
	}	
	
}
