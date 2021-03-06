/**
 * 
 */
package com.restinhosoft.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.restinhosoft.game.hittheballoon.StartScreen;
import com.restinhosoft.game.hitthecolor.ColorGameMenu;
import com.restinhosoft.game.memorizefast.NovaGeniusGameMenu;
import com.restinhosoft.game.shakethebottle.ShakeTheBottleGameMenu;
import com.restinhosoft.main.AudioManager;
import com.restinhosoft.main.LanguageManager;
import com.restinhosoft.main.ShakeThisBottle;

/**
 * @author Italo
 *
 */
public class GameSelectionScreen implements Screen {

	private ShakeThisBottle game;
	
	//*****************************MUDANCAS***********
	
	@SuppressWarnings("unused")
	private Texture textureMEMO;
	@SuppressWarnings("unused")
	private Texture textureSHAKE;
	@SuppressWarnings("unused")
	private Texture textureBALL;
	@SuppressWarnings("unused")
	private Texture textureCOLOR;
	
	private TextureAtlas textureAtlasMEMO;
	private TextureAtlas textureAtlasSHAKE;
	private TextureAtlas textureAtlasBALL;
	private TextureAtlas textureAtlasCOLOR;
	
	private Skin skinMEMO;
	private Skin skinSHAKE;
	private Skin skinBALL;
	private Skin skinCOLOR;
	
	private TextButtonStyle memoBTStyle;
	private TextButtonStyle shakeBTStyle;
	private TextButtonStyle ballBTStyle;
	private TextButtonStyle colorBTStyle;
	
	private TextButton memoButton;
	private TextButton shakeButton;
	private TextButton ballButton;
	private TextButton colorButton;
	
	//*****************************MUDANCAS***********
	private Stage stage;
	private Texture menuImg;
	private TextureAtlas atlas1;
	private Skin skin1;
	private BitmapFont bitmapFont;
	private Table table;
	private TextureAtlas atlas2;
	private Skin skin2;
	private TextButton textButton;
	private FitViewport fitViewport;
	
	private LanguageManager languageManager;
	public String language;

	private AudioManager audioManager;
	
	private TextureAtlas creatingAtlas(String file){ 
		return new TextureAtlas(Gdx.files.internal(file));
	}
	
	private Skin creatingSkin(TextureAtlas atlas){ 
		return new Skin(atlas);
	}
	
	@SuppressWarnings("unused")
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
	
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#show()
	 */
	@Override
	public void show() {
		this.game = (ShakeThisBottle) Gdx.app.getApplicationListener();
		
		languageManager = LanguageManager.getInstance();
		try {
			language = languageManager.getLanguage();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		audioManager = new AudioManager("audio/gameselection.ogg");
		audioManager.playMusic();
		
		fitViewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		stage = new Stage();
		stage.setViewport(fitViewport);
		
		Gdx.input.setInputProcessor(stage);
		
		menuImg = new Texture(Gdx.files.internal("icons/sub_menu.png"));
		atlas1 = new TextureAtlas(Gdx.files.internal("shakeit_button.atlas"));
		atlas2 = new TextureAtlas(Gdx.files.internal("button.atlas"));
		
		skin1 = new Skin(atlas1);
		skin2 = new Skin(atlas2);
		
		bitmapFont = new BitmapFont(Gdx.files.internal("neuropol-x-free-m.fnt"));
		
		//******************************MUDANCAS**************************************************

		textureMEMO = new Texture(Gdx.files.internal("icons/games_memoria.png"));
		textureSHAKE= new Texture(Gdx.files.internal("icons/games_garrafa.png"));
		textureCOLOR= new Texture(Gdx.files.internal("icons/games_colour.png"));
		textureBALL = new Texture(Gdx.files.internal("icons/games_balao.png"));

		textureAtlasMEMO = creatingAtlas("icons/games_memoria.atlas");
		textureAtlasSHAKE= creatingAtlas("icons/games_garrafa.atlas");
		textureAtlasCOLOR= creatingAtlas("icons/games_colour.atlas");
		textureAtlasBALL = creatingAtlas("icons/games_balao.atlas");
		
		skinMEMO = creatingSkin(textureAtlasMEMO);
		skinSHAKE = creatingSkin(textureAtlasSHAKE);
		skinCOLOR = creatingSkin(textureAtlasCOLOR);
		skinBALL = creatingSkin(textureAtlasBALL);

		memoBTStyle = creatingTextButtonStyles(skinMEMO, "game_memoria", bitmapFont);
		shakeBTStyle= creatingTextButtonStyles(skinSHAKE,"game_garrafa", bitmapFont);
		colorBTStyle= creatingTextButtonStyles(skinCOLOR,"game_colour", bitmapFont);
		ballBTStyle = creatingTextButtonStyles(skinBALL, "game_balao", bitmapFont);
		
		TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = bitmapFont;
		
		memoButton = new TextButton("", memoBTStyle);
		memoButton.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				//game.setScreen(new MemorizeFastStartScreen());	
				game.setScreen(new NovaGeniusGameMenu());
				dispose();
			}
		});
		
		shakeButton = new TextButton("", shakeBTStyle);
		shakeButton.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				//game.setScreen(new ShakeThisBottleStartScreen());
				game.setScreen(new ShakeTheBottleGameMenu());
				dispose();
			}
		});
		colorButton = new TextButton("", colorBTStyle);
		colorButton.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new ColorGameMenu());
				//game.setScreen(new HitTheCircleStartScreen());
				dispose();
			}
		});
		ballButton = new TextButton("", ballBTStyle);
		ballButton.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new StartScreen());
				dispose();
			}
		});
		
		textButton = new TextButton((language.equals(languageManager.languageEN)?"Back ":"Voltar "), textButtonStyle);
		textButton.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new MainMenuScreen());
				dispose();
			}
		});
		textButton.pad(15);
		
		Table table2 = creatingTable(-Gdx.graphics.getWidth()/2, -Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth(), 100);
		table = new Table();
		table.setFillParent(true);
		table.setBounds(-Gdx.graphics.getWidth()/2, -Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-100);
		stage.addActor(table);
		stage.addActor(table2);
		
		table.row().pad(20);
		table.add(ballButton).pad(20);
		//table.columnDefaults(3);
		table.add(memoButton);
		table.row().pad(20);
		table.add(shakeButton).pad(20);
		table.add(colorButton);
		table.row().pad(15);
		//
		table.align(Align.center);
		/*table.add(imageButton1);
		table.add(imageButton2);
		table.add(imageButton3);
		table.row();
		table.add(imageButton4);
		table.add(imageButton5);
		table.add(imageButton6);
		table.row();
		table.add();*/
		table2.add(textButton).spaceBottom(20);
		table2.align(Align.center);
		//table2.align(Align.bottom);

	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#render(float)
	 */
	@Override
	public void render(float delta) {
		GL20 gl = Gdx.gl;
		gl.glClearColor(1, 1, 1, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.begin();
		game.batch.draw(menuImg, 0, 0);
		game.batch.end();
		
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
		//background.dispose();
		//button.dispose();
		//backtomenu.dispose();
		audioManager.close();
	}
	
}
