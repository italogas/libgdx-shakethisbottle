package com.restinhosoft.shakethisbottle.hitthecircle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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

public class HitTheBalloonStartScreen implements Screen {

	private ShakeThisBottle game;
	private OrthographicCamera cam;
	private Texture background;
	private Texture play;
	private Table table;
	private TextButton playButton;
	private BitmapFont bitmapFont;
	private Skin skin;
	private TextureAtlas atlas;
	private Stage stage;
	private Label label;
	private TextButton backButton;

	@Override
	public void show() {
		this.game = (ShakeThisBottle) Gdx.app.getApplicationListener(); 
				
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 320, 480);
		
//		background = new Texture(Gdx.files.internal("background.png"));
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

		table = new Table(skin);
		table.setFillParent(true);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		table.setDebug(true);
		
		stage.addActor(table);
		
		label = new Label("Hit The Balloon!", labelStyle);
		
		playButton = new TextButton("Play!", textButtonStyle);
		playButton.addListener(new ClickListener(){
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				playButton.setText("Play!");
			}
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				playButton.setText("Touched!");
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
		
		table.add(label).expand();
		table.row();
		table.add(playButton);
		table.row();
		table.add(backButton);
		
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
		background.dispose();
		play.dispose();
		atlas.dispose();
		bitmapFont.dispose();
		skin.dispose();
		stage.dispose();
	}

}
