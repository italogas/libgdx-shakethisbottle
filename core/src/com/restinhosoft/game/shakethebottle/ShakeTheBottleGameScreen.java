/**
 * 
 */
package com.restinhosoft.game.shakethebottle;

import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
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
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.restinhosoft.game.memorizefast.NovaGeniusGameMenu;
import com.restinhosoft.main.AchievementsManager;
import com.restinhosoft.main.LanguageManager;
import com.restinhosoft.main.MiniGamesIF;
import com.restinhosoft.main.ScoresManager;
import com.restinhosoft.main.ShakeThisBottle;
import com.restinhosoft.ui.AuxScreenCreation;
//import jdk.nashorn.internal.runtime.regexp.joni.Config;
//import android.hardware.Sensor;
//import android.hardware.SensorEvent;
//import android.hardware.SensorEventListener;
//import android.hardware.SensorManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Gdx;


/**
 * @author Mailson
 *
 */
public class ShakeTheBottleGameScreen implements Screen, MiniGamesIF{
	//********************************Novo**************************
private final String defaultDifficulty = "normal";
	
	private final BitmapFont bitmapFont = new BitmapFont(Gdx.files.internal("default.fnt"));
	
	private int level = 0;
	private int score = 0;
	private int bonus = 0;
	private int next = 0;
	
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
	private TextButton back;
	
	private LanguageManager languageManager;
	public String language;
	
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
	
	//*******************************Accel*****************************
	private float minimalX =4.0f;
	
	//********************LOGICS*****************************************
	private int shakes = 0;
	
	private final int maxGameTimer = 15;
	private final int initialLevel = 1; 
	private final int initialBonus = 0;
	private final int initialScore = 0;
	
	private int timer = maxGameTimer;
	private int difficultyTimer = maxGameTimer;
	//private int level = initialLevel;
	
//	private int score = initialScore;
	//private int bonus = initialBonus;
	
	private boolean gameOver = false;
	private boolean levelUp  = false;
	
//********************GRAPHICS***************************************
	ShakeThisBottle game;
	
	private OrthographicCamera camera;
	
	private Texture memorizefastGameBackground;
	
	private Stage stage;
	
	private TextureAtlas atlasBottle;
	private TextureAtlas atlasSpace;
	private TextureAtlas atlasImageGhostSqr;
	private TextureAtlas atlasGameTexts;
	
	private Skin bottleSkin;
	private Skin spaceSkin;
	private Skin gameTextSkin;
	
	//private BitmapFont bitmapFont;
	
	private Table gameTextTable;
	//private Table gameButtonsTable;
	
	private TextButton levelBT;
	private TextButton scoreBT;
	@SuppressWarnings("unused")
	private TextButton spaceBT;
	private TextButton timerBT;
	
	private TextButton bottleBT;
	
	private TextButtonStyle bottleStyle;
	private TextButtonStyle spaceStyle;
	private TextButtonStyle gameStatsStyle;

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
//	
	private Timer counterTimer;
	private TimerTask task;
	private final long second = 1000;
//	
	private int showTimer = 5;
	private boolean play = false;	
//	
	private void showSequence(){
//		
		if(showTimer>0){
			play=false;
		}else if(showTimer==0){
		}else{
			play=true;
		}
	}
//	
	private void updates(){
		timerBT.setText( "TIMER: "+ timer);
		levelBT.setText( "LEVEL: "+ level);
		scoreBT.setText( "SCORE: "+ score);
	}
//
	private void addGameButtons(){
		gameTextTable.add(levelBT);
		gameTextTable.add(timerBT);
		gameTextTable.row();
		gameTextTable.add(bottleBT);
		gameTextTable.top();
		
	}
//	
	/*public ShakeTheBottleGameScreen(int score,int level,int bonus){
		this.score = score;
		this.level=level;
		this.bonus=bonus;
	}*/
//	
//	/* (non-Javadoc)
//	 * @see com.badlogic.gdx.Screen#show()
//	 */
	@Override
	public void show() {
		this.game = (ShakeThisBottle) Gdx.app.getApplicationListener();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, width, height);
		
		stage = new Stage();
//		
		Gdx.input.setInputProcessor(stage);
//		
		memorizefastGameBackground = new Texture(Gdx.files.internal("background_profile_screen.png"));
												// "shakethisbottle/shakethebottle_background.png"));
//		
//		//timer
		counterTimer = new Timer();
//		
		this.task = new TimerTask() {  
            public void run() {  
                try { 
                	if(showTimer >= 0){                		
                		showTimer--;
                	}else{
                		
                		timer--;
                	}
                 } catch (Exception e) {  
                	 System.err.println(e.getMessage());  
                 }  
            }  
        }; 
//       
        counterTimer.scheduleAtFixedRate(task, second, second);
//		
//		//creating graphics
		this.atlasBottle=creatingAtlas( "shakethisbottle/garrafa.atlas");
//		
		this.atlasSpace=creatingAtlas( "space.atlas");
		this.atlasGameTexts    =creatingAtlas( "imageghostsqr.atlas");
//		
		this.bottleSkin  =creatingSkin( this.atlasBottle);
		this.spaceSkin   =creatingSkin(this.atlasSpace);
		this.gameTextSkin=creatingSkin( this.atlasGameTexts);
		this.bottleStyle   =creatingTextButtonStyles( this.bottleSkin, "garrafa", bitmapFont);
//		
	//	bitmapFont = new BitmapFont(Gdx.files.internal("default.fnt"));
		/*
		this.gameStatsStyle=creatingTextButtonStyles( this.gameTextSkin, "imageghost_sqr", bitmapFont);
		
		this.spaceStyle    =creatingTextButtonStyles( this.spaceSkin, "space", bitmapFont);
//		
		this.bottleBT=creatingTextButton( "", this.bottleStyle, true);
//		
		this.levelBT=creatingTextButton( "LEVEL: "+ level+"      ", this.gameStatsStyle, true);
		this.timerBT=creatingTextButton( "TIMER: "+ showTimer, this.gameStatsStyle, true);
		this.spaceBT=creatingTextButton("", this.spaceStyle, true);
		this.scoreBT=creatingTextButton( "SCORE: "+ score, this.gameStatsStyle, true);
//			*/	
		this.bottleBT=creatingTextButton( "", this.bottleStyle, true);
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
		
		timerBt = new TextButton((language.equals(languageManager.languageEN)?"TIME LEFT: "+timer:"TEMPO RESTANTE: "+timer), textButtonStyle);
		timerBt.setDisabled(true);
		
		startBt = new TextButton((language.equals(languageManager.languageEN)?"START":"INICIO"), textButtonStyle);
		startBt.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(start==false)start=true;
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
				
				game.setScreen(new NovaGeniusGameMenu());
				//dispose();
			}
		});
		
		this.gameTextTable   =creatingTable( 0,30, Gdx.graphics.getWidth(), Gdx.graphics.getWidth());
//		
		stage.addActor(gameTextTable);
//			
//		//********************************Logic Game*****************************************************
		showSequence();
		addGameButtonsT();
	}
//
//	/* (non-Javadoc)
//	 * @see com.badlogic.gdx.Screen#render(float)
//	 */
	@SuppressWarnings("static-access")
	@Override
	public void render(float delta) {
		GL20 gl = Gdx.gl;
		gl.glClearColor(1, 0, 0, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		
		game.batch.begin();
		game.batch.draw(memorizefastGameBackground, 0, 0);
//
		game.batch.end();
//		
//		//************************game logic*******************************************
//		
		if(timer<0){
			gameOver = true;
		}/*
		if(shakes>=30){
			score = timer*level;
			if(timer>difficultyTimer/2){
				bonus = timer*level;
				score = score+bonus;
//			
			}
			level++;
			game.setScreen(new ShakeThisBottleLevelUpScreen(score, level, bonus));
		}
		if(levelUp){
			levelUp=false;
			score = score + level*timer;
			
			timer = 10;
			showTimer=5;
//				
			if(level%2==0){
				difficultyTimer--;
			}
//			
			showSequence();	
//			
		}else if(gameOver){
			bonus=0;
			game.setScreen(new ShakeThisBottleGameOverScreen(score, level, bonus));
		}else{
			if(showTimer>0)			timerBT.setText( "SHOWING: "+ showTimer);
			else if(showTimer==0)	timerBT.setText( "START");
			else{
				updates();
			}
        showSequence();
		}*/
		timerBt.setText((language.equals(languageManager.languageEN)?"TIME LEFT: "+timer:"TEMPO RESTANTE: "+timer));
		
		scoreBt.setText((language.equals(languageManager.languageEN)?"SCORE: "+score:"PONTUACAO: "+score));
		levelBt.setText((language.equals(languageManager.languageEN)?"LEVEL: "+level:"NIVEL: "+level));
		//************************game logic*******************************************
		float accelX = Gdx.input.getAccelerometerX();
		move = accelX+minimalX;
        if(accelX>minimalX &&play){// && accelX!=move){//(accelX>move+minimalX ||accelX<move+minimalX)){
			shakes++;
			try{Thread.currentThread().sleep(100);}catch(Exception e){gameOver=true;};
		}
		stage.act(delta);
		stage.draw();
	}
//		
	private void addGameButtonsT(){
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
		gameTextTable.row().pad(10);
		gameTextTable.row();
		gameTextTable.row();
		gameTextTable.add(bottleBT);
		gameTextTable.row();
		gameTextTable.add(back);
		gameTextTable.center();
		
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
		atlasBottle.dispose();
		atlasSpace.dispose();
		atlasImageGhostSqr.dispose();
		atlasGameTexts.dispose();
	
		bitmapFont.dispose();
		
		memorizefastGameBackground.dispose();
		
		stage.dispose();

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
   

}
