package com.restinhosoft.playertests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.prefs.Preferences;

import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Json;
import com.restinhosoft.player.PlayerPreferencesApplicationListener;
import com.restinhosoft.player.PlayerPreferencesGdx;
import com.restinhosoft.player.PlayerPreferencesIOBuffer;
import com.restinhosoft.player.PlayerPreferencesJson;
import com.restinhosoft.player.PreferencesTent;

/**
 * @author Mailson
 *
 */
public class PlayerPreferencesJsonTest {
	private PlayerPreferencesJson pref;// = new PlayerPreferences();	
	private final int volumeDef = 25;
	private final String lanDef = "engl";
	
	
	private PlayerPreferencesApplicationListener prefApp;
	private PreferencesTent preftent;
	
	@Test
	public void defaultPreferences() {
		 //prefApp = new PlayerPreferencesApplicationListener();
		
		//pref = new PlayerPreferencesJson();

		//pref.setSoundEnable(false);
		 //prefApp.setSoundEnable(false);
		//preftent = new PreferencesTent();
		//preftent.save();
		
		
	}
	
	@Test
	public void settingPreferences() {
		
	}
	
	
	
}
