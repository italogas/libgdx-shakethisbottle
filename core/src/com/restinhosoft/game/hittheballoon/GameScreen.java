package com.restinhosoft.game.hittheballoon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.restinhosoft.game.hittheballoon.Balloon.BallonColor;
import com.restinhosoft.shakethisbottle.ui.ShakeThisBottle;

public class GameScreen implements Screen {

	private Stage stage;
	private Balloon balloon;
	private ShakeThisBottle game;

	@Override
	public void show() {
		game = (ShakeThisBottle) Gdx.app.getApplicationListener();
		
		stage = new Stage();
		stage.getViewport().setScreenSize(Gdx.graphics.getWidth(), Gdx.graphics.getWidth());
		Gdx.input.setInputProcessor(stage);
		
		Array<Balloon> balloon_array = new Array<Balloon>();
		balloon_array.add(new Balloon(BallonColor.BLUE));
		balloon_array.add(new Balloon(BallonColor.GREEN));
		balloon_array.add(new Balloon(BallonColor.ORANGE));
		balloon_array.add(new Balloon(BallonColor.PINK));
		balloon_array.add(new Balloon(BallonColor.PURPLE));
		balloon_array.add(new Balloon(BallonColor.RED));
		balloon_array.add(new Balloon(BallonColor.YELLOW));
		balloon_array.shuffle();
		
		balloon = (balloon_array.first());
		balloon.setVisible(true);
		balloon.setTouchable(Touchable.enabled);
		
		stage.addActor(balloon);
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
		update();
		
	}

	private void update() {
		if(balloon.isTouched()){
			balloon.setTouched(true);
			game.setScreen(new GameOverScreen());
		} else {
			if(balloon.isOut()){
				game.setScreen(new GameOverScreen());
			}
		}
	}
	
	@Override
	public void hide() {}
	
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
