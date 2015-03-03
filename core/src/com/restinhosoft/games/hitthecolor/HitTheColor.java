package com.restinhosoft.games.hitthecolor;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.restinhosoft.games.hittheballoon.GameScreen.GameManager;
import com.restinhosoft.options.LanguageManager;
import com.restinhosoft.player.ScoresManager;
import com.restinhosoft.shakethisbottle.ui.ShakeThisBottle;

public class HitTheColor implements Screen {
	
	private Stage stage;
	private Table table;
	private ShakeThisBottle game;
	private BitmapFont bitmapFont;
	private Label label;
	
	private static class GameManager{
		public static int score;
		public int level;
		public int difficulty;
		public boolean survival;
		
		public static ArrayList<Colours> colors = new ArrayList<Colours>();
		
		public LanguageManager languageManager;
		public String language;
		public String name;
		
		
		public void LanguageManagerInstance(){
			languageManager = LanguageManager.getInstance();
			try {
				language = languageManager.getLanguage();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
				
		public GameManager(int score, int level,int difficulty, boolean survival){
			this.score = score;
			this.level = level;
			this.difficulty = difficulty;
			this.survival = survival;
			
			LanguageManagerInstance();
			
			if(language.equals(languageManager.languageEN))	this.name = "HIT THE COLOR";
			else this.name = "ACERTE NA COR";
			
			fillGameColours();
			setColoursPositions();
		}
		
		private boolean pause;
		private int scoreNow;
		private final int ncolors = 14;
		private static ScoresManager scoreFile = new ScoresManager("");
		
		private void fillGameColours(){
			for(int i=0;i<ncolors;i++){
				colors.add(new Colours(0,0));
				colors.get(i).setTouchable(Touchable.enabled);
			}
		}
		
		private void setColoursPositions(){
			colors.get(0).setPosition(192, 64);
			colors.get(1).setPosition(192, 128);
			colors.get(2).setPosition(192, 192);
			
			colors.get(3).setPosition(260, 32);
			colors.get(4).setPosition(260, 96);
			colors.get(5).setPosition(260, 160);
			colors.get(6).setPosition(260, 224);
			
			colors.get(7).setPosition(328, 64);
			colors.get(8).setPosition(328, 128);
			colors.get(9).setPosition(328, 192);
		}
	
			
		private void saveScore(){
			scoreFile.saveUniqueScore(name, score);
		}
		
		
	}
	
	
	
	@Override
	public void show() {
		game = (ShakeThisBottle) Gdx.app.getApplicationListener();
		
		stage = new Stage();
		stage.getViewport().setScreenSize(Gdx.graphics.getWidth(), Gdx.graphics.getWidth());
		Gdx.input.setInputProcessor(stage);
		
		table = new Table();
		//table.setFillParent(true);
		
		bitmapFont = new BitmapFont(Gdx.files.internal("default.fnt"), false);
		
		LabelStyle labelStyle = new Label.LabelStyle();
		labelStyle.font = bitmapFont;
		labelStyle.fontColor= Color.BLACK;
		
		label = new Label("Score: " + GameManager.score, labelStyle);
		label.setPosition(0, Gdx.graphics.getHeight() - label.getHeight());
		
		gm = new GameManager(0, 1, 1, false);
		
/*		for(int i=0;i<gm.colors.size();i++){
			stage.addActor(gm.colors.get(i));
		}*/
		/*gm.colors.get(0).setTouchable(Touchable.enabled);
		gm.colors.get(1).setTouchable(Touchable.enabled);
		gm.colors.get(2).setTouchable(Touchable.enabled);
		gm.colors.get(3).setTouchable(Touchable.enabled);
		gm.colors.get(4).setTouchable(Touchable.enabled);
		gm.colors.get(5).setTouchable(Touchable.enabled);
		gm.colors.get(6).setTouchable(Touchable.enabled);
		gm.colors.get(7).setTouchable(Touchable.enabled);
		gm.colors.get(8).setTouchable(Touchable.enabled);
		gm.colors.get(9).setTouchable(Touchable.enabled);*/
		
		timer();
		
		
		
		/*table.setBounds(32, 192, 256, 192);
		table.setOrigin(32, 288);
		table.align(Align.center);
		table.add(gm.colors.get(0)).;
		table.add(gm.colors.get(1)).align(Align.center);
		table.add(gm.colors.get(2)).align(Align.right);
		//table.add("Fim");
		/*table.row();
		table.add(gm.colors.get(3));
		table.add(gm.colors.get(4));
		table.add(gm.colors.get(5));
		table.add(gm.colors.get(6));
		table.row();
		table.add(gm.colors.get(7));
		table.add(gm.colors.get(8));
		table.add(gm.colors.get(9));*/
		
		//stage.addActor(label);
		//stage.addActor(table);
		//stage.getActors().get(0).setOrigin(32, 288);
		
		
		
		
		stage.addActor(gm.colors.get(0));
		
		stage.getActors().get(0).setOrigin(32, 288);
		//stage.getActors().get(0).setColor(Color.BLUE);
		stage.getActors().get(0).setBounds(32, 288, 64, 64);
		//stage.getActors().get(0).setPosition(32, 288);
		stage.getActors().get(0).addCaptureListener(new InputListener(){
				@Override
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					((Colours) event.getTarget()).setVisible(false);
					System.out.println("ok");
					return true;
				}
			
		});
		

		/*stage.addActor(gm.colors.get(1));
		stage.addActor(gm.colors.get(2));
		stage.addActor(gm.colors.get(3));
		stage.addActor(gm.colors.get(4));
		stage.addActor(gm.colors.get(5));
		stage.addActor(gm.colors.get(6));
		stage.addActor(gm.colors.get(7));
		stage.addActor(gm.colors.get(8));
		stage.addActor(gm.colors.get(9));*/
		
		/*stage.addActor(label);
		
		stage.getActors().get(0).setPosition(gm.colors.get(0).getPositionY(), gm.colors.get(0).getPositionX());
		stage.getActors().get(1).setPosition(gm.colors.get(1).getPositionY(), gm.colors.get(1).getPositionX());
		stage.getActors().get(2).setPosition(gm.colors.get(2).getPositionY(), gm.colors.get(2).getPositionX());
		stage.getActors().get(3).setPosition(gm.colors.get(3).getPositionY(), gm.colors.get(3).getPositionX());
		stage.getActors().get(4).setPosition(gm.colors.get(4).getPositionY(), gm.colors.get(4).getPositionX());
		stage.getActors().get(5).setPosition(gm.colors.get(5).getPositionY(), gm.colors.get(5).getPositionX());
		stage.getActors().get(6).setPosition(gm.colors.get(6).getPositionY(), gm.colors.get(6).getPositionX());
		stage.getActors().get(7).setPosition(gm.colors.get(7).getPositionY(), gm.colors.get(7).getPositionX());
		stage.getActors().get(8).setPosition(gm.colors.get(8).getPositionY(), gm.colors.get(8).getPositionX());
		stage.getActors().get(9).setPosition(gm.colors.get(9).getPositionY(), gm.colors.get(9).getPositionX());
		//GameManager(0,1,1,false);*/
		
	}

	private GameManager gm;
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
		update();
		
		touchedTest();
	
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

	
	private Timer counterTimer;
	private TimerTask task;
	private final long twoSeconds =222000;
	
	private int timer = 0; 
	
	private void timer(){
		counterTimer = new Timer();
		
		this.task = new TimerTask() {  
            public void run() {  
                try { 
                	if(timer>100){	timer = 0; 	}
                	timer++;
                } catch (Exception e) {e.printStackTrace();}  
           }  
		};
		counterTimer.scheduleAtFixedRate(task, twoSeconds, twoSeconds);
	}
	
	private void update() {
		if(timer%2==0){
			for(int i=0;i<14;i++){
				gm.colors.get(i).setVisible(true);
				gm.colors.get(i).setColorRan();
				gm.colors.get(i).setTexture();
			}
			for(int i=0;i<5;i++){
				Random rn = new Random();
				int pos = rn.nextInt(10);
				gm.colors.get(pos).setVisible(false);
			}
		}
	}
	
	private void touchedTest(){
		if(gm.colors.get(0).isTouched()) System.out.println("zero");
		if(gm.colors.get(1).isTouched()) System.out.println("um");
		if(gm.colors.get(2).isTouched()) System.out.println("dois");
		if(gm.colors.get(3).isTouched()) System.out.println("tres");
		if(gm.colors.get(4).isTouched()) System.out.println("quat");
		if(gm.colors.get(5).isTouched()) System.out.println("cinc");
		if(gm.colors.get(6).isTouched()) System.out.println("seis");
		if(gm.colors.get(7).isTouched()) System.out.println("sete");
		if(gm.colors.get(8).isTouched()) System.out.println("oito");
		if(gm.colors.get(9).isTouched()) System.out.println("nove");
	}
}

