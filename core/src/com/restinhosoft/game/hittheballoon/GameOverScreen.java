package com.restinhosoft.game.hittheballoon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.restinhosoft.shakethisbottle.ui.ShakeThisBottle;

public class GameOverScreen implements Screen {

	private ShakeThisBottle game;
	private Stage stage;
	private TextureAtlas textureAtlas;
	private Skin skin;
	private BitmapFont bitmapFont;
	private LabelStyle labelStyle;
	private Table table;

	@Override
	public void show() {
		this.game = (ShakeThisBottle) Gdx.app.getApplicationListener();
		
		stage = new Stage();
		
		Gdx.input.setInputProcessor(stage);
		
		textureAtlas = new TextureAtlas(Gdx.files.internal("button.atlas"));
		
		skin = new Skin(textureAtlas);
		
		bitmapFont = new BitmapFont(Gdx.files.internal("default.fnt"), false);
		
		TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("blue_button");
		textButtonStyle.down  = skin.getDrawable("blue_button");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = bitmapFont;
		textButtonStyle.fontColor = Color.WHITE;
		
		labelStyle = new Label.LabelStyle();
		labelStyle.font = bitmapFont;
		labelStyle.fontColor= Color.RED;
		
		TextButton backButton = new TextButton("Back", textButtonStyle);
		backButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new StartScreen());
			}
		});
		backButton.pad(10);
		
		TextButton tryAgainButton = new TextButton("Try Again", textButtonStyle);
		tryAgainButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new GameScreen());
				
			}
		});
		tryAgainButton.pad(10);
		
		Label gameOverLabel = new Label("GAME OVER", labelStyle);
		gameOverLabel.setFontScale(1);
		
		Label scoreLabel = new Label("Final Score: " + GameScreen.GameManager.getScore(), labelStyle);
		GameScreen.GameManager.resetScore();
		
		table = new Table(skin);
		table.setFillParent(true);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//		table.setDebug(true);
		
		table.add(gameOverLabel);
		table.getCell(gameOverLabel).spaceBottom(50);
		table.row();
		table.add(scoreLabel);
		table.getCell(scoreLabel).spaceBottom(50);
		table.row();
		table.add(backButton);
		table.getCell(backButton).spaceBottom(5);
		table.row();
		table.add(tryAgainButton);
		table.getCell(tryAgainButton).spaceBottom(5);
		
		stage.addActor(table);
		
	}
	

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
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
		bitmapFont.dispose();
		skin.dispose();
		textureAtlas.dispose();
		stage.dispose();
	}

}
