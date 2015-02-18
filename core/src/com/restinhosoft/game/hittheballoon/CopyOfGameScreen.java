package com.restinhosoft.game.hittheballoon;

import java.security.InvalidParameterException;
import java.util.Calendar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Array;
import com.restinhosoft.game.hittheballoon.Balloon.BallonColor;
import com.restinhosoft.player.PreferencesTent.GameManager;
import com.restinhosoft.shakethisbottle.ui.ShakeThisBottle;

public class CopyOfGameScreen implements Screen {

	private Stage stage;
	private ShakeThisBottle game;
	private BitmapFont bitmapFont;
	private Label label;
	
	@Override
	public void show() {
		game = (ShakeThisBottle) Gdx.app.getApplicationListener();
		
		stage = new Stage();
		stage.getViewport().setScreenSize(Gdx.graphics.getWidth(), Gdx.graphics.getWidth());
		Gdx.input.setInputProcessor(stage);
		
		bitmapFont = new BitmapFont(Gdx.files.internal("default.fnt"), false);
		
		LabelStyle labelStyle = new Label.LabelStyle();
		labelStyle.font = bitmapFont;
		labelStyle.fontColor= Color.BLACK;
		
		label = new Label("Score: " + GameManager.score, labelStyle);
		label.setPosition(0, Gdx.graphics.getHeight() - label.getHeight());
		
		GameManager.start();
		GameManager.resetScore();
		
		
		
		stage.addActor(label);
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
		update();
		
	}

	private void update() {
			
	}
	
	@Override
	public void hide() {}
	
	@Override
	public void resize(int width, int height) {}
	
	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void dispose() {
		stage.dispose();
	}
	
	private GameManager manager = new GameManager();
	public void save(){
		manager.saveScore();
	}
	
	public void load(){
		System.out.println(manager.loadScore());
	}
	/**
	 * It handles some game functionality
	 * @author Ã?talo
	 *
	 */
	public static class GameManager{
		
		//		game level
		private static String level;
		//	 the game score
		private static int score;
		//		the game mode
		private static boolean survival = false;;
		//	 the game ballons
		
		
		
		public static void start(){
			
			
			resetScore();
			
		}
		

		public static void resetScore() {
			setScore(0);
		}

		public static int getScore() {
			return score;
		}

		public static  void setScore(int score) {
			GameManager.score = score;
		}
		
		
		public static void saveScore(){
			String time = Calendar.getInstance().getTime().toString();
			try{
				System.out.println(Gdx.files);
				FileHandle local = Gdx.files.local("fidesu.txt");
				local.writeString("In " + time + ", Score: " + score, false);
			}  catch (RuntimeException re){
				System.err.println(re.getMessage());
			}
		}
		
		public static String loadScore(){
			String readString = null;
			try {
				FileHandle local = Gdx.files.local("fidesu.txt");
				readString = local.readString();
			} catch (RuntimeException re){
				System.err.println(re.getMessage());
			}
			return readString;
			
		}
		
		public static String getLevel() {
			return level;
		}

		public static void setLevel(String string) {
			
		}

		public static void setSurvivalMode() {
			survival = true;
			
		}

	}
	
		
}
