/**
 * 
 */
package com.restinhosoft.ui;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.restinhosoft.game.hittheballoon.ActorAccessor;
import com.restinhosoft.main.AudioManager;
import com.restinhosoft.main.LanguageManager;
import com.restinhosoft.main.ShakeThisBottle;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Text;

/**
 * @author Italo
 *
 */
public class MainMenuScreen implements Screen {

	ShakeThisBottle game;
	private Texture menuImg;
	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private BitmapFont bitmapFont;
	private Table table;
	
	private Table tableTitle;
	
	private TextButton gameSelectionBT;
	private TextButton optionBT;
	private TextButton playerProfileBT;
	private TextButton exitBT;
	
	private TextButton title;
	
	private TweenManager tweenManager;
	private FitViewport fitViewport;
	
	private LanguageManager languageManager;
	public String language;
	
	private AudioManager audioManager;

		/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#show()
	 */
	@Override
	public void show() {
		this.game = (ShakeThisBottle) Gdx.app.getApplicationListener();
		
		languageManager = LanguageManager.getInstance();
		
		audioManager = new AudioManager("audio/7inn.ogg");
		if(!audioManager.getMusic().isPlaying()) audioManager.playMusic();
		
		
		try {
			language = languageManager.getLanguage();
			if(language ==null)System.exit(0);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		fitViewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		stage = new Stage();
		stage.setViewport(fitViewport);
		
		Gdx.input.setInputProcessor(stage);
		
		menuImg = new Texture(Gdx.files.internal("main/main_menu.png"));
		
		atlas = new TextureAtlas(Gdx.files.internal("button.atlas"));
		
		skin = new Skin(atlas);
		
		bitmapFont = new BitmapFont(Gdx.files.internal("default.fnt"));
		
		TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("blue_button");
		textButtonStyle.down = skin.getDrawable("blue_button");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = bitmapFont;
	
		table = new Table();
		table.setFillParent(true);
		table.setBounds(-Gdx.graphics.getWidth()/2, -Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		tableTitle = new Table();
		tableTitle.setFillParent(true);
		tableTitle.setBounds(-155, -20, 320, 200);
		
		stage.addActor(tableTitle);
		stage.addActor(table);
		
		gameSelectionBT = new TextButton((language.equals(languageManager.languageEN)?"Play Game ":"Jogar"),textButtonStyle);
		gameSelectionBT.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new GameSelectionScreen());
				dispose();
			}
		});
		gameSelectionBT.pad(15);
		
		optionBT = new TextButton((language.equals(languageManager.languageEN)?"Options ":"Opcoes"), textButtonStyle);
		optionBT.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new OptionsScreen());
				dispose();
			}
		});
		optionBT.pad(15);
		
		playerProfileBT = new TextButton((language.equals(languageManager.languageEN)?"Player Profile ":"Perfil do Jogador"), textButtonStyle);
		playerProfileBT.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new PlayerProfileScreen());//TEST
				dispose();
			}
		});
		playerProfileBT.pad(15);
		
		exitBT = new TextButton((language.equals(languageManager.languageEN)?"Exit ":"Sair"), textButtonStyle);
		exitBT.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Gdx.app.exit();
			}
		});
		exitBT.pad(15);
		
		title = new TextButton("THE GAME NAME", textButtonStyle);
		title.setDisabled(true);
		
		tableTitle.add(title);
		tableTitle.row();
		
		table.add(gameSelectionBT);
		table.getCell(gameSelectionBT).spaceBottom(10);
		table.row();
		table.add(optionBT);
		table.getCell(optionBT).spaceBottom(10);
		table.row();
		table.add(playerProfileBT);
		table.getCell(playerProfileBT).spaceBottom(10);
		table.row();
		table.add(exitBT);
		table.align(Align.right);
		
		//		simple animation
		tweenManager = new TweenManager();
		Tween.registerAccessor(Actor.class, new ActorAccessor());
		
		Timeline.createSequence().beginSequence()
			.push(Tween.set(gameSelectionBT, ActorAccessor.ALPHA).target(0))
			.push(Tween.set(optionBT, ActorAccessor.ALPHA).target(0))
			.push(Tween.set(playerProfileBT, ActorAccessor.ALPHA).target(0))
			.push(Tween.set(exitBT, ActorAccessor.ALPHA).target(0))
			.push(Tween.to(gameSelectionBT, ActorAccessor.ALPHA, .5f).target(1))
			.push(Tween.to(optionBT, ActorAccessor.ALPHA, .5f).target(1))
			.push(Tween.to(playerProfileBT, ActorAccessor.ALPHA, .5f).target(1))
			.push(Tween.to(exitBT, ActorAccessor.ALPHA, .5f).target(1))
			.end().start(tweenManager);
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
		
		tweenManager.update(delta);
		
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
		
		audioManager.stopMusic();
		audioManager.close();
	}

	
	
}
