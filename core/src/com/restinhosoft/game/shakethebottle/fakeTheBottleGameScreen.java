/**
 * 
 */
package com.restinhosoft.game.shakethebottle;

import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
//import jdk.nashorn.internal.runtime.regexp.joni.Config;
//import android.hardware.Sensor;
//import android.hardware.SensorEvent;
//import android.hardware.SensorEventListener;
//import android.hardware.SensorManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.restinhosoft.main.AchievementsManager;
import com.restinhosoft.main.AudioManager;
import com.restinhosoft.main.LanguageManager;
import com.restinhosoft.main.MiniGamesIF;
import com.restinhosoft.main.ScoresManager;
import com.restinhosoft.main.ShakeThisBottle;
import com.restinhosoft.ui.AuxScreenCreation;


/**
 * @author Mailson
 *
 */
public class fakeTheBottleGameScreen implements Screen, MiniGamesIF,ApplicationListener{
	//********************************Novo**************************
	private final int max = 30;
	
	private final String defaultDifficulty = "normal";
	
	private final BitmapFont bitmapFont = new BitmapFont(Gdx.files.internal("default.fnt"));
	
	private int level = 0;
	private int score = 0;
	private int bonus = 0;
	private int next = 0;
	
	private int shakes = 0;
	
	private boolean survival = false;
	private boolean pause = false;
	private boolean start = false;
	private boolean gameover = false;
	
	private String difficulty = defaultDifficulty;
	
	private final AuxScreenCreation creating = new AuxScreenCreation();

	private TextButton title;
	private TextButton scoreBt;
	private TextButton levelBt;
	private TextButton startBt;
	private TextButton pauseBt;
	private TextButton timerBt;
	private TextButton garrafa;
	private TextButton back;
	
	private LanguageManager languageManager;
	public String language;
	
	private AudioManager audioManager;
	
	private AchievementsManager aManager = new AchievementsManager();
	
	private void LangInstance(){
		languageManager = LanguageManager.getInstance();
		try {
			language = languageManager.getLanguage();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	private void saveScore(int score){
		LangInstance();
		String temp = language;
		try {
			languageManager.setLanguage("engl");
			new ScoresManager("scores_eng.txt").saveDefaultMultipleScore("NOVA GENIUS", score);
			languageManager.setLanguage("ptbr");
			new ScoresManager("scores_pt.txt").saveDefaultMultipleScore("NOVA GENIUS", score);
			languageManager.setLanguage(temp);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	

	private Timer counterTimerNo;
	private TimerTask taskNo;
	private final long seconds = 1000;
	private final long twoSeconds = 2000;
	
	private int timerDefault = 0;
	private int timerChange = 0;
	private int timerNo = 0; 
	
	private void timerChange(){
		counterTimerNo = new Timer();
		
		this.taskNo = new TimerTask() {  
            public void run() {  
                try {
                	if(timerChange==1 && !pause){	
                		timerChange++;
                	}
                	else timerChange++;
                } catch (Exception e) {System.err.println(e.getMessage());;}  
           }  
		};
		if(!pause && start)	counterTimerNo.scheduleAtFixedRate(taskNo, twoSeconds, twoSeconds);
	}
	
	private void timer(){
		counterTimerNo = new Timer();
		
		this.taskNo = new TimerTask() {  
            public void run() {  
                try {
                	if(!pause) 	timerNo--;
                } catch (Exception e) {System.err.println(e.getMessage());;}  
           }  
		};
		if(!pause && start)	counterTimerNo.scheduleAtFixedRate(taskNo, seconds, seconds);
	}
	
	private void estimateTime(){
		if(difficulty.equals("easy"))   timerNo =25;
		if(difficulty.equals("normal")) timerNo =20;
		if(difficulty.equals("hard"))   timerNo =12;
		if(difficulty.equals("insane")) timerNo =8;
		timerDefault = timerNo;
	}

	
	private void levelup(){
		next=0;
		shakes=0;
		timerChange = 0;
		estimateTime();
		saveScore(score);
		level++;
	}
	
	private void changeLevel(){
		if(max==shakes) {
			levelup();
			try{
				Gdx.input.vibrate(500);	
			}catch(Exception e){
				
			}
			
			audioManager.getSoundtrack().get(0).play(); 
		}
		if(level==10){
			aManager.addAchievement("superShake", "Level 10 Shake the bottle");
		}if(level>=20){
			aManager.addAchievement("Lucky", "FINAL LEVEL");
		}
		
	}
	
	private float minimalX =4.0f;
		
	ShakeThisBottle game;
	
	private OrthographicCamera camera;
	
	private Texture background;
	
	private Stage stage;
	
	private TextureAtlas atlasBottle;
	
	private Skin bottleSkin;
	private Table gameTextTable;
		
	@SuppressWarnings("unused")
	private TextButton spaceBT;
	private TextButton timerBT;
	
	private TextButton bottleBT;
	
	private TextButtonStyle bottleStyle;
	private TextButtonStyle bottleStyle2;
	
	private int width = 320;
	private int height= 480;
	
	@SuppressWarnings("unused")
	private float move;
	
	private TextureAtlas creatingAtlas(String file){ 
		return new TextureAtlas(Gdx.files.internal(file));
	}
	
	private Skin creatingSkin(TextureAtlas atlas){ 
		return new Skin(atlas);
	}
	
	private TextButton creatingTextButton(String text,TextButtonStyle style, boolean disable){
		TextButton button = new TextButton(text, style);
		button.setDisabled(disable);
		return button;
	}
	
	private TextButtonStyle creatingTextButtonStyles(Skin skin, String file, BitmapFont font){
		TextButtonStyle style = new TextButton.TextButtonStyle();
		style.up   = skin.getDrawable(file);
		style.down = skin.getDrawable(file);
		style.pressedOffsetX = 1;
		style.pressedOffsetY =-1;
		style.font = font;
		
		return style;
	}
	
	private Table creatingTable(int beginningX,int beginningY, int width, int height){
		Table table = new Table();
		table.setBounds(beginningX, beginningY, width, height);
		
		return table;
	}



	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#show()
	 */
	@Override
	public void show() {
		this.game = (ShakeThisBottle) Gdx.app.getApplicationListener();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, width, height);
		
		audioManager = new AudioManager("audio/gameselection.ogg");
		audioManager.addToSoundTrack("audio/clear.ogg");
		audioManager.addToSoundTrack("audio/botoes_first.mp3");
		audioManager.addToSoundTrack("audio/clear.ogg");
		
		estimateTime();
		
		stage = new Stage();
		
		Gdx.input.setInputProcessor(stage);
		
		background = new Texture(Gdx.files.internal("games/game_background.png"));
 
        this.atlasBottle=creatingAtlas( "games/garrafa.atlas");
		this.bottleSkin  =creatingSkin( this.atlasBottle);
		this.bottleStyle   =creatingTextButtonStyles( this.bottleSkin, "garrafa", bitmapFont);
		this.bottleStyle2   =creatingTextButtonStyles( this.bottleSkin, "garrafa_im", bitmapFont);

		this.bottleBT=creatingTextButton( "", this.bottleStyle, true);
		bottleBT.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(start){
					score+=13;
		        	shakes++;
					saveScore(score);
					if(bottleBT.getStyle().equals(bottleStyle))	bottleBT.setStyle(bottleStyle2);
					else if(bottleBT.getStyle().equals(bottleStyle2))	bottleBT.setStyle(bottleStyle);	
				}
				
				
			}
		});
		LangInstance();
		
		TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = bitmapFont;
		
		title = new TextButton((language.equals(languageManager.languageEN)?"SHAKE THE BOTTLE ":"AGITE A GARRAFA"), textButtonStyle);
		title.setDisabled(true);
		
		scoreBt = new TextButton((language.equals(languageManager.languageEN)?"SCORE: "+score:"PONTOS: "+score), textButtonStyle);
		scoreBt.setDisabled(true);
		
		levelBt = new TextButton((language.equals(languageManager.languageEN)?"LEVEL: "+level:"NIVEL: "+level), textButtonStyle);
		levelBt.setDisabled(true);
		
		timerBt = new TextButton((language.equals(languageManager.languageEN)?"TIME LEFT: "+timerNo:"TEMPO RESTANTE: "+timerNo), textButtonStyle);
		timerBt.setDisabled(true);
		
		startBt = new TextButton((language.equals(languageManager.languageEN)?"START":"INICIO"), textButtonStyle);
		startBt.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(start==false)start=true;
				timer();
				startBt.setColor(Color.GREEN);
				startBt.setDisabled(true);
			}
		});
		startBt.setColor(Color.RED);
		
		pauseBt = new TextButton((language.equals(languageManager.languageEN)?"PAUSE ":"PAUSA"), textButtonStyle);
		pauseBt.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(pause)pause=false;
				else pause=true;
			}
		});
		
		back = new TextButton((language.equals(languageManager.languageEN)?"BACK ":"VOLTAR "), textButtonStyle);
		back.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				saveScore(score);
				game.setScreen(new ShakeTheBottleGameMenu());
				dispose();
			}
		});
		
		this.gameTextTable   =creatingTable( 0,30, Gdx.graphics.getWidth(), Gdx.graphics.getWidth());
		
		stage.addActor(gameTextTable);
			
		addGameButtons();
	}
	
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#render(float)
	 */
	@SuppressWarnings("static-access")
	@Override
	public void render(float delta) {
		GL20 gl = Gdx.gl;
		gl.glClearColor(1, 0, 0, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.begin();
		game.batch.draw(background, 0, 0);

		game.batch.end();
		bottleBT.setDisabled(false);
		if(timerNo<0){
			saveScore(score);
			gameover = true;
			audioManager.getSoundtrack().get(2).play();
			aManager.addAchievement("Loser", "GAMEOVER");
			game.setScreen(new ShakeTheBottleGameover());
		}
		
		changeLevel();
		timerBt.setText((language.equals(languageManager.languageEN)?"TIME LEFT: "+timerNo:"TEMPO RESTANTE: "+timerNo));
		scoreBt.setText((language.equals(languageManager.languageEN)?"SCORE: "+score:"PONTUACAO: "+score));
		levelBt.setText((language.equals(languageManager.languageEN)?"LEVEL: "+level:"NIVEL: "+level));
		

		
		float accelX = Gdx.input.getAccelerometerX();
		move = accelX+minimalX;
        if(accelX>minimalX &&start){
        	if(bottleBT.getStyle().equals(bottleStyle))	bottleBT.setStyle(bottleStyle2);
			else if(bottleBT.getStyle().equals(bottleStyle2))	bottleBT.setStyle(bottleStyle);
			score+=13;
        	shakes++;
			try{Thread.currentThread().sleep(100);}catch(Exception e){gameover=true;};
		}
		stage.act(delta);
		stage.draw();
	}
//		
	private void addGameButtons(){
		gameTextTable.add(title);
		gameTextTable.row();
		gameTextTable.add(scoreBt);
		gameTextTable.add(levelBt);
		gameTextTable.row();
		gameTextTable.add(timerBt);
		gameTextTable.row().pad(10);
	
		gameTextTable.row();
		gameTextTable.row().pad(10);
		gameTextTable.add(startBt).pad(10);
		gameTextTable.add(pauseBt).pad(10);
		gameTextTable.row().pad(30);
		gameTextTable.row();
		gameTextTable.row();
		gameTextTable.add(bottleBT);
		gameTextTable.getCell(bottleBT).align(Align.center);
		gameTextTable.row();
		gameTextTable.row().pad(30);
		gameTextTable.add(back);
		gameTextTable.center();
		gameTextTable.top();
		
	}
	
//	private float move;
//	/* (non-Javadoc)
//	 * @see com.badlogic.gdx.Screen#resize(int, int)
//	 */
	@Override
	public void resize(int width, int height) {
		/*if(width>0&& height>0){
//			this.width = width;
//			this.height=height;
		}*/
	}
//
//	/* (non-Javadoc)
//	 * @see com.badlogic.gdx.Screen#pause()
//	 */
	@Override
	public void pause() {
	}
//
//	/* (non-Javadoc)
//	 * @see com.badlogic.gdx.Screen#resume()
//	 */
	@Override
	public void resume() {
//		
	}
//
//	/* (non-Javadoc)
//	 * @see com.badlogic.gdx.Screen#hide()
//	 */
	@Override
	public void hide() {
	}
//
//	/* (non-Javadoc)
//	 * @see com.badlogic.gdx.Screen#dispose()
//	 */
        @Override
	public void dispose() {
        	//atlasBottle.dispose();
		//bitmapFont.dispose();
		//background.dispose();
		//stage.dispose();
		audioManager.close();

	}

    	@Override
    	public void setSurvival(boolean mode) {
    		survival = mode;
    	}

    	@Override
    	public void setDifficulty(String difficulty) {
    		if(difficulty.equals("easy")||difficulty.equals("normal")||difficulty.equals("hard")||difficulty.equals("insane")) this.difficulty = difficulty;
    	}

    	@Override
    	public void setScore(int score) {
    		if(score>=0)this.score=score;
    	}

    	@Override
    	public void setLevel(int level) {
    		if(level>=0)this.level = level;

    	}

    	@Override
    	public void setBonus(int bonus) {
    		if(bonus>=0)this.bonus = bonus;

    	}

    	@Override
    	public void setPause(boolean pause) {
    		this.pause = pause;
    	}

    	@Override
    	public String getName() {
    		return "";
    	}

    	@Override
    	public String getDescription() {
    		// TODO Auto-generated method stub
    		return null;
    	}

    	@Override
    	public boolean getSurvival() {
    		return survival;
    	}

    	@Override
    	public String getDifficulty() {
    		return difficulty;
    	}

    	@Override
    	public int getScore() {
    		return score;
    	}

    	@Override
    	public int getLevel() {
    		return level;
    	}

    	@Override
    	public int getBonus() {
    		return bonus;
    	}

    	@Override
    	public boolean paused() {
    		return pause;
    	}

    	@Override
    	public boolean saveTempGame() {
    		return false;
    	}

    	@Override
    	public boolean loadTempGame() {
    		return false;
    	}

		@Override
		public void create() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void render() {
			// TODO Auto-generated method stub
			
		}
   

}
