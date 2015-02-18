package com.restinhosoft.shakethisbottle.ui;

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
import com.restinhosoft.player.PlayerPreferencesIOBuffer;

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
	
	public 	PlayerPreferencesIOBuffer pref;

	@Override
	public void show() {
		this.game = (ShakeThisBottle) Gdx.app.getApplicationListener();
		
		pref = new PlayerPreferencesIOBuffer();
		
		fitViewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		stage = new Stage();
		stage.setViewport(fitViewport);
		
		Gdx.input.setInputProcessor(stage);
		
		background = new Texture(Gdx.files.internal("menu.png"));
		atlas = new TextureAtlas(Gdx.files.internal("uiskin.atlas"));
		
		skin = new Skin(atlas);
		
		bitmapFont = new BitmapFont(Gdx.files.internal("default.fnt"));
		
		LabelStyle labelStyle = new Label.LabelStyle();
		labelStyle.font = bitmapFont;
		selectLabel = new Label("Select Language: ", labelStyle);
		
		CheckBoxStyle checkBoxStyle = new CheckBox.CheckBoxStyle();
		checkBoxStyle.checkboxOff = skin.getDrawable("check-off");
		checkBoxStyle.checkboxOn = skin.getDrawable("check-on");
		checkBoxStyle.pressedOffsetX = 1;
		checkBoxStyle.pressedOffsetY = -1;
		checkBoxStyle.font = bitmapFont;
		checkBox0 = new CheckBox("PT  ", checkBoxStyle);
		checkBox1 = new CheckBox("EN  ", checkBoxStyle);
		
		TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("default-rect");
		textButtonStyle.down = skin.getDrawable("default-rect-down");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = bitmapFont;
		
		backButton = new TextButton("Back", textButtonStyle);
		backButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new OptionsScreen());
			}
		});
		
		table = new Table();
		table.setBounds(-Gdx.graphics.getWidth()/2, -Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		table.setFillParent(true);
//		table.setDebug(true);
		stage.addActor(table);
		
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
		table.getCell(backButton).spaceBottom(15);

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.begin();
		game.batch.draw(background, 0, 0);
		game.batch.end();
		//*******language
		if(pref.getLanguage().equals("ptbr")){ 	checkBox0.setChecked(true);}
		
		if(pref.getLanguage().equals("engl")){  checkBox1.setChecked(true);}
		
		if(checkBox0.isPressed()){
			pref.setLanguage("ptbr");
			checkBox1.setChecked(false);
		}
		
		if(checkBox1.isPressed()){
			pref.setLanguage("engl");
			checkBox0.setChecked(false);
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
