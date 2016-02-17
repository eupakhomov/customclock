package com.ubs.opsit.interviews;

/**
 * Represents one row of clock in row chain where every row responsible for 
 * its own time units dimension.
 * 
 * @author Eugene
 *
 */
public class ChainedIndicatorsRow extends IndicatorsRow {
	
	private final long divisor;
	private final char color;
		
	public ChainedIndicatorsRow(long divisor, int length, 
			char color) {
		super(length);
		checkDivisor(divisor);
		this.divisor = divisor;
		this.color = color;
	}

	/**
	 * Get active indicators number in the row.
	 * 
	 * @param units of time of appropriate dimension for the row. 
	 * @return number of active indicators in the row which is units / divisor.
	 */
	@Override
	protected int getActiveCount(long units) {
		return (int) (units / divisor);
	}

	/**
	 * Get updated units value of appropriate dimension for the next row.
	 * 
	 * @param units of time of appropriate dimension for the row.
	 * @return units of time of appropriate dimension for the next row which is units % divisor.
	 */
	@Override
	protected long getUpdatedUnits(long units) {
		return units % divisor;
	}

	/**
	 * Get color for indicator on given position.
	 * 
	 * @param position indicator position.
	 * @return char which represents color.
	 */
	@Override
	protected char getColor(int position) {
		return color;
	}
	
	private void checkDivisor(long divisor) {
		if(divisor <= 0) {
			throw new IllegalArgumentException(
					"Divisor must be positive integer");
		}
	}

}
