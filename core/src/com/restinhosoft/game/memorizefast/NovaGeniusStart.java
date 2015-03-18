package com.restinhosoft.game.memorizefast;

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
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.restinhosoft.game.hitthecolor.ColorGameMenu;
import com.restinhosoft.game.hitthecolor.ColorGameover;
import com.restinhosoft.main.AchievementsManager;
import com.restinhosoft.main.AudioManager;
import com.restinhosoft.main.LanguageManager;
import com.restinhosoft.main.MiniGamesIF;
import com.restinhosoft.main.ScoresManager;
import com.restinhosoft.main.ShakeThisBottle;
import com.restinhosoft.ui.AuxScreenCreation;

public class NovaGeniusStart implements MiniGamesIF, Screen {
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
	
	private final TextButtonStyle colorStyleBarra= creating.creatingTextButtonStyles(colorSkin, "barra", bitmapFont);
	private TextButton barra = creating.creatingTextButton("", colorStyleBarra,  true);
	
	private final TextButtonStyle styleBlue  = creating.creatingTextButtonStyles(colorSkin, "blue", bitmapFont);
	private final TextButtonStyle styleRed   = creating.creatingTextButtonStyles(colorSkin, "red", bitmapFont);
	private final TextButtonStyle styleGreen = creating.creatingTextButtonStyles(colorSkin, "green", bitmapFont);
	private final TextButtonStyle styleYellow= creating.creatingTextButtonStyles(colorSkin, "yellow", bitmapFont);
	private final TextButtonStyle styleGray  = creating.creatingTextButtonStyles(colorSkin, "gray", bitmapFont);
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
	private TextButton hit03 = creating.creatingTextButton("", colorStyleGreen, false);
	private TextButton hit04 = creating.creatingTextButton("", colorStyleYellow, false);
	private TextButton hit05 = creating.creatingTextButton("", colorStyleGray, false);
	private TextButton hit06 = creating.creatingTextButton("", colorStyleWhite, false);
	private TextButton hit07 = creating.creatingTextButton("", colorStylePurple, false);
	
	private ArrayList<TextButton> seqColor = new ArrayList<TextButton>();
	private ArrayList<TextButton> hitList = new ArrayList<TextButton>();
	
	private ArrayList<Integer> colorNumber = new ArrayList<Integer>();
	
	
	private AchievementsManager aManager = new AchievementsManager();
	
	private final int max = 6;
	private final String defaultDifficulty = "normal";
	
	private int level = 0;
	private int score = 0;
	private int bonus = 0;
	private int next = 0;
	
	private boolean survival = false;
	private boolean pause = false;
	private boolean start = false;
	private boolean gameover = false;
	
	private String difficulty = defaultDifficulty;
	
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
	
	private AudioManager audioManager;
	
	private void addColorNumberList(){
		Random rn = new Random();
		for(int i=0;i<max;i++){
			colorNumber.add( rn.nextInt(6));
		}
	}
	
	private void addButtonsList(){
		seqColor.add(seq01);
		seqColor.add(seq02);
		seqColor.add(seq03);
		seqColor.add(seq04);
		seqColor.add(seq05);
		seqColor.add(seq06);
		
		hitList.add(hit01);
		hitList.add(hit02);
		hitList.add(hit03);
		hitList.add(hit04);
		hitList.add(hit05);
		hitList.add(hit06);
		hitList.add(hit07);
	}
	
	private void updateColorList(){
		colorNumber= new ArrayList<Integer>();
		addColorNumberList();
	}
	
	private void updateColors(){
		updateColorList();
		for(int i=0;i<max;i++){
			seqColor.get(i).setVisible(true);
			     if(colorNumber.get(i)==0)seqColor.get(i).setStyle(styleBlue);
			else if(colorNumber.get(i)==1)seqColor.get(i).setStyle(styleRed);
			else if(colorNumber.get(i)==2)seqColor.get(i).setStyle(styleGreen);
			else if(colorNumber.get(i)==3)seqColor.get(i).setStyle(styleYellow);
			else if(colorNumber.get(i)==4)seqColor.get(i).setStyle(styleGray);
			else if(colorNumber.get(i)==5)seqColor.get(i).setStyle(styleWhite);
			else if(colorNumber.get(i)==6)seqColor.get(i).setStyle(stylePurple);
		}
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
				if(cor==colorNumber.get(next)){
					if(start && !pause){
						score += givePoints(next);
						seqColor.get(next).setVisible(true);
						audioManager.getSoundtrack().get(1).play();
						if(next<max)next++;
					}
				}
				else{
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
		hit(hit07,6);
	}
	
	private void levelup(){
		Random rn = new Random();
		next=0;
		timerChange = 0;
		estimateTime();
		saveScore(score);
		level++;
	}
	
	private void sequenceInvisible(){
		for(int i=0;i<max;i++){
			seqColor.get(i).setVisible(false);
		}	
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
			aManager.addAchievement("superGenius", "Level 10 NOVA GENIUS");
			audioManager.getSoundtrack().get(0).play();
		}else if(level==10 && score >=1500){
			levelup();
			audioManager.getSoundtrack().get(0).play();
		}else if(score>=2000){
			aManager.addAchievement("Lucky", "FINAL LEVEL");
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
                	if(timerChange==2 && !pause){	
                		sequenceInvisible();
                	}
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
                	if(!pause) 	timer--;
                } catch (Exception e) {System.err.println(e.getMessage());;}  
           }  
		};
		if(!pause && start)	counterTimer.scheduleAtFixedRate(task, seconds, seconds);
	}
	
	private void estimateTime(){
		if(difficulty.equals("easy"))   timer =15;
		if(difficulty.equals("normal")) timer =10;
		if(difficulty.equals("hard"))   timer =8;
		if(difficulty.equals("insane")) timer =5;
	}

	
	@Override
	public void show() {
		this.game = (ShakeThisBottle) Gdx.app.getApplicationListener();
		
		fitViewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		LangInstance();
		addButtonsList();
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
		tableScore.setBounds(-160, -50, Gdx.graphics.getWidth(), 100);
		
		tableGame = new Table();
		tableGame.setFillParent(true);
		tableGame.setBounds(-Gdx.graphics.getWidth()/2, -Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth(), 100);
		
		Table tableGame2 = new Table();
		tableGame2.setFillParent(true);
		tableGame2.setBounds(-Gdx.graphics.getWidth()/2, -Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth(), 200);
		
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
		tableGame.add(startBt);
		if(!survival)tableGame.add(pauseBt);
		tableGame.row().pad(10);
		
		tableGame.add(barra);
		tableGame.getCell(barra).align(Align.center);
		tableGame.row().pad(10);
		tableGame.add(seq01);
		tableGame.add(seq02);
		tableGame.add(seq03);
		tableGame.row().pad(10);
		tableGame.add(seq04);
		tableGame.add(seq05);
		tableGame.add(seq06);
		tableGame.row().pad(10);
		tableGame.add(barra);
		tableGame.row().pad(30);
		tableGame.add(hit01).pad(10);
		tableGame.add(hit02).pad(10);
		tableGame.add(hit03);
		tableGame.row().pad(10);
		tableGame.add(hit04).pad(10);
		tableGame.add(hit05).pad(10);
		tableGame.add(hit06);
		tableGame.row().pad(10);
		tableGame.add(hit07);
		tableGame.row().pad(10);
		
		tableBack.align(Align.center);
		tableBack.add(back);
		tableBack.getCell(back).spaceBottom(10);
		
		
		
	}

	@Override
	public void render(float delta) {
		GL20 gl = Gdx.gl;
		gl.glClearColor(1, 1, 1, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		if(timerChange==2){
			sequenceInvisible();
		}
		if(timer==0 || gameover){
			audioManager.getSoundtrack().get(2).play();
			aManager.addAchievement("Loser", "GAMEOVER");
			saveScore(score);
			game.setScreen(new NovaGeniusGameover());
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
