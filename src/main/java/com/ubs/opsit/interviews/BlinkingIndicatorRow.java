package com.ubs.opsit.interviews;

/**
 * Represents one row of clock with one blinking indicator
 * which blinks every period time starting from zero.
 * 
 * @author Eugene
 *
 */
public class BlinkingIndicatorRow extends IndicatorsRow {

	
	@Override
	protected int getActiveCount(long units) {
		return 0;
	}

	@Override
	protected long getUpdatedUnits(long units) {
		return 0;
	}

	@Override
	protected char getColor(int position) {
		return ' ';
	}
}
