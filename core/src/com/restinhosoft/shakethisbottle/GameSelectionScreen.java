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
import com.badlogic.gdx.math.Vector3;

/**
 * @author Ítalo
 *
 */
public class GameSelectionScreen implements Screen {

	private ShakeThisBottle game;
	private OrthographicCamera cam;
	private Texture background;
	private Texture button;
	private Texture backtomenu;
	private Rectangle backButtonArea;
	private float backX;
	private float backY;

	public GameSelectionScreen(ShakeThisBottle game) {
		this.game = game;
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 320, 480);
		
		background = new Texture(Gdx.files.internal("menu.png"));
		button = new Texture(Gdx.files.internal("shakeitbutton.png"));
		backtomenu = new Texture(Gdx.files.internal("backtomenu.png"));
		
		backX = 320/2 - backtomenu.getWidth()/2;
		backY = 16;
		
		backButtonArea = new Rectangle(backX, backY, backtomenu.getWidth(), backtomenu.getWidth());
		
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
		gl.glClearColor(1, 0, 0, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		cam.update();
		game.batch.setProjectionMatrix(cam.combined);
		game.batch.begin();
		
		game.batch.draw(background, 0, 0);
		game.batch.draw(button, 32, 480- 480/3 - button.getHeight());
		game.batch.draw(button, 32 + button.getWidth() + 8, 480 - 480/3 -button.getHeight());
		game.batch.draw(button, 32 + 2*(button.getWidth() + 8), 480 - 480/3 -button.getHeight());
		
		game.batch.draw(button, 32, 480 - 480/3 - (button.getHeight()+8)*2);
		game.batch.draw(button, 32 + button.getWidth() + 8, 480 - 480/3 - (button.getHeight()+8)*2);
		game.batch.draw(button, 32 + 2*(button.getWidth() + 8), 480 - 480/3 - (button.getHeight()+8)*2);
		
		game.batch.draw(button, 32, 480 - 480/3 - (button.getHeight()+8)*3);
		game.batch.draw(button, 32 + button.getWidth() + 8, 480 - 480/3 - (button.getHeight()+8)*3);
		game.batch.draw(button, 32 + 2*(button.getWidth() + 8), 480 - 480/3 - (button.getHeight()+8)*3);
		
		game.batch.draw(backtomenu, backX, backY);
		
		game.batch.end();
		
		if(Gdx.input.isTouched()){
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(touchPos);
			if(backButtonArea.contains(touchPos.x, touchPos.y)){
				game.setScreen(new MainMenuScreen(game));
				return;
			}
		}

	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#resize(int, int)
	 */
	@Override
	public void resize(int width, int height) {}

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
		button.dispose();
		backtomenu.dispose();
	}

}
