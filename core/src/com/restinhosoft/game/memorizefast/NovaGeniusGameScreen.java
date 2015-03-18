/**
 * 
 */
package com.restinhosoft.game.memorizefast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
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
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.restinhosoft.game.hitthecolor.ColorGameMenu;
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
public class NovaGeniusGameScreen implements Screen , MiniGamesIF {
//************************************NOVA*****************************
	private ArrayList<Integer> colorSequence = new ArrayList<Integer>();
	private ArrayList<TextButton> seqColor = new ArrayList<TextButton>();
	
	private final int max = 6;
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
	
	private final TextureAtlas colorAtlas = creating.creatingAtlas("games/color_button.atlas");
	private final Skin colorSkin = creating.creatingSkin(colorAtlas);
	private final TextButtonStyle colorStyleBlue  = creating.creatingTextButtonStyles(colorSkin, "blue_bt", bitmapFont);
	private final TextButtonStyle colorStyleRed   = creating.creatingTextButtonStyles(colorSkin, "red_bt", bitmapFont);
	private final TextButtonStyle colorStyleGreen = creating.creatingTextButtonStyles(colorSkin, "green_bt", bitmapFont);
	private final TextButtonStyle colorStyleYellow= creating.creatingTextButtonStyles(colorSkin, "yellow_bt", bitmapFont);
	private final TextButtonStyle colorStyleWhite = creating.creatingTextButtonStyles(colorSkin, "white_bt", bitmapFont);
	private final TextButtonStyle colorStylePurple= creating.creatingTextButtonStyles(colorSkin, "purple_bt", bitmapFont);
	
	private final TextButtonStyle colorStyleBarra= creating.creatingTextButtonStyles(colorSkin, "barra", bitmapFont);
	private TextButton barra = creating.creatingTextButton("", colorStyleBarra,  true);
	
	private final TextButtonStyle styleBlue  = creating.creatingTextButtonStyles(colorSkin, "blue", bitmapFont);
	private final TextButtonStyle styleRed   = creating.creatingTextButtonStyles(colorSkin, "red", bitmapFont);
	private final TextButtonStyle styleGreen = creating.creatingTextButtonStyles(colorSkin, "green", bitmapFont);
	private final TextButtonStyle styleYellow= creating.creatingTextButtonStyles(colorSkin, "yellow", bitmapFont);
	private final TextButtonStyle styleWhite = creating.creatingTextButtonStyles(colorSkin, "white", bitmapFont);
	private final TextButtonStyle stylePurple= creating.creatingTextButtonStyles(colorSkin, "purple", bitmapFont);
	
	private TextButton seq01 = creating.creatingTextButton("", styleBlue,  true);
	private TextButton seq02 = creating.creatingTextButton("", styleGreen, true);
	private TextButton seq03 = creating.creatingTextButton("", styleRed,   true);
	private TextButton seq04 = creating.creatingTextButton("", styleWhite, true);
	private TextButton seq05 = creating.creatingTextButton("", styleYellow,true);
	private TextButton seq06 = creating.creatingTextButton("", stylePurple,true);
	
	private TextButton hit01 = creating.creatingTextButton("", colorStyleBlue, false);
	private TextButton hit02 = creating.creatingTextButton("", colorStyleRed, false);
	private TextButton hit03 = creating.creatingTextButton("",colorStyleYellow, false);
	private TextButton hit04 = creating.creatingTextButton("",colorStyleGreen , false);
	private TextButton hit05 = creating.creatingTextButton("", colorStylePurple, false);
	private TextButton hit06 = creating.creatingTextButton("", colorStyleWhite, false);
	
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
                		sequenceInvisible();
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
		if(difficulty.equals("easy"))   timerNo =15;
		if(difficulty.equals("normal")) timerNo =12;
		if(difficulty.equals("hard"))   timerNo =10;
		if(difficulty.equals("insane")) timerNo =8;
		timerDefault = timerNo;
	}

	
	private void levelup(){
		buildColorSequence();
		next=0;
		timerChange = 0;
		estimateTime();
		saveScore(score);
		level++;
	}
	
	private void changeLevel(){
		if(max==next) {
			levelup();
			audioManager.getSoundtrack().get(0).play(); 
		}
		if(level==10){
			aManager.addAchievement("superGenius", "Level 10 NOVA GENIUS");
			audioManager.getSoundtrack().get(0).play();
		}if(level>=20){
			aManager.addAchievement("Lucky", "FINAL LEVEL");
		}
		
	}
	
	private void addButtonsList(){
		seqColor.add(seq01);
		seqColor.add(seq02);
		seqColor.add(seq03);
		seqColor.add(seq04);
		seqColor.add(seq05);
		seqColor.add(seq06);
	}
	
	private void changeColors(){
		for(int i=0; i<max;i++){
			if(colorSequence.get(i)==0) seqColor.get(i).setStyle(styleBlue);
			if(colorSequence.get(i)==1) seqColor.get(i).setStyle(styleRed);
			if(colorSequence.get(i)==2) seqColor.get(i).setStyle(styleYellow);
			if(colorSequence.get(i)==3) seqColor.get(i).setStyle(styleGreen);
			if(colorSequence.get(i)==4) seqColor.get(i).setStyle(stylePurple);
			if(colorSequence.get(i)==5) seqColor.get(i).setStyle(styleWhite);
		}
	}
	
	private void buildColorSequence(){
		colorSequence = new ArrayList<Integer>();
		Random rn = new Random();
		for(int i=0; i<max;i++){
			colorSequence.add(rn.nextInt(6));
		}
		changeColors();
	}
	
	private int givePoints(int c){
		if(c==0)return 10;
		else if(c==1)return 12;
		else if(c==2)return 15;
		else if(c==3)return 17;
		else return 15;
		
	}
	
	private void hit(final TextButton button, final int cor){
		button.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(cor==colorSequence.get(next)){
					if(start && !pause){
						System.out.println("cor:"+cor+"\nxt:"+colorSequence.get(next));
						score += givePoints(next);
						seqColor.get(next).setVisible(true);
						audioManager.getSoundtrack().get(1).play();
						if(next<max)next++;
						if(next==max){
							levelup();
						}
					}
				}else{
					if(start && !pause){
						gameover =true;	
					}
				}
			}
		});
	}

	private void hittingButtons(){
		hit(hit01,0);
		hit(hit02,1);
		hit(hit03,2);
		hit(hit04,3);
		hit(hit05,4);
		hit(hit06,5);	
				
		
		//hit(hit07,6);
	}
	
	private void sequenceInvisible(){
		for(int i=0;i<max;i++){
			seqColor.get(i).setVisible(false);
		}	
		hit01.setDisabled(false);
		hit02.setDisabled(false);
		hit03.setDisabled(false);
		hit04.setDisabled(false);
		hit05.setDisabled(false);
		hit06.setDisabled(false);
		
	}
	
	private void sequenceVisible(){
		for(int i=0;i<max;i++){
			seqColor.get(i).setVisible(true);
		}	
		hit01.setDisabled(true);
		hit02.setDisabled(true);
		hit03.setDisabled(true);
		hit04.setDisabled(true);
		hit05.setDisabled(true);
		hit06.setDisabled(true);
	}
	
	ShakeThisBottle game;
	
	private OrthographicCamera camera;
	private Texture memorizefastGameBackground;
	private Stage stage;
	private Table gameTextTable;
	private int width = 320;
	private int height= 480;
	
//************************************NOVA*****************************

	private AudioManager audioManager;
	
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#show()
	 */
	@Override
	public void show() {
		this.game = (ShakeThisBottle) Gdx.app.getApplicationListener();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, width, height);
		
		stage = new Stage();
		
		Gdx.input.setInputProcessor(stage);
		
		memorizefastGameBackground = new Texture(Gdx.files.internal(
													"games/game_background.png"));
		//*****************************************************NOVA*****************************************
		audioManager = new AudioManager("audio/gameselection.ogg");
		audioManager.addToSoundTrack("audio/clear.ogg");
		audioManager.addToSoundTrack("audio/botoes_first.mp3");
		audioManager.addToSoundTrack("audio/clear.ogg");
		
		estimateTime();
		
		addButtonsList();
		buildColorSequence();
		
		sequenceInvisible();
		
		LangInstance();
		
		TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = bitmapFont;
		
		title = new TextButton((language.equals(languageManager.languageEN)?"HIT THE COLOR ":"ACERTE NA COR"), textButtonStyle);
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
				startBt.setColor(Color.GREEN);
				buildColorSequence();
				sequenceVisible();
				startBt.setDisabled(true);
				timer();
				timerChange();
				
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
				dispose();
			}
		});
		this.gameTextTable   = creating.creatingTable(0,30, Gdx.graphics.getWidth(), Gdx.graphics.getWidth());
		stage.addActor(gameTextTable);
		hittingButtons();
		addGameButtons();
		//*****************************************************NOVA*****************************************
	}

	private void addGameButtons(){
		gameTextTable.add(title);
		gameTextTable.row();
		gameTextTable.add(scoreBt);
		gameTextTable.add(levelBt);
		gameTextTable.row();
		gameTextTable.add(timerBt);
		gameTextTable.row().pad(10);
		gameTextTable.add(seqColor.get(0));
		gameTextTable.add(seqColor.get(1));
		gameTextTable.add(seqColor.get(2));
		gameTextTable.row().pad(10);
		gameTextTable.add(seqColor.get(3));
		gameTextTable.add(seqColor.get(4));
		gameTextTable.add(seqColor.get(5));
		gameTextTable.row();
		gameTextTable.row().pad(10);
		gameTextTable.add(startBt).pad(10);
		gameTextTable.add(pauseBt).pad(10);
		gameTextTable.row().pad(10);
		gameTextTable.add(hit01);
		gameTextTable.add(hit02);
		gameTextTable.add(hit03);
		gameTextTable.row();
		gameTextTable.add(hit04);
		gameTextTable.add(hit05);
		gameTextTable.add(hit06);
		gameTextTable.row();
		
		gameTextTable.row();
		gameTextTable.add(back);
		gameTextTable.center();
		
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
		
		if(timerNo==0 || gameover){
			audioManager.getSoundtrack().get(2).play();
			aManager.addAchievement("Loser", "GAMEOVER");
			saveScore(score);
			game.setScreen(new NovaGeniusGameover());
			dispose();
		}
		
		
		
		changeLevel();
		
		timerBt.setText((language.equals(languageManager.languageEN)?"TIME LEFT: "+timerNo:"TEMPO RESTANTE: "+timerNo));
		
		scoreBt.setText((language.equals(languageManager.languageEN)?"SCORE: "+score:"PONTUACAO: "+score));
		levelBt.setText((language.equals(languageManager.languageEN)?"LEVEL: "+level:"NIVEL: "+level));
		
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
		//atlasCircle.dispose();
		//atlasSquare.dispose();
	//	//atlasSpace.dispose();
	//	atlasImageGhostSqr.dispose();
	//	atlasGameTexts.dispose();
		
		bitmapFont.dispose();
		
	//	memorizefastGameBackground.dispose();
		
		stage.dispose();
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
}
