package com.restinhosoft.ui;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.restinhosoft.game.hittheballoon.ActorAccessor;
import com.restinhosoft.main.LanguageManager;
import com.restinhosoft.main.ShakeThisBottle;

public class StartScreen implements Screen {

	private ShakeThisBottle game;
	private Texture play;
	private Table table;
	private BitmapFont bitmapFont;
	private Skin skin;
	private TextureAtlas atlas;
	private Stage stage;
	private Label label;
	private TweenManager tweenManager;
	
	private LanguageManager languageManager;
	public String language;
	private BitmapFont bitmapFont2;
	private Label label2;

	@Override
	public void show() {
		this.game = (ShakeThisBottle) Gdx.app.getApplicationListener();
		
		languageManager = LanguageManager.getInstance();
		try {
			language = languageManager.getLanguage();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		stage = new Stage();
		
		Gdx.input.setInputProcessor(stage);

		atlas = new TextureAtlas(Gdx.files.internal("button.atlas"));
		
		skin = new Skin(atlas);
		
		bitmapFont = new BitmapFont(Gdx.files.internal("default.fnt"), false);
		bitmapFont2 = new BitmapFont(Gdx.files.internal("zorque.fnt"), false);
		
		LabelStyle labelStyle = new Label.LabelStyle();
		labelStyle.font = bitmapFont;
		labelStyle.fontColor = Color.WHITE;
		
		LabelStyle labelStyle2 = new Label.LabelStyle();
		labelStyle2.font = bitmapFont2;

		table = new Table(skin);
		table.setFillParent(true);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		stage.addActor(table);
		
		label = new Label("Shake\n   The\nBottle", labelStyle2);
		
		label2 = new Label("Tap to begin", labelStyle);
		
		table.add(label);
		table.getCell(label).spaceBottom(50);
		table.row();
		table.add(label2);
		table.getCell(label2).spaceBottom(5);
		table.row();
		
		tweenManager = new TweenManager();
		Tween.registerAccessor(Actor.class, new ActorAccessor());
		
		Timeline.createSequence().beginSequence()
		.push(Tween.to(label2, ActorAccessor.RGB, .5f).target(0, 0, 0))
		.push(Tween.to(label2, ActorAccessor.RGB, .5f).target(1, 1, 1))
		.end().repeat(Tween.INFINITY, 0).start(tweenManager);
		
	}

	@Override
	public void render(float delta) {
		GL20 gl = Gdx.gl;
		gl.glClearColor(0, 0, 0, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		tweenManager.update(delta);
		
		stage.act(delta);
		stage.draw();
		
		if(Gdx.input.isTouched()){
			game.setScreen(new MainMenuScreen());
		}
		
	}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {}

	@Override
	public void dispose() {
		play.dispose();
		atlas.dispose();
		bitmapFont.dispose();
		skin.dispose();
		stage.dispose();
	}

}
