package com.restinhosoft.shakethisbottle.hitthecircle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Balloon extends Actor {

	Texture texture = new Texture(Gdx.files.internal("circle1.png"));
	float balloon_x = 0;
	float balloon_y = 0;
	public static boolean started = false;
	
	public Balloon() {
		this.setBounds(balloon_x, balloon_y, texture.getWidth(), texture.getHeight());
		addListener(new InputListener(){
			@SuppressWarnings("static-access")
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				System.out.println("Key Down!");
				((Balloon) event.getTarget()).started = true;
				return true;
			}
		});
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(texture, balloon_x, balloon_y);
	}
	
	@Override
	public void act(float delta) {
		if(started)
			balloon_y += 2;
		return;
	}

}
