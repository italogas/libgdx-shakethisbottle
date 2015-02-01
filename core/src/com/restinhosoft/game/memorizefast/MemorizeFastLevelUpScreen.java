/**
 * 
 */
package com.restinhosoft.game.memorizefast;

import java.util.Timer;
import java.util.TimerTask;

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
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.restinhosoft.shakethisbottle.ui.ShakeThisBottle;


/**
 * @author Mailson
 *
 */
public class MemorizeFastLevelUpScreen implements Screen {
//********************LOGICS*****************************************

	private int level;
	private int score;
	private int bonus;
	
		
//********************GRAPHICS***************************************
	ShakeThisBottle game;
	
	private OrthographicCamera camera;
	
	private Texture memorizefastGameLevelUpYellow;
	private Texture memorizefastGameLevelUpRed;
	private Texture memorizefastGameBackGroundScreen;
	
	private Stage stage;
	
	private TextureAtlas atlasGameTexts;
	private TextureAtlas atlasSquare;
	
	private Skin squareSkin;
	private Skin gameTextSkin;
	
	private BitmapFont bitmapFont;
	
	private Table table;
	
	private TextButton levelBT;
	private TextButton scoreBT;
	private TextButton bonusBT;
	private TextButton okBT;

	private TextButtonStyle gameStatsStyle;
	private TextButtonStyle squareStyle;

	private int width = 320;
	private int height= 480;
	
	//private int width = Gdx.graphics.getWidth();
	//private int height= Gdx.graphics.getHeight();
	
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
	
	private Timer counterTimer;
	private TimerTask task;
	private final long second = 1000;
	
	private int showTimer = 5;
		
	private void updates(){
		levelBT.setText( "LEVEL: "+ level);
		scoreBT.setText( "SCORE: "+ score);
		bonusBT.setText( "BONUS: "+ bonus);
	}

	public MemorizeFastLevelUpScreen(int score, int level,int bonus) {
		this.score= score;
		this.level= level;
		this.bonus= bonus;
		
		//okBT.setVisible(false);
	}
	
	public int getScore(){return score;}
	public int getLevel(){return level;}
	public int getBonus(){return bonus;}
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#show()
	 */
	@Override
	public void show() {
		this.game = (ShakeThisBottle) Gdx.app.getApplicationListener();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, width, height);
		
		stage = new Stage();
		
		Gdx.input.setInputProcessor(stage);

		memorizefastGameLevelUpYellow = new Texture(Gdx.files.internal(
				 								 "memorizefast_img/memorizefast_levelup_yellow.png"));
		memorizefastGameLevelUpRed   = new Texture(Gdx.files.internal(
												 "memorizefast_img/memorizefast_levelup_red.png"));
		
		memorizefastGameBackGroundScreen = memorizefastGameLevelUpYellow;
		
		this.atlasGameTexts    =creatingAtlas( "imageghostsqr.atlas");
		this.atlasSquare=creatingAtlas( "memorizefast_img/memorizefast_square.atlas");
		
		this.gameTextSkin=creatingSkin( this.atlasGameTexts);
		this.squareSkin  =creatingSkin( this.atlasSquare);
		
		bitmapFont = new BitmapFont(Gdx.files.internal("default.fnt"));
		
		this.gameStatsStyle=creatingTextButtonStyles( this.gameTextSkin, "imageghost_sqr", bitmapFont);
		this.squareStyle   =creatingTextButtonStyles( this.squareSkin, "memorizefast_square", bitmapFont);
				
		this.levelBT=creatingTextButton( "LEVEL: "+ level, this.gameStatsStyle, true);
		this.bonusBT=creatingTextButton( "BONUS: "+ bonus, this.gameStatsStyle, true);
		this.scoreBT=creatingTextButton( "SCORE: "+ score, this.gameStatsStyle, true);
		
		this.okBT=creatingTextButton( "OK", this.squareStyle, false);
		okBT.setVisible(false);
		okBT.setColor(Color.GRAY);
		okBT.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new MemorizeFastGameScreen(score,level,bonus));
			}
		});
				
		this.table  =creatingTable( 0,30, Gdx.graphics.getWidth(), Gdx.graphics.getWidth());
		
		stage.addActor(table);
		//*******************************TIMER****************************************
		//timer
				counterTimer = new Timer();
				
				this.task = new TimerTask() {  
		            public void run() {  
		                try { 
		                	if(showTimer >= 0){
		                		okBT.setVisible(false);
		                		if(showTimer%2==0){
		                			memorizefastGameBackGroundScreen = memorizefastGameLevelUpRed;
		                		}else {memorizefastGameBackGroundScreen = memorizefastGameLevelUpYellow;}
		                		showTimer--;
		                	}else okBT.setVisible(true);	
		                 } catch (Exception e) {  
		                      e.printStackTrace();  
		                 }  
		            }  
		        }; 
		       
		        counterTimer.scheduleAtFixedRate(task, second, second);
		//********************************SCREEN*****************************************************
		table.add(levelBT);
		table.row();
		table.add(scoreBT);
		table.row();
		table.add(bonusBT);
		table.row();
		table.add(okBT);
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
		game.batch.draw(memorizefastGameBackGroundScreen, 0, 0);
		game.batch.end();
		
		//************************game logic*******************************************
		updates();
		//************************game logic*******************************************
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
		atlasGameTexts.dispose();
		
		bitmapFont.dispose();
		
		memorizefastGameBackGroundScreen.dispose();
		memorizefastGameLevelUpYellow.dispose();
		memorizefastGameLevelUpRed.dispose();
		
		stage.dispose();

	}

}
