package com.ubs.opsit.interviews;

/**
 * Represents one row of clock in row chain where every row responsible for 
 * its own time units dimension.
 * 
 * @author Eugene
 *
 */
public class ChainedIndicatorsRow extends IndicatorsRow {

	/**
	 * Get active indicators number in the row.
	 * 
	 * @param units of time of appropriate dimension for the row. 
	 * @return number of active indicators in the row which is units / divisor.
	 */
	@Override
	protected int getActiveCount(long units) {
		return 0;
	}

	/**
	 * Get updated units value of appropriate dimension for the next row.
	 * 
	 * @param units of time of appropriate dimension for the row.
	 * @return units of time of appropriate dimension for the next row which is units % divisor.
	 */
	@Override
	protected long getUpdatedUnits(long units) {
		return 0;
	}

	/**
	 * Get color for indicator on given position.
	 * 
	 * @param position indicator position.
	 * @return char which represents color.
	 */
	@Override
	protected char getColor(int position) {
		return ' ';
	}
}
