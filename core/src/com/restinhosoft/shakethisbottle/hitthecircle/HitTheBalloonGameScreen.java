/**
 * 
 */
package com.restinhosoft.shakethisbottle.hitthecircle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.restinhosoft.shakethisbottle.ui.ShakeThisBottle;

/**
 * @author �talo
 *
 */
public class HitTheBalloonGameScreen extends ScreenAdapter {

	private OrthographicCamera cam;
	@SuppressWarnings("unused")
	private Vector3 touchPoint;
	@SuppressWarnings("unused")
	private Texture red_balloon;
	/**
	 * 
	 */
	public HitTheBalloonGameScreen(ShakeThisBottle game) {
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 320, 480);
		touchPoint = new Vector3();
		
//		circle = new Texture(Gdx.files.internal("circle.png"));
//		square = new Texture(Gdx.files.internal("square.png"));
		red_balloon = new Texture(Gdx.files.internal("red_balloon.png"));
		red_balloon = new Texture(Gdx.files.internal("blue_balloon.png"));
		red_balloon = new Texture(Gdx.files.internal("green_balloon.png"));
		red_balloon = new Texture(Gdx.files.internal("yellow_balloon.png"));
		red_balloon = new Texture(Gdx.files.internal("purple_balloon.png"));
		red_balloon = new Texture(Gdx.files.internal("red_balloon.png"));
		red_balloon = new Texture(Gdx.files.internal("red_balloon.png"));
		
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		super.render(delta);
	}
	
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		super.pause();
	}

}