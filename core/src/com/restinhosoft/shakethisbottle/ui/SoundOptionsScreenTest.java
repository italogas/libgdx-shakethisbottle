package com.restinhosoft.shakethisbottle.ui;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Array;

public class SoundOptionsScreenTest {

	private SoundOptionsScreen screen;

	@Before
	public void setUp() throws Exception {
		screen = new SoundOptionsScreen();
		screen.show();
	}

	 @Test
	public void creationTest() {
		assertNotNull(screen.game);
		assertNotNull(screen.background);
		assertNotNull(screen.atlas);
		assertNotNull(screen.bitmapFont);
		assertNotNull(screen.skin);
		assertNotNull(screen.fitViewport);
		assertNotNull(screen.stage);
		assertNotNull(screen.table);
		assertNotNull(screen.gameMusicLabel);
		assertNotNull(screen.enableSoundLabel);
		assertNotNull(screen.generalVolumeLabel);
		assertNotNull(screen.soundEffectsLabel);
		assertNotNull(screen.backButton);
		assertNotNull(screen.checkBox0);
		assertNotNull(screen.checkBox1);
		assertNotNull(screen.checkBox2);
		assertNotNull(screen.progressBar);
	}
	 
	 
	@Test
	public void assetsLocationTest() throws Exception {
		assertTrue(Gdx.files.internal("menu.png").exists());
		assertTrue(Gdx.files.internal("uiskin.atlas").exists());
		assertTrue(Gdx.files.internal("default.fnt").exists());
		
	}
	
	
	@Test
	public void abelStateTest() throws Exception {
		assertTrue(screen.enableSoundLabel.isVisible());
		assertTrue(screen.gameMusicLabel.isVisible());
		assertTrue(screen.generalVolumeLabel.isVisible());
		assertTrue(screen.soundEffectsLabel.isVisible());
		assertEquals("Enable Sound: ", screen.enableSoundLabel.getText());
		assertEquals("Game Music: ", screen.gameMusicLabel.getText());
		assertEquals("General Volume: ", screen.generalVolumeLabel.getText());
		assertEquals("Sound Effects: ", screen.soundEffectsLabel.getText());
		
	}
	
	
	@Test
	public void selectLabelLayoutTest() throws Exception {
		assertEquals(screen.bitmapFont, screen.enableSoundLabel.getStyle().font);
		assertEquals(screen.bitmapFont, screen.gameMusicLabel.getStyle().font);
		assertEquals(screen.bitmapFont, screen.generalVolumeLabel.getStyle().font);
		assertEquals(screen.bitmapFont, screen.soundEffectsLabel.getStyle().font);
		
	}
	
	
	@Test
	public void checkBoxStateTest()  throws Exception{
		assertTrue(screen.checkBox0.isVisible());
		if(screen.pref.getSoundEnable()){
			assertTrue(screen.checkBox0.isChecked());
		}
		assertFalse(screen.checkBox0.isChecked());
		
		assertTrue(screen.checkBox1.isVisible());
		if(screen.pref.getMusicEnable()){
			assertTrue(screen.checkBox1.isChecked());
		}
		assertFalse(screen.checkBox1.isChecked());
		
		assertTrue(screen.checkBox2.isVisible());
		if(screen.pref.getBGMEnable()){
			assertTrue(screen.checkBox2.isChecked());
		}
		assertFalse(screen.checkBox2.isChecked());
		
	}
	
	@Test
	public void checkBoxLayoutTest()  throws Exception {
		assertEquals(screen.bitmapFont, screen.checkBox0.getStyle().font);
		assertEquals(screen.bitmapFont, screen.checkBox1.getStyle().font);
		assertEquals(screen.bitmapFont, screen.checkBox2.getStyle().font);
		assertEquals(1, (int) screen.checkBox0.getStyle().pressedOffsetX);
		assertEquals(-1, (int) screen.checkBox0.getStyle().pressedOffsetY);
		assertEquals(1, (int) screen.checkBox1.getStyle().pressedOffsetX);
		assertEquals(-1, (int) screen.checkBox1.getStyle().pressedOffsetY);
		assertEquals(1, (int) screen.checkBox2.getStyle().pressedOffsetX);
		assertEquals(-1, (int) screen.checkBox2.getStyle().pressedOffsetY);
		assertEquals(screen.skin.getDrawable("check-off"), screen.checkBox0.getStyle().checkboxOff);
		assertEquals(screen.skin.getDrawable("check-on"), screen.checkBox0.getStyle().checkboxOn);
		assertEquals(screen.skin.getDrawable("check-off"), screen.checkBox1.getStyle().checkboxOff);
		assertEquals(screen.skin.getDrawable("check-on"), screen.checkBox1.getStyle().checkboxOn);
		assertEquals(screen.skin.getDrawable("check-off"), screen.checkBox2.getStyle().checkboxOff);
		assertEquals(screen.skin.getDrawable("check-on"), screen.checkBox2.getStyle().checkboxOn);
		
	}
	
	
	@Test
	public void checkBoxBehaviorTest() throws Exception {
		assertTrue(screen.checkBox0.getClickListener().handle(new ChangeEvent()));
		assertTrue(screen.checkBox1.getClickListener().handle(new ChangeEvent()));
		assertTrue(screen.checkBox2.getClickListener().handle(new ChangeEvent()));
		
	}
	
	@Test
	public void progressBarStateTest() throws Exception {
		assertTrue(screen.progressBar.isVisible());
		
	}
	
	@Test
	public void progressBarLayoutTest() throws Exception {
		assertEquals(screen.skin.getDrawable("default-slider"), screen.progressBar.getStyle().background);
	}
	
	@Test
	public void progressBarBehaviorTest() throws Exception {
		assertTrue(screen.progressBar.getListeners().get(0).handle(new ChangeEvent()));
		// TODO:add other tests here to check if progress bar value is changing
	}
	
	@Test
	public void backButtonStateTest()  throws Exception {
		assertTrue(screen.backButton.isVisible());
		assertFalse(screen.backButton.isPressed());
		assertEquals("Back",  screen.backButton.getText());
		
	}
	
	@Test
	public void backButtonLayoutTest() throws Exception {
		assertEquals(screen.bitmapFont, screen.backButton.getStyle().font);
		assertEquals(1, (int) screen.backButton.getStyle().pressedOffsetX);
		assertEquals(-1, (int) screen.backButton.getStyle().pressedOffsetY);
		assertEquals(screen.skin.getDrawable("default-rect"), screen.backButton.getStyle().up);
		assertEquals(screen.skin.getDrawable("default-rect-down"), screen.backButton.getStyle().down);
		
	}

	@Test
	public void backButtonBehaviorTest() throws Exception {
		assertTrue(screen.backButton.getListeners().get(0).handle(new ChangeEvent()));
		
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void tableStateTest()  throws Exception {
		assertTrue(screen.table.isVisible());
		
		assertEquals(Gdx.graphics.getHeight(), (int) screen.table.getHeight());
		assertEquals(Gdx.graphics.getWidth(), (int) screen.table.getWidth());
		assertEquals(-Gdx.graphics.getHeight()/2, (int) screen.table.getX());
		assertEquals(-Gdx.graphics.getWidth()/2, (int) screen.table.getY());
		assertEquals(2, screen.table.getColumns());
		assertEquals(5, screen.table.getRows());
		
		Array<Cell> cells = screen.table.getCells();
		assertEquals(9,  cells.size);
		assertEquals(screen.enableSoundLabel, cells.get(0));
		assertEquals(screen.gameMusicLabel, cells.get(1));
		assertEquals(screen.enableSoundLabel, cells.get(2));
		assertEquals(screen.generalVolumeLabel, cells.get(3));
		assertEquals(screen.checkBox0, cells.get(4));
		assertEquals(screen.checkBox1, cells.get(5));
		assertEquals(screen.checkBox2, cells.get(6));
		assertEquals(screen.backButton, cells.get(7));
		
		for(Cell c : cells){
			assertTrue(c.getActor().isVisible());
		}
		
	} 

}