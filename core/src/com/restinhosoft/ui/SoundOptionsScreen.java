/**
 * 
 */
package com.restinhosoft.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.restinhosoft.main.LanguageManager;
import com.restinhosoft.main.ShakeThisBottle;


/**
 * @author Italo
 *
 */
public class SoundOptionsScreen implements Screen {

	// static variables to enable screen state testing
	public ShakeThisBottle game;
	public Stage stage;
	public TextureAtlas atlas;
	public Skin skin;
	public final BitmapFont bitmapFont = new BitmapFont(Gdx.files.internal("neuropol-x-free-m.fnt"));
	public Table table;
	public Label enableSoundLabel;
	public Label gameMusicLabel;
	public Label soundEffectsLabel;
	public Label generalVolumeLabel;
	public CheckBox checkBox0;
	public CheckBox checkBox1;
	public CheckBox checkBox2;
	public TextButton backButton;
	public ProgressBar progressBar;
	float actualValue;
	public Texture background;
	public FitViewport fitViewport;
	
	//PlayerPreferencesIOBuffer pref;
	
	private LanguageManager languageManager;
	public String language;

	private static final String fileName = "options.txt";
	private static String options = "true true true 1";
	private static String[] optionArray = options.split(" ");
	
	private static String buildingSave(){
		String jun = "";
		for(int i=0;i<optionArray.length;i++ ){
			jun+=optionArray[i]+" ";
		}
		return jun;
	}
	
	private static void saveOptions(){
		options = buildingSave();
		try{
			FileHandle local = Gdx.files.local(fileName);
			local.writeString(options,false);
		}  catch (RuntimeException re){
			System.err.println(re.getMessage());
		}
	}
	
	public static String loadOptions(){
		String readString = null;
		try {
			FileHandle local = Gdx.files.local(fileName);
			readString = local.readString();
		} catch (RuntimeException re){
			System.err.println(re.getMessage());
		}
		if(readString==null) readString = "true true true 1";
		return readString;
		
	}
	
	private TextButton soundEnableBT;
	private TextButton musicEnableBT;
	private TextButton trackEnableBT;
	
	private Texture subBackGround;
	
	private final TextureAtlas soundAtlasTempEnable = creatingAtlas("icons/sound_enable.atlas");
	private final TextureAtlas musicAtlasTempEnable = creatingAtlas("icons/music_enable.atlas");
	private final TextureAtlas trackAtlasTempEnable = creatingAtlas("icons/bgm_enable.atlas");
	private final TextureAtlas soundAtlasTempDisable= creatingAtlas("icons/sound_disable.atlas");
	private final TextureAtlas musicAtlasTempDisable= creatingAtlas("icons/music_disable.atlas");
	private final TextureAtlas trackAtlasTempDisable= creatingAtlas("icons/bgm_disable.atlas");
	
	private final Skin soundSkinTempEnable = creatingSkin(soundAtlasTempEnable);
	private final Skin musicSkinTempEnable = creatingSkin(musicAtlasTempEnable);
	private final Skin trackSkinTempEnable = creatingSkin(trackAtlasTempEnable);
	private final Skin soundSkinTempDisable= creatingSkin(soundAtlasTempDisable);
	private final Skin musicSkinTempDisable= creatingSkin(musicAtlasTempDisable);
	private final Skin trackSkinTempDisable= creatingSkin(trackAtlasTempDisable);
	
	private TextButtonStyle soundStyle;
	private TextButtonStyle musicStyle;
	private TextButtonStyle trackStyle;
	
	private final TextButtonStyle soundStyleEnable = creatingTextButtonStyles(soundSkinTempEnable, "sound_enable", bitmapFont);
	private final TextButtonStyle musicStyleEnable = creatingTextButtonStyles(musicSkinTempEnable, "music_enable", bitmapFont);
	private final TextButtonStyle trackStyleEnable = creatingTextButtonStyles(trackSkinTempEnable, "bgm_enable", bitmapFont);
	private final TextButtonStyle soundStyleDisable = creatingTextButtonStyles(soundSkinTempDisable, "sound_disable", bitmapFont);
	private final TextButtonStyle musicStyleDisable = creatingTextButtonStyles(musicSkinTempDisable, "music_disable", bitmapFont);
	private final TextButtonStyle trackStyleDisable = creatingTextButtonStyles(trackSkinTempDisable, "bgm_disable", bitmapFont);
	
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
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#show()
	 */
	@Override
	public void show() {
		enabled();
		this.game = (ShakeThisBottle) Gdx.app.getApplicationListener();
		
		FileHandle local = Gdx.files.local(fileName);
		if(local.exists()){
			options  = loadOptions();
			optionArray = options.split(" ");
			saveOptions();
		}else{
			saveOptions();	
		}
		//pref = new PlayerPreferencesIOBuffer();
		enabled();
		languageManager = LanguageManager.getInstance();
		try {
			language = languageManager.getLanguage();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		fitViewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage = new Stage();
		stage.setViewport(fitViewport);
		
		Gdx.input.setInputProcessor(stage);
		
		background = new Texture(Gdx.files.internal("menu.png"));
		subBackGround = new Texture(Gdx.files.internal("icons/sub_menu.png"));
		
		atlas = new TextureAtlas(Gdx.files.internal("uiskin.atlas"));
		
		skin = new Skin(atlas);
		
		//bitmapFont = new BitmapFont(Gdx.files.internal("default.fnt"));
		
		LabelStyle labelStyle = new Label.LabelStyle();
		labelStyle.font = bitmapFont;
		enableSoundLabel = new Label((language.equals(languageManager.languageEN)?"Enable Sound: ":"Habilitar Som: "), labelStyle);
		gameMusicLabel = new Label((language.equals(languageManager.languageEN)?"Game Music: ":"Musica: "), labelStyle);
		soundEffectsLabel = new Label((language.equals(languageManager.languageEN)?"Sound Effects: ":"Efeitos Sonoros: "), labelStyle);
		generalVolumeLabel = new Label((language.equals(languageManager.languageEN)?"General Volume: ":"Volume: "), labelStyle);
		
		CheckBoxStyle checkBoxStyle = new CheckBox.CheckBoxStyle();
		checkBoxStyle.checkboxOff = skin.getDrawable("check-off");
		checkBoxStyle.checkboxOn = skin.getDrawable("check-on");
		checkBoxStyle.pressedOffsetX = 1;
		checkBoxStyle.pressedOffsetY = -1;
		checkBoxStyle.font = bitmapFont;
		
		checkBox0 = new CheckBox("", checkBoxStyle);
		
		checkBox1 = new CheckBox("", checkBoxStyle);
		checkBox2 = new CheckBox("", checkBoxStyle);
		
		TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		//textButtonStyle.up = skin.getDrawable("default-rect");
		//textButtonStyle.down = skin.getDrawable("default-rect-down");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = bitmapFont;
		
		
		//backButton = new TextButton("Back", textButtonStyle);
		backButton = new TextButton((language.equals(languageManager.languageEN)?"Back ":"Voltar "), textButtonStyle);
		backButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new OptionsScreen());
			}
		});
		
		soundEnableBT = creatingTextButton("", soundStyle, false);
		musicEnableBT = creatingTextButton("", musicStyle, false);
		trackEnableBT = creatingTextButton("", trackStyle, false);
		
		
		
		
		
		soundEnableBT.addListener(new ChangeListener() {
			boolean soundEnable = Boolean.parseBoolean(optionArray[0]);
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(soundEnable){
					optionArray[0] = "false";	
					saveOptions();
					soundEnableBT.setStyle(soundStyleDisable);
					game.setScreen(new SoundOptionsScreen());
					dispose();
				}else{
					optionArray[0] = "true";	
					saveOptions();
					soundEnableBT.setStyle(soundStyleEnable);
					game.setScreen(new SoundOptionsScreen());
					dispose();
				}
			}
		});
		
		musicEnableBT.addListener(new ChangeListener() {
			boolean soundEnable = Boolean.parseBoolean(optionArray[0]);
			boolean musicEnable = Boolean.parseBoolean(optionArray[1]);
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(soundEnable && musicEnable){
					optionArray[1] = "false";	
					saveOptions();
					game.setScreen(new SoundOptionsScreen());
					dispose();
				}else if(soundEnable && !musicEnable){
					optionArray[1] = "true";	
					saveOptions();
					game.setScreen(new SoundOptionsScreen());
					dispose();
				}
				enabled();
			}
		});
		
		trackEnableBT.addListener(new ChangeListener() {
			boolean soundEnable = Boolean.parseBoolean(optionArray[0]);
			boolean trackEnable = Boolean.parseBoolean(optionArray[2]);
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(soundEnable && trackEnable){
					optionArray[2] = "false";	
					saveOptions();
					game.setScreen(new SoundOptionsScreen());
					dispose();
				}else if(soundEnable && !trackEnable){
					optionArray[2] = "true";	
					saveOptions();
					game.setScreen(new SoundOptionsScreen());
					dispose();
				}
				enabled();
			}
		});
		
		
		
		
		ProgressBarStyle progressBarStyle = new ProgressBar.ProgressBarStyle();
		progressBarStyle.background = skin.getDrawable("default-slider");
		
		progressBar = new ProgressBar(0, 100, 1, false, progressBarStyle);
		actualValue = progressBar.getMinValue();
		progressBar.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				progressBar.setValue(++actualValue);
				//pref.setVolume((int)actualValue);
			}
		});
		
		table = new Table(skin);
		table.setBounds(-Gdx.graphics.getWidth()/2, -Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		table.setFillParent(true);
//		table.setDebug(true);
		stage.addActor(table);
		/*
		table.add(enableSoundLabel);
		table.getCell(enableSoundLabel).spaceBottom(10);
		table.add(checkBox0);
		table.getCell(checkBox0).spaceBottom(10);
		table.row();
		table.add(gameMusicLabel);
		table.getCell(gameMusicLabel).spaceBottom(10);
		table.add(checkBox1);
		table.getCell(checkBox1).spaceBottom(10);
		table.row();
		table.add(soundEffectsLabel);
		table.getCell(soundEffectsLabel).spaceBottom(10);
		table.add(checkBox2);
		table.getCell(checkBox2).spaceBottom(10);
		table.row();
		table.add(generalVolumeLabel);
		table.getCell(generalVolumeLabel).spaceBottom(10);
		table.add(progressBar);
		table.getCell(progressBar).spaceBottom(10);*/
		table.row().pad(10);
		table.add(soundEnableBT).pad(15);
		table.row();
		table.add(musicEnableBT).pad(15);
		table.row();
		table.add(trackEnableBT).pad(15);
		table.row();
		table.add(backButton);
		table.align(Align.center);

	}
	private void enabled(){
		if(optionArray[0].equals("true")){
			soundStyle= soundStyleEnable;
		}else{
			soundStyle= soundStyleDisable;
		}
		if(optionArray[1].equals("true")){
			musicStyle= musicStyleEnable;
		}else{
			musicStyle= musicStyleDisable;
		}
		if(optionArray[2].equals("true")){
			trackStyle= trackStyleEnable;
		}else{
			trackStyle= trackStyleDisable;
		}
		
		
	}

	
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#render(float)
	 */
	@Override
	public void render(float delta) {
		
		GL20 gl = Gdx.gl;
		gl.glClearColor(0, 0, 0, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
				
		game.batch.begin();
		game.batch.draw(subBackGround, 0, 0);
		game.batch.end();
		
		enabled();
		
		
		//checkbox*********************************
		/*options = loadOptions();
		optionArray = options.split(" ");
		*/
		//System.out.println("options "+ options);
		if(optionArray[0].equals("true"))checkBox0.setChecked(true);
		if(optionArray[1].equals("true"))checkBox1.setChecked(true);
		if(optionArray[2].equals("true"))checkBox2.setChecked(true);
		
		if(   checkBox0.isPressed()   && !checkBox0.isChecked()){
			  optionArray[0] = "true";	
			  saveOptions();
		}else if(checkBox0.isPressed()&& checkBox0.isChecked()){
			  optionArray[0] = "false";
			  saveOptions();
			  //System.out.println(loadOptions());
		}
		
		if(   checkBox1.isPressed()   && !checkBox1.isChecked()){
			  optionArray[1] = "true";
			  saveOptions();
		}else if(checkBox1.isPressed()&& checkBox1.isChecked()){
			  optionArray[1] = "false";
			  saveOptions();
		}
		
		if(   checkBox2.isPressed()   && !checkBox2.isChecked()){
			  optionArray[2] = "true";
			  saveOptions();
		}else if(checkBox2.isPressed()&& checkBox2.isChecked()){
			  optionArray[2] = "false";
			  saveOptions();
		}
		
		if(optionArray[0].equals("true"))checkBox0.setChecked(true);
		else checkBox0.setChecked(false);
		
		if(optionArray[1].equals("true"))checkBox1.setChecked(true);
		else checkBox1.setChecked(false);
		
		if(optionArray[2].equals("true"))checkBox2.setChecked(true);
		else checkBox2.setChecked(false);
		
		//saveOptions();
		/*//read
		if(pref.getSoundEnable())checkBox0.setChecked(true);
		if(pref.getMusicEnable())checkBox1.setChecked(true);
		if(pref.getBGMEnable())  checkBox2.setChecked(true);
		
		//write
		if(   checkBox0.isPressed()   && !checkBox0.isChecked()){
			  pref.setSoundEnable(true);			
		}else if(checkBox0.isPressed()&& checkBox0.isChecked()){
			pref.setSoundEnable(false);
		}
		
		if(checkBox1.isPressed() && !checkBox1.isChecked()){
			  pref.setMusicEnable(true);			
		}else if(checkBox1.isPressed()&& checkBox1.isChecked()){
			pref.setMusicEnable(false);
		}
		
		if(checkBox2.isPressed() && !checkBox2.isChecked()){
			  pref.setBGMEnable(true);			
		}else if(checkBox2.isPressed()&& checkBox2.isChecked()){
			pref.setBGMEnable(false);
		}
				
		//show
		if(pref.getSoundEnable())checkBox0.setChecked(true);
		else checkBox0.setChecked(false);
		
		if(pref.getMusicEnable())checkBox1.setChecked(true);
		else checkBox1.setChecked(false);
		
		if(pref.getBGMEnable())checkBox2.setChecked(true);
		else checkBox2.setChecked(false);
		
		//ok--^
		//problemas barra volume==>>>>>>
		if(progressBar.getValue()>=0 && progressBar.getValue()<=100) pref.setVolume((int) progressBar.getValue());
		if(pref.getVolume() > 0) progressBar.setValue(pref.getVolume());
		System.out.println(pref.getVolume());
		*/
		//checkbox*********************************		
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
	public void pause() {}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#resume()
	 */
	@Override
	public void resume() {}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#hide()
	 */
	@Override
	public void hide() {}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#dispose()
	 */
	@Override
	public void dispose() {
		background.dispose();
		bitmapFont.dispose();
		skin.dispose();
		stage.dispose();
	}

}
