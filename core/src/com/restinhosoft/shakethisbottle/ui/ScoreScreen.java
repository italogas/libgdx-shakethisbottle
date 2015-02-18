/**
 * 
 */
package com.restinhosoft.shakethisbottle.ui;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
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
import com.restinhosoft.player.PlayerScoresIOBuffer;


/**
 * @author Mailson
 *
 */
public class ScoreScreen implements Screen {
	ShakeThisBottle game;
	
	private OrthographicCamera cam;
	
	private Texture menuImg;
	
	private Stage stage;
	
	private TextureAtlas atlas;
	
	private Skin skin;
	private Skin textSkin;
	
	private BitmapFont bitmapFont;
	
	private Table table;
	
	private TextButton scoreText;
	private TextButton backText;
	
	private int width = 320;
	private int height= 480;
	
	//private int width = Gdx.graphics.getWidth();
	//private int height= Gdx.graphics.getHeight();
	
	private TextField profileTextArea;

	private FitViewport fitViewport;
	
	private String scoreString;
	
	//*******************************************SAVE*******************************************
	
	private static final String fileName = "scores.txt";
	private static String scores = "";
	
	private static ArrayList<String> gameScores= new ArrayList<String>();
	private static ArrayList<String> gameNames = new ArrayList<String>();
		
	private int gettingPosition(String name){
		int position = -1;
		for(int i=0; i < gameNames.size();i++){
			if(gameNames.get(i).equals(name)){
				position = i;
			}
		}
		return position;
	}
	
	private static void loadingScores(){
		String[] gScores = loadPlayersScores().split("\n");
		gameScores = new ArrayList<String>();
		for(int i=0; i < gScores.length;i++){
			gameScores.add(gScores[i]);
		}
	}
	
	public void addScore(String gameName, int score){
		FileHandle local = Gdx.files.local(fileName);
		if(local.exists()){
			scores  = loadPlayersScores();
			savingScore();
		}else{
			savingScore();	
		}
		
		loadingScores();
		if(gameName!= null && gameName!=""&& score>=0){
			int position = (gettingPosition(gameName));
			if(gettingPosition(gameName)!=-1){
				String[] temp = gameScores.get(position).split(":");
				if(Integer.parseInt(temp[1])< score){
					temp[1] = ""+score;
				}
				gameScores.set(position, temp[0]+":"+temp[1]);
				savePlayersScores();
			}else{
				gameScores.add(gameName+":"+score);
				gameNames.add (gameName);
				savePlayersScores();
			}
		}
	}
	private static String savingScore(){
		String jun = "";
		for(int i=0;i<gameScores.size();i++ ){
			jun+= gameScores.get(i)+"\n";
		}
		return jun;
	}
	
	private static void savePlayersScores(){
		scores = savingScore();
		try{
			FileHandle local = Gdx.files.local(fileName);
			local.writeString(scores,false);
		}  catch (RuntimeException re){
			System.err.println(re.getMessage());
		}
	}
	
	private static String loadPlayersScores(){
		FileHandle call = Gdx.files.local(fileName);
		if(!call.exists()){
			call.writeString("",false);
		}
		String readString = null;
		try {
			FileHandle local = Gdx.files.local(fileName);
			readString = local.readString();
		} catch (RuntimeException re){
			System.err.println(re.getMessage());
		}
		return readString;
		
	}
	
	
	//**********************************************SAVE*/*****************************************
	private String getScoreString(){
		String string = "";
		
	
		return string;
	}
	public ScoreScreen() {
		this.scoreString = getScoreString();
	}
	
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#show()
	 */
	@Override
	public void show() {
		this.game = (ShakeThisBottle) Gdx.app.getApplicationListener();
		
		FileHandle local = Gdx.files.local(fileName);
		if(local.exists()){
			scores  = loadPlayersScores();
			savingScore();
		}else{
			savingScore();	
		}
		
		fitViewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage = new Stage();
		stage.setViewport(fitViewport);
		
		Gdx.input.setInputProcessor(stage);
		
		menuImg = new Texture(Gdx.files.internal("menu.png"));
		atlas = new TextureAtlas(Gdx.files.internal("button.atlas"));
		
		skin = new Skin(atlas);
		
		bitmapFont = new BitmapFont(Gdx.files.internal("default.fnt"));
		
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
		
		
		
		scoreText = new TextButton(scores, textButtonEnableStyle);
		scoreText.setColor(Color.LIGHT_GRAY);
		scoreText.setHeight(height);
		scoreText.setWidth(width);
		scoreText.setDisabled(true);
		scoreText.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
			}
		});
		
		backText = new TextButton("BACK", textButtonEnableStyle);
		backText.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new PlayerProfileScreen());
			}
		});
		
		table.add(scoreText);
		table.row();
		table.add(backText);
		
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
