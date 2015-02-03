package com.restinhosoft.game.hittheballoon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.restinhosoft.shakethisbottle.ui.GameSelectionScreen;
import com.restinhosoft.shakethisbottle.ui.ShakeThisBottle;

public class StartScreen implements Screen {

	private ShakeThisBottle game;
	private OrthographicCamera cam;
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

	@Override
	public void show() {
		this.game = (ShakeThisBottle) Gdx.app.getApplicationListener(); 
				
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 320, 480);
		
		play = new Texture(Gdx.files.internal("play.png"));
		
		stage = new Stage();
		
		Gdx.input.setInputProcessor(stage);

		atlas = new TextureAtlas(Gdx.files.internal("uiskin.atlas"));
		
		skin = new Skin(atlas);
		
		bitmapFont = new BitmapFont(Gdx.files.internal("default.fnt"), false);
		
		TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("default-rect");
		textButtonStyle.down = skin.getDrawable("default-rect-down");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = bitmapFont;
		
		LabelStyle labelStyle = new Label.LabelStyle();
		labelStyle.font = bitmapFont;
		labelStyle.fontColor = Color.DARK_GRAY;

		table = new Table(skin);
		table.setFillParent(true);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//		table.setDebug(true);
		
		stage.addActor(table);
		
		label = new Label("Hit The Balloon!", labelStyle);
		
		playButton = new TextButton("Play!", textButtonStyle);
		playButton.addListener(new ClickListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				game.setScreen(new GameScreen());
				return true;
			}
		});
		
		highScoresButton  = new TextButton("High Scores", textButtonStyle);
		highScoresButton.addListener(new ClickListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				game.setScreen(new HighScoreScreen());
				return true;
			}
		});
		
		backButton = new TextButton("Back", textButtonStyle);
		backButton.addListener(new ClickListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				game.setScreen(new GameSelectionScreen());
				return true;
			}
		});
		
		table.add(label);
		table.getCell(label).spaceBottom(50);
		table.row();
		table.add(playButton);
		table.getCell(playButton).spaceBottom(5);
		table.row();
		table.add(highScoresButton);
		table.getCell(highScoresButton).spaceBottom(5);
		table.row();
		table.add(backButton);
		table.getCell(backButton).spaceBottom(5);
		
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
		play.dispose();
		atlas.dispose();
		bitmapFont.dispose();
		skin.dispose();
		stage.dispose();
	}

}
