/**
 * 
 */
package com.restinhosoft.shakethisbottle.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * @author Italo
 *
 */
public class SoundOptionsScreen implements Screen {

	private ShakeThisBottle game;
	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private BitmapFont bitmapFont;
	private Table table;
	private Label enableSoundLabel;
	private Label gameMusicLable;
	private Label soundEffectsLabel;
	private Label generalVolumeLabel;
	private CheckBox checkBox0;
	private CheckBox checkBox1;
	private CheckBox checkBox2;
	private TextButton textButton;
	private ProgressBar progressBar;
	private float actualValue;
	private Texture background;
	private FitViewport fitViewport;

	public SoundOptionsScreen() {
		this.game = (ShakeThisBottle) Gdx.app.getApplicationListener();
		
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
		enableSoundLabel = new Label("Enable Sound: ", labelStyle);
		gameMusicLable = new Label("Game Music: ", labelStyle);
		soundEffectsLabel = new Label("Sound Effects: ", labelStyle);
		generalVolumeLabel = new Label("General Volume: ", labelStyle);
		
		CheckBoxStyle checkBoxStyle = new CheckBox.CheckBoxStyle();
		checkBoxStyle.checkboxOff = skin.getDrawable("check-off");
		checkBoxStyle.checkboxOn = skin.getDrawable("check-on");
		checkBoxStyle.pressedOffsetX = 1;
		checkBoxStyle.pressedOffsetY = -1;
		checkBoxStyle.font = bitmapFont;
		checkBox0 = new CheckBox("", checkBoxStyle);
		checkBox1 = new CheckBox("", checkBoxStyle);
		checkBox2 = new CheckBox("", checkBoxStyle);
		
		TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("default-rect");
		textButtonStyle.down = skin.getDrawable("default-rect-down");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = bitmapFont;
		
		textButton = new TextButton("Back", textButtonStyle);
		textButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new OptionsScreen());
			}
		});
		
		ProgressBarStyle progressBarStyle = new ProgressBar.ProgressBarStyle();
		progressBarStyle.background = skin.getDrawable("default-slider");
		
		progressBar = new ProgressBar(0, 10, 1, false, progressBarStyle);
		actualValue = progressBar.getMinValue();
		progressBar.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				progressBar.setValue(++actualValue);
			}
		});
		
		table = new Table(skin);
		table.setBounds(-Gdx.graphics.getWidth()/2, -Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		table.setFillParent(true);
//		table.setDebug(true);
		stage.addActor(table);
		
		table.add(enableSoundLabel);
		table.getCell(enableSoundLabel).spaceBottom(10);
		table.add(checkBox0);
		table.getCell(checkBox0).spaceBottom(10);
		table.row();
		table.add(gameMusicLable);
		table.getCell(gameMusicLable).spaceBottom(10);
		table.add(checkBox1);
		table.getCell(checkBox1).spaceBottom(10);
		table.row();
		table.add(soundEffectsLabel);
		table.getCell(soundEffectsLabel).spaceBottom(10);
		table.add(checkBox2);
		table.getCell(checkBox2).spaceBottom(10);
		table.row();
		table.add(generalVolumeLabel);
		table.getCell(generalVolumeLabel).spaceBottom(10);
		table.add(progressBar);
		table.getCell(progressBar).spaceBottom(10);
		table.row();
		table.add(textButton);
		
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#show()
	 */
	@Override
	public void show() {}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#render(float)
	 */
	@Override
	public void render(float delta) {
		GL20 gl = Gdx.gl;
		gl.glClearColor(0, 0, 0, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.begin();
		game.batch.draw(background, 0, 0);
		game.batch.end();
		
		stage.act(delta);
		stage.draw();
		
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#resize(int, int)
	 */
	@Override
	public void resize(int width, int height) {
		fitViewport.update(width, height);
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#pause()
	 */
	@Override
	public void pause() {}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#resume()
	 */
	@Override
	public void resume() {}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#hide()
	 */
	@Override
	public void hide() {}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#dispose()
	 */
	@Override
	public void dispose() {
		background.dispose();
		bitmapFont.dispose();
		skin.dispose();
		stage.dispose();
	}

}
