package com.ubs.opsit.interviews;

/**
 * Represents one row of clock with one blinking indicator
 * which blinks every period time starting from zero.
 * 
 * @author Eugene
 *
 */
public class BlinkingIndicatorRow extends IndicatorsRow {

	private final int period;
	private final char color;
	
	public BlinkingIndicatorRow(int period, char color) {
		super(1);
		checkPeriod(period);
		this.period = period;
		this.color = color;
	}

	/**
	 * Get active indicators number in the row.
	 * As that is row with one blinking indicator
	 * the result will be 1 if indicator is active or 0 if not.
	 * 
	 * @param units of time of appropriate dimension for the row.  
	 * @return 1 if indicator is active or 0 if not.
	 */
	@Override
	protected int getActiveCount(long units) {
		return units % period == 0 ? 1 : 0;
	}

	/**
	 * Get updated units value of appropriate dimension for the next row.
	 * 
	 * @param units of time of appropriate dimension for the row.  
	 * @return same units value as dimension is not changed by parent row. 
	 */
	@Override
	protected long getUpdatedUnits(long units) {
		return units;
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

	private void checkPeriod(int period) {
		if(period <= 0) {
			throw new IllegalArgumentException(
					"Period must be positive integer");
		}
	}
}
