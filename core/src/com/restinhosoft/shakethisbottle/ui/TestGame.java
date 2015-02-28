package com.restinhosoft.shakethisbottle.ui;

import com.badlogic.gdx.Screen;

public class TestGame extends ShakeThisBottle {
	private Screen screen;
	
	public TestGame(Screen screen){
		this.screen = screen;	
	}
	
	@Override
	public void create() {
		this.setScreen(this.screen);
	}
	
	@Override
	public void render() {}
	
	@Override
	public void dispose() {}
	}