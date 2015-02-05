///**
// * 
// */
package com.restinhosoft.game.donotshakethisbottle;
//
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
//

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
//
//
///**
// * @author Mailson
// *
// */
public class DoNotShakeThisBottleGameScreen implements Screen{
//	//*******************************Accel*****************************
	private float minimalX =0.5f;
//	
//	//********************LOGICS*****************************************
	private int shakes = 0;
//	
	private final int maxGameTimer = 30;
	private final int initialLevel = 1; 
	private final int initialBonus = 0;
	private final int initialScore = 0;
//	
	private int timer = maxGameTimer;
	private int difficultyTimer = maxGameTimer;
	private int level = initialLevel;
//	
	private int score = initialScore;
	private int bonus = initialBonus;
//	
	private boolean gameOver = false;
	private boolean levelUp  = false;
//	
////********************GRAPHICS***************************************
	ShakeThisBottle game;
//	
	private OrthographicCamera camera;
//	
	private Texture gameBackground;
//	
	private Stage stage;
//	
	private TextureAtlas atlasBottle;
	private TextureAtlas atlasSpace;
	private TextureAtlas atlasImageGhostSqr;
	private TextureAtlas atlasGameTexts;
//	
	private Skin bottleSkin;
	private Skin spaceSkin;
	private Skin gameTextSkin;
//	
	private BitmapFont bitmapFont;
//	
	private Table gameTextTable;
	//private Table gameButtonsTable;
//	
	private TextButton levelBT;
	private TextButton scoreBT;
	private TextButton spaceBT;
	private TextButton timerBT;
//	
	private TextButton bottleBT;
//	
	private TextButtonStyle bottleStyle;
	private TextButtonStyle spaceStyle;
	private TextButtonStyle gameStatsStyle;
//
	private int width = 320;
	private int height= 480;
//	
	//private int width = Gdx.graphics.getWidth();
	//private int height= Gdx.graphics.getHeight();
	
	private float move;
//	
	private TextureAtlas creatingAtlas(String file){ 
		return new TextureAtlas(Gdx.files.internal(file));
	}
//	
	private Skin creatingSkin(TextureAtlas atlas){ 
		return new Skin(atlas);
	}
//	
	private TextButton creatingTextButton(String text,TextButtonStyle style, boolean disable){
		TextButton button = new TextButton(text, style);
		button.setDisabled(disable);
		return button;
	}
//	
	private TextButtonStyle creatingTextButtonStyles(Skin skin, String file, BitmapFont font){
		TextButtonStyle style = new TextButton.TextButtonStyle();
		style.up   = skin.getDrawable(file);
		style.down = skin.getDrawable(file);
		style.pressedOffsetX = 1;
		style.pressedOffsetY =-1;
		style.font = font;
//		
		return style;
	}
//	
	private Table creatingTable(int beginningX,int beginningY, int width, int height){
		Table table = new Table();
		table.setBounds(beginningX, beginningY, width, height);
//		
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
//		
	}
//	
	public DoNotShakeThisBottleGameScreen(int score,int level,int bonus){
		this.score = score;
		this.level=level;
		this.bonus=bonus;
	}
//	
//	/* (non-Javadoc)
//	 * @see com.badlogic.gdx.Screen#show()
//	 */
	@Override
	public void show() {
		this.game = (ShakeThisBottle) Gdx.app.getApplicationListener();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, width, height);
//		
		stage = new Stage();
//		
		Gdx.input.setInputProcessor(stage);
//		
		gameBackground = new Texture(Gdx.files.internal("background_profile_screen.png"));
//												// "shakethisbottle/shakethebottle_background.png"));
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
//                		
                		timer--;
                	}
                 } catch (Exception e) {  
                      e.printStackTrace();  
                 }  
            }  
        }; 
//       
        counterTimer.scheduleAtFixedRate(task, second, second);
//		
//		//creating graphics
		this.atlasBottle=creatingAtlas( "donotshakethisbottle/garrafa.atlas");
//		
		this.atlasSpace=creatingAtlas( "space.atlas");
		this.atlasGameTexts    =creatingAtlas( "imageghostsqr.atlas");
//		
		this.bottleSkin  =creatingSkin( this.atlasBottle);
		this.spaceSkin   =creatingSkin(this.atlasSpace);
		this.gameTextSkin=creatingSkin( this.atlasGameTexts);
//		
		bitmapFont = new BitmapFont(Gdx.files.internal("default.fnt"));
//		
		this.gameStatsStyle=creatingTextButtonStyles( this.gameTextSkin, "imageghost_sqr", bitmapFont);
		this.bottleStyle   =creatingTextButtonStyles( this.bottleSkin, "garrafa", bitmapFont);
		this.spaceStyle    =creatingTextButtonStyles( this.spaceSkin, "space", bitmapFont);
//		
		this.bottleBT=creatingTextButton( "", this.bottleStyle, true);
//		
		this.levelBT=creatingTextButton( "LEVEL: "+ level+"      ", this.gameStatsStyle, true);
		this.timerBT=creatingTextButton( "TIMER: "+ showTimer, this.gameStatsStyle, true);
		this.spaceBT=creatingTextButton("", this.spaceStyle, true);
		this.scoreBT=creatingTextButton( "SCORE: "+ score, this.gameStatsStyle, true);
//				
		this.gameTextTable   =creatingTable( 0,30, Gdx.graphics.getWidth(), Gdx.graphics.getWidth());
//		
		stage.addActor(gameTextTable);
//			
//		//********************************Logic Game*****************************************************
		showSequence();
		addGameButtons();
	}
//
//	/* (non-Javadoc)
//	 * @see com.badlogic.gdx.Screen#render(float)
//	 */
	@Override
	public void render(float delta) {
		GL20 gl = Gdx.gl;
		gl.glClearColor(1, 0, 0, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		
		game.batch.begin();
		game.batch.draw(gameBackground, 0, 0);
//
		game.batch.end();
//		
//		//************************game logic*******************************************
//		
		if(timer<0){
			gameOver = true;
		}
		if(timer==0){
			score = (difficultyTimer-timer)*level;
			score = score+bonus;
//			
			if(level%2==0){	difficultyTimer++;	}
//			
			level++;
			game.setScreen(new DoNotShakeThisBottleLevelUpScreen(score, level, bonus));
		}
		if(levelUp){
			levelUp=false;
			score = score + level*timer;
//			
			timer = 10;
			showTimer=5;
//			
//			
			showSequence();	
//			
		}else if(gameOver){
			bonus=0;
			game.setScreen(new DoNotShakeThisBottleGameOverScreen(score, level, bonus));
		}else{
			if(showTimer>0)			timerBT.setText( "SHOWING: "+ showTimer);
			else if(showTimer==0)	timerBT.setText( "START");
			else{
				updates();
			}
			showSequence();
		}
//		//************************game logic*******************************************
		float accelX = Gdx.input.getAccelerometerX();
		move = accelX+minimalX;
		if(accelX>minimalX &&play){// && accelX!=move){//(accelX>move+minimalX ||accelX<move+minimalX)){
			gameOver=true;
		}
		stage.act(delta);
		stage.draw();
	}
//		
//	private float move;
//	/* (non-Javadoc)
//	 * @see com.badlogic.gdx.Screen#resize(int, int)
//	 */
	@Override
	public void resize(int width, int height) {
		/*if(width>0&& height>0){
			this.width = width;
			this.height=height;
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
//		
		bitmapFont.dispose();
//		
		gameBackground.dispose();
//		
		stage.dispose();
//
	}
//   
//
}
