package com.ubs.opsit.interviews;

import static org.junit.Assert.*;

import org.junit.Test;

public class ChainedIndicatorsRowTest implements TimeUnitsSpec {

	private ChainedIndicatorsRow row = new ChainedIndicatorsRow(MINS_DIVISOR * 5, 11, 
			'C');
	
	@Test
	public void testGetActiveCount() {
		assertEquals(11, row.getActiveCount(MINS_DIVISOR * 59));
		assertEquals(5, row.getActiveCount(MINS_DIVISOR * 25));
		assertEquals(0, row.getActiveCount(0));
	}

	@Test
	public void testGetUpdatedUnits() {
		assertEquals(MINS_DIVISOR, row.getUpdatedUnits(HOURS_DIVISOR + MINS_DIVISOR));
	}

	@Test
	public void testGetColor() {
		assertEquals('C', row.getColor(1));
		
		ChainedIndicatorsRow rowWithTwoColours = 
				new ChainedIndicatorsRow(MINS_DIVISOR * 5, 11, 'F', 'S', 4);
		
		assertEquals('F', rowWithTwoColours.getColor(1));
		assertEquals('S', rowWithTwoColours.getColor(8));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testChainedIndicatorsRowLongIntCharWrongDivisor() {
		new ChainedIndicatorsRow(0, 11, 'C');
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testChainedIndicatorsRowLongIntCharWrongLength() {
		new ChainedIndicatorsRow(MINS_DIVISOR * 5, 0, 'C');
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testChainedIndicatorsRowLongIntCharCharIntWrongSecondColorPeriod() {
		new ChainedIndicatorsRow(MINS_DIVISOR * 5, 11, 'F', 'S', -1);
	}

	@Test
	public void testSetup() {
		long units = row.setup(MINS_DIVISOR * 17);
		assertEquals(MINS_DIVISOR * 2, units);
		units = row.setup(MINS_DIVISOR * 25);
		assertEquals(0, units);
	}
	
	@Test(expected = IllegalStateException.class)
	public void testSetupWrongConfiguration() {
		row.setup(Integer.MAX_VALUE);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetupWrongUnits() {
		row.setup(-1l);
	}

	@Test
	public void testReset() {
		row.setup(MINS_DIVISOR * 17);
		assertEquals("CCCOOOOOOOO", row.getOutput());
		row.reset();
		assertEquals("OOOOOOOOOOO", row.getOutput());
	}

	@Test
	public void testGetOutput() {
		ChainedIndicatorsRow rowWithTwoColours = 
				new ChainedIndicatorsRow(MINS_DIVISOR * 5, 11, 'F', 'S', 4);
		rowWithTwoColours.setup(0l);
		assertEquals("OOOOOOOOOOO", rowWithTwoColours.getOutput());
		rowWithTwoColours.reset();
		rowWithTwoColours.setup(MINS_DIVISOR * 15);
		assertEquals("FFFOOOOOOOO", rowWithTwoColours.getOutput());
		rowWithTwoColours.reset();
		rowWithTwoColours.setup(MINS_DIVISOR * 59);
		assertEquals("FFFSFFFSFFF", rowWithTwoColours.getOutput());
	}

}
