/**
 * 
 */
package com.restinhosoft.game.shakethisbottle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import jdk.nashorn.internal.runtime.regexp.joni.Config;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
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
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.restinhosoft.game.memorizefast.MemorizeFastGameOverScreen;
import com.restinhosoft.game.memorizefast.MemorizeFastLevelUpScreen;
import com.restinhosoft.shakethisbottle.ui.GameSelectionScreen;
import com.restinhosoft.shakethisbottle.ui.ShakeThisBottle;
import com.sun.org.apache.bcel.internal.generic.GETSTATIC;


/**
 * @author Mailson
 *
 */
public class ShakeThisBottleGameScreen implements Screen,  SensorEventListener{
	//*******************************Accel*****************************
	private float minimalX = Gdx.input.getAccelerometerX();//0.0f;
	private float minimalY = Gdx.input.getAccelerometerY();//0.0f;
	private float minimalZ = Gdx.input.getAccelerometerZ();//0.0f;
	
	//********************LOGICS*****************************************
	private int shakes = 0;
	
	private final int maxGameTimer = 30;
	private final int initialLevel = 1; 
	private final int initialBonus = 0;
	private final int initialScore = 0;
	
	private int timer = maxGameTimer;
	private int difficultyTimer = maxGameTimer;
	private int level = initialLevel;
	
	private int score = initialScore;
	private int bonus = initialBonus;
	
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
	
	private BitmapFont bitmapFont;
	
	private Table gameTextTable;
	//private Table gameButtonsTable;
	
	private TextButton levelBT;
	private TextButton scoreBT;
	private TextButton spaceBT;
	private TextButton timerBT;
	
	private TextButton bottleBT;
	
	private TextButtonStyle bottleStyle;
	private TextButtonStyle spaceStyle;
	private TextButtonStyle gameStatsStyle;

	private int width = 320;
	private int height= 480;
	
	//private int width = Gdx.graphics.getWidth();
	//private int height= Gdx.graphics.getHeight();
	
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
	
	private Timer counterTimer;
	private TimerTask task;
	private final long second = 1000;
	
	private int showTimer = 5;
		
	private void showSequence(){
		if(showTimer>0){
			
		}else if(showTimer==0){
		}else{

		}
	}
	
	private void shakesIncrement(){
		if(shakes==30){
			levelUp=true;
			shakes=0;
		}else if(shakes>30)shakes=0;
		else shakes++;
	}
	
		
	private void updates(){
		timerBT.setText( "TIMER: "+ timer);
		levelBT.setText( "LEVEL: "+ level+" shake"+shakes);
		scoreBT.setText( "SCORE: "+ score);
	}

	private void addGameButtons(){
		gameTextTable.add(levelBT);
		gameTextTable.add(timerBT);
		gameTextTable.row();
		gameTextTable.add(bottleBT);
		gameTextTable.top();
		
	}
	
	public ShakeThisBottleGameScreen(int score,int level,int bonus){
		this.score = score;
		this.level=level;
		this.bonus=bonus;
	}
	
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#show()
	 */
	@Override
	public void show() {
		this.game = (ShakeThisBottle) Gdx.app.getApplicationListener();
		//cfg.useAccelerometer = true;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, width, height);
		
		stage = new Stage();
		
		Gdx.input.setInputProcessor(stage);
		
		memorizefastGameBackground = new Texture(Gdx.files.internal("background_profile_screen.png"));
												// "shakethisbottle/shakethebottle_background.png"));
		
		//timer
		counterTimer = new Timer();
		
		this.task = new TimerTask() {  
            public void run() {  
                try { 
                	if(showTimer >= 0){                		
                		showTimer--;
                	}else{
                		
                		timer--;
                	}
                 } catch (Exception e) {  
                      e.printStackTrace();  
                 }  
            }  
        }; 
       
        counterTimer.scheduleAtFixedRate(task, second, second);
		
		//creating graphics
		this.atlasBottle=creatingAtlas( "shakethisbottle/garrafa.atlas");
		
		this.atlasSpace=creatingAtlas( "space.atlas");
		this.atlasGameTexts    =creatingAtlas( "imageghostsqr.atlas");
		
		this.bottleSkin  =creatingSkin( this.atlasBottle);
		this.spaceSkin   =creatingSkin(this.atlasSpace);
		this.gameTextSkin=creatingSkin( this.atlasGameTexts);
		
		bitmapFont = new BitmapFont(Gdx.files.internal("default.fnt"));
		
		this.gameStatsStyle=creatingTextButtonStyles( this.gameTextSkin, "imageghost_sqr", bitmapFont);
		this.bottleStyle   =creatingTextButtonStyles( this.bottleSkin, "garrafa", bitmapFont);
		this.spaceStyle    =creatingTextButtonStyles( this.spaceSkin, "space", bitmapFont);
		
		this.bottleBT=creatingTextButton( "", this.bottleStyle, true);
		
		this.levelBT=creatingTextButton( "LEVEL: "+ level+"      ", this.gameStatsStyle, true);
		this.timerBT=creatingTextButton( "TIMER: "+ showTimer, this.gameStatsStyle, true);
		this.spaceBT=creatingTextButton("", this.spaceStyle, true);
		this.scoreBT=creatingTextButton( "SCORE: "+ score, this.gameStatsStyle, true);
				
		this.gameTextTable   =creatingTable( 0,30, Gdx.graphics.getWidth(), Gdx.graphics.getWidth());
		
		stage.addActor(gameTextTable);
			
		//********************************Logic Game*****************************************************
		showSequence();
		addGameButtons();
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#render(float)
	 */
	@Override
	public void render(float delta) {
		GL20 gl = Gdx.gl;
		gl.glClearColor(1, 0, 0, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.begin();
		game.batch.draw(memorizefastGameBackground, 0, 0);

		game.batch.end();
		
		//************************game logic*******************************************
		
		if(timer<0){
			gameOver = true;
		}
		if(shakes==30){
			score = timer*level;
			if(timer>difficultyTimer/2){
				bonus = timer*level;
				score = score+bonus;
			
			}
			level++;
			Gdx.input.vibrate(100);
	       
			game.setScreen(new ShakeThisBottleLevelUpScreen(score, level, bonus));
		}
		if(levelUp){
			levelUp=false;
			score = score + level*timer;
			
			timer = 10;
			showTimer=5;
				
			if(level%2==0){
				difficultyTimer--;
			}
			
			showSequence();	
			
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
		}
		//************************game logic*******************************************
		//accel*************
		
	  /*  SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
	    List<Sensor> sensors = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);       
	    if (sensors.size() > 0) {
	        sm.registerListener(this, sensors.get(0), SensorManager.SENSOR_DELAY_GAME);
	    }*/

		//onSensorChanged(Gdx.input.getCurrentEventTime());
		
		bottleBT.setText(shakes+"");
		
		stage.act(delta);
		stage.draw();
	}
		

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#resize(int, int)
	 */
	@Override
	public void resize(int width, int height) {
		/*if(width>0&& height>0){
			this.width = width;
			this.height=height;
		}*/
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#pause()
	 */
	@Override
	public void pause() {
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#resume()
	 */
	@Override
	public void resume() {
		
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#hide()
	 */
	@Override
	public void hide() {
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#dispose()
	 */
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
//***********************************************************HUHUE
    private long last_update =0 , last_movement = 0;
    private float prevX = 0, prevY = 0,prevZ = 0;
    private float currX = 0, currY = 0,currZ = 0;
    
	@Override
	public void onSensorChanged(SensorEvent event) {
		synchronized (this) {
			long currentTime  = event.timestamp;
									
			currX = event.values[0];
	    	currY = event.values[1];
		    currZ = event.values[2];
						
	     	if (prevX == 0 && prevY == 0 && prevZ == 0) {
	            last_update   = currentTime;
	            last_movement = currentTime;
	            prevX = currX;
	            prevY = currY;
	            prevZ = currZ;
	        }
			 
	        long time_difference = currentTime - last_update + 2500000;//constante para se ajustar ao aceleromentro
	        if (time_difference > 0) {
	            float movement = Math.abs((currX + currY + currZ) - (prevX - prevY - prevZ)) / time_difference;
	            int limit = 1500;
	            float min_movement = 1E-6f;
	            if (movement > min_movement) {
	                if (currentTime - last_movement >= limit) {                    
	                    shakes++;
	                }
	            }
	            last_movement = currentTime;
	        }
	            prevX = currX;
	            prevY = currY;
	            prevZ = currZ;
	            last_update = currentTime;
	        }
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

}
