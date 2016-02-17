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
	private final char firstColor;
	private final char secondColor;
	private final int secondColorPeriod;	
	
	public ChainedIndicatorsRow(long divisor, int length, 
			char color) {
		this(divisor, length, color, color, 0);
	}	
	
	public ChainedIndicatorsRow(long divisor, int length, 
			char firstColor, char secondColor, int secondColorPeriod) {		
		super(length);
		checkDivisor(divisor);
		checkSecondColorPeriod(secondColorPeriod);
		this.divisor = divisor; 
		this.firstColor = firstColor;
		this.secondColor = secondColor;
		this.secondColorPeriod = secondColorPeriod;
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
		return secondColorPeriod == 0 
				? firstColor
				: position % secondColorPeriod != 0 ? firstColor : secondColor;
	}
	
	private void checkDivisor(long divisor) {
		if(divisor <= 0) {
			throw new IllegalArgumentException(
					"Divisor must be positive integer");
		}
	}
	
	private void checkSecondColorPeriod(int secondColorPeriod) {
		if(secondColorPeriod < 0) {
			throw new IllegalArgumentException(
					"Second color period must be positive integer or zero in case of one color");
		}
	}

}
