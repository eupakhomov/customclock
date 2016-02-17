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
	 * Start counting period from zero.
	 */
	@Override
	protected int getActiveCount(long units) {
		return units % period == 0 ? 1 : 0;
	}

	@Override
	protected long getUpdatedUnits(long units) {
		return units;
	}

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
