package com.restinhosoft.ui.menus;

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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.restinhosoft.options.LanguageManager;
import com.restinhosoft.shakethisbottle.ui.ShakeThisBottle;


public class OptionsScreen implements Screen {

	private ShakeThisBottle game;
	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private BitmapFont bitmapFont;
	private Table table;
	private TextButton textButton1;
	private TextButton textButton2;
	private TextButton textButton3;
	private Texture background;
	private FitViewport fitViewport;
	
	private LanguageManager languageManager;
	public String language;
	
	@Override
	public void show() {
		this.game = (ShakeThisBottle) Gdx.app.getApplicationListener();
		
		fitViewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		languageManager = LanguageManager.getInstance();
		try {
			language = languageManager.getLanguage();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		stage = new Stage();
		stage.setViewport(fitViewport);
		
		Gdx.input.setInputProcessor(stage);
		
		background = new Texture(Gdx.files.internal("menu.png"));
		
		atlas = new TextureAtlas(Gdx.files.internal("button.atlas"));
		
		skin = new Skin(atlas);
		
		bitmapFont = new BitmapFont(Gdx.files.internal("default.fnt"));
		
		TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("blue_button");
		textButtonStyle.down = skin.getDrawable("blue_button");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = bitmapFont;
		
		table = new Table();
		table.setFillParent(true);
		table.setBounds(-Gdx.graphics.getWidth()/2, -Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage.addActor(table);
		
		textButton1 = new TextButton((language.equals(languageManager.languageEN)?"Language ":"Linguagem "), textButtonStyle);
		textButton1.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new LanguageOptionsScreen());
			}
		});
		textButton1.pad(15);
		
		textButton2 = new TextButton((language.equals(languageManager.languageEN)?"Sound Options ":"Opcoes de Som "), textButtonStyle);
		textButton2.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new SoundOptionsScreen());
			}
		});
		textButton2.pad(15);
		
		textButton3 = new TextButton((language.equals(languageManager.languageEN)?"Exit ":"Sair "), textButtonStyle);
		textButton3.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new MainMenuScreen());
			}
		});
		textButton3.pad(15);
		
		table.add(textButton1);
		table.getCell(textButton1).spaceBottom(10);
		table.row();
		table.add(textButton2);
		table.getCell(textButton2).spaceBottom(10);
		table.row();
		table.add(textButton3);
		
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
	}

}
