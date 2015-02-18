package com.restinhosoft.shakethisbottle.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Array;

public class LanguageOptionsScreenTest {

	LanguageOptionsScreen screen;

	@Before
	public void setUp() throws Exception {
		screen = new LanguageOptionsScreen();
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
		assertNotNull(screen.selectLabel);
		assertNotNull(screen.backButton);
		assertNotNull(screen.checkBox0);
		assertNotNull(screen.checkBox1);
	}
	
	@Test
	public void assetsLocationTest() throws Exception {
		assertTrue(Gdx.files.internal("menu.png").exists());
		assertTrue(Gdx.files.internal("uiskin.atlas").exists());
		assertTrue(Gdx.files.internal("default.fnt").exists());
		
	}
	
	@Test
	public void selectLabelStateTest() throws Exception {
		assertTrue(screen.selectLabel.isVisible());
		assertEquals("Select Language: ", screen.selectLabel.getText());
		
	}
	
	@Test
	public void selectLabelLayoutTest() throws Exception {
		assertEquals(screen.bitmapFont, screen.selectLabel.getStyle().font);
		
	}
	
	@Test
	public void checkBoxStateTest()  throws Exception{
		assertTrue(screen.checkBox0.isVisible());
		if(screen.pref.getLanguage().equalsIgnoreCase("ptbr")){
			assertTrue(screen.checkBox0.isChecked());
		}
		assertFalse(screen.checkBox0.isChecked());
		
		assertTrue(screen.checkBox1.isVisible());
		if(screen.pref.getLanguage().equalsIgnoreCase("engl")){
			assertTrue(screen.checkBox1.isChecked());
		}
		assertFalse(screen.checkBox1.isChecked());
		
	}
	
	@Test
	public void checkBoxLayoutTest()  throws Exception {
		assertEquals(screen.bitmapFont, screen.checkBox0.getStyle().font);
		assertEquals(screen.bitmapFont, screen.checkBox1.getStyle().font);
		assertEquals(1, (int) screen.checkBox0.getStyle().pressedOffsetX);
		assertEquals(-1, (int) screen.checkBox0.getStyle().pressedOffsetY);
		assertEquals(1, (int) screen.checkBox1.getStyle().pressedOffsetX);
		assertEquals(-1, (int) screen.checkBox1.getStyle().pressedOffsetY);
		assertEquals(screen.skin.getDrawable("check-off"), screen.checkBox0.getStyle().checkboxOff);
		assertEquals(screen.skin.getDrawable("check-on"), screen.checkBox0.getStyle().checkboxOn);
		assertEquals(screen.skin.getDrawable("check-off"), screen.checkBox1.getStyle().checkboxOff);
		assertEquals(screen.skin.getDrawable("check-on"), screen.checkBox1.getStyle().checkboxOn);
		
	}
	
	@Test
	public void checkBoxBehaviorTest() throws Exception {
		assertTrue(screen.checkBox0.getClickListener().handle(new ChangeEvent()));
		assertTrue(screen.checkBox1.getClickListener().handle(new ChangeEvent()));
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
		assertEquals(1, screen.table.getColumns());
		assertEquals(4, screen.table.getRows());
		
		Array<Cell> cells = screen.table.getCells();
		assertEquals(4,  cells.size);
		assertEquals(screen.selectLabel, cells.get(0));
		assertEquals(screen.checkBox0, cells.get(1));
		assertEquals(screen.checkBox1, cells.get(2));
		assertEquals(screen.backButton, cells.get(3));
		
		for(Cell c : cells){
			assertTrue(c.getActor().isVisible());
		}
		
	}

}
