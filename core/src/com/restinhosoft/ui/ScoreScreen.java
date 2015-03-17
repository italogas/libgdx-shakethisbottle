/**
 * 
 */
package com.restinhosoft.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
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
import com.restinhosoft.main.AudioManager;
import com.restinhosoft.main.LanguageManager;
import com.restinhosoft.main.ScoresManager;
import com.restinhosoft.main.ShakeThisBottle;


/**
 * @author Mailson
 *
 */
public class ScoreScreen implements Screen {
	ShakeThisBottle game;
	
	private Texture menuImg;
	
	private Stage stage;
	
	private TextureAtlas atlas;
	
	private Skin skin;
	
	private final BitmapFont bitmapFont = new BitmapFont(Gdx.files.internal("default.fnt"));
	
	private Table table;
	
	private TextButton scoreText;
	private TextButton backText;
	
	private final AuxScreenCreation creatingButton = new AuxScreenCreation();
	
	private TextButton balloonBT;
	private TextButton memoryBT;
	private TextButton colourBT;
	private TextButton bottleBT;
	
	private final TextureAtlas atlasBalloon = creatingButton.creatingAtlas("icons/games_balao.atlas");
	private final TextureAtlas atlasMemory  = creatingButton.creatingAtlas("icons/games_memoria.atlas");
	private final TextureAtlas atlasColour  = creatingButton.creatingAtlas("icons/games_colour.atlas");
	private final TextureAtlas atlasBottle  = creatingButton.creatingAtlas("icons/games_garrafa.atlas");
	
	
	private final Skin skinBalloon= creatingButton.creatingSkin(atlasBalloon);
	private final Skin skinMemory = creatingButton.creatingSkin(atlasMemory);
	private final Skin skinColour = creatingButton.creatingSkin(atlasColour);
	private final Skin skinBottle = creatingButton.creatingSkin(atlasBottle);
	
	private final TextButtonStyle balloonStyle= creatingButton.creatingTextButtonStyles(skinBalloon,"game_balao",  bitmapFont);
	private final TextButtonStyle memoryStyle = creatingButton.creatingTextButtonStyles(skinMemory ,"game_memoria",bitmapFont);
	private final TextButtonStyle colourStyle = creatingButton.creatingTextButtonStyles(skinColour ,"game_colour", bitmapFont);
	private final TextButtonStyle bottleStyle = creatingButton.creatingTextButtonStyles(skinBottle ,"game_garrafa",bitmapFont);
	
	private int width = 320;
	private int height= 480;
	
	//private int width = Gdx.graphics.getWidth();
	//private int height= Gdx.graphics.getHeight();
	
	private FitViewport fitViewport;
	
	private String scoreString;
	private String scoreShow = "00000";
	
	private AudioManager audioManager;
	
	private static LanguageManager languageManager;
	public static String language;
	private static String fileName;
	
	private static void scoreLanguageManager(){
		languageManager = LanguageManager.getInstance();
		try {
			language = languageManager.getLanguage();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		if(language.equals(languageManager.languageEN))	fileName = "scores_eng.txt";
		else fileName = "scores_pt.txt";
	}
	
	private String[] scoreMaster = new String[4];
	
	private void ScoreShow(String score){
		String[] scoresArray = score.split("\n"); 
		for(int i=0;i<scoresArray.length;i++){
			String[] tempArray = scoresArray[i].split(":");
			if(tempArray[0].equals("HIT THE CIRCLE")||tempArray[0].equals("ACERTE NO CIRCULO")){
				scoreMaster[0] = tempArray[1];
				colourBT.setVisible(true);
				colourBT.setDisabled(false);
			}
			if(tempArray[0].equals("HIT THE BALLOON")||tempArray[0].equals("ESTOURE O BALAO")){
				scoreMaster[1] = tempArray[1];
				balloonBT.setVisible(true);
				balloonBT.setDisabled(false);
			}
			if(tempArray[0].equals("MEMORIZE FAST")||tempArray[0].equals("MEMORIZE RAPIDO")){
				scoreMaster[2] = tempArray[1];
				memoryBT.setVisible(true);
				memoryBT.setDisabled(false);
			}
			if(tempArray[0].equals("SHAKE THE BOTTLE")||tempArray[0].equals("AGITE A GARRAFA")){
				scoreMaster[3] = tempArray[1];
				bottleBT.setVisible(true);
				bottleBT.setDisabled(false);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#show()
	 */
	@SuppressWarnings("static-access")
	@Override
	public void show() {
		this.game = (ShakeThisBottle) Gdx.app.getApplicationListener();
		
		audioManager = new AudioManager("audio/scores.mp3");
		audioManager.playMusic();
		audioManager.getMusic().setLooping(false);
		
		languageManager = LanguageManager.getInstance();
		
		scoreLanguageManager();
		scoreString = new ScoresManager(fileName).loadScoresList(fileName);
		
		fitViewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage = new Stage();
		stage.setViewport(fitViewport);
		
		Gdx.input.setInputProcessor(stage);
		
		menuImg = new Texture(Gdx.files.internal("icons/sub_menu.png"));
		atlas = new TextureAtlas(Gdx.files.internal("button.atlas"));
		
		skin = new Skin(atlas);
		
		//bitmapFont = new BitmapFont(Gdx.files.internal("default.fnt"));
		
		TextButtonStyle textButtonEnableStyle = new TextButton.TextButtonStyle();
		//textButtonEnableStyle.up = skin.getDrawable("blue_button");
		//textButtonEnableStyle.down = skin.getDrawable("blue_button");
		textButtonEnableStyle.pressedOffsetX = 1;
		textButtonEnableStyle.pressedOffsetY = -1;
		textButtonEnableStyle.font = bitmapFont;
		
		balloonBT= creatingButton.creatingTextButton("", balloonStyle, false);
		memoryBT = creatingButton.creatingTextButton("", memoryStyle, false);
		colourBT = creatingButton.creatingTextButton("", colourStyle, false);
		bottleBT = creatingButton.creatingTextButton("", bottleStyle, false);
		
		balloonBT.setVisible(false);
		memoryBT.setVisible(false);
		colourBT.setVisible(false);
		bottleBT.setVisible(false);
		
		balloonBT.setDisabled(true);
		memoryBT.setDisabled(true);
		colourBT.setDisabled(true);
		bottleBT.setDisabled(true);
		
		ScoreShow(scoreString);

		table = new Table();
		table.setFillParent(true);
		table.setBounds(-Gdx.graphics.getWidth()/2, -Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage.addActor(table);
		
		scoreText = new TextButton(language.equals(languageManager.languageEN)?"SCORE: "+scoreShow
													:"PONTUACAO: "+scoreShow,textButtonEnableStyle);
		scoreText.setColor(Color.WHITE);
		scoreText.setHeight(height);
		scoreText.setWidth(width);
		scoreText.setDisabled(true);
		
		
		backText = new TextButton("BACK", textButtonEnableStyle);
		backText = new TextButton((language.equals(languageManager.languageEN)?"Back ":"Voltar "), textButtonEnableStyle);
		backText.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new PlayerProfileScreen());
				dispose();
			}
		});
		
		table.row().pad(20);
		table.add(scoreText).align(Align.center);
		table.getCell(scoreText).align(Align.center);
		table.row().pad(20);
		table.add(balloonBT).pad(20);
		table.add(memoryBT).pad(20);
		table.row().pad(20);
		table.add(bottleBT).pad(20);
		table.add(colourBT).pad(20);
		table.row().pad(20);
		table.add(backText);
		table.getCell(backText).align(Align.center);
		table.align(Align.center);
		
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
		game.batch.draw(menuImg, 0, 0);
		game.batch.end();
		
		balloonBT.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				scoreShow =scoreMaster[1];
			}
		});
		
		memoryBT.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				scoreShow =scoreMaster[2];
			}
		});

		colourBT.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				scoreShow =scoreMaster[0];
			}
		});

		bottleBT.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				scoreShow =scoreMaster[3];
			}
		});
		
		scoreText.setText(language.equals(languageManager.languageEN)?
				"SCORE: "+scoreShow
				:"PONTUACAO: "+scoreShow);

		
		stage.act(delta);
		stage.draw();
	}
		

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#resize(int, int)
	 */
	@Override
	public void resize(int width, int height) {
		fitViewport.update(width, height);
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
		atlas.dispose();
		bitmapFont.dispose();
		menuImg.dispose();
		stage.dispose();
		
		audioManager.close();

	}

}