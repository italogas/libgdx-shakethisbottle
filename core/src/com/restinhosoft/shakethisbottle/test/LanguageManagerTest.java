package com.restinhosoft.shakethisbottle.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.restinhosoft.shakethisbottle.exception.SoundManagerNotStartedException;
import com.restinhosoft.shakethisbottle.impl.LanguageManager;

public class LanguageManagerTest {
	
	LanguageManager manager;
	
	@Rule
	public ExpectedException thrown  = ExpectedException.none();

	@Before
	public void setUp() throws Exception {
		manager = LanguageManager.getInstance();
	}

	@Test
	public void testCreation1() {
		assertFalse(manager.started);
	}
	
	@Test
	public void testCreation2() {
		manager.load();
		assertTrue(manager.started);
	}
	
	@Test 
	public void testCreation3() throws SoundManagerNotStartedException{
		thrown.expect(SoundManagerNotStartedException.class);
		thrown.expectMessage("LanguageManager was not started properly. " +
					"Call load() method to enable LanguageManager to load the Player language preferences. ");
		
		//manager.turnOn(); // it should not pass here
	}

}
