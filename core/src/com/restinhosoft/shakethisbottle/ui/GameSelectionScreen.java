/**
 * 
 */
package com.restinhosoft.shakethisbottle.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
import com.restinhosoft.game.hitthecircle.HitTheCircleStartScreen;
import com.restinhosoft.game.memorizefast.MemorizeFastStartScreen;
import com.restinhosoft.game.shakethisbottle.ShakeThisBottleStartScreen;
import com.restinhosoft.shakethisbottle.hitthecircle.HitTheBalloonStartScreen;

/**
 * @author Ítalo
 *
 */
public class GameSelectionScreen implements Screen {

	private ShakeThisBottle game;
	private OrthographicCamera cam;
	private Texture background;
	private Texture button;
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

		
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#show()
	 */
	@Override
	public void show() {
		this.game = (ShakeThisBottle) Gdx.app.getApplicationListener();
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 320, 480);
		
		stage = new Stage();
		
		Gdx.input.setInputProcessor(stage);
		
		menuImg = new Texture(Gdx.files.internal("menu.png"));
		atlas1 = new TextureAtlas(Gdx.files.internal("shakeit_button.atlas"));
		atlas2 = new TextureAtlas(Gdx.files.internal("button.atlas"));
		
		
		skin1 = new Skin(atlas1);
		skin2 = new Skin(atlas2);
		
		bitmapFont = new BitmapFont(Gdx.files.internal("default.fnt"));
		
		table = new Table();
		table.setFillParent(true);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//		table.setDebug(true);
		stage.addActor(table);
		
		TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.up = skin2.getDrawable("blue_button");
		textButtonStyle.down = skin2.getDrawable("blue_button");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = bitmapFont;
		
		textButton = new TextButton("Back to MAIN MENU", textButtonStyle);
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
				game.setScreen(new HitTheBalloonStartScreen());
				
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
				game.setScreen(new HitTheCircleStartScreen());
			}
		});
		
		imageButton5 = new ImageButton(imageButtonStyle);
		imageButton5.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				// TODO Auto-generated method stub
				
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
	public void resize(int width, int height) {}

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
