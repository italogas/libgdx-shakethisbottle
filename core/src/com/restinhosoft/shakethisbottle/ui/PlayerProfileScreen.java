/**
 * 
 */
package com.restinhosoft.shakethisbottle.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
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
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;


/**
 * @author Mailson
 *
 */
public class PlayerProfileScreen implements Screen {
	ShakeThisBottle game;
	
	private OrthographicCamera cam;
	
	private Texture menuImg;
	
	private Stage stage;
	
	private TextureAtlas atlas;
	
	private Skin skin;
	private Skin textSkin;
	
	private BitmapFont bitmapFont;
	
	private Table table;
	
	private TextButton playerProfileText;
	private TextButton scoreText;
	private TextButton achievementText;
	private TextButton friendsText;
	private TextButton backText;

	private int width = 320;
	private int height= 480;
	
	//private int width = Gdx.graphics.getWidth();
	//private int height= Gdx.graphics.getHeight();
	
	private TextField profileTextArea;

	private FitViewport fitViewport;
	
	private Preferences pref;
	
	
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#show()
	 */
	@Override
	public void show() {
		this.game = (ShakeThisBottle) Gdx.app.getApplicationListener();
		
		fitViewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage = new Stage();
		stage.setViewport(fitViewport);
		
		Gdx.input.setInputProcessor(stage);
		
		menuImg = new Texture(Gdx.files.internal("menu.png"));
		atlas = new TextureAtlas(Gdx.files.internal("button.atlas"));
		
		skin = new Skin(atlas);
		
		bitmapFont = new BitmapFont(Gdx.files.internal("default.fnt"));
		
		pref = Gdx.app.getPreferences("My Preferences");
		
		TextButtonStyle textButtonEnableStyle = new TextButton.TextButtonStyle();
		textButtonEnableStyle.up = skin.getDrawable("blue_button");
		textButtonEnableStyle.down = skin.getDrawable("blue_button");
		textButtonEnableStyle.pressedOffsetX = 1;
		textButtonEnableStyle.pressedOffsetY = -1;
		textButtonEnableStyle.font = bitmapFont;
		
		table = new Table();
		table.setFillParent(true);
		table.setBounds(-Gdx.graphics.getWidth()/2, -Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage.addActor(table);
		
		playerProfileText = new TextButton("NAME: Player Name "+"\n ID: 26EAD857", textButtonEnableStyle);
		playerProfileText.setColor(Color.BLACK);
		playerProfileText.setHeight(height);
		playerProfileText.setWidth(width);
		playerProfileText.setDisabled(true);
		
		
		scoreText = new TextButton("SCORE", textButtonEnableStyle);
		scoreText.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new ScoreScreen());
				
			}
		});
		scoreText.pad(10);
		
		achievementText = new TextButton("ACHIEVEMENTS", textButtonEnableStyle);
		achievementText.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new AchievementsScreen());	
			}
		});
		achievementText.pad(10);
		
		friendsText = new TextButton("FRIEND", textButtonEnableStyle);
		friendsText.setColor(Color.LIGHT_GRAY);
		friendsText.setDisabled(true);
		friendsText.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
					
			}
		});
		friendsText.pad(10);
		
		backText = new TextButton("BACK", textButtonEnableStyle);
		backText.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new MainMenuScreen());
			}
		});
		backText.pad(10);
		
		
		
		
		table.add(playerProfileText);
		table.getCell(playerProfileText).spaceBottom(25);
		table.row();
		table.add(scoreText);
		table.getCell(scoreText).spaceBottom(10);
		table.row();
		table.add(achievementText);
		table.getCell(achievementText).spaceBottom(10);
		table.row();
		table.add(friendsText);
		table.getCell(friendsText).spaceBottom(10);
		table.row();
		table.add(backText);
		table.getCell(backText).spaceBottom(10);
	
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

	}

}
