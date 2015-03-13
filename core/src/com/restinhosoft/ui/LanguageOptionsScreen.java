package com.restinhosoft.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.restinhosoft.game.hitthecolor.HitTheCircleStartScreen;
import com.restinhosoft.main.LanguageManager;
import com.restinhosoft.main.ShakeThisBottle;

public class LanguageOptionsScreen implements Screen {

	public ShakeThisBottle game;
	public Stage stage;
	public TextureAtlas atlas;
	public Skin skin;
	public BitmapFont bitmapFont;
	public Label selectLabel;
	public Table table;
	public CheckBox checkBox0;
	public CheckBox checkBox1;
	public TextButton backButton;
	public Texture background;
	public FitViewport fitViewport;

	private LanguageManager languageManager;
	public String language;

	private TextButton flagBRBT;
	private TextButton flagUSBT;
	
	private Texture subBackGround;
	private TextureAtlas flagAtlasBR;
	private TextureAtlas flagAtlasUS;
	private Skin flagSkinBR;
	private Skin flagSkinUS;
	
	
	private TextureAtlas creatingAtlas(String file){ 
		return new TextureAtlas(Gdx.files.internal(file));
	}
	
	private Skin creatingSkin(TextureAtlas atlas){ 
		return new Skin(atlas);
	}
	
	@SuppressWarnings("unused")
	private TextButton creatingTextButton(String text,TextButtonStyle style, boolean disable){
		TextButton button = new TextButton(text, style);
		button.setDisabled(disable);
		return button;
	}
	
	private TextButtonStyle creatingTextButtonStyles(Skin skin, String file, BitmapFont font){
		TextButtonStyle style = new TextButton.TextButtonStyle();
		style.up   = skin.getDrawable(file);
		style.down = skin.getDrawable(file);
		style.pressedOffsetX = 1;
		style.pressedOffsetY =-1;
		style.font = font;
		
		return style;
	}
	
	@Override
	public void show() {
		this.game = (ShakeThisBottle) Gdx.app.getApplicationListener();
		
		languageManager = LanguageManager.getInstance();
		
		try {
			language = languageManager.getLanguage();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		fitViewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		stage = new Stage();
		stage.setViewport(fitViewport);
		
		Gdx.input.setInputProcessor(stage);
		
		background    = new Texture(Gdx.files.internal("menu.png"));
		subBackGround = new Texture(Gdx.files.internal("icons/sub_menu.png"));
		
		atlas = new TextureAtlas(Gdx.files.internal("uiskin.atlas"));
		flagAtlasBR = creatingAtlas("icons/icone_lang_br.atlas");//new TextureAtlas(Gdx.files.internal("icons/icone_lang_br.atlas"));
		flagAtlasUS = creatingAtlas("icons/icone_lang_usa.atlas");//new TextureAtlas(Gdx.files.internal("icons/icone_lang_usa.atlas"));
		
		skin = new Skin(atlas);
		flagSkinBR= creatingSkin(flagAtlasBR);//new Skin(flagAtlasBR);
		flagSkinUS= creatingSkin(flagAtlasUS);//new Skin(flagAtlasUS);
		
		bitmapFont = new BitmapFont(Gdx.files.internal("default.fnt"));
		
		LabelStyle labelStyle = new Label.LabelStyle();
		labelStyle.font = bitmapFont;
		selectLabel = new Label((language.equals(languageManager.languageEN)?"Select Language: ":"Selecionar Linguagem: "), labelStyle);
		
		CheckBoxStyle checkBoxStyle = new CheckBox.CheckBoxStyle();
		checkBoxStyle.checkboxOff = skin.getDrawable("check-off");
		checkBoxStyle.checkboxOn = skin.getDrawable("check-on");
		checkBoxStyle.pressedOffsetX = 1;
		checkBoxStyle.pressedOffsetY = -1;
		checkBoxStyle.font = bitmapFont;
		checkBox0 = new CheckBox("PT  ", checkBoxStyle);
		checkBox1 = new CheckBox("EN  ", checkBoxStyle);
		
		TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = bitmapFont;
		
		TextButtonStyle flagBRStyle = creatingTextButtonStyles(flagSkinBR,"icone_lang_br", bitmapFont);
		TextButtonStyle flagUSStyle = creatingTextButtonStyles(flagSkinUS,"icone_lang_usa", bitmapFont);
		
		flagBRBT = creatingTextButton("", flagBRStyle, false);
		flagUSBT = creatingTextButton("", flagUSStyle, false);
		
		/*TextButtonStyle flagBRStyle = new TextButton.TextButtonStyle();
		textButtonStyle.up = flagSkinBR.getDrawable("icone_lang_br");
		textButtonStyle.down = flagSkinBR.getDrawable("icone_lang_br");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = bitmapFont;
		
		TextButtonStyle flagUSStyle = new TextButton.TextButtonStyle();
		textButtonStyle.up = flagSkinUS.getDrawable("icone_lang_usa");
		textButtonStyle.down = flagSkinUS.getDrawable("icone_lang_usa");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = bitmapFont;*/
		
		
		flagBRBT.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				language = languageManager.languagePT;
				try {
					languageManager.setLanguage(language);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
				selectLabel.setText("Selecionar Linguagem:");
				backButton.setText("Voltar");
			}
		});

		
		flagUSBT.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				language = languageManager.languageEN;
				try {
					languageManager.setLanguage(language);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
				selectLabel.setText("Select Language:");
				backButton.setText("Back");
			}
		});
		
		backButton = new TextButton((language.equals(languageManager.languageEN)?"Back ":"Voltar "), textButtonStyle);
		backButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new OptionsScreen());
				dispose();
			}
		});
		
		table = new Table();
		table.setBounds(-Gdx.graphics.getWidth()/2, -Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		table.setFillParent(true);
		stage.addActor(table);
		
		table.add(selectLabel);
		table.getCell(selectLabel).spaceBottom(10);
		table.row();
		table.add(flagUSBT).pad(20);
		table.row();
		table.add(flagBRBT).pad(20);
		table.row();
		table.add(backButton);
		table.getCell(backButton).spaceBottom(15);
		/*
		table.add(selectLabel);
		table.getCell(selectLabel).spaceBottom(25);
		table.row();
		table.add(checkBox0);
		table.getCell(checkBox0).spaceBottom(10);
		table.row();
		table.add(checkBox1);
		table.getCell(checkBox1).spaceBottom(10);
		table.row();
		table.add(backButton);
		table.getCell(backButton).spaceBottom(15);*/

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.begin();
		game.batch.draw(subBackGround,0,0);//background, 0, 0);
		game.batch.end();
		
		if(language.equals(languageManager.languagePT)) checkBox0.setChecked(true);
		if(language.equals(languageManager.languageEN)) checkBox1.setChecked(true);
		
		if(checkBox0.isPressed()){
			language = languageManager.languagePT;
			try {
				languageManager.setLanguage(language);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
			checkBox1.setChecked(false);
			selectLabel.setText("Selecionar Linguagem:");
			backButton.setText("Voltar");
		}
		
		if(checkBox1.isPressed()){
			language = languageManager.languageEN;
			try {
				languageManager.setLanguage(language);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
			checkBox0.setChecked(false);
			selectLabel.setText("Select Language:");
			backButton.setText("Back");
		}
		
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
		bitmapFont.dispose();
	}

}
