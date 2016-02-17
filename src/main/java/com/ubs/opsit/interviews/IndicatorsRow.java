package com.ubs.opsit.interviews;

/**
 * Represents the row of indicators in Berlin clock 
 * (or in other clock based on the same principles) 
 * 
 * @author Eugene
 *
 */
public abstract class IndicatorsRow {
	
	public static final char NO_COLOR = 'O';
	

	/**
	 * Setup with given time.
	 * 
	 * @param units of time of appropriate dimension. 
	 * @return units of time of appropriate dimension for next row.
	 */
	public long setup(long units) {
		return 0;
	}
	
	/**
	 * Reset row output.
	 */
	public void reset() {		
	}
	
	/**
	 * Get String with rendered row output.
	 * 
	 * @return String with rendered row output.
	 */
	public String getOutput() {
		return null;
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
}
