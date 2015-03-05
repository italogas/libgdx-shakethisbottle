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
	public BitmapFont bitmapFont;
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
	private static String options = "true true true 25";
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
		return readString;
		
	}
	
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#show()
	 */
	@Override
	public void show() {
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
		
		atlas = new TextureAtlas(Gdx.files.internal("uiskin.atlas"));
		
		skin = new Skin(atlas);
		
		bitmapFont = new BitmapFont(Gdx.files.internal("default.fnt"));
		
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
		textButtonStyle.up = skin.getDrawable("default-rect");
		textButtonStyle.down = skin.getDrawable("default-rect-down");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = bitmapFont;
		
		//backButton = new TextButton("Back", textButtonStyle);
		backButton = new TextButton((language.equals(languageManager.languageEN)?"Back: ":"Voltar "), textButtonStyle);
		backButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new OptionsScreen());
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
		table.getCell(progressBar).spaceBottom(10);
		table.row();
		table.add(backButton);

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
		game.batch.draw(background, 0, 0);
		game.batch.end();
		
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
