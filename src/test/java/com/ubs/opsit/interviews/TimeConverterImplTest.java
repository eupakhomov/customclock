package com.ubs.opsit.interviews;

import static org.junit.Assert.*;

import org.junit.Test;

public class TimeConverterImplTest {
	
	IndicatorsRow[] rows = new IndicatorsRow[4];
	
	{
		rows[0] = new BlinkingIndicatorRow(5, 'G');
		rows[1] = new ChainedIndicatorsRow(60 * 60 * 12, 1, 'R');	
		rows[2] = new ChainedIndicatorsRow(60 * 60, 11, 'R');
		rows[3] = new ChainedIndicatorsRow(60, 59, 'Y');
	}	
	
	TimeConverterImpl testClock = new TimeConverterImpl(rows, "");


	@Test
	public void testConvertTime() {
		assertEquals("GOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO", 
				testClock.convertTime("00:00:00"));
		assertEquals("OORRRRRRRRRRRYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY", 
				testClock.convertTime("11:59:01"));
		assertEquals("GROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO", 
				testClock.convertTime("12:00:05"));
		assertEquals("ORROOOOOOOOOOYYYYYYYYYYYYYYYOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO", 
				testClock.convertTime("13:15:17"));
		assertEquals("GRRRRRRRRRRRRYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY", 
				testClock.convertTime("23:59:00"));

	}

}
