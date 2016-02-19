package com.ubs.opsit.interviews;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * Time converter implementation for Berlin clock and alike.
 * 
 * @author Eugene
 */
public class TimeConverterImpl implements TimeConverter {
	
	private static final String DEFAULT_OUTPUT_ROW_DELIMITER = System.lineSeparator();
	
	private final IndicatorsRow[] rows;
	
	private final String outputRowDelimiter;

	public TimeConverterImpl(IndicatorsRow[] rows) {
		this(rows, null);
	}
	
	public TimeConverterImpl(IndicatorsRow[] rows, String outputRowDelimiter) {
		checkRows(rows);
		this.rows = rows;
		this.outputRowDelimiter = (outputRowDelimiter != null) ? 
				outputRowDelimiter : DEFAULT_OUTPUT_ROW_DELIMITER;
	}


	/**
	 * Time converter implementation for Berlin clock and alike.
	 * Seconds are used as base time units.
	 * 
	 * @param aTime time in ISO-8601 format.
	 * @return String output with represents Berlin clock (or alike) indicators state. 
	 */
	@Override
	public String convertTime(String aTime) {
		long base = getTimeInSeconds(aTime);
		
		final StringBuilder output = new StringBuilder();
		
		for(int i = 0; i < rows.length; i++) {
			base = rows[i].setup(base);
			renderOutput(output, rows[i]);			
			rows[i].reset();
		}
		
		return output.toString();

	}	
	
	private long getTimeInSeconds(String aTime) throws IllegalArgumentException {
		if(aTime == null) {
			throw new IllegalArgumentException("Time must have not null value");
		}
		if(aTime.isEmpty()) {
			throw new IllegalArgumentException("Time must have not empty value");
		}

		try {
			final LocalTime time = LocalTime.parse(aTime);
			return time.toSecondOfDay();
			
		} catch(DateTimeParseException ex) {
			throw new IllegalArgumentException("Time must be in ISO-8601 format", ex);
		}
		
	}
	
	private void renderOutput(StringBuilder output, IndicatorsRow row) {
		if(output.length() != 0) {
			output.append(outputRowDelimiter);
		}
		output.append(row.getOutput());
	}
	
	private void checkRows(IndicatorsRow[] rows) {
		if(rows == null || rows.length == 0) {
			throw new IllegalArgumentException("Rows cannot not be null or empty array");
		}
		
		for(IndicatorsRow row : rows) {
			if(row == null) {
				throw new IllegalArgumentException("Rows array cannot contain null elements");
			}
		}
	}

}
