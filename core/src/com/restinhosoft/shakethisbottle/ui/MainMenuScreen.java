/**
 * 
 */
package com.restinhosoft.shakethisbottle.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * @author Ítalo
 *
 */
public class MainMenuScreen implements Screen {

	final ShakeThisBottle game;
	private OrthographicCamera cam;
	private Texture menuImg;
	private Texture selectGameButtonImg;
	private Texture optionsButtonImg;
	private Texture exitButtonImg;
	private Rectangle startButtonArea;
	private Rectangle optionsButtonArea;
	private Rectangle exitButtonArea;
	private int selectX;
	private int selectY;
	private int optionsX;
	private int optionsY;
	private int exitX;
	private int exitY;
	private Texture registerButtonImg;
	private int registerX;
	private int registerY;
	private Rectangle registerButtonArea;

	/**
	 * @param shakeThisBottle 
	 * 
	 */
	public MainMenuScreen(final ShakeThisBottle game) {
		this.game = game;
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 320, 480);
		
		menuImg = new Texture(Gdx.files.internal("menu.png"));
		selectGameButtonImg = new Texture(Gdx.files.internal("selectgame.png"));
		optionsButtonImg = new Texture(Gdx.files.internal("options.png"));
		registerButtonImg = new Texture(Gdx.files.internal("register.png"));
		exitButtonImg = new Texture(Gdx.files.internal("exit.png"));
		
		selectX = 320 / 2 -  selectGameButtonImg.getWidth() / 2;
		selectY = 480 / 2 +  selectGameButtonImg.getHeight() / 2;
		
		optionsX = 320 / 2 -  optionsButtonImg.getWidth() / 2;
		optionsY = selectY - optionsButtonImg.getHeight() - 16;
		
		registerX = 320 / 2 -  registerButtonImg.getWidth() / 2;
		registerY = optionsY - registerButtonImg.getHeight() - 16;
		
		exitX = 320 / 2 -  exitButtonImg.getWidth() / 2;
		exitY = registerY - exitButtonImg.getHeight() - 16;
		
		startButtonArea = new Rectangle(selectX, selectY, selectGameButtonImg.getWidth(), selectGameButtonImg.getWidth());
		optionsButtonArea = new Rectangle(optionsX, optionsY, optionsButtonImg.getWidth(), optionsButtonImg.getWidth());
		exitButtonArea = new Rectangle(exitX, exitY, exitButtonImg.getWidth(), exitButtonImg.getWidth());
		registerButtonArea = new Rectangle(registerX, registerY, registerButtonImg.getWidth(), registerButtonImg.getWidth());
		
		
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
		game.batch.draw(selectGameButtonImg, selectX, selectY);
		game.batch.draw(optionsButtonImg, optionsX, optionsY);
		game.batch.draw(registerButtonImg, registerX, registerY);
		game.batch.draw(exitButtonImg, exitX, exitY);
		game.batch.end();
		
		if(Gdx.input.isTouched()){
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(touchPos);
			if(startButtonArea.contains(touchPos.x, touchPos.y)){
				this.dispose();
				game.setScreen(new GameSelectionScreen(game));
				return;
			}
			if(optionsButtonArea.contains(touchPos.x, touchPos.y)){
				this.dispose();
				game.setScreen(new OptionsScreen(game));
				return;
			}
			if(registerButtonArea.contains(touchPos.x, touchPos.y)){
				this.dispose();
				game.setScreen(new PlayerProfileScreen(game));
			}
			if(exitButtonArea.contains(touchPos.x, touchPos.y)){
//				System.exit(0);
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
		selectGameButtonImg.dispose();
		optionsButtonImg.dispose();
		registerButtonImg.dispose();
		exitButtonImg.dispose();

	}

}
