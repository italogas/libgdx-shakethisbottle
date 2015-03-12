/**
 * 
 */
package com.restinhosoft.game.hitthecolor;

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
public class HitTheCircleGameScreen implements Screen {
	//********************LOGICS*****************************************
		private ArrayList<TextButton> buttonList = new ArrayList<TextButton>();
		private ArrayList<Integer> buttonSequence = new ArrayList<Integer>();
		private ArrayList<Integer> colorSequence = new ArrayList<Integer>();
		
		private int hit = 0;
		
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
		
		private void buildHitColor(){
			colorSequence.add(1);//red
			colorSequence.add(2);//green
			colorSequence.add(3);//yellow
			colorSequence.add(4);//blue

			Collections.shuffle(colorSequence);
			
			if(colorSequence.get(0)==1){
				hitBT.setColor(Color.RED);
			}else if(colorSequence.get(0)==2){
				hitBT.setColor(Color.GREEN);
			}else if(colorSequence.get(0)==3){
				hitBT.setColor(Color.YELLOW);
			}else if(colorSequence.get(0)==4){
				hitBT.setColor(Color.BLUE);
			}
		} 
		
		private void buildButtonColor(){
			ArrayList<Integer> colorCopy = colorSequence;
			for(int i=0;i<12;i++){
				Collections.shuffle(colorCopy);
				buttonSequence.add(colorCopy.get(0));
			}for(int i=0;i<12;i++){
				
				if(buttonSequence.get(i)==1){
					buttonList.get(i).setColor(Color.RED);
				}else if(buttonSequence.get(i)==2){
					buttonList.get(i).setColor(Color.GREEN);
				}else if(buttonSequence.get(i)==3){
					buttonList.get(i).setColor(Color.YELLOW);
				}else if(buttonSequence.get(i)==4){
					buttonList.get(i).setColor(Color.BLUE);
				}
				
				if(buttonList.get(i).getColor().equals(hitBT.getColor())){	hit++;	}
			}
		} 
		
		
		
	//********************GRAPHICS***************************************
		ShakeThisBottle game;
		
		private OrthographicCamera camera;
		
		private Texture memorizefastGameBackground;
		
		private Stage stage;
		
		private TextureAtlas atlasLeft;
		private TextureAtlas atlasMiddle;
		private TextureAtlas atlasRight;
		private TextureAtlas atlasGameTexts;
		
		private Skin leftSkin;
		private Skin rightSkin;
		private Skin middleSkin;
		private Skin gameTextSkin;
		
		private BitmapFont bitmapFont;
		
		private Table table;
		
		private TextButton levelBT;
		private TextButton hitBT;
		private TextButton timerBT;
		
		private TextButton circle01;
		private TextButton circle02;
		private TextButton circle03;
		private TextButton circle04;
		private TextButton circle05;
		private TextButton circle06;
		private TextButton circle07;
		private TextButton circle08;
		private TextButton circle09;
		private TextButton circle10;
		private TextButton circle11;
		private TextButton circle12;
		
		private TextButtonStyle leftStyle;
		private TextButtonStyle rightStyle;
		private TextButtonStyle middleStyle;
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
				
				circle01.setDisabled(true);
				circle02.setDisabled(true);
				circle03.setDisabled(true);
				circle04.setDisabled(true);
				circle05.setDisabled(true);
				circle06.setDisabled(true);
				circle07.setDisabled(true);
				circle08.setDisabled(true);
				circle09.setDisabled(true);
				circle10.setDisabled(true);
				circle11.setDisabled(true);
				circle12.setDisabled(true);
				
			}else if(showTimer==0){
				
			}else{
				circle01.setDisabled(false);
				circle02.setDisabled(false);
				circle03.setDisabled(false);
				circle04.setDisabled(false);
				circle05.setDisabled(false);
				circle06.setDisabled(false);
				circle07.setDisabled(false);
				circle08.setDisabled(false);
				circle09.setDisabled(false);
				circle10.setDisabled(false);
				circle11.setDisabled(false);
				circle12.setDisabled(false);
				
			}
		}
				
		private void  buttonClick(){
			circle01.addListener(new ChangeListener() {
				@Override
				public void changed(ChangeEvent event, Actor actor) {
					System.out.println("circle01");
					if(circle01.getColor().equals(hitBT.getColor())){
						buttonList.get(0).setText("OK");
						hit--;
						audioManager.getSoundtrack().get(1).play();
					}else{
						gameOver=true;
					}
				}
			});
			
			circle02.addListener(new ChangeListener() {
				@Override
				public void changed(ChangeEvent event, Actor actor) {
					if(circle02.getColor().equals(hitBT.getColor())){
						buttonList.get(1).setText("OK");
						hit--;
						audioManager.getSoundtrack().get(1).play();
					}else{
						gameOver=true;
					}
				}
			});
			
			circle03.addListener(new ChangeListener() {
				@Override
				public void changed(ChangeEvent event, Actor actor) {
					if(circle03.getColor().equals(hitBT.getColor())){
						buttonList.get(2).setText("OK");
						hit--;
						audioManager.getSoundtrack().get(1).play();
					}else{
						gameOver=true;
					}
				}
			});
			
			circle04.addListener(new ChangeListener() {
				@Override
				public void changed(ChangeEvent event, Actor actor) {
					if(circle04.getColor().equals(hitBT.getColor())){
						buttonList.get(3).setText("OK");
						hit--;
						audioManager.getSoundtrack().get(1).play();
					}else{
						gameOver=true;
					}
				}
			});
			
			circle05.addListener(new ChangeListener() {
				@Override
				public void changed(ChangeEvent event, Actor actor) {
					if(circle05.getColor().equals(hitBT.getColor())){
						buttonList.get(4).setText("OK");
						hit--;
						audioManager.getSoundtrack().get(1).play();
					}else{
						gameOver=true;
					}
				}
			});
			
			circle06.addListener(new ChangeListener() {
				@Override
				public void changed(ChangeEvent event, Actor actor) {
					if(circle06.getColor().equals(hitBT.getColor())){
						buttonList.get(5).setText("OK");
						hit--;
						audioManager.getSoundtrack().get(1).play();
					}else{
						gameOver=true;
					}
				}
			});
			
			circle07.addListener(new ChangeListener() {
				@Override
				public void changed(ChangeEvent event, Actor actor) {
					if(circle07.getColor().equals(hitBT.getColor())){
						buttonList.get(6).setText("OK");
						hit--;
						audioManager.getSoundtrack().get(1).play();
					}else{
						gameOver=true;
					}
				}
			});
			
			circle08.addListener(new ChangeListener() {
				@Override
				public void changed(ChangeEvent event, Actor actor) {
					if(circle08.getColor().equals(hitBT.getColor())){
						buttonList.get(7).setText("OK");
						hit--;
						audioManager.getSoundtrack().get(1).play();
					}else{
						gameOver=true;
					}
				}
			});
			
			circle09.addListener(new ChangeListener() {
				@Override
				public void changed(ChangeEvent event, Actor actor) {
					if(circle09.getColor().equals(hitBT.getColor())){
						buttonList.get(8).setText("OK");
						hit--;
						audioManager.getSoundtrack().get(1).play();
					}else{
						gameOver=true;
					}
				}
			});
			
			circle10.addListener(new ChangeListener() {
				@Override
				public void changed(ChangeEvent event, Actor actor) {
					if(circle10.getColor().equals(hitBT.getColor())){
						buttonList.get(9).setText("OK");
						hit--;
						audioManager.getSoundtrack().get(1).play();
					}else{
						gameOver=true;
					}
				}
			});
			
			circle11.addListener(new ChangeListener() {
				@Override
				public void changed(ChangeEvent event, Actor actor) {
					if(circle11.getColor().equals(hitBT.getColor())){
						buttonList.get(10).setText("OK");
						hit--;
						audioManager.getSoundtrack().get(1).play();
					}else{
						gameOver=true;
					}
				}
			});
			
			circle12.addListener(new ChangeListener() {
				@Override
				public void changed(ChangeEvent event, Actor actor) {
					if(circle12.getColor().equals(hitBT.getColor())){
						buttonList.get(11).setText("OK");
						hit--;
						audioManager.getSoundtrack().get(1).play();
					}else{
						gameOver=true;
					}
				}
			});
			
			
		}
		
		private void updates(){
			timerBT.setText( "TIMER: "+ timer);
			levelBT.setText( "LEVEL: "+ level+"      ");
			hitBT.setText( "HIT");
		}

		private void addGameButtons(){
			table.setFillParent(true);
			//gameTextTable.add(scoreBT);
			
			table.add(levelBT);
			table.add(timerBT);
			table.add(hitBT);
			table.row();
			table.add(circle01);
			table.add(circle02);
			table.add(circle03);
			table.row();
			table.add(circle04);
			table.add(circle05);
			table.add(circle06);
			table.row();
			table.add(circle07);
			table.add(circle08);
			table.add(circle09);
			table.row();
			table.add(circle10);
			table.add(circle11);
			table.add(circle12);
			table.top();
			
			//gameButtonsTable.setFillParent(true);
			
		}
		
		public HitTheCircleGameScreen(int score,int level,int bonus) {
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
													 "hitthecircle/hitthecircle_background.png"));
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
			this.atlasLeft     =creatingAtlas( "hitthecircle/hitthecircle_left.atlas");
			this.atlasMiddle   =creatingAtlas("hitthecircle/hitthecircle_middle.atlas");
			this.atlasRight    =creatingAtlas( "hitthecircle/hitthecircle_right.atlas");
			this.atlasGameTexts=creatingAtlas( "imageghostsqr.atlas");
			
			this.leftSkin    =creatingSkin( this.atlasLeft);
			this.rightSkin   =creatingSkin( this.atlasRight);
			this.middleSkin  =creatingSkin( this.atlasMiddle);
			this.gameTextSkin=creatingSkin( this.atlasGameTexts);
			
			bitmapFont = new BitmapFont(Gdx.files.internal("default.fnt"));
			
			this.gameStatsStyle=creatingTextButtonStyles( this.gameTextSkin, "imageghost_sqr", bitmapFont);
			this.leftStyle   =creatingTextButtonStyles( this.leftSkin,  "hitthecircle_left"  , bitmapFont);
			this.rightStyle  =creatingTextButtonStyles( this.rightSkin, "hitthecircle_right" , bitmapFont);
			this.middleStyle =creatingTextButtonStyles( this.middleSkin,"hitthecircle_middle", bitmapFont);
			
			this.circle01=creatingTextButton( "", this.leftStyle, true);
			this.circle04=creatingTextButton( "", this.leftStyle, true);
			this.circle07=creatingTextButton( "", this.leftStyle, true);
			this.circle10=creatingTextButton( "", this.leftStyle, true);
			
			this.circle02=creatingTextButton( "", this.middleStyle, true);
			this.circle05=creatingTextButton( "", this.middleStyle, true);
			this.circle08=creatingTextButton( "", this.middleStyle, true);
			this.circle11=creatingTextButton( "", this.middleStyle, true);
			
			this.circle03=creatingTextButton( "", this.rightStyle, true);
			this.circle06=creatingTextButton( "", this.rightStyle, true);
			this.circle09=creatingTextButton( "", this.rightStyle, true);
			this.circle12=creatingTextButton( "", this.rightStyle, true);
			
			this.levelBT=creatingTextButton( "LEVEL: "+ level+"      ", this.gameStatsStyle, true);
			this.timerBT=creatingTextButton( "TIMER: "+ showTimer, this.gameStatsStyle, true);
			
			this.hitBT=creatingTextButton( "HIT", this.middleStyle, true);
					
			this.table   =creatingTable( 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getWidth());
			
			stage.addActor(table);
				
			//********************************Logic Game*****************************************************
			
			buttonList.add(circle01);
			buttonList.add(circle02);
			buttonList.add(circle03);
			buttonList.add(circle04);
			buttonList.add(circle05);
			buttonList.add(circle06);
			buttonList.add(circle07);
			buttonList.add(circle08);
			buttonList.add(circle09);
			buttonList.add(circle10);
			buttonList.add(circle11);
			buttonList.add(circle12);
			
			buildHitColor();
			buildButtonColor();
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
			if(hit==0){
				score = timer*level;
				if(timer>difficultyTimer/2){
					bonus = timer*level;
					score = score+bonus;
				
				}
				level++;
				game.setScreen(new HitTheCircleLevelUpScreen(score, level, bonus));
			}
			if(levelUp){
				levelUp=false;
				score = score + level*timer;
				
				timer = 10;
				showTimer=5;
					
				if(level%2==0){
					difficultyTimer--;
				}
				
				buildHitColor();
				buildButtonColor();
				showSequence();	
				
			}else if(gameOver){
				bonus=0;
				game.setScreen(new HitTheCircleGameOverScreen(score, level, bonus));
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
			atlasLeft.dispose();
			atlasMiddle.dispose();
			atlasRight.dispose();
			atlasGameTexts.dispose();
			
			bitmapFont.dispose();
			
			memorizefastGameBackground.dispose();
			
			stage.dispose();

		}

	}
