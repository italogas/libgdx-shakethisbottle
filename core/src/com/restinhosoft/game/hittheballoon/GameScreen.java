package com.restinhosoft.game.hittheballoon;

import java.util.Calendar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.restinhosoft.game.hittheballoon.Balloon.BallonColor;
import com.restinhosoft.shakethisbottle.ui.ShakeThisBottle;

public class GameScreen implements Screen {

	
	private Stage stage;
	private Balloon balloon;
	private ShakeThisBottle game;
	
	@Override
	public void show() {
		game = (ShakeThisBottle) Gdx.app.getApplicationListener();
		
		stage = new Stage();
		stage.getViewport().setScreenSize(Gdx.graphics.getWidth(), Gdx.graphics.getWidth());
		Gdx.input.setInputProcessor(stage);
		
		GameManager.start();
		
		balloon = GameManager.getBalloon();
		balloon.setVisible(true);
		balloon.setTouchable(Touchable.enabled);
		
		stage.addActor(balloon);
		
		GameManager.resetScore();
		
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
		if(balloon.isTouched()){
			
			//		it adds the balloon points to the game score
			int points = balloon.getBalloonPoints();
			GameManager.setScore(GameManager.getScore() + points);
			
			//	 it creates a new balloon and adds it to the game
			balloon = GameManager.getBalloon();
			balloon.setVisible(true);
			balloon.setTouchable(Touchable.enabled);
			stage.addActor(balloon);
			
		} else {
			if(balloon.isOut()){
				GameManager.saveScore();
				game.setScreen(new GameOverScreen());
			}
		}
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
	
	/**
	 * It handles some game functionality
	 * @author Ítalo
	 *
	 */
	public static class GameManager{
		
		//	 the game score
		private static int score;
		//	 the game ballons
		private static Array<Balloon> balloon_array;
		
		public static void start(){
			
			balloon_array = new Array<Balloon>();
			balloon_array.add(new Balloon(BallonColor.BLUE));
			balloon_array.add(new Balloon(BallonColor.GREEN));
			balloon_array.add(new Balloon(BallonColor.ORANGE));
			balloon_array.add(new Balloon(BallonColor.PINK));
			balloon_array.add(new Balloon(BallonColor.PURPLE));
			balloon_array.add(new Balloon(BallonColor.RED));
			balloon_array.add(new Balloon(BallonColor.YELLOW));
			
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
		
		public static Balloon getBalloon() {
			balloon_array.shuffle();
			return balloon_array.first();
		}
		
		public static void saveScore(){
			String time = Calendar.getInstance().getTime().toString();
			try{
				FileHandle local = Gdx.files.local("scoreData.txt");
				local.writeString("In " + time + ", Score: " + score, false);
			}  catch (RuntimeException re){
				System.err.println(re.getMessage());
			}
		}
		
		public static String loadScore(){
			String readString = null;
			try {
				FileHandle local = Gdx.files.local("scoreData.txt");
				readString = local.readString();
			} catch (RuntimeException re){
				System.err.println(re.getMessage());
			}
			return readString;
			
		}

	}
	
		
}
