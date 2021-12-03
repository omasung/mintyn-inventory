package com.mintyn.inventory.utility.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import org.springframework.stereotype.Service;
import com.mintyn.inventory.utility.service.DateTimeService;

@Service
public class DateTimeServiceImpl implements DateTimeService {
		
	@Override
	public LocalDateTime PresentDateTime() {
		
		LocalDateTime now = LocalDateTime.now(ZoneOffset.of("+1"));
		
		return now;
	}
	
	@Override
	public LocalDate PresentDate() {
		
		LocalDate now = LocalDate.now(ZoneOffset.of("+1"));
		
		return now;
	}	
	
	@Override
	public LocalTime PresentTime() {
		
		LocalTime now = LocalTime.now(ZoneOffset.of("+1"));
		
		return now;
	}
	
	
}
