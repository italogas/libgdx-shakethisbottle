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
import com.restinhosoft.player.PlayerScoresIOBuffer;

/**
 * @author Mailson
 *
 */
public class PlayerScoresTest {
	private PlayerScoresIOBuffer scores;	
		
	@Test
	public void initialScores() {
		scores = new PlayerScoresIOBuffer();
		
		System.out.println(scores.getScores());
	}
	
	@Test
	public void settingScores() {
	}
	
	
	
}
