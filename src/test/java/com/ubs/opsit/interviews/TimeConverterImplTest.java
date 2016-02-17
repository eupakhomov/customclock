package com.ubs.opsit.interviews;

import static org.junit.Assert.*;

import org.junit.Test;

public class TimeConverterImplTest implements TimeUnitsSpec {
	
	IndicatorsRow[] rowsOne = new IndicatorsRow[4];
	
	{
		rowsOne[0] = new BlinkingIndicatorRow(5, 'G');
		rowsOne[1] = new ChainedIndicatorsRow(HOURS_DIVISOR * 12, 1, 'R');	
		rowsOne[2] = new ChainedIndicatorsRow(HOURS_DIVISOR, 11, 'R');
		rowsOne[3] = new ChainedIndicatorsRow(MINS_DIVISOR, 59, 'Y', 'R', 5);
	}	
	
	IndicatorsRow[] rowsTwo = new IndicatorsRow[5];
	
	{
		rowsTwo[0] = new BlinkingIndicatorRow(7, 'B');
		rowsTwo[1] = new ChainedIndicatorsRow(HOURS_DIVISOR * 8, 2, 'G');	
		rowsTwo[2] = new ChainedIndicatorsRow(HOURS_DIVISOR, 7, 'G');
		rowsTwo[3] = new ChainedIndicatorsRow(MINS_DIVISOR * 10, 5, 'R');
		rowsTwo[4] = new ChainedIndicatorsRow(MINS_DIVISOR, 9, 'R', 'G', 5);
	}	
	
	TimeConverterImpl testClockOne = new TimeConverterImpl(rowsOne, "");
	TimeConverterImpl testClockTwo = new TimeConverterImpl(rowsTwo, "");

	@Test
	public void testConvertTime() {
		assertEquals("GOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO", 
				testClockOne.convertTime("00:00:00"));
		assertEquals("BOOOOOOOOOOOOOOOOOOOOOOO", 
				testClockTwo.convertTime("00:00:00"));
		assertEquals("OORRRRRRRRRRRYYYYRYYYYRYYYYRYYYYRYYYYRYYYYRYYYYRYYYYRYYYYRYYYYRYYYYRYYYY", 
				testClockOne.convertTime("11:59:01"));
		assertEquals("BGOGGGOOOORRRRRRRRRGRRRR", 
				testClockTwo.convertTime("11:59:01"));
		assertEquals("GROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO", 
				testClockOne.convertTime("12:00:05"));
		assertEquals("OGOGGGGOOOOOOOOOOOOOOOOO", 
				testClockTwo.convertTime("12:00:05"));
		assertEquals("ORROOOOOOOOOOYYYYRYYYYRYYYYROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO", 
				testClockOne.convertTime("13:15:17"));
		assertEquals("OGOGGGGGOOROOOORRRRGOOOO", 
				testClockTwo.convertTime("13:15:17"));
		assertEquals("GRRRRRRRRRRRRYYYYRYYYYRYYYYRYYYYRYYYYRYYYYRYYYYRYYYYRYYYYRYYYYRYYYYRYYYY", 
				testClockOne.convertTime("23:59:00"));
		assertEquals("OGGGGGGGGGRRRRRRRRRGRRRR", 
				testClockTwo.convertTime("23:59:00"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConvertTimeErrorWrongTime() {
		testClockOne.convertTime("24:00:00");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConvertTimeErrorWrongFormat() {
		testClockTwo.convertTime("13-12-11");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTimeConverterImplNullRows() {
		new TimeConverterImpl(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTimeConverterImplEmptyRows() {
		new TimeConverterImpl(new IndicatorsRow[0]);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTimeConverterImplNullRow() {
		new TimeConverterImpl(new IndicatorsRow[]{null});
	}
}
