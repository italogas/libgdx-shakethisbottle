package com.restinhosoft.shakethisbottle.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.restinhosoft.shakethisbottle.impl.Options;
import com.restinhosoft.shakethisbottle.impl.Player;
import com.restinhosoft.shakethisbottle.impl.gametest;

/**
 * @author Mailson
 *
 */
public class PlayerClassTest {

	private gametest gt= new gametest();
	private Player playerJames = new Player("james","r154asd");
	
	
	
	@Test
	public void testPlayerCreation() {
		//fail("Not yet implemented");
		
		assertEquals(playerJames.getName(),"james");
		assertEquals(playerJames.getID(),"r154asd");
		
		assertEquals("initial number of achievements is 0" , playerJames.getNumberOfAchievements(),0);
		
		assertEquals("achievements list is empty", playerJames.getAllAchievements().size(),0);
		assertEquals("scores list is empty", playerJames.getHighScores().size(),0);
	}
	
		
	@Test
	public void defaultPlayerOptionsTest(){
				
		assertTrue(playerJames.getPlayerOptions().getEnableMusic());
		assertTrue(playerJames.getPlayerOptions().getEnableSound());
		assertTrue(playerJames.getPlayerOptions().getEnableSoundTrack());
		
		assertEquals("default volume is 50",playerJames.getPlayerOptions().getVolume(), 50);
	}
	
	@Test
	public void changingNameAndIDTest(){
		Player local = new Player("local", "lol0121");
		gt.addPlayer(playerJames);
		gt.addPlayer(local);
		
		assertEquals(local.getName(), "local");
		assertEquals(local.getID(), "lol0121");
		
		local.setName("jo");
		
		assertEquals(local.getName(), "jo");
		assertEquals(local.getID(), "lol0121");
		
		local.setID("lol0120");
		
		assertEquals(local.getName(), "jo");
		assertEquals(local.getID(), "lol0120");
		
		local.setID("r154asd");
		assertEquals(local.getName(), "jo");
		assertEquals(local.getID(), "lol0120");

	}
	
	@Test
	public void settingOptionsTest(){
		assertTrue(playerJames.getPlayerOptions().getEnableMusic());
		assertTrue(playerJames.getPlayerOptions().getEnableSound());
		assertTrue(playerJames.getPlayerOptions().getEnableSoundTrack());
		
		assertEquals("default volume is 50",playerJames.getPlayerOptions().getVolume(), 50);
		
		playerJames.setPlayerOptions(new Options(false, true, false, 0));
		
		assertFalse(playerJames.getPlayerOptions().getEnableMusic());
		assertFalse(playerJames.getPlayerOptions().getEnableSound());
		assertFalse(playerJames.getPlayerOptions().getEnableSoundTrack());
		
		System.out.println(playerJames.getPlayerOptions().getVolume());
		
		assertEquals("default volume is 50",playerJames.getPlayerOptions().getVolume(), 0);
		
		playerJames.setPlayerOptions(new Options(false, true, false,150));
		
		assertFalse(playerJames.getPlayerOptions().getEnableMusic());
		assertFalse(playerJames.getPlayerOptions().getEnableSound());
		assertFalse(playerJames.getPlayerOptions().getEnableSoundTrack());
		
		System.out.println(playerJames.getPlayerOptions().getVolume());
		
		assertEquals(playerJames.getPlayerOptions().getVolume(), 50);
		
		playerJames.getPlayerOptions().setEnableSound(true);
		
		assertFalse(playerJames.getPlayerOptions().getEnableMusic());
		assertTrue(playerJames.getPlayerOptions().getEnableSound());
		assertFalse(playerJames.getPlayerOptions().getEnableSoundTrack());
		
		assertEquals("default volume is 50",playerJames.getPlayerOptions().getVolume(), 50);
		
		playerJames.getPlayerOptions().setEnableSound(false);
		playerJames.getPlayerOptions().setEnableSoundTrack(true);
		
		assertFalse(playerJames.getPlayerOptions().getEnableMusic());
		assertFalse(playerJames.getPlayerOptions().getEnableSound());
		assertFalse(playerJames.getPlayerOptions().getEnableSoundTrack());
		
		assertEquals("default volume is 50",playerJames.getPlayerOptions().getVolume(), 50);
	}
	
	

}