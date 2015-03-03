/**
 * 
 */
package com.restinhosoft.ui.menus;

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
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.restinhosoft.games.hittheballoon.StartScreen;
import com.restinhosoft.games.hitthecolor.HitTheCircleStartScreen;
import com.restinhosoft.games.hitthecolor.HitTheColor;
import com.restinhosoft.games.memorizefast.MemorizeFastStartScreen;
import com.restinhosoft.games.shakethebottle.ShakeThisBottleStartScreen;
import com.restinhosoft.options.LanguageManager;
import com.restinhosoft.shakethisbottle.ui.ShakeThisBottle;

/**
 * @author Italo
 *
 */
public class GameSelectionScreen implements Screen {

	private ShakeThisBottle game;
	private Texture background;
	private Texture button;
	
	//*****************************MUDANCAS***********
	
	private Texture textureMEMO;
	private Texture textureSHAKE;
	private Texture textureBALL;
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
	private Texture backtomenu;
	private Stage stage;
	private Texture menuImg;
	private TextureAtlas atlas1;
	private Skin skin1;
	private BitmapFont bitmapFont;
	private Table table;
	private ImageButton imageButton1;
	private ImageButton imageButton2;
	private ImageButton imageButton3;
	private ImageButton imageButton4;
	private ImageButton imageButton5;
	private Actor imageButton6;
	private TextureAtlas atlas2;
	private Skin skin2;
	private TextButton textButton;
	private FitViewport fitViewport;
	
	private LanguageManager languageManager;
	public String language;

		
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
			e.printStackTrace();
		}
		
		fitViewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		stage = new Stage();
		stage.setViewport(fitViewport);
		
		Gdx.input.setInputProcessor(stage);
		
		menuImg = new Texture(Gdx.files.internal("menu.png"));
		atlas1 = new TextureAtlas(Gdx.files.internal("shakeit_button.atlas"));
		atlas2 = new TextureAtlas(Gdx.files.internal("button.atlas"));
		
		skin1 = new Skin(atlas1);
		skin2 = new Skin(atlas2);
		
		bitmapFont = new BitmapFont(Gdx.files.internal("default.fnt"));
		
		//******************************MUDANCAS**************************************************

		textureMEMO = new Texture(Gdx.files.internal("menugame/menu_memoria.png"));
		textureSHAKE= new Texture(Gdx.files.internal("menugame/menu_garrafa.png"));
		textureCOLOR= new Texture(Gdx.files.internal("menugame/menu_color.png"));
		textureBALL = new Texture(Gdx.files.internal("menugame/menu_balloon.png"));

		textureAtlasMEMO = creatingAtlas("menugame/menu_memoria.atlas");
		textureAtlasSHAKE= creatingAtlas("menugame/menu_garrafa.atlas");
		textureAtlasCOLOR= creatingAtlas("menugame/menu_color.atlas");
		textureAtlasBALL = creatingAtlas("menugame/menu_balloon.atlas");
		
		skinMEMO = creatingSkin(textureAtlasMEMO);
		skinSHAKE = creatingSkin(textureAtlasSHAKE);
		skinCOLOR = creatingSkin(textureAtlasCOLOR);
		skinBALL = creatingSkin(textureAtlasBALL);

		memoBTStyle = creatingTextButtonStyles(skinMEMO, "memoriabt", bitmapFont);
		shakeBTStyle= creatingTextButtonStyles(skinSHAKE,"garrafabt", bitmapFont);
		colorBTStyle= creatingTextButtonStyles(skinCOLOR,"colorbt", bitmapFont);
		ballBTStyle = creatingTextButtonStyles(skinBALL, "balloonbt", bitmapFont);
		
		memoButton = new TextButton("", memoBTStyle);
		memoButton.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new MemorizeFastStartScreen());	
			}
		});
		
		shakeButton = new TextButton("", shakeBTStyle);
		shakeButton.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new ShakeThisBottleStartScreen());	
			}
		});
		colorButton = new TextButton("", colorBTStyle);
		colorButton.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new HitTheColor());	
			}
		});
		ballButton = new TextButton("", ballBTStyle);
		ballButton.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new StartScreen());	
			}
		});
		
		Table table2 = creatingTable(-Gdx.graphics.getWidth()/2, -Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth(), 100);
		//******************************MUDANCAS**************************************************
		table = new Table();
		table.setFillParent(true);
		table.setBounds(-Gdx.graphics.getWidth()/2, -Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-100);
//		table.setDebug(true);
		stage.addActor(table);
		stage.addActor(table2);
		
		TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.up = skin2.getDrawable("blue_button");
		textButtonStyle.down = skin2.getDrawable("blue_button");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = bitmapFont;
		
		//textButton = new TextButton("Back to MAIN MENU", textButtonStyle);
		textButton = new TextButton((language.equals(languageManager.languageEN)?"Back to Menu":"Voltar para Menu"), textButtonStyle);
		textButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new MainMenuScreen());
			}
		});
		
		ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();
		imageButtonStyle.up = skin1.getDrawable("shakeitbutton");
		imageButtonStyle.down = skin1.getDrawable("shakeitbutton");
		imageButtonStyle.pressedOffsetX = 1;
		imageButtonStyle.pressedOffsetX = -1;
		
		imageButton1 = new ImageButton(imageButtonStyle);
		imageButton1.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new StartScreen());
				
			}
		});
		
		imageButton2 = new ImageButton(imageButtonStyle);
		imageButton2.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new ShakeThisBottleStartScreen());
				
			}
		});
		
		imageButton3 = new ImageButton(imageButtonStyle);
		imageButton3.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				// TODO Auto-generated method stub
				game.setScreen(new MemorizeFastStartScreen());
			}
		});
		
		imageButton4 = new ImageButton(imageButtonStyle);
		imageButton4.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				// TODO Auto-generated method stub
				//game.setScreen(new HitTheCircleStartScreen());
				game.setScreen(new HitTheColor());
			}
		});
		
		imageButton5 = new ImageButton(imageButtonStyle);
		imageButton5.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				//game.setScreen(new DoNotShakeThisBottleStartScreen());
				
			}
		});
		
		imageButton6 = new ImageButton(imageButtonStyle);
		imageButton6.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				// TODO Auto-generated method stub
				
			}
		});
		table.add(ballButton);
		//table.columnDefaults(3);
		table.add(memoButton);
		table.row();
		table.add(shakeButton);
		table.add(colorButton);
		table.row();
		table.row();
		table.row();
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
		table2.add(textButton);
		table2.align(Align.bottom);

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
		background.dispose();
		button.dispose();
		backtomenu.dispose();
	}
	
}
