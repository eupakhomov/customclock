package com.ubs.opsit.interviews;

import java.util.Arrays;

/**
 * Represents the row of indicators in Berlin clock 
 * (or in other clock based on the same principles) 
 * 
 * @author Eugene
 *
 */
public abstract class IndicatorsRow {
	
	public static final char NO_COLOR = 'O';
	
	private final char[] output;
	private final int length;
	
	public IndicatorsRow(int length) {		
		checkLength(length);		
		this.length = length;
		this.output = new char[length];
		Arrays.fill(this.output, NO_COLOR);
	}
	

	/**
	 * Setup with given time.
	 * 
	 * @param units of time of appropriate dimension. 
	 * @return units of time of appropriate dimension for next row.
	 */
	public long setup(long units) {
		checkUnits(units);
		
		int activeIndicators = getActiveCount(units);
		
		if(activeIndicators > length) {
			throw new IllegalStateException(
					"Rows must be set " +
							"to produce number of indicators not greater than row length");
		}
		
		for(int j = 0; j < activeIndicators; j++) {
			output[j] = getColor(j + 1);
		}
		
		return getUpdatedUnits(units);

	}
	
	/**
	 * Reset row output.
	 */
	public void reset() {
		Arrays.fill(this.output, NO_COLOR);
	}
	
	/**
	 * Get String with rendered row output.
	 * 
	 * @return String with rendered row output.
	 */
	public String getOutput() {
		return new String(output);
	}
	
	/**
	 * Get active indicators number in the row.
	 * 
	 * @param units of time of appropriate dimension for the row. 
	 * @return number of active indicators in the row.
	 */
	protected abstract int getActiveCount(long units);
	
	/**
	 * Get updated units value of appropriate dimension for the next row.
	 * 
	 * @param units of time of appropriate dimension for the row.
	 * @return units of time of appropriate dimension for the next row.
	 */
	protected abstract long getUpdatedUnits(long units);
	
	/**
	 * Get color for indicator on given position.
	 * 
	 * @param position indicator position.
	 * @return char which represents color.
	 */
	protected abstract char getColor(int position);
	
	private void checkLength(int length) {
		if(length <= 0) {
			throw new IllegalArgumentException(
					"Length must be positive integer");
		}
	}
	
	private void checkUnits(long units) {
		if(units < 0) {
			throw new IllegalArgumentException(
					"Units must be positive long or zero");
		}
	}
}
