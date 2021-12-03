package com.mintyn.inventory.utility.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface DateTimeService {

	LocalDateTime PresentDateTime();
	
	LocalDate PresentDate();

	LocalTime PresentTime();

}
