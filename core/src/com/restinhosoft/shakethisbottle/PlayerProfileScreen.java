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
public class PlayerProfileScreen implements Screen {

	private ShakeThisBottle game;
	private OrthographicCamera cam;
	
	private Texture screenBackground;
	private Texture backgroundButton;
	
	private int scoreButtonPositionX;
	private int scoreButtonPositionY;
	
	private int achievementsButtonPositionX;
	private int achievementsButtonPositionY;
	
	private int backButtonPositionX;
	private int backButtonPositionY;
	
	private Rectangle scoresRec;
	private Rectangle achievementsRec;
	private Rectangle backgroundbuttonRec;
	

	public PlayerProfileScreen(ShakeThisBottle game) {
		this.game = game;
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 320, 480);
		
		//texturas dos botoes
		
		screenBackground = new Texture(Gdx.files.internal("background_profile_screen.png"));
		backgroundButton = new Texture(Gdx.files.internal("background_button.png"));
		
		//alinhamento dos botoes na tela
		
		scoreButtonPositionX = 60;//alinhamento a esquerda
		scoreButtonPositionY = 480/2 - backgroundButton.getHeight()/2;//Alinhamento no centro da tela eixo y
		
		achievementsButtonPositionX = 60;
		achievementsButtonPositionY = scoreButtonPositionY - (backgroundButton.getHeight()/2 + 50);//alinha com o anterior e da um espacamento de 20 pixels
		
		backButtonPositionX = 60;
		backButtonPositionY = achievementsButtonPositionY - (backgroundButton.getHeight()/2 + 50);
		
		//colisao touch dos botoes
		
		scoresRec = new Rectangle();
		scoresRec.set(scoreButtonPositionX, scoreButtonPositionY, 
					  backgroundButton.getWidth(), backgroundButton.getHeight());//area de colisao de score
		
		achievementsRec = new Rectangle();
		achievementsRec.set(achievementsButtonPositionX, achievementsButtonPositionY,
							backgroundButton.getWidth(), backgroundButton.getWidth());//area de colisao achievement
		
		backgroundbuttonRec = new Rectangle();
		backgroundbuttonRec.set(backButtonPositionX, backButtonPositionY,
								backgroundButton.getWidth(), backgroundButton.getWidth());//area de colisao de voltar
		
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
		game.batch.draw(screenBackground, 0, 0);
		game.batch.draw(backgroundButton, scoreButtonPositionX, scoreButtonPositionY);
		game.batch.draw(backgroundButton, achievementsButtonPositionX, achievementsButtonPositionY);
		game.batch.draw(backgroundButton, backButtonPositionX, backButtonPositionY);
		game.batch.end();
		
		if(Gdx.input.isTouched()){
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(touchPos);
			if(scoresRec.contains(touchPos.x, touchPos.y)){
				this.dispose();
				game.setScreen(new SoundOptionsScreen(game));
				try {
					this.finalize();
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			}
			if(achievementsRec.contains(touchPos.x, touchPos.y)){
				this.dispose();
				game.setScreen(new GameSelectionScreen(game));
				try {
					this.finalize();
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			}
			if(backgroundbuttonRec.contains(touchPos.x, touchPos.y)){
				this.dispose();
				game.setScreen(new MainMenuScreen(game));
				try {
					this.finalize();
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
