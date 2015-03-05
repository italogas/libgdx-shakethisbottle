/**
 * 
 */
package com.restinhosoft.game.shakethebottle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.restinhosoft.main.LanguageManager;
import com.restinhosoft.main.ShakeThisBottle;
import com.restinhosoft.ui.GameSelectionScreen;


/**
 * @author Mailson
 *
 */
public class ShakeThisBottleStartScreen implements Screen {
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

	private LanguageManager languageManager;
	public String language;
	
	private String descriptionEN =   "In this minigame you have to shake"+
			"\n the bottle to celebrate the new"+
			"\n year if the bottle not burst will"+
			"\n be the end  of the world,"+
			"\n you have until the end of "+
			"\n the count to save the world,"+
			"\n GOOD LUCK.";
	
	private String descriptionPT =   "Nesse minigame voce tem que balancar"+
			"\n a garrafa para celebrar o ano novo"+
			"\n se a garrafa nao estourar"+
			"\n sera o fim do mundo, voce tem ate"+
			"\n o fim da contagem para salvar o mundo."+
			"\n BOA SORTE.";
	
	private String tutorialEN = 	"Shake your smartphone in any direction"+
			 "\n to count a shake in the bottle."+
			 "\n Each shake counts points in game score.";
	
	private String tutorialPT = 	"Balance o seu smartphone em qualquer"+
			 "\n direcao para agitar a garrafa." +
			 "\n Agitar a garrafa conta pontos para voce.";
	
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
		
		cam = new OrthographicCamera();
		//cam.setToOrtho(false, width, height);
		cam.setToOrtho(false, 320, 480);
		
		stage = new Stage();
		
		Gdx.input.setInputProcessor(stage);
		
		menuImg = new Texture(Gdx.files.internal("background_profile_screen.png"));
		
		atlas = new TextureAtlas(Gdx.files.internal("button.atlas"));
		gameImageAtlas = new TextureAtlas(Gdx.files.internal("shakethisbottle/shake_intro.atlas"));
		
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
		gameImageButtonStyle.up = gameImageSkin.getDrawable("shakeintro");
		gameImageButtonStyle.down = gameImageSkin.getDrawable("shakeintro");
		gameImageButtonStyle.pressedOffsetX = 1;
		gameImageButtonStyle.pressedOffsetY = -1;
		gameImageButtonStyle.font = bitmapFont;
		
		table = new Table();
		table.setFillParent(true);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage.addActor(table);
		
		gameImageBTFake = new TextButton("", gameImageButtonStyle);
		gameImageBTFake.setDisabled(true);
		
		
		//descriptionButton = new TextButton("DESCRIPTION", textButtonEnableStyle);
		descriptionButton = new TextButton((language.equals(languageManager.languageEN)?"Description":"Descricao"), textButtonEnableStyle);
		descriptionButton.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				gameImageBTFake.setText(language.equals(languageManager.languageEN)? descriptionEN: descriptionPT);
			}
		});
		
		tutorialButton = new TextButton("Tutorial", textButtonEnableStyle);
		tutorialButton.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				gameImageBTFake.setText(language.equals(languageManager.languageEN)? tutorialEN: tutorialPT);
			}
		});
		
		playButton = new TextButton((language.equals(languageManager.languageEN)?"Play":"Jogar"), textButtonEnableStyle);
		playButton.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new ShakeThisBottleGameScreen(0,1,0));
			}
		});
		
		backButton = new TextButton((language.equals(languageManager.languageEN)?"Back":"Voltar"), textButtonEnableStyle);
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
