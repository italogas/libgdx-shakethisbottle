package com.restinhosoft.test;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.restinhosoft.main.AudioManager;

@RunWith (GdxTestRunner.class)
@FixMethodOrder (MethodSorters.NAME_ASCENDING)
public class AudioManagerTest {

	String fileName;
	AudioManager audioManager;
	FileHandle fileHandle;

	@Before
	public void setUp() throws Exception {
		
		assertTrue(Gdx.files.internal("..\\android\\assets\\audio\\mainmenu\\7inn.ogg").exists());
		
		fileHandle = Gdx.files.internal("..\\android\\assets\\audio\\mainmenu\\7inn.ogg");
		
		fileName = "7inn.ogg";
		audioManager = new AudioManager(fileName);
	}

	@Test (expected = InvalidParameterException.class)
	public void creationTest0() {
		new AudioManager(null);
	}
	
	@Test (expected = InvalidParameterException.class)
	public void creationTest1() {
		new AudioManager("");
	}
	
	@Test (expected = InvalidParameterException.class)
	public void creationTest2() {
		new AudioManager("garrafa.png");
	}
	
	@Test 
	public void creationTest3() {
		try {
			new AudioManager("garrafa.png");
		} catch (InvalidParameterException ie) {
			assertEquals("Invalid audio file format. ", ie.getMessage());
		}
		
	}
	
	@Test 
	public void crationTest4() {
		assertEquals(fileName, audioManager.getMusicFileName());
	}
	
	@Test 
	public void playMusicTest() {
		audioManager.playMusic();
		assertTrue(audioManager.getMusic().isPlaying());
		
		audioManager.stopMusic();
		assertFalse(audioManager.getMusic().isPlaying());
		
	}
	
	@Test
	public void volmeTest0() throws Exception {
		assertTrue(Gdx.files.local("volume.txt").exists());
	}
	
//	@Test (expected = InvalidParameterException.class)
//	public void volumeTest1() {
//		audioManager.setVolume(-1);
//	}
//	
//	@Test (expected = InvalidParameterException.class)
//	public void volumeTest2() {
//		audioManager.setVolume(2);
//	}
//	
//	@Test 
//	public void volumeTest3() {
//		audioManager.setVolume(0.5f);
//		assertEquals(0.5f, audioManager.getVolume(), 0);
//	}

}

