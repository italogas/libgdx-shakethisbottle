package com.restinhosoft.game.hitthecolor;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.restinhosoft.main.AudioManager;
import com.restinhosoft.main.LanguageManager;
import com.restinhosoft.main.MiniGamesIF;
import com.restinhosoft.main.ShakeThisBottle;
import com.restinhosoft.ui.AuxScreenCreation;
import com.restinhosoft.ui.GameSelectionScreen;

public class ColorStart implements MiniGamesIF, Screen {
	private ShakeThisBottle game;
	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private final BitmapFont bitmapFont = new BitmapFont(Gdx.files.internal("default.fnt"));
	private Table table;
	private Texture background;
	private FitViewport fitViewport;
	private TextButton back;
	
	private LanguageManager languageManager;
	public String language;
	
	private final AuxScreenCreation creating = new AuxScreenCreation();
	private TextButton title;
	private TextButton Score;
	private TextButton Level;
	
	private final TextureAtlas colorAtlas = creating.creatingAtlas("games/color_button.atlas");
	private final Skin colorSkin = creating.creatingSkin(colorAtlas);
	private final TextButtonStyle colorStyleBlue  = creating.creatingTextButtonStyles(colorSkin, "blue_bt", bitmapFont);
	private final TextButtonStyle colorStyleRed   = creating.creatingTextButtonStyles(colorSkin, "red_bt", bitmapFont);
	private final TextButtonStyle colorStyleGreen = creating.creatingTextButtonStyles(colorSkin, "green_bt", bitmapFont);
	private final TextButtonStyle colorStyleYellow= creating.creatingTextButtonStyles(colorSkin, "yellow_bt", bitmapFont);
	private final TextButtonStyle colorStyleGray  = creating.creatingTextButtonStyles(colorSkin, "gray_bt", bitmapFont);
	private final TextButtonStyle colorStyleWhite = creating.creatingTextButtonStyles(colorSkin, "white_bt", bitmapFont);
	private final TextButtonStyle colorStylePurple= creating.creatingTextButtonStyles(colorSkin, "purple_bt", bitmapFont);
	
	private TextButton cor01;
	private TextButton cor02;
	private TextButton cor03;
	private TextButton cor04;
	private TextButton cor05;
	private TextButton cor06;
	private TextButton cor07;
	private TextButton cor08;
	private TextButton cor09;
	private TextButton cor10;
	private TextButton cor11;
	private TextButton cor12;
	private TextButton cor13;
	private TextButton cor14;
	private TextButton cor15;
	private TextButton cor16;
	private TextButton cor17;
	private TextButton cor18;
	
	private ArrayList<Integer> colorNumber = new ArrayList<Integer>();
	private ArrayList<TextButton> buttons  = new ArrayList<TextButton>();
	
	
	
	private AudioManager audioManager;

	private void addColorNumberList(){
		
	}
	
	private void addButtonsList(){
		
	}

	@Override
	public void setGameMode(String mode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDifficulty(String difficulty) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setScore(int score) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLevel(int level) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBonus(int bonus) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPause() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGameMode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDifficulty() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBonus() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean paused() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveTempGame() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean loadTempGame() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void show() {
this.game = (ShakeThisBottle) Gdx.app.getApplicationListener();
		
		fitViewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		languageManager = LanguageManager.getInstance();
		try {
			language = languageManager.getLanguage();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		audioManager = new AudioManager("audio/gameselection.ogg");
		audioManager.addToSoundTrack("audio/failbt.mp3");
		audioManager.addToSoundTrack("audio/botoes_first.mp3");
		
		stage = new Stage();
		stage.setViewport(fitViewport);
		
		Gdx.input.setInputProcessor(stage);
		
		background = new Texture(Gdx.files.internal("games/game_background.png"));//new Texture(Gdx.files.internal("menu.png"));
		
		atlas = new TextureAtlas(Gdx.files.internal("button.atlas"));
		
		skin = new Skin(atlas);
		
		TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = bitmapFont;
		
		table = new Table();
		table.setFillParent(true);
		table.setBounds(-Gdx.graphics.getWidth()/2, -Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage.addActor(table);
		
		/*
		
		easy = new TextButton((language.equals(languageManager.languageEN)?"EASY ":"FACIL "), textButtonStyle);
		easy.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				gameInstance.setDifficulty("easy");
				game.setScreen(gameInstance);
			}
		});
		easy.pad(15);
		
		normal = new TextButton((language.equals(languageManager.languageEN)?"NORMAL ":"NORMAL "), textButtonStyle);
		normal.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				gameInstance.setDifficulty("normal");
				game.setScreen(gameInstance);
			}
		});
		normal.pad(15);
		
		hard = new TextButton((language.equals(languageManager.languageEN)?"HARD":"DIFICIL"), textButtonStyle);
		hard.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				gameInstance.setDifficulty("hard");
				game.setScreen(gameInstance);
			}
		});
		hard.pad(15);
		
		insane = new TextButton((language.equals(languageManager.languageEN)?"INSANE":"INSANO"), textButtonStyle);
		insane.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				gameInstance.setDifficulty("insane");
				game.setScreen(gameInstance);
			}
		});
		insane.pad(15);
		*/
		back = new TextButton((language.equals(languageManager.languageEN)?"BACK ":"VOLTAR "), textButtonStyle);
		back.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new ColorGameMenu());
				dispose();
			}
		});
		/*
		back.pad(15);
		table.add(title);
		table.getCell(title).spaceBottom(10);
		table.row();
		back.pad(15);
		table.add(insane);
		table.getCell(insane).spaceBottom(10);
		table.row();
		table.add(hard);
		table.getCell(hard).spaceBottom(10);
		table.row();
		table.add(normal);
		table.getCell(normal).spaceBottom(10);
		table.row();
		table.add(easy);
		table.getCell(easy).spaceBottom(10);
		table.row();*/
		table.add(back);
		table.getCell(back).spaceBottom(10);
		table.row();
		
		
	}

	@Override
	public void render(float delta) {
		GL20 gl = Gdx.gl;
		gl.glClearColor(1, 1, 1, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.begin();
		game.batch.draw(background, 0, 0);
		game.batch.end();
		
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		fitViewport.update(width, height);
	}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {}

	@Override
	public void dispose() {
		background.dispose();
		atlas.dispose();
		skin.dispose();
		stage.dispose();
		bitmapFont.dispose();
		audioManager.close();
	}
}
