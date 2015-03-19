package com.restinhosoft.game.hittheballoon;

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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.restinhosoft.main.LanguageManager;
import com.restinhosoft.main.ShakeThisBottle;
import com.restinhosoft.ui.GameSelectionScreen;

public class StartScreen implements Screen {

	private ShakeThisBottle game;
	private Texture play;
	private Table table;
	private TextButton playButton;
	private BitmapFont bitmapFont;
	private Skin skin;
	private TextureAtlas atlas;
	private Stage stage;
	private Label label;
	private TextButton backButton;
	private TextButton highScoresButton;
	private TextButton tutorialButton;
	private TextButton survivalButton;
	private TweenManager tweenManager;
	
	private LanguageManager languageManager;
	public String language;
	private BitmapFont bitmapFont2;

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
		bitmapFont2 = new BitmapFont(Gdx.files.internal("neuropol-x-free.fnt"), false);
		
		TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("blue_button");
		textButtonStyle.down = skin.getDrawable("blue_button");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = bitmapFont;
		
		LabelStyle labelStyle = new Label.LabelStyle();
		labelStyle.font = bitmapFont;
		labelStyle.fontColor = Color.WHITE;
		
		LabelStyle labelStyle2 = new Label.LabelStyle();
		labelStyle2.font = bitmapFont2;

		table = new Table(skin);
		table.setFillParent(true);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		stage.addActor(table);
		
		label = new Label("Hit The Balloon!", labelStyle2);
		
		//playButton = new TextButton("Play!", textButtonStyle);
		playButton = new TextButton((language.equals(languageManager.languageEN)?"Play!":"Jogar!"), textButtonStyle);
		playButton.addListener(new ClickListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				game.setScreen(new SelectLevelScreen());
				return true;
			}
		});
		playButton.pad(15);
		
		survivalButton = new TextButton("Play Survival!",  textButtonStyle);
		survivalButton = new TextButton((language.equals(languageManager.languageEN)?"Play Survival!":"Jogar Survival!"), textButtonStyle);
		survivalButton.addListener(new ClickListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				GameScreen.GameManager.setSurvivalMode();
				GameScreen.GameManager.setLevel("easy");
				game.setScreen(new GameScreen());
				return true;
			}
		});
		survivalButton.pad(15);
		
		tutorialButton = new TextButton("Tutorial", textButtonStyle);
		tutorialButton.addListener(new ClickListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				game.setScreen(new TutorialScreen());
				return true;
			}
		});
		tutorialButton.pad(15);
		
		//highScoresButton  = new TextButton("High Scores", textButtonStyle);
		highScoresButton = new TextButton((language.equals(languageManager.languageEN)?"High Scores":"Pontuacoes"), textButtonStyle);
		highScoresButton.addListener(new ClickListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				game.setScreen(new HighScoreScreen());
				return true;
			}
		});
		highScoresButton.pad(15);
		
		//backButton = new TextButton("Back", textButtonStyle);
		backButton = new TextButton((language.equals(languageManager.languageEN)?"Back":"Voltar"), textButtonStyle);
		backButton.addListener(new ClickListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				game.setScreen(new GameSelectionScreen());
				return true;
			}
		});
		backButton.pad(15);
		
		table.add(label);
		table.getCell(label).spaceBottom(50);
		table.row();
		table.add(playButton);
		table.getCell(playButton).spaceBottom(5);
		table.row();
		table.add(survivalButton);
		table.getCell(survivalButton).spaceBottom(5);
		table.row();
		table.add(tutorialButton);;
		table.getCell(tutorialButton).spaceBottom(5);
		table.row();
		table.add(highScoresButton);;
		table.getCell(highScoresButton).spaceBottom(5);
		table.row();
		table.add(backButton);
		table.getCell(backButton).spaceBottom(5);
		
		//		animations
		tweenManager = new TweenManager();
		Tween.registerAccessor(Actor.class, new ActorAccessor());
		
		Timeline.createSequence().beginSequence()
			.push(Tween.to(label, ActorAccessor.RGB, .5f).target(0, 0, 1))
			.push(Tween.to(label, ActorAccessor.RGB, .5f).target(0, 1, 0))
			.push(Tween.to(label, ActorAccessor.RGB, .5f).target(0, 1, 1))
			.push(Tween.to(label, ActorAccessor.RGB, .5f).target(1, 0, 0))
			.push(Tween.to(label, ActorAccessor.RGB, .5f).target(1, 0, 1))
			.push(Tween.to(label, ActorAccessor.RGB, .5f).target(1, 1, 0))
			.push(Tween.to(label, ActorAccessor.RGB, .5f).target(1, 1, 1))
			.end().repeat(Tween.INFINITY, 0).start(tweenManager);
		
		Timeline.createSequence().beginSequence()
			.push(Tween.set(playButton, ActorAccessor.ALPHA).target(0))
			.push(Tween.set(survivalButton, ActorAccessor.ALPHA).target(0))
			.push(Tween.set(tutorialButton, ActorAccessor.ALPHA).target(0))
			.push(Tween.set(highScoresButton, ActorAccessor.ALPHA).target(0))
			.push(Tween.set(backButton, ActorAccessor.ALPHA).target(0))
			.push(Tween.from(label, ActorAccessor.ALPHA, .5f).target(0))
			.push(Tween.to(playButton, ActorAccessor.ALPHA, .5f).target(1))
			.push(Tween.to(survivalButton, ActorAccessor.ALPHA, .5f).target(1))
			.push(Tween.to(tutorialButton, ActorAccessor.ALPHA, .5f).target(1))
			.push(Tween.to(highScoresButton, ActorAccessor.ALPHA, .5f).target(1))
			.push(Tween.to	(backButton, ActorAccessor.ALPHA, .5f).target(1))
			.end().start(tweenManager);
		
		Tween.from(table, ActorAccessor.ALPHA, .5f).target(0).start(tweenManager);
		Tween.from(table, ActorAccessor.Y, .5f).target(Gdx.graphics.getHeight()/8).start(tweenManager);
		
	}

	@Override
	public void render(float delta) {
		GL20 gl = Gdx.gl;
		gl.glClearColor(0, 0, 0, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		tweenManager.update(delta);
		
		stage.act(delta);
		stage.draw();
		
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
