package com.ubs.opsit.interviews;

public interface TimeConverter {

	/**
	 * Time converter implementation for Berlin clock and alike.
	 * 
	 * @param aTime time in ISO-8601 format.
	 * @return String output with represents Berlin clock (or alike) indicators state. 
	 */
    String convertTime(String aTime);

}
