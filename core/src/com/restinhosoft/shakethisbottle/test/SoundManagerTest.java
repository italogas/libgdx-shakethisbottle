/**
 * 
 */
package com.restinhosoft.shakethisbottle.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.security.InvalidParameterException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.restinhosoft.shakethisbottle.exception.SoundManagerNotStartedException;
import com.restinhosoft.shakethisbottle.exception.SoundNotEnabledException;
import com.restinhosoft.shakethisbottle.impl.SoundManager;

/**
 * @author �talo
 *
 */
public class SoundManagerTest {
	
	SoundManager manager;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none(); 

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		manager = SoundManager.getInstance();
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
	public void testCreation3() {
		manager.load();
		manager.turnOn();
		assertTrue(manager.started);
		assertTrue(manager.sound_enabled);
	}
	
	@Test 
	public void testCreation4() throws SoundManagerNotStartedException{
		thrown.expect(SoundManagerNotStartedException.class);
		thrown.expectMessage("SoundManager was not started properly. " +
					"Call load() method to enable SoundManager to load the Player sound preferences. ");
		
		manager.turnOn(); // it should not pass here
	}
	
	@Test
	public void testDisableSound1() {
		manager.load();
		manager.turnOn();
		manager.turnOff();
		assertFalse(manager.sound_enabled);
	}
	
	@Test
	public void testDisableSound2() {
		thrown.expect(SoundNotEnabledException.class);
		thrown.expectMessage("There is no sound playing right now. ");
		
		manager.load();
		manager.turnOff();// it should not pass here
		
//		try {
//			manager.load();
//			manager.turnOff();
//			fail("Expected an SoundNotEnabledException to be trown");
//		} catch (SoundNotEnabledException exc) {
//			assertThat(exc.getMessage(), is("There is no sound playing right now. "));
//		}
	}
	
	@Test
	public void testDisableSoundEffects() {
		manager.load();
		manager.turnOn();
		manager.disableSoundEffects();
		assertFalse(manager.sound_effects_enabled);
	}
	
	@Test
	public void testDisableMusic() {
		manager.load();
		manager.turnOn();
		manager.disableMusic();
		assertFalse(manager.music_enabled);
	}
	
	@Test 
	public void testSetVolume1() {
		thrown.expect(InvalidParameterException.class);
		thrown.expectMessage("Invalid volume value. Set value to 0 - 100. ");
		
		manager.load();
		manager.turnOn();
		manager.setVolume(-12); // it should not pass here
		
//		try {
//			manager.setVolume(-12);
//			fail("Expected an InvalidParameterException to be trown");
//		} catch (InvalidParameterException e) {
//			assertThat(e.getMessage(), is("Invalid volume value. Set value to 0 - 100. "));
//		}
	}
	
	@Test 
	public void testSetVolume2() {
		thrown.expect(InvalidParameterException.class);
		thrown.expectMessage("Invalid volume value. Set value to 0 - 100. ");
		
		manager.load();
		manager.turnOn();
		manager.setVolume(125); // it should not pass here
		
//		try {
//			manager.setVolume(102);
//			fail("Expected an InvalidParameterException to be trown");
//		} catch (InvalidParameterException e) {
//			assertThat(e.getMessage(), is("Invalid volume value. Set value to 0 - 100. "));
//		}
	}
	
}
