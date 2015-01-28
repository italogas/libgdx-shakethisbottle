/**
 * 
 */
package com.restinhosoft.shakethisbottle.ui;

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
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * @author Ítalo
 *
 */
public class AchievementsScreen implements Screen {

	private ShakeThisBottle game;
	
	private OrthographicCamera cam;
	
	private Texture background;
	private Texture button;
	private Texture backtomenu;
	
	private Stage stage;
	
	private Texture menuImg;
	
	private TextureAtlas atlas1;
	private TextureAtlas atlas2;
	
	private Skin skin1;
	private Skin skin2;
	
	private BitmapFont bitmapFont;
	
	private Table table;
	
	private ImageButton achievement01BT;
	private ImageButton achievement02BT;
	private ImageButton achievement03BT;
	private ImageButton achievement04BT;
	private ImageButton achievement05BT;
	
	private Actor imageButton6;
	
	private TextButton backtxBT;
	
	private int width = 320;
	private int height= 480;
	
	//private int width = Gdx.graphics.getWidth();
	//private int height= Gdx.graphics.getHeight();
		
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#show()
	 */
	@Override
	public void show() {
		this.game = (ShakeThisBottle) Gdx.app.getApplicationListener();
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, width, height);
		
		stage = new Stage();
		
		Gdx.input.setInputProcessor(stage);
		
		menuImg = new Texture(Gdx.files.internal("background_profile_screen.png"));
		
		atlas1 = new TextureAtlas(Gdx.files.internal("conquistas00_button.atlas"));
		
		atlas2 = new TextureAtlas(Gdx.files.internal("button.atlas"));
		
		
		skin1 = new Skin(atlas1);
		skin2 = new Skin(atlas2);
		
		bitmapFont = new BitmapFont(Gdx.files.internal("default.fnt"));
		
		table = new Table();
		table.setFillParent(true);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage.addActor(table);
		
		TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.up = skin2.getDrawable("blue_button");
		textButtonStyle.down = skin2.getDrawable("blue_button");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = bitmapFont;
		
		backtxBT = new TextButton("BACK", textButtonStyle);
		backtxBT.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new PlayerProfileScreen());
			}
		});
		
		ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();
		imageButtonStyle.up = skin1.getDrawable("conquistas00");
		imageButtonStyle.down = skin1.getDrawable("conquistas00");
		imageButtonStyle.pressedOffsetX = 1;
		imageButtonStyle.pressedOffsetX = -1;
		
		achievement01BT = new ImageButton(imageButtonStyle);
		achievement01BT.setColor(Color.BLACK);
		achievement01BT.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				achievement01BT.setColor(Color.RED);
				
			}
		});
		
		achievement02BT = new ImageButton(imageButtonStyle);
		achievement02BT.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				// TODO Auto-generated method stub
				
			}
		});
		
		achievement03BT = new ImageButton(imageButtonStyle);
		achievement03BT.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				// TODO Auto-generated method stub
				
			}
		});
		
		achievement04BT = new ImageButton(imageButtonStyle);
		achievement04BT.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				// TODO Auto-generated method stub
				
			}
		});
		
		achievement05BT = new ImageButton(imageButtonStyle);
		achievement05BT.addListener(new ChangeListener() {
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
		
		table.add(achievement01BT);
		table.add(achievement02BT);
		table.add(achievement03BT);
		table.row();
		table.add(achievement04BT);
		table.add(achievement05BT);
		table.add(imageButton6);
		table.row();
		table.add();
		table.add(backtxBT);

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
		if(width>0&& height>0){
			this.width = width;
			this.height=height;
		}
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
