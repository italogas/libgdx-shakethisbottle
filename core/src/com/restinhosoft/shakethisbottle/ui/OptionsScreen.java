package com.restinhosoft.shakethisbottle.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class OptionsScreen implements Screen {

	private ShakeThisBottle game;
	private BitmapFont bitmapFont;
	private int regionHeight;
	private int regionWidth;
	private int soundX;
	private int soundY;
	private int languageX;
	private int languageY;
	private int exitX;
	private int exitY;
	private Texture backgroundImg;
	private Rectangle soundRec;
	private Rectangle languageRec;
	private Rectangle exitRec;
	private OrthographicCamera cam;
	private Texture languageImg;
	private Texture soundImg;
	private Texture backtomenuImg;

	public OptionsScreen(ShakeThisBottle game) {
		this.game = game;
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 320, 480);
		
		backgroundImg =  new Texture(Gdx.files.internal("menu.png"));
		languageImg =  new Texture(Gdx.files.internal("language.png"));
		soundImg =  new Texture(Gdx.files.internal("sound_options.png"));
		backtomenuImg =  new Texture(Gdx.files.internal("backtomenu.png"));
		
		soundX = 320 / 2 -  soundImg.getWidth() / 2;
		soundY = 480 / 2 +  soundImg.getHeight() / 2;
		
		soundRec = new Rectangle();
		soundRec.set(soundX, soundY, soundImg.getWidth(), soundImg.getHeight());
		
		languageX = 320 / 2 -  languageImg.getWidth() / 2;
		languageY = soundY - languageImg.getHeight() / 2 - 32;
		
		languageRec = new Rectangle();
		languageRec.set(languageX, languageY, languageImg.getWidth(), languageImg.getHeight());
		
		exitX = 320 / 2 -  backtomenuImg.getWidth() / 2;
		exitY = languageY  - backtomenuImg.getHeight() / 2 - 32;
		
		exitRec = new Rectangle();
		exitRec.set(exitX, exitY, backtomenuImg.getWidth(), backtomenuImg.getHeight());
	}

	@Override
	public void show() {}

	@Override
	public void render(float delta) {
		GL20 gl = Gdx.gl;
		gl.glClearColor(1, 0, 0, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		cam.update();
		game.batch.setProjectionMatrix(cam.combined);
		game.batch.begin();
		game.batch.draw(backgroundImg, 0, 0);
		game.batch.draw(soundImg, soundX, soundY);
		game.batch.draw(languageImg, languageX, languageY);
		game.batch.draw(backtomenuImg, exitX, exitY);
//		bitmapFont.draw(game.batch, "SOUND MENU", soundX, soundY);
//		bitmapFont.draw(game.batch, "LANGUAGE", languageX, languageY);
//		bitmapFont.draw(game.batch, "EXIT", exitX, exitY);
		game.batch.end();
		
		if(Gdx.input.isTouched()){
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(touchPos);
			if(soundRec.contains(touchPos.x, touchPos.y)){
				this.dispose();
				game.setScreen(new SoundOptionsScreen());
				return;
			}
			if(languageRec.contains(touchPos.x, touchPos.y)){
				this.dispose();
				game.setScreen(new LanguageOptionsScreen());
				return;
			}
			if(exitRec.contains(touchPos.x, touchPos.y)){
				this.dispose();
				game.setScreen(new MainMenuScreen(game));
				return;
			}
		}
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
		backgroundImg.dispose();
		soundImg.dispose();
		languageImg.dispose();
		backtomenuImg.dispose();
	}

}
