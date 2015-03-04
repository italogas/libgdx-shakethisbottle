package com.restinhosoft.game.hittheballoon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.restinhosoft.main.LanguageManager;
import com.restinhosoft.main.ShakeThisBottle;

public class SelectLevelScreen implements Screen {

	public ShakeThisBottle game;
	public Stage stage;
	public TextureAtlas atlas;
	public BitmapFont bitmapFont;
	public Skin skin;
	public TextButton easyButton;
	public TextButton normalButton;
	public TextButton hardButton;
	public TextButton insaneButton;
	public Table table;
	
	private LanguageManager languageManager;
	public String language;

	@Override
	public void show() {
		this.game = (ShakeThisBottle) Gdx.app.getApplicationListener();
		
		languageManager = LanguageManager.getInstance();
		try {
			language = languageManager.getLanguage();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		stage = new Stage();
		
		Gdx.input.setInputProcessor(stage); 

		atlas = new TextureAtlas(Gdx.files.internal("button.atlas"));
		
		skin = new Skin(atlas);
		
		bitmapFont = new BitmapFont(Gdx.files.internal("default.fnt"), false);
		
		TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("blue_button");
		textButtonStyle.down = skin.getDrawable("blue_button");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = bitmapFont;
		
		//easyButton = new TextButton("Easy", textButtonStyle);
		easyButton = new TextButton((language.equals(languageManager.languageEN)?"Easy":"Facil"), textButtonStyle);
		easyButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				GameScreen.GameManager.setLevel("Easy");
				game.setScreen(new GameScreen());
			}
		});
		easyButton.pad(10);
		
		normalButton = new TextButton("Normal", textButtonStyle);
		normalButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				GameScreen.GameManager.setLevel("Normal");
				game.setScreen(new GameScreen());
			}
		});
		normalButton.pad(10);
		
		//hardButton = new TextButton("Hard", textButtonStyle);
		hardButton = new TextButton((language.equals(languageManager.languageEN)?"Hard":"Dificil"), textButtonStyle);
		hardButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				GameScreen.GameManager.setLevel("Hard");
				game.setScreen(new GameScreen());
			}
		});
		hardButton.pad(10);
		
		//insaneButton = new TextButton("Insane", textButtonStyle);
		insaneButton = new TextButton((language.equals(languageManager.languageEN)?"Insane":"Insano"), textButtonStyle);
		insaneButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				GameScreen.GameManager.setLevel("Insane");
				game.setScreen(new GameScreen());
			}
		});
		insaneButton.pad(10);
		
		table = new Table(skin);
		table.setFillParent(true);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		table.add(easyButton);
		table.getCell(easyButton).spaceBottom(5);
		table.row();
		table.add(normalButton);
		table.getCell(normalButton).spaceBottom(5);
		table.row();
		table.add(hardButton);
		table.getCell(hardButton).spaceBottom(5);
		table.row();
		table.add(insaneButton);
		table.getCell(insaneButton).spaceBottom(5);
		table.row();
		
		stage.addActor(table);

	}

	@Override
	public void render(float delta) {
		GL20 gl = Gdx.gl;
		gl.glClearColor(0, 0, 0, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
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
		atlas.dispose();
		bitmapFont.dispose();
		skin.dispose();
		stage.dispose();
	}

}
