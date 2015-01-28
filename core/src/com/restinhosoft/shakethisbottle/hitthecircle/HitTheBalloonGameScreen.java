package com.restinhosoft.shakethisbottle.hitthecircle;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class HitTheBalloonGameScreen implements ApplicationListener {

	private Stage stage;

	@Override
	public void create() {
//		stage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		Balloon balloon = new Balloon();
		balloon.setTouchable(Touchable.enabled);
		
//		Group group = new Group();
//		group.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//		group.setVisible(true);
//		group.addActor(balloon);
		
//		stage.addActor(group);
		stage.addActor(balloon);
		
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
	}

	@Override
	public void resize(int width, int height) {}
	
	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void dispose() {
		stage.dispose();
	}

	
}
