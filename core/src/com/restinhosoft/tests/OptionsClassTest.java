package com.restinhosoft.tests;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import com.restinhosoft.shakethisbottle.Options;
import com.restinhosoft.shakethisbottle.Player;

/**
 * @author Mailson
 *
 */
public class OptionsClassTest {

	private final int  defaultVol = 50;
	private Options option = new Options(true, true, true, defaultVol);
	
	@Test
	public void settingOptonsTest() {
		assertTrue(option.getEnableSound());
		assertTrue(option.getEnableSoundTrack());
		assertTrue(option.getEnableMusic());
		assertEquals(option.getVolume(),50);
	}
	
		
	@Test
	public void enableSoundTest(){
		assertTrue(option.getEnableSound());
		assertTrue(option.getEnableSoundTrack());
		assertTrue(option.getEnableMusic());
		
		option.setEnableSound(false);
		
		assertFalse(option.getEnableSound());
		assertFalse(option.getEnableSoundTrack());
		assertFalse(option.getEnableMusic());
		
		option.setEnableSound(true);
		
		assertTrue(option.getEnableSound());
		assertFalse(option.getEnableSoundTrack());
		assertFalse(option.getEnableMusic());
		
	}
	
	@Test
	public void setEnableSoundTrackTest(){
		assertTrue(option.getEnableSoundTrack());
			
		option.setEnableSoundTrack(false);
		assertFalse(option.getEnableSoundTrack());
		
		option.setEnableSoundTrack(false);
		option.setEnableSound(false);
		assertFalse(option.getEnableSoundTrack());
		
		option.setEnableSound(true);
		assertFalse(option.getEnableSoundTrack());
		
		option.setEnableSoundTrack(true);
		System.out.println(option.getEnableSound());
		assertTrue(option.getEnableSoundTrack());
	}
	
	@Test
	public void setEnableMusicTest(){
		assertTrue(option.getEnableMusic());
		
		option.setEnableMusic(false);
		assertFalse(option.getEnableMusic());
		
		option.setEnableMusic(true);
		option.setEnableSound(false);
		assertFalse(option.getEnableMusic());
		
		option.setEnableSound(true);
		assertFalse(option.getEnableMusic());
		
		option.setEnableMusic(true);
		assertTrue(option.getEnableMusic());
	}
	
	@Test
	public void setVolumeTest(){
		assertEquals(option.getVolume(),50);
		
		option.setVolume(0);
		assertEquals(option.getVolume(),0);
		
		option.setVolume(-1);
		assertEquals(option.getVolume(),0);
		
		option.setVolume(101);
		assertEquals(option.getVolume(),0);
		
		Options option2 = new Options(true,true,true,120);
		assertEquals(option2.getVolume(),50);
		
		Options option3 = new Options(true,true,true,-120);
		assertEquals(option3.getVolume(),50);
		
	}
	

}
