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
import com.restinhosoft.shakethisbottle.ui.ShakeThisBottle;

public class SelectLevelScreen implements Screen {

	private ShakeThisBottle game;
	private Stage stage;
	private TextureAtlas atlas;
	private BitmapFont bitmapFont;
	private Skin skin;

	@Override
	public void show() {
		this.game = (ShakeThisBottle) Gdx.app.getApplicationListener(); 
		
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
		
		TextButton easyButton = new TextButton("Easy", textButtonStyle);
		easyButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				GameScreen.GameManager.setLevel("Easy");
				game.setScreen(new GameScreen());
			}
		});
		
		TextButton normalButton = new TextButton("Normal", textButtonStyle);
		normalButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				GameScreen.GameManager.setLevel("Normal");
				game.setScreen(new GameScreen());
			}
		});
		
		TextButton hardButton = new TextButton("Hard", textButtonStyle);
		hardButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				GameScreen.GameManager.setLevel("Hard");
				game.setScreen(new GameScreen());
			}
		});
		
		TextButton insaneButton = new TextButton("Insane", textButtonStyle);
		insaneButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				GameScreen.GameManager.setLevel("Insane");
				game.setScreen(new GameScreen());
			}
		});
		
		Table table = new Table(skin);
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
		gl.glClearColor(1, 1, 1, 1);
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
