package com.restinhosoft.ui;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.restinhosoft.game.hittheballoon.TimeHelper;
import com.restinhosoft.main.ShakeThisBottle;

public class SplashScreen implements Screen {

	private SpriteBatch spriteBatch;
	private Texture texture;
	private FitViewport fitViewport;
	private Stage stage;
 	private TimeHelper timeHelper;
	private ShakeThisBottle applicationListener;
	private TweenManager tweenManager;
	private Sprite splashSprite;

	@Override
	public void show() {
		applicationListener = (ShakeThisBottle) Gdx.app.getApplicationListener();
		spriteBatch = new SpriteBatch();
		fitViewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage = new Stage();
		stage.setViewport(fitViewport);
		texture = new Texture(Gdx.files.internal("badge.png"));
		splashSprite = new Sprite(texture);
		splashSprite.setCenterX(Gdx.graphics.getWidth()/2);
		splashSprite.setCenterY(Gdx.graphics.getHeight()/2);
		
		tweenManager = new TweenManager();		
		Tween.registerAccessor(Sprite.class, new SpriteAcessor());
		
		Tween.set(splashSprite, SpriteAcessor.ALPHA).target(0).start(tweenManager);
		Tween.to(splashSprite, SpriteAcessor.ALPHA, 3).target(1).start(tweenManager);
		Tween.to(splashSprite, SpriteAcessor.ALPHA, 3).target(0).delay(2).start(tweenManager);
		
		timeHelper = new TimeHelper();
	}

	@Override
	public void render(float delta) {
		GL20 gl = Gdx.gl;
		gl.glClearColor(1, 1, 1, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		tweenManager.update(delta);
		
		spriteBatch.begin();
		splashSprite.draw(spriteBatch);
		spriteBatch.end();
		
		if(timeHelper.getElapsedTimeInSeconds() > 5) {
			applicationListener.setScreen(new MainMenuScreen());
		}
		
	}

	@Override
	public void resize(int width, int height) {
		fitViewport.setScreenSize(width, height);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
		spriteBatch.dispose();
		stage.dispose();
	}

}
