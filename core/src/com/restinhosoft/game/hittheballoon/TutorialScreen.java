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
import com.restinhosoft.main.LanguageManager;
import com.restinhosoft.main.ShakeThisBottle;

public class TutorialScreen implements Screen {
	
	private String tutorial_description_en = "Hit the ballons as soon as\n     they appear in the game screen!";
	private String tutorial_description_pt = "Acerte os baloes \n     assim que eles aparecerem \n          na tela do jogo!";
	private ShakeThisBottle game;
	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private BitmapFont bitmapFont;
	private Table table;
	private TextButton okButton;
	
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
		
		//TextButton okButton = new TextButton("Understood!!", textButtonStyle);
		okButton = new TextButton((language.equals(languageManager.languageEN)?"Understood!!":"Entendido!!"), textButtonStyle);
		okButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new StartScreen());;
			}
		});
		okButton.pad(10);
		
		LabelStyle labelStyle = new Label.LabelStyle();
		labelStyle.font = bitmapFont;
		labelStyle.fontColor = Color.MAGENTA;
		
		//Label label = new Label(tutorial_description, labelStyle);
		Label label = new Label((language.equals(languageManager.languageEN)? tutorial_description_en : tutorial_description_pt), labelStyle);

		table = new Table(skin);
		table.setFillParent(true);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		table.add(label);
		table.getCell(label).spaceBottom(50);
		table.row();
		table.add(okButton);
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
