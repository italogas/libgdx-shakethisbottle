/**
 * 
 */
package com.restinhosoft.shakethisbottle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * @author Ítalo
 *
 */
public class MainMenuScreen implements Screen {

	final ShakeThisBottle game;
	private OrthographicCamera cam;
	private Texture menuImg;
	private Texture startButtonImg;
	private Texture optionsButtonImg;
	private Rectangle startButtonArea;
	private int startX;
	private int startY;
	private int optionsX;
	private int optionsY;
	private Rectangle optionsButtonArea;

	/**
	 * @param shakeThisBottle 
	 * 
	 */
	public MainMenuScreen(final ShakeThisBottle game) {
		this.game = game;
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 320, 480);
		
		menuImg = new Texture(Gdx.files.internal("menu.png"));
		startButtonImg = new Texture(Gdx.files.internal("play.png"));
		optionsButtonImg = new Texture(Gdx.files.internal("options.png"));
		
		startX = 320 / 2 -  startButtonImg.getWidth() / 2;
		startY = 480 / 2 +  startButtonImg.getHeight() / 2;
		
		optionsX = 320 / 2 -  optionsButtonImg.getWidth() / 2;
		optionsY = 480 / 2 -  (optionsButtonImg.getHeight() / 2 + 16); 
		
		startButtonArea = new Rectangle(startX, startY, startButtonImg.getWidth(), startButtonImg.getWidth());
		optionsButtonArea = new Rectangle(optionsX, optionsY, optionsButtonImg.getWidth(), optionsButtonImg.getWidth());
		
		
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#show()
	 */
	@Override
	public void show() {
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#render(float)
	 */
	@Override
	public void render(float delta) {
		GL20 gl = Gdx.gl;
		gl.glClearColor(1, 0, 0, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		cam.update();
		game.batch.setProjectionMatrix(cam.combined);
		game.batch.begin();
		game.batch.draw(menuImg, 0, 0);
		game.batch.draw(startButtonImg, startX, startY);
		game.batch.draw(optionsButtonImg, optionsX, optionsY);
		game.batch.end();
		
		if(Gdx.input.isTouched()){
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(touchPos);
			if(startButtonArea.contains(touchPos.x, touchPos.y)){
				game.setScreen(new GameSelectionScreen(game));
				return;
			}
			if(optionsButtonArea.contains(touchPos.x, touchPos.y)){
				game.setScreen(new OptionsScreen(game));
				return;
			}
		}

	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#resize(int, int)
	 */
	@Override
	public void resize(int width, int height) {
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#pause()
	 */
	@Override
	public void pause() {
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#resume()
	 */
	@Override
	public void resume() {
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#hide()
	 */
	@Override
	public void hide() {
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#dispose()
	 */
	@Override
	public void dispose() {
		menuImg.dispose();
		startButtonImg.dispose();
		optionsButtonImg.dispose();

	}

}
