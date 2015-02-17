package com.restinhosoft.playertests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Json;
import com.restinhosoft.player.PlayerPreferencesGdx;
import com.restinhosoft.player.PlayerPreferencesIOBuffer;
import com.restinhosoft.player.PlayerPreferencesJson;

/**
 * @author Mailson
 *
 */
public class PlayerPreferencesTest {
	private PlayerPreferencesIOBuffer pref;// = new PlayerPreferences();	
	private final int volumeDef = 25;
	private final String lanDef = "engl";
	
	@Test
	public void defaultPreferences() {
		pref = new PlayerPreferencesIOBuffer();

		pref.defaultPreferences();
		
		assertTrue(pref.getSoundEnable());
		assertTrue(pref.getMusicEnable());
		assertTrue(pref.getBGMEnable());
		assertEquals(volumeDef,pref.getVolume());
		assertEquals(lanDef, pref.getLanguage());
	}
	
	@Test
	public void settingPreferences() {
		pref = new PlayerPreferencesIOBuffer();

		pref.setMusicEnable(false);
		pref.setBGMEnable(false);
		pref.setSoundEnable(false);
		
		assertFalse(pref.getSoundEnable());
		assertFalse(pref.getMusicEnable());
		assertFalse(pref.getBGMEnable());
		
		pref.setLanguage("english");
		pref.setVolume(500);
		
		assertEquals(volumeDef,pref.getVolume());
		assertEquals(lanDef, pref.getLanguage());
		
		pref.setLanguage("ptbr");
		assertEquals("ptbr", pref.getLanguage());
		
		pref.setVolume(50);
		assertEquals(50, pref.getVolume());
	}
	
	
	
}
