package com.restinhosoft.game.hitthecolor;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.restinhosoft.main.AchievementsManager;
import com.restinhosoft.main.AudioManager;
import com.restinhosoft.main.LanguageManager;
import com.restinhosoft.main.MiniGamesIF;
import com.restinhosoft.main.ScoresManager;
import com.restinhosoft.main.ShakeThisBottle;
import com.restinhosoft.ui.AuxScreenCreation;

public class ColorStart implements MiniGamesIF, Screen {
	private ShakeThisBottle game;
	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private final BitmapFont bitmapFont = new BitmapFont(Gdx.files.internal("default.fnt"));
	private Table tableTitle;
	private Table tableScore;
	private Table tableGame;
	private Table tableBack;

	public Label selectLabel;
	private Texture background;
	private FitViewport fitViewport;
	private TextButton back;
	
	private LanguageManager languageManager;
	public String language;
	
	private final AuxScreenCreation creating = new AuxScreenCreation();
	private TextButton title;
	private TextButton scoreBt;
	private TextButton levelBt;
	private TextButton startBt;
	private TextButton pauseBt;
	private TextButton hitBt;
	private TextButton timerBt;
	
	private final TextureAtlas colorAtlas = creating.creatingAtlas("games/color_button.atlas");
	private final Skin colorSkin = creating.creatingSkin(colorAtlas);
	private final TextButtonStyle colorStyleBlue  = creating.creatingTextButtonStyles(colorSkin, "blue_bt", bitmapFont);
	private final TextButtonStyle colorStyleRed   = creating.creatingTextButtonStyles(colorSkin, "red_bt", bitmapFont);
	private final TextButtonStyle colorStyleGreen = creating.creatingTextButtonStyles(colorSkin, "green_bt", bitmapFont);
	private final TextButtonStyle colorStyleYellow= creating.creatingTextButtonStyles(colorSkin, "yellow_bt", bitmapFont);
	private final TextButtonStyle colorStyleGray  = creating.creatingTextButtonStyles(colorSkin, "gray_bt", bitmapFont);
	private final TextButtonStyle colorStyleWhite = creating.creatingTextButtonStyles(colorSkin, "white_bt", bitmapFont);
	private final TextButtonStyle colorStylePurple= creating.creatingTextButtonStyles(colorSkin, "purple_bt", bitmapFont);
	
	private final TextButtonStyle styleBlue  = creating.creatingTextButtonStyles(colorSkin, "blue", bitmapFont);
	private final TextButtonStyle styleRed   = creating.creatingTextButtonStyles(colorSkin, "red", bitmapFont);
	private final TextButtonStyle styleGreen = creating.creatingTextButtonStyles(colorSkin, "green", bitmapFont);
	private final TextButtonStyle styleYellow= creating.creatingTextButtonStyles(colorSkin, "yellow", bitmapFont);
	private final TextButtonStyle styleGray  = creating.creatingTextButtonStyles(colorSkin, "gray", bitmapFont);
	private final TextButtonStyle styleWhite = creating.creatingTextButtonStyles(colorSkin, "white", bitmapFont);
	private final TextButtonStyle stylePurple= creating.creatingTextButtonStyles(colorSkin, "purple", bitmapFont);
	
	private TextButton hitShow = creating.creatingTextButton("hit", colorStyleBlue, false);
	
	private TextButton cor01 = creating.creatingTextButton("", colorStyleBlue, false);
	private TextButton cor02 = creating.creatingTextButton("", colorStyleGreen, false);
	private TextButton cor03 = creating.creatingTextButton("", colorStyleRed, false);
	private TextButton cor04 = creating.creatingTextButton("", colorStyleWhite, false);
	private TextButton cor05 = creating.creatingTextButton("", colorStyleYellow, false);
	private TextButton cor06 = creating.creatingTextButton("", colorStyleGray, false);
	private TextButton cor07 = creating.creatingTextButton("", colorStylePurple, false);
	private TextButton cor08 = creating.creatingTextButton("", colorStyleBlue, false);
	private TextButton cor09 = creating.creatingTextButton("", colorStyleWhite, false);
	private TextButton cor10 = creating.creatingTextButton("", colorStylePurple, false);
	private TextButton cor11 = creating.creatingTextButton("", colorStyleRed, false);
	private TextButton cor12 = creating.creatingTextButton("", colorStyleGreen, false);
	private TextButton cor13 = creating.creatingTextButton("", colorStyleGray, false);
	private TextButton cor14 = creating.creatingTextButton("", colorStyleYellow, false);
	private TextButton cor15 = creating.creatingTextButton("", colorStyleRed, false);
	private TextButton cor16 = creating.creatingTextButton("", colorStyleBlue, false);
	private TextButton cor17 = creating.creatingTextButton("", colorStyleGreen, false);
	private TextButton cor18 = creating.creatingTextButton("", colorStyleYellow, false);
	
	private ArrayList<Integer> colorNumber = new ArrayList<Integer>();
	private ArrayList<TextButton> buttons  = new ArrayList<TextButton>();
	
	private AchievementsManager aManager = new AchievementsManager();
	
	private final int max = 18-6;
	private final String defaultDifficulty = "normal";
	
	private int hit = 0;
	private int level = 0;
	private int score = 0;
	private int bonus = 0;
	
	private boolean survival = false;
	private boolean pause = false;
	private boolean start = false;
	
	private String difficulty = defaultDifficulty;
	
	private void saveScore(int score){
		LangInstance();
		String temp = language;
		try {
			languageManager.setLanguage("engl");
			new ScoresManager("scores_eng.txt").saveDefaultMultipleScore("HIT THE COLOR", score);
			languageManager.setLanguage("ptbr");
			new ScoresManager("scores_pt.txt").saveDefaultMultipleScore("ACERTE NA COR", score);
			languageManager.setLanguage(temp);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	private AudioManager audioManager;

	private void addColorNumberList(){
		Random rn = new Random();
		
		for(int i=0;i<max;i++){
			colorNumber.add( rn.nextInt(6));
		}
	}
	
	private void addButtonsList(){
		buttons.add(cor01);
		buttons.add(cor02);
		buttons.add(cor03);
		buttons.add(cor04);
		buttons.add(cor05);
		buttons.add(cor06);
		buttons.add(cor07);
		buttons.add(cor08);
		buttons.add(cor09);
		buttons.add(cor10);
		buttons.add(cor11);
		buttons.add(cor12);
		/*buttons.add(cor13);
		buttons.add(cor14);
		buttons.add(cor15);
		buttons.add(cor16);
		buttons.add(cor17);
		buttons.add(cor18);*/
	}
	
	private void updateColorList(){
		colorNumber= new ArrayList<Integer>();
		addColorNumberList();
	}
	
	private void updateColors(){
		for(int i=0;i<max;i++){
			buttons.get(i).setVisible(true);
			     if(colorNumber.get(i)==0)buttons.get(i).setStyle(colorStyleBlue);
			else if(colorNumber.get(i)==1)buttons.get(i).setStyle(colorStyleRed);
			else if(colorNumber.get(i)==2)buttons.get(i).setStyle(colorStyleGreen);
			else if(colorNumber.get(i)==3)buttons.get(i).setStyle(colorStyleYellow);
			else if(colorNumber.get(i)==4)buttons.get(i).setStyle(colorStyleGray);
			else if(colorNumber.get(i)==5)buttons.get(i).setStyle(colorStyleWhite);
			else if(colorNumber.get(i)==6)buttons.get(i).setStyle(colorStylePurple);
		}
		Random rn = new Random();
		int uso =rn.nextInt(max-1);
			 if(hit==0)buttons.get(uso).setStyle(colorStyleBlue);
		else if(hit==1)buttons.get(uso).setStyle(colorStyleRed);
		else if(hit==2)buttons.get(uso).setStyle(colorStyleGreen);
		else if(hit==3)buttons.get(uso).setStyle(colorStyleYellow);
		else if(hit==4)buttons.get(uso).setStyle(colorStyleGray);
		else if(hit==5)buttons.get(uso).setStyle(colorStyleWhite);
		else buttons.get(uso).setStyle(colorStylePurple);
		colorNumber.set(uso, hit);
	}
	
	private int givePoints(int c){
		if(c==0)return 10;
		else if(c==1)return 12;
		else if(c==2)return 15;
		else if(c==3)return 17;
		else if(c==4)return 15;
		else if(c==5)return 12;
		else return 10;
	}
	
	private void hit(final TextButton button, final int position){
		button.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(colorNumber.get(position)==hit){
					if(start && !pause){
						score += givePoints(hit);
						button.setVisible(false);
						audioManager.getSoundtrack().get(1).play();
					}
					 
				}
				else{
					if(start && !pause){
						if(score-hit >=0)score -= givePoints(hit);
						else score = 0;	
					}
				}
			}
		});
	}

	private void hittingButtons(){
		hit(cor01,0);
		hit(cor02,1);
		hit(cor03,2);
		hit(cor04,3);
		hit(cor05,4);
		hit(cor06,5);
		hit(cor07,6);
		hit(cor08,7);
		hit(cor09,8);
		hit(cor10,9);
		hit(cor11,10);
		hit(cor12,11);
		/*hit(cor13,12);
		hit(cor14,13);
		hit(cor15,14);
		hit(cor16,15);
		hit(cor17,16);
		hit(cor18,17);*/
	}
	
	private void hitShow(){
			 if(hit==0)hitShow.setStyle(styleBlue);
		else if(hit==1)hitShow.setStyle(styleRed);
		else if(hit==2)hitShow.setStyle(styleGreen);
		else if(hit==3)hitShow.setStyle(styleYellow);
		else if(hit==4)hitShow.setStyle(styleGray);
		else if(hit==5)hitShow.setStyle(styleWhite);
		else if(hit==6)hitShow.setStyle(stylePurple);
	}

	
	private void levelup(){
		Random rn = new Random();
		hit = rn.nextInt(6);
		hitShow();
		int timerTemp = timer;
		estimateTime();
		this.timer+=timerTemp;
		saveScore(score);
		level++;
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

	private void LangInstance(){
		languageManager = LanguageManager.getInstance();
		try {
			language = languageManager.getLanguage();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	private void changeLevel(){
		if(level==1 && score >=100) {
			aManager.addAchievement("superVision", "Level 10 HIT THE COLOR");
			levelup();
			audioManager.getSoundtrack().get(0).play(); 
		}else if(level==2 && score >=150){
			levelup();
			audioManager.getSoundtrack().get(0).play();
		}else if(level==3 && score >=250){
			levelup();
			audioManager.getSoundtrack().get(0).play();
		}else if(level==4 && score >=400){
			levelup();
			audioManager.getSoundtrack().get(0).play();
		}else if(level==5 && score >=550){
			levelup();
			audioManager.getSoundtrack().get(0).play();
		}else if(level==6 && score >=700){
			levelup();
			audioManager.getSoundtrack().get(0).play();
		}else if(level==7 && score >=850){
			levelup();
			audioManager.getSoundtrack().get(0).play();
		}else if(level==8 && score >=1000){
			levelup();
			audioManager.getSoundtrack().get(0).play();
		}else if(level==9 && score >=1250){
			levelup();
			aManager.addAchievement("superVision", "Level 10 HIT THE COLOR");
			audioManager.getSoundtrack().get(0).play();
		}else if(level==10 && score >=1500){
			levelup();
			audioManager.getSoundtrack().get(0).play();
		}else if(score>=2000){
			levelup();
			audioManager.getSoundtrack().get(0).play();
		}
		
	}
	
	private Timer counterTimer;
	private TimerTask task;
	private final long seconds = 1000;
	private final long twoSeconds = 2000;
	
	
	private int timerChange = 0;
	private int timer = 0; 
	
	private void timerChange(){
		counterTimer = new Timer();
		
		this.task = new TimerTask() {  
            public void run() {  
                try {
                	if(timerChange==2){	timerChange = 1; 	}
                	else timerChange++;
                } catch (Exception e) {System.err.println(e.getMessage());;}  
           }  
		};
		if(!pause && start)	counterTimer.scheduleAtFixedRate(task, twoSeconds, twoSeconds);
	}
	
	private void timer(){
		counterTimer = new Timer();
		
		this.task = new TimerTask() {  
            public void run() {  
                try {
                	timer--;
                } catch (Exception e) {System.err.println(e.getMessage());;}  
           }  
		};
		if(!pause && start)	counterTimer.scheduleAtFixedRate(task, seconds, seconds);
	}
	
	private void estimateTime(){
		if(difficulty.equals("easy"))   timer =20;
		if(difficulty.equals("normal")) timer =15;
		if(difficulty.equals("hard"))   timer =10;
		if(difficulty.equals("insane")) timer =5;
	}

	
	@Override
	public void show() {
		this.game = (ShakeThisBottle) Gdx.app.getApplicationListener();
		
		fitViewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		LangInstance();
		addButtonsList();
		updateColorList();
		updateColors();
		levelup();
		
		audioManager = new AudioManager("audio/gameselection.ogg");
		audioManager.addToSoundTrack("audio/clear.ogg");
		audioManager.addToSoundTrack("audio/botoes_first.mp3");
		audioManager.addToSoundTrack("audio/clear.ogg");
		
		stage = new Stage();
		stage.setViewport(fitViewport);
		
		Gdx.input.setInputProcessor(stage);
		
		background = new Texture(Gdx.files.internal("games/game_background.png"));//new Texture(Gdx.files.internal("menu.png"));
		
		atlas = new TextureAtlas(Gdx.files.internal("button.atlas"));
		
		skin = new Skin(atlas);
		
		LabelStyle labelStyle = new Label.LabelStyle();
		labelStyle.font = bitmapFont;
		selectLabel = new Label((language.equals(languageManager.languageEN)?"HIT THE COLOR ":"ACERTE NA COR"), labelStyle);
		
		TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = bitmapFont;
		
		tableTitle = new Table();
		tableTitle.setFillParent(true);
		tableTitle.setBounds(-160, -30, Gdx.graphics.getWidth(),100);
		
		tableScore = new Table();
		tableScore.setFillParent(true);
		tableScore.setBounds(-160, -50, Gdx.graphics.getWidth(), 300);
		
		tableGame = new Table();
		tableGame.setFillParent(true);
		tableGame.setBounds(-Gdx.graphics.getWidth()/2, -Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		tableBack = new Table();
		tableBack.setFillParent(true);
		tableBack.setBounds(-160, -420, Gdx.graphics.getWidth(), 100);
		
		//stage.addActor(tableTitle);
		stage.addActor(tableScore);
		stage.addActor(tableGame);
		stage.addActor(tableBack);
		
		hittingButtons();
		
		title = new TextButton((language.equals(languageManager.languageEN)?"HIT THE COLOR ":"ACERTE NA COR"), textButtonStyle);
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
				game.setScreen(new ColorGameMenu());
				dispose();
			}
		});
		
		hittingButtons();
				
		tableTitle.align(Align.center);
		tableTitle.add(title);
		tableTitle.getCell(title).spaceBottom(10);
		tableGame.row().pad(10);

				
		tableScore.align(Align.center);
		tableScore.add(scoreBt).pad(15);
		tableScore.add(levelBt);
		tableScore.row();
		tableScore.add(timerBt);
		tableGame.row().pad(10);
		
		tableGame.align(Align.center);
		if(!survival)tableGame.add(startBt);
		tableGame.add(hitShow);
		if(!survival)tableGame.add(pauseBt);
		tableGame.row().pad(10);
		tableGame.add(cor01).pad(10);
		tableGame.add(cor02).pad(10);
		tableGame.add(cor03).pad(10);
		tableGame.row().pad(10);
		tableGame.add(cor04).pad(10);
		tableGame.add(cor05).pad(10);
		tableGame.add(cor06).pad(10);
		tableGame.row().pad(10);
		tableGame.add(cor07).pad(10);
		tableGame.add(cor08).pad(10);
		tableGame.add(cor09).pad(10);
		tableGame.row().pad(10);
		tableGame.add(cor10).pad(10);
		tableGame.add(cor11).pad(10);
		tableGame.add(cor12).pad(10);
		/*tableGame.row().pad(10);
		tableGame.add(cor13).pad(1);
		tableGame.add(cor14).pad(1);
		tableGame.add(cor15).pad(1);
		tableGame.row().pad(10);
		tableGame.add(cor16).pad(1);
		tableGame.add(cor17).pad(1);
		tableGame.add(cor18).pad(1);*/
		tableGame.row().pad(10);
		
		tableBack.align(Align.center);
		if(!survival)tableBack.add(back);
		tableBack.getCell(back).spaceBottom(10);
		
		
		
	}

	@Override
	public void render(float delta) {
		GL20 gl = Gdx.gl;
		gl.glClearColor(1, 1, 1, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		if(timerChange==2){
			updateColorList();
			updateColors();	
		}
		if(timer==0){
			audioManager.getSoundtrack().get(2).play();
			saveScore(score);
			game.setScreen(new ColorGameMenu());
			dispose();
		}
		
		
		
		changeLevel();
		
		timerBt.setText((language.equals(languageManager.languageEN)?"TIME LEFT: "+timer:"TEMPO RESTANTE: "+timer));
		
		scoreBt.setText((language.equals(languageManager.languageEN)?"SCORE: "+score:"PONTUACAO: "+score));
		levelBt.setText((language.equals(languageManager.languageEN)?"LEVEL: "+level:"NIVEL: "+level));
		
		game.batch.begin();
		game.batch.draw(background, 0, 0);
		game.batch.end();
		
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		fitViewport.update(width, height);
	}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {}

	@Override
	public void dispose() {
		background.dispose();
		atlas.dispose();
		skin.dispose();
		stage.dispose();
		bitmapFont.dispose();
		audioManager.close();
	}
	
	
}
