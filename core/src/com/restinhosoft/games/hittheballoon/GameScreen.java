package com.restinhosoft.games.hittheballoon;

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
import com.restinhosoft.games.hittheballoon.Balloon.BallonColor;
import com.restinhosoft.shakethisbottle.ui.ShakeThisBottle;
import com.restinhosoft.ui.menus.ScoreScreen;

public class GameScreen implements Screen {

	
	private Stage stage;
	private Balloon balloon;
	private ShakeThisBottle game;
	private TimeHelper timeHelper;
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
		
		timeHelper = new TimeHelper();
		
		balloon = GameManager.getBalloon();
		balloon.setVisible(true);
		balloon.setTouchable(Touchable.enabled);
		
		stage.addActor(balloon);
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
		if(balloon.isTouched()){
			
			//		it adds the balloon points to the game score
			int points = balloon.getBalloonPoints();
			GameManager.setScore(GameManager.getScore() + points);
			label.setText("Score: " + Integer.toString(GameManager.getScore() + points));
			
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
		
		//		it handles the survival game mode
		if(GameManager.survival && (timeHelper.getElapsedTimeInSeconds()%10==0)){
			String level = GameManager.getLevel();
			if(level.toUpperCase().equals("EASY")){
				GameManager.setLevel("normal");
			} else if (level.toUpperCase().equals("NORMAL")){
				GameManager.setLevel("hard");
			} else if (level.toUpperCase().equals("HARD")){
				GameManager.setLevel("insanel");
			} else if (level.toUpperCase().equals("INSANE")){
				GameManager.setLevel("easy");
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
	 * @author Ã�talo
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
		private static Array<Balloon> balloon_array = new Array<Balloon>();
		
		
		
		public static void start(){
			
			setBalloonArray();
			
			resetScore();
			
		}
		
		private static void setBalloonArray() {
			balloon_array.add(new Balloon(BallonColor.BLUE));
			balloon_array.add(new Balloon(BallonColor.GREEN));
			balloon_array.add(new Balloon(BallonColor.ORANGE));
			balloon_array.add(new Balloon(BallonColor.PINK));
			balloon_array.add(new Balloon(BallonColor.PURPLE));
			balloon_array.add(new Balloon(BallonColor.RED));
			balloon_array.add(new Balloon(BallonColor.YELLOW));
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
			setBalloonArray();
			setLevel(level);
			balloon_array.shuffle();
			return balloon_array.first();
		}
		
		public static void saveScore(){
			String time = Calendar.getInstance().getTime().toString();
			try{
				ScoreScreen scoreScreen = new ScoreScreen();
				scoreScreen.addScore("HIT THE BALLOON", score);
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
		
		public static String getLevel() {
			return level;
		}

		public static void setLevel(String string) {
			level = string; 
			if(string.toUpperCase().equals("EASY")){
				for(Balloon b : balloon_array){
					b.setMotionRate(Balloon.LOW_ACCEL);
				}
			} else if (string.toUpperCase().equals("NORMAL")){
				for(Balloon b : balloon_array){
					b.setMotionRate(Balloon.NORMAL_ACCEL);
				}
			} else if (string.toUpperCase().equals("HARD")){
				for(Balloon b : balloon_array){
					b.setMotionRate(Balloon.FAST_ACCEL);
				}
			} else if (string.toUpperCase().equals("INSANE")){
				for(Balloon b : balloon_array){
					b.setMotionRate(Balloon.VERY_FAST_ACCEL);
				}
			} else {
				throw  new InvalidParameterException("Invalid level selected.");
			}
			
		}

		public static void setSurvivalMode() {
			survival = true;
			
		}

	}
	
		
}
