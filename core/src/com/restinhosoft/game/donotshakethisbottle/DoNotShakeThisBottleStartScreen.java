/**
 * 
 */
package com.restinhosoft.game.donotshakethisbottle;

import com.badlogic.gdx.Gdx;
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
import com.restinhosoft.shakethisbottle.ui.AchievementsScreen;
import com.restinhosoft.shakethisbottle.ui.GameSelectionScreen;
import com.restinhosoft.shakethisbottle.ui.MainMenuScreen;
import com.restinhosoft.shakethisbottle.ui.ScoreScreen;
import com.restinhosoft.shakethisbottle.ui.ShakeThisBottle;


/**
 * @author Mailson
 *
 */
public class DoNotShakeThisBottleStartScreen implements Screen {
	ShakeThisBottle game;
	
	private OrthographicCamera cam;
	
	private Texture menuImg;
	
	private Stage stage;
	
	private TextureAtlas atlas;
	private TextureAtlas gameImageAtlas;
	
	private Skin skin;
	private Skin gameImageSkin;
	
	private BitmapFont bitmapFont;
	
	private Table table;
	
	private TextButton gameImageBTFake;
	private TextButton descriptionButton;
	private TextButton tutorialButton;
	private TextButton playButton;
	private TextButton backButton;

	private int width = 320;
	private int height= 480;
	
	//private int width = Gdx.graphics.getWidth();
	//private int height= Gdx.graphics.getHeight();
	
	private TextField profileTextArea;
	
	
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#show()
	 */
	@Override
	public void show() {
		this.game = (ShakeThisBottle) Gdx.app.getApplicationListener();
		
		cam = new OrthographicCamera();
		//cam.setToOrtho(false, width, height);
		cam.setToOrtho(false, 320, 480);
		
		stage = new Stage();
		
		Gdx.input.setInputProcessor(stage);
		
		menuImg = new Texture(Gdx.files.internal("background_profile_screen.png"));
		
		atlas = new TextureAtlas(Gdx.files.internal("button.atlas"));
		gameImageAtlas = new TextureAtlas(Gdx.files.internal("donotshakethisbottle/donotshake_intro.atlas"));
		
		skin = new Skin(atlas);
		gameImageSkin = new Skin(gameImageAtlas);
		
		bitmapFont = new BitmapFont(Gdx.files.internal("default.fnt"));
		
		TextButtonStyle textButtonEnableStyle = new TextButton.TextButtonStyle();
		textButtonEnableStyle.up = skin.getDrawable("blue_button");
		textButtonEnableStyle.down = skin.getDrawable("blue_button");
		textButtonEnableStyle.pressedOffsetX = 1;
		textButtonEnableStyle.pressedOffsetY = -1;
		textButtonEnableStyle.font = bitmapFont;
		
		TextButtonStyle gameImageButtonStyle = new TextButton.TextButtonStyle();
		gameImageButtonStyle.up = gameImageSkin.getDrawable("donotshake_intro");
		gameImageButtonStyle.down = gameImageSkin.getDrawable("donotshake_intro");
		gameImageButtonStyle.pressedOffsetX = 1;
		gameImageButtonStyle.pressedOffsetY = -1;
		gameImageButtonStyle.font = bitmapFont;
		
		table = new Table();
		table.setFillParent(true);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage.addActor(table);
		
		gameImageBTFake = new TextButton("", gameImageButtonStyle);
		gameImageBTFake.setDisabled(true);
		
		
		descriptionButton = new TextButton("DESCRIPTION", textButtonEnableStyle);
		descriptionButton.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				gameImageBTFake.setText(
						"In this mini game you can"
				         +"\n not move the bottle,"
						 + "\n because it contains an"
				         +"\n unstable liquid that can explode."
						 + "\n To save the world, you should deicar"
				         +"\n your smartphone stable during the"+""
				         + "\n countdown,and if the move "
				         +"\nrecklessly device, it's game over.");
				
			}
		});
		
		tutorialButton = new TextButton("TUTORIAL", textButtonEnableStyle);
		tutorialButton.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				gameImageBTFake.setText(
						"If your smartphone moves in any direction"+
					 "\n it will to count a shake."+
					 "\n And is GAMEOVER.");
			}
		});
		
		playButton = new TextButton("PLAY", textButtonEnableStyle);
		playButton.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new DoNotShakeThisBottleGameScreen(0,1,0));
			}
		});
		
		backButton = new TextButton("BACK", textButtonEnableStyle);
		backButton.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new GameSelectionScreen());
			}
		});
		
		table.add(gameImageBTFake);
		table.row();
		table.add(descriptionButton);
		table.row();
		table.add(tutorialButton);
		table.row();
		table.add(playButton);
		table.row();
		table.add(backButton);
	
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
		/*if(width>0&& height>0){
			this.width = width;
			this.height=height;
		}*/
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
		gameImageAtlas.dispose();
		atlas.dispose();
		bitmapFont.dispose();
		menuImg.dispose();
		stage.dispose();

	}

}
