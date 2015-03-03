/**
 * 
 */
package com.restinhosoft.ui.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
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
	
	private Texture buttonMEMO;
	private Texture buttonSHAKE;
	private Texture buttonBALL;
	private Texture buttonCOLOR;
	
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
		
		table = new Table();
		table.setFillParent(true);
		table.setBounds(-Gdx.graphics.getWidth()/2, -Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//		table.setDebug(true);
		stage.addActor(table);
		
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
		
		table.add(imageButton1);
		table.add(imageButton2);
		table.add(imageButton3);
		table.row();
		table.add(imageButton4);
		table.add(imageButton5);
		table.add(imageButton6);
		table.row();
		table.add();
		table.add(textButton);

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
