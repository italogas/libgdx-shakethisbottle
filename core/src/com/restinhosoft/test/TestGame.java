package com.restinhosoft.test;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.restinhosoft.main.ShakeThisBottle;
import com.restinhosoft.ui.MainMenuScreen;

public class TestGame extends ShakeThisBottle {
	
	public BitmapFont font;
	
	@Override
	public void create() {
		font = new BitmapFont();
		this.setScreen(new MainMenuScreen());
	}
	
	@Override
	public void dispose() {
		font.dispose();
	}
	

}
