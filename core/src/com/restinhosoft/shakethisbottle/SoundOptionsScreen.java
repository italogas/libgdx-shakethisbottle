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
public class SoundOptionsScreen implements Screen {

	private ShakeThisBottle game;
	private OrthographicCamera cam;
	private Texture backgroundImg;
	private Texture onOffImg;
	private Texture offOnImg;
	private Texture soundImg;
	private int soundX;
	private int soundY;
	private Texture volumeImg;
	private Texture musicImg;
	private int volumeX;
	private int volumeY;
	private int musicX;
	private int musicY;
	private int onOffX;
	private int onOffY;
	private int offOnX;
	private int offOnY;
	private Rectangle onOffRec;
	private Rectangle offOnRec;
	private Texture backtomenuImg;
	private int backX;
	private int backY;
	private Rectangle backRec;

	public SoundOptionsScreen(ShakeThisBottle game) {
		this.game = game;
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 320, 480);
		
		backgroundImg =  new Texture(Gdx.files.internal("menu.png"));
		soundImg =  new Texture(Gdx.files.internal("sound.png"));
		volumeImg =  new Texture(Gdx.files.internal("volume.png"));
		musicImg =  new Texture(Gdx.files.internal("music.png"));
		onOffImg =  new Texture(Gdx.files.internal("onoff.png"));
		offOnImg =  new Texture(Gdx.files.internal("offon.png"));
		backtomenuImg =  new Texture(Gdx.files.internal("backtomenu.png"));
		
		soundX = 8;
		soundY = 480 / 2 -  soundImg.getHeight() / 2;
		volumeX= 8;
		volumeY = soundY -  (volumeImg.getHeight() + 16);
		musicX = 8;
		musicY = volumeY -  (musicImg.getHeight()  + 16);
		onOffX = 320 - onOffImg.getWidth();
		onOffY = 480 / 2 -  onOffImg.getHeight() / 2;
		offOnX = 320 - offOnImg.getWidth();
		offOnY = onOffY - (offOnImg.getHeight() + 16);
		backX = 320/2 - backtomenuImg.getWidth()/2;
		backY = 32;
		
		onOffRec = new Rectangle();
		onOffRec.set(onOffX, onOffY, onOffImg.getWidth(), onOffImg.getHeight());
		offOnRec = new Rectangle();
		offOnRec.set(offOnX, offOnY, offOnImg.getWidth(), offOnImg.getHeight());
		backRec = new Rectangle();
		backRec.set(backX, backY, backtomenuImg.getWidth(), backtomenuImg.getHeight());
		
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
		game.batch.draw(backgroundImg, 0, 0);
		game.batch.draw(soundImg, soundX, soundY);
		game.batch.draw(musicImg, musicX, musicY);
		game.batch.draw(musicImg, musicX, musicY);
		game.batch.draw(volumeImg, volumeX, volumeY);
		game.batch.draw(onOffImg, onOffX, onOffY);
		game.batch.draw(offOnImg, offOnX, offOnY);
		game.batch.draw(backtomenuImg, backX, backY);
		game.batch.end();
		
		if(Gdx.input.isTouched()){
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(touchPos);
			if(onOffRec.contains(touchPos.x, touchPos.y)){
				this.dispose();
				game.batch.begin();
				game.batch.draw(offOnImg, onOffX, onOffY);
				game.batch.end();
				return;
			}
			if(offOnRec.contains(touchPos.x, touchPos.y)){
				this.dispose();
				game.batch.begin();
				game.batch.draw(onOffImg, offOnX, offOnY);
				game.batch.end();
				return;
			}
			if(backRec.contains(touchPos.x, touchPos.y)){
				this.dispose();
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
	public void dispose() {}

}
