/**
 * 
 */
package com.restinhosoft.game.memorizefast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Gdx;
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
import com.restinhosoft.main.AudioManager;
import com.restinhosoft.main.ShakeThisBottle;


/**
 * @author Mailson
 *
 */
public class MemorizeFastGameScreen implements Screen {
//********************LOGICS*****************************************
	private ArrayList<TextButton> circleList   = new ArrayList<TextButton>();
	private ArrayList<Integer> villainSequence = new ArrayList<Integer>();
	
	private int next = 0;
	
	private final int maxGameTimer = 10;
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
	
	private void buildVillainSequence(){
		villainSequence.add(1);//red
		villainSequence.add(2);//green
		villainSequence.add(3);//yellow
		villainSequence.add(4);//blue

		Collections.shuffle(villainSequence);
	} 
	
//********************GRAPHICS***************************************
	ShakeThisBottle game;
	
	private OrthographicCamera camera;
	
	private Texture memorizefastGameBackground;
	
	private Stage stage;
	
	private TextureAtlas atlasCircle;
	private TextureAtlas atlasSquare;
	private TextureAtlas atlasSpace;
	private TextureAtlas atlasImageGhostSqr;
	private TextureAtlas atlasGameTexts;
	
	private Skin circleSkin;
	private Skin squareSkin;
	private Skin spaceSkin;
	private Skin gameTextSkin;
	
	private BitmapFont bitmapFont;
	
	private Table gameTextTable;
	//private Table gameButtonsTable;
	
	private TextButton levelBT;
	private TextButton scoreBT;
	private TextButton spaceBT;
	private TextButton timerBT;
	
	
	private TextButton circle01;
	private TextButton circle02;
	private TextButton circle03;
	private TextButton circle04;
	private TextButton circle05;
	private TextButton circle06;

	
	private TextButton square01;
	private TextButton square02;
	private TextButton square03;
	private TextButton square04;
	
	private TextButtonStyle circleStyle;
	private TextButtonStyle squareStyle;
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
			
			square01.setDisabled(true);
			square02.setDisabled(true);
			square03.setDisabled(true);
			square04.setDisabled(true);
			
			for(int i=0;i<villainSequence.size();i++){
				if(villainSequence.get(i)==1){
					circleList.get(i).setColor(Color.RED);
				}else if(villainSequence.get(i)==2){
					circleList.get(i).setColor(Color.GREEN);
				}else if(villainSequence.get(i)==3){
					circleList.get(i).setColor(Color.YELLOW);
				}else if(villainSequence.get(i)==4){
					circleList.get(i).setColor(Color.BLUE);
				}
			}
		}else if(showTimer==0){
			for(int i=0;i<circleList.size();i++){
				circleList.get(i).setColor(Color.WHITE);
			}
		}else{
			square01.setDisabled(false);
			square02.setDisabled(false);
			square03.setDisabled(false);
			square04.setDisabled(false);
			
		}
	}
	
	private void nextIncrement(){
		if(next==3){
			levelUp=true;
			next=0;
		}else if(next>3)next=0;
		else next++;
	}
	
	private void  buttonClick(){
		square01.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(villainSequence.get(0)==1){
					villainSequence.remove(0);
					circleList.get(next).setColor(Color.RED);
					nextIncrement();
					audioManager.getSoundtrack().get(1).play();
				}else{
					gameOver=true;
				}				
			}
		});
		
		square02.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(villainSequence.get(0)==2){
					villainSequence.remove(0);
					circleList.get(next).setColor(Color.GREEN);
					nextIncrement();
					audioManager.getSoundtrack().get(1).play();
				}else{
					gameOver=true;
				}			}
		});
		
		
		square03.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(villainSequence.get(0)==3){
					villainSequence.remove(0);
					circleList.get(next).setColor(Color.YELLOW);
					nextIncrement();
					audioManager.getSoundtrack().get(1).play();
				}else{
					gameOver=true;
				}			}
		});
		
		
		square04.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(villainSequence.get(0)==4){
					villainSequence.remove(0);
					circleList.get(next).setColor(Color.BLUE);
					nextIncrement();
					audioManager.getSoundtrack().get(1).play();
				}else{
					gameOver=true;
				}			}
		});
		
		
	}
	
	private void updates(){
		timerBT.setText( "TIMER: "+ timer);
		levelBT.setText( "LEVEL: "+ level+"      ");
		scoreBT.setText( "SCORE: "+ score);
	}

	private void addGameButtons(){
		gameTextTable.add(levelBT);
		gameTextTable.add(timerBT);
		gameTextTable.row();

		
		gameTextTable.add(circle01);
		gameTextTable.add(circle02);
		gameTextTable.add(circle03);
		gameTextTable.add(circle04);
		
		gameTextTable.row();
		gameTextTable.add(spaceBT);
		gameTextTable.row();
		
		gameTextTable.add(square01);
		gameTextTable.add(square02);
		gameTextTable.add(square03);
		gameTextTable.add(square04);
		gameTextTable.top();
		
	}
	
	public MemorizeFastGameScreen(int score,int level,int bonus) {
		this.score = score;
		this.bonus = bonus;
		this.level = level;
	}
	
	private AudioManager audioManager;
	
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#show()
	 */
	@Override
	public void show() {
		this.game = (ShakeThisBottle) Gdx.app.getApplicationListener();
		audioManager = new AudioManager("audio/gameselection.ogg");
		audioManager.addToSoundTrack("audio/failbt.mp3");
		audioManager.addToSoundTrack("audio/botoes_first.mp3");
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, width, height);
		
		stage = new Stage();
		
		Gdx.input.setInputProcessor(stage);
		
		memorizefastGameBackground = new Texture(Gdx.files.internal(
												 "memorizefast/memorizefast_background.png"));
		
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
                	 System.err.println(e.getMessage());  
                 }  
            }  
        }; 
       
        counterTimer.scheduleAtFixedRate(task, second, second);
		
		//creating graphics
		this.atlasCircle=creatingAtlas( "memorizefast/memorizefast_circle.atlas");
		this.atlasSquare=creatingAtlas( "memorizefast/memorizefast_square.atlas");
		
		this.atlasSpace=creatingAtlas( "space.atlas");
		this.atlasGameTexts    =creatingAtlas( "imageghostsqr.atlas");
		
		this.circleSkin  =creatingSkin( this.atlasCircle);
		this.squareSkin  =creatingSkin( this.atlasSquare);
		this.spaceSkin   =creatingSkin(this.atlasSpace);
		this.gameTextSkin=creatingSkin( this.atlasGameTexts);
		
		bitmapFont = new BitmapFont(Gdx.files.internal("default.fnt"));
		
		this.gameStatsStyle=creatingTextButtonStyles( this.gameTextSkin, "imageghost_sqr", bitmapFont);
		this.circleStyle   =creatingTextButtonStyles( this.circleSkin, "memorizefast_circle", bitmapFont);
		this.squareStyle   =creatingTextButtonStyles( this.squareSkin, "memorizefast_square", bitmapFont);
		this.spaceStyle    =creatingTextButtonStyles( this.spaceSkin, "space", bitmapFont);
		
		this.circle01=creatingTextButton( "", this.circleStyle, true);
		this.circle02=creatingTextButton( "", this.circleStyle, true);
		this.circle03=creatingTextButton( "", this.circleStyle, true);
		this.circle04=creatingTextButton( "", this.circleStyle, true);
		
		this.square01=creatingTextButton( "", this.squareStyle, true);
		this.square02=creatingTextButton( "", this.squareStyle, true);
		this.square03=creatingTextButton( "", this.squareStyle, true);
		this.square04=creatingTextButton( "", this.squareStyle, true);
		
		this.levelBT=creatingTextButton( "LEVEL: "+ level+"      ", this.gameStatsStyle, true);
		this.timerBT=creatingTextButton( "TIMER: "+ showTimer, this.gameStatsStyle, true);
		this.spaceBT=creatingTextButton("", this.spaceStyle, true);
		this.scoreBT=creatingTextButton( "SCORE: "+ score, this.gameStatsStyle, true);
				
		this.gameTextTable   =creatingTable( 0,30, Gdx.graphics.getWidth(), Gdx.graphics.getWidth());
		
		stage.addActor(gameTextTable);
			
		//********************************Logic Game*****************************************************
		circleList.add(circle01);
		circleList.add(circle02);
		circleList.add(circle03);
		circleList.add(circle04);
		
		square01.setColor(Color.RED);
		square02.setColor(Color.GREEN);
		square03.setColor(Color.YELLOW);
		square04.setColor(Color.BLUE);
		
		buildVillainSequence();
		showSequence();
		buttonClick();
		
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
			dispose();
		}
		if(villainSequence.size()==0){
			score = timer*level;
			if(timer>difficultyTimer/2){
				bonus = timer*level;
				
			
			}
			level++;
			game.setScreen(new MemorizeFastLevelUpScreen(score, level, bonus));
		}
		if(levelUp){
			levelUp=false;
			score = score + level*timer;
			
			timer = 10;
			showTimer=5;
				
			if(level%2==0){
				difficultyTimer--;
			}
			
			buildVillainSequence();
			showSequence();	
			
		}else if(gameOver){
			bonus=0;
			game.setScreen(new MemorizeFastGameOverScreen(score, level, bonus));
		}else{
			if(showTimer>0)			timerBT.setText( "SHOWING: "+ showTimer);
			else if(showTimer==0)	timerBT.setText( "START");
			else{
				updates();
			}
			showSequence();
		}
		//************************game logic*******************************************
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
		atlasCircle.dispose();
		atlasSquare.dispose();
		atlasSpace.dispose();
		atlasImageGhostSqr.dispose();
		atlasGameTexts.dispose();
		
		bitmapFont.dispose();
		
		memorizefastGameBackground.dispose();
		
		stage.dispose();
		audioManager.close();
	}

}
