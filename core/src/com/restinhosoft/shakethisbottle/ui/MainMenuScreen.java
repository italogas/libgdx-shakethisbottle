/**
 * 
 */
package com.restinhosoft.shakethisbottle.ui;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

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
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.restinhosoft.game.hittheballoon.ActorAccessor;

/**
 * @author Ítalo
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
	private TextButton textButton1;
	private TextButton textButton2;
	private TextButton textButton3;
	private TextButton textButton4;
	private TweenManager tweenManager;
	private FitViewport fitViewport;

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
		
		TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("blue_button");
		textButtonStyle.down = skin.getDrawable("blue_button");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = bitmapFont;
		
		table = new Table();
		table.setFillParent(true);
		table.setBounds(-Gdx.graphics.getWidth()/2, -Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage.addActor(table);
		
		textButton1 = new TextButton("Play Game", textButtonStyle);
		textButton1.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new GameSelectionScreen());
			}
		});
		textButton1.pad(15);
		
		textButton2 = new TextButton("Options", textButtonStyle);
		textButton2.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new OptionsScreen());
			}
		});
		textButton2.pad(15);
		
		textButton3 = new TextButton("Player Profile", textButtonStyle);
		textButton3.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				//TO DO
				
				game.setScreen(new PlayerProfileScreen());//TEST		
			}
		});
		textButton3.pad(15);
		
		textButton4 = new TextButton("Exit", textButtonStyle);
		textButton4.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				//	app closed correctly
				Gdx.app.exit();
			}
		});
		textButton4.pad(15);
		
		table.add(textButton1);
		table.getCell(textButton1).spaceBottom(10);
		table.row();
		table.add(textButton2);
		table.getCell(textButton2).spaceBottom(10);
		table.row();
		table.add(textButton3);
		table.getCell(textButton3).spaceBottom(10);
		table.row();
		table.add(textButton4);
//		table.getCell(textButton4).spaceBottom(15);
		
		//		simple animation
		tweenManager = new TweenManager();
		Tween.registerAccessor(Actor.class, new ActorAccessor());
		
		Timeline.createSequence().beginSequence()
			.push(Tween.set(textButton1, ActorAccessor.ALPHA).target(0))
			.push(Tween.set(textButton2, ActorAccessor.ALPHA).target(0))
			.push(Tween.set(textButton3, ActorAccessor.ALPHA).target(0))
			.push(Tween.set(textButton4, ActorAccessor.ALPHA).target(0))
			.push(Tween.to(textButton1, ActorAccessor.ALPHA, .5f).target(1))
			.push(Tween.to(textButton2, ActorAccessor.ALPHA, .5f).target(1))
			.push(Tween.to(textButton3, ActorAccessor.ALPHA, .5f).target(1))
			.push(Tween.to(textButton4, ActorAccessor.ALPHA, .5f).target(1))
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
		//	app must be resized correctly
//		stage.getViewport().update(width, height, true);
//		table.invalidateHierarchy();
//		table.setSize(width, height);
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
