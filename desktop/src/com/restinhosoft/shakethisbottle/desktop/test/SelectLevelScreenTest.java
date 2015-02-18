package com.restinhosoft.shakethisbottle.desktop.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Array;
import com.restinhosoft.game.hittheballoon.SelectLevelScreen;
import com.restinhosoft.shakethisbottle.ui.TestGame;

/**
 * 
 * @author Italo
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SelectLevelScreenTest {

	private SelectLevelScreen screen;

	private TestGame testGame;

	@Before
	public void setUp() throws Exception {
		screen = new SelectLevelScreen();
		testGame = new TestGame(screen);
		
		new DesktopTestLauncher(testGame);
		
		screen.show();

	}

	@Test
	public void creationTest() {
		assertNotNull(screen.game);
		assertNotNull(screen.atlas);
		assertNotNull(screen.bitmapFont);
		assertNotNull(screen.skin);
		assertNotNull(screen.stage);
		assertNotNull(screen.table);
		assertNotNull(screen.easyButton);
		assertNotNull(screen.normalButton);
		assertNotNull(screen.hardButton);
		assertNotNull(screen.insaneButton);
	}
	
	@Test
	public void assetsLocationTest() throws Exception {
		assertTrue(Gdx.files.internal("button.atlas").exists());
		assertTrue(Gdx.files.internal("default.fnt").exists());
		
	}
	
	@Test
	public void buttonStateTest()  throws Exception {
		assertTrue(screen.easyButton.isVisible());
		assertFalse(screen.easyButton.isPressed());
		assertEquals("Easy",  screen.easyButton.getText());
		
		assertTrue(screen.normalButton.isVisible());
		assertFalse(screen.normalButton.isPressed());
		assertEquals("Normal",  screen.normalButton.getText());
		
		assertTrue(screen.hardButton.isVisible());
		assertFalse(screen.hardButton.isPressed());
		assertEquals("Hard",  screen.hardButton.getText());
		
		assertTrue(screen.insaneButton.isVisible());
		assertFalse(screen.insaneButton.isPressed());
		assertEquals("Insane",  screen.insaneButton.getText());
		
	}
	
	@Test
	public void backButtonLayoutTest() throws Exception {
		assertEquals(screen.bitmapFont, screen.easyButton.getStyle().font);
		assertEquals(1, (int) screen.easyButton.getStyle().pressedOffsetX);
		assertEquals(-1, (int) screen.easyButton.getStyle().pressedOffsetY);
		assertEquals(screen.skin.getDrawable("default-rect"), screen.easyButton.getStyle().up);
		assertEquals(screen.skin.getDrawable("default-rect-down"), screen.easyButton.getStyle().down);
		
		assertEquals(screen.bitmapFont, screen.normalButton.getStyle().font);
		assertEquals(1, (int) screen.normalButton.getStyle().pressedOffsetX);
		assertEquals(-1, (int) screen.normalButton.getStyle().pressedOffsetY);
		assertEquals(screen.skin.getDrawable("default-rect"), screen.normalButton.getStyle().up);
		assertEquals(screen.skin.getDrawable("default-rect-down"), screen.normalButton.getStyle().down);
		
		assertEquals(screen.bitmapFont, screen.hardButton.getStyle().font);
		assertEquals(1, (int) screen.hardButton.getStyle().pressedOffsetX);
		assertEquals(-1, (int) screen.hardButton.getStyle().pressedOffsetY);
		assertEquals(screen.skin.getDrawable("default-rect"), screen.hardButton.getStyle().up);
		assertEquals(screen.skin.getDrawable("default-rect-down"), screen.hardButton.getStyle().down);
		
		assertEquals(screen.bitmapFont, screen.insaneButton.getStyle().font);
		assertEquals(1, (int) screen.insaneButton.getStyle().pressedOffsetX);
		assertEquals(-1, (int) screen.insaneButton.getStyle().pressedOffsetY);
		assertEquals(screen.skin.getDrawable("default-rect"), screen.insaneButton.getStyle().up);
		assertEquals(screen.skin.getDrawable("default-rect-down"), screen.insaneButton.getStyle().down);
		
	}
	
	@Test
	public void backButtonBehaviorTest() throws Exception {
		assertTrue(screen.easyButton.getListeners().get(0).handle(new ChangeEvent()));
		assertTrue(screen.normalButton.getListeners().get(0).handle(new ChangeEvent()));
		assertTrue(screen.hardButton.getListeners().get(0).handle(new ChangeEvent()));
		assertTrue(screen.insaneButton.getListeners().get(0).handle(new ChangeEvent()));
		
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void tableStateTest()  throws Exception {
		assertTrue(screen.table.isVisible());
		
		assertEquals(Gdx.graphics.getHeight(), (int) screen.table.getHeight());
		assertEquals(Gdx.graphics.getWidth(), (int) screen.table.getWidth());
		assertEquals(0, (int) screen.table.getX());
		assertEquals(0, (int) screen.table.getY());
		assertEquals(1, screen.table.getColumns());
		assertEquals(4, screen.table.getRows());
		
		Array<Cell> cells = screen.table.getCells();
		assertEquals(4,  cells.size);
		assertEquals(screen.easyButton, cells.get(0));
		assertEquals(screen.normalButton, cells.get(1));
		assertEquals(screen.hardButton, cells.get(2));
		assertEquals(screen.insaneButton, cells.get(3));
		
		for(Cell c : cells){
			assertTrue(c.getActor().isVisible());
		}
		
	}

}
