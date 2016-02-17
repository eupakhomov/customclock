package com.ubs.opsit.interviews;

import static org.junit.Assert.*;

import org.junit.Test;

public class BlinkingIndicatorRowTest {
	
	private BlinkingIndicatorRow row = new BlinkingIndicatorRow(5, 'C');

	@Test
	public void testGetActiveCountOn() {
		assertEquals(1, row.getActiveCount(5));
	}
	
	@Test
	public void testGetActiveCountOff() {
		assertEquals(0, row.getActiveCount(6));
	}
	
	@Test
	public void testGetUpdatedUnits() {
		assertEquals(1l, row.getUpdatedUnits(1l)); 
	}

	@Test
	public void testGetColor() {
		assertEquals('C', row.getColor(1));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testBlinkingIndicatorRowWongPeriod() {
		new BlinkingIndicatorRow(-1, 'C');
	}

	@Test
	public void testSetup() {
		long units = row.setup(5l);
		assertEquals(5l, units);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetupWrongUnits() {
		row.setup(-1l);
	}

	@Test
	public void testReset() {
		row.setup(5l);
		assertEquals("C", row.getOutput());
		row.reset();
		assertEquals("O", row.getOutput());
	}

	@Test
	public void testGetOutput() {
		row.setup(5l);
		assertEquals("C", row.getOutput());
		row.reset();
		row.setup(4l);
		assertEquals("O", row.getOutput());
	}

}
