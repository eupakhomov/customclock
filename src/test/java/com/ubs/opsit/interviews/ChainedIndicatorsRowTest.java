package com.ubs.opsit.interviews;

import static org.junit.Assert.*;

import org.junit.Test;

public class ChainedIndicatorsRowTest {
	
	private ChainedIndicatorsRow row = new ChainedIndicatorsRow(60 * 5, 11, 
			'C');

	@Test
	public void testGetActiveCount() {
		assertEquals(11, row.getActiveCount(60 * 59));
		assertEquals(5, row.getActiveCount(60 * 25));
		assertEquals(0, row.getActiveCount(0));
	}

	@Test
	public void testGetUpdatedUnits() {
		assertEquals(60, row.getUpdatedUnits(60 * 60 + 60));
	}

	@Test
	public void testGetColor() {
		assertEquals('C', row.getColor(1));
	}

	@Test
	public void testSetup() {
		long units = row.setup(60 * 17);
		assertEquals(60 * 2, units);
		units = row.setup(60 * 25);
		assertEquals(0, units);

	}

	@Test
	public void testReset() {
		row.setup(60 * 17);
		assertEquals("CCCOOOOOOOO", row.getOutput());
		row.reset();
		assertEquals("OOOOOOOOOOO", row.getOutput());

	}

	@Test
	public void testGetOutput() {
		row.reset();
		row.setup(0l);
		assertEquals("OOOOOOOOOOO", row.getOutput());
	}

}
