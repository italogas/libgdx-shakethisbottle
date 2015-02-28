package com.restinhosoft.games.hittheballoon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.restinhosoft.options.LanguageManager;
import com.restinhosoft.shakethisbottle.ui.ShakeThisBottle;

public class HighScoreScreen implements Screen {

	private ShakeThisBottle game;
	private Stage stage;
	private BitmapFont bitmapFont;
	private Table table;
	private com.badlogic.gdx.scenes.scene2d.ui.Label label;
	private TextureAtlas textureAtlas;
	private Skin skin;
	
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
		
		textureAtlas = new TextureAtlas(Gdx.files.internal("button.atlas"));
		
		skin = new Skin(textureAtlas);
		
		bitmapFont = new BitmapFont(Gdx.files.internal("default.fnt"));
		
		LabelStyle labelStyle = new LabelStyle();
		labelStyle.font = bitmapFont;
		labelStyle.fontColor = Color.BLUE;
		
		TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("blue_button");
		textButtonStyle.down  = skin.getDrawable("blue_button");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = bitmapFont;
		textButtonStyle.fontColor = Color.WHITE;
		
		String highScore = GameScreen.GameManager.loadScore();
		label = new com.badlogic.gdx.scenes.scene2d.ui.Label(highScore, labelStyle);
		
		//TextButton textButton = new TextButton("Back", textButtonStyle);
		TextButton textButton = new TextButton((language.equals(languageManager.languageEN)?"Back":"Voltar"), textButtonStyle);
		textButton.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new StartScreen());
			}
		});
		textButton.pad(10);
		
		table = new Table();
		table.setFillParent(true);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		table.add(label);
		table.getCell(label).spaceBottom(50);
		table.row();
		table.add(textButton);
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
		stage.dispose();
		bitmapFont.dispose();
	}

}
