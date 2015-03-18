package com.restinhosoft.game.shakethebottle;

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
import com.restinhosoft.main.LanguageManager;
import com.restinhosoft.main.ShakeThisBottle;
import com.restinhosoft.ui.AuxScreenCreation;
import com.restinhosoft.ui.GameSelectionScreen;


public class ShakeTheBottleGameMenu implements Screen {

	private ShakeThisBottle game;
	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	
	private final AuxScreenCreation creating = new AuxScreenCreation(); 
	private TextureAtlas atlastitle = creating.creatingAtlas("games/hittitle.atlas");
	private Skin skintitle = creating.creatingSkin(atlastitle);
	private TextButtonStyle titleStyleEN = creating.creatingTextButtonStyles(skintitle, "garrafatitle_en", new BitmapFont(Gdx.files.internal("default.fnt")));
	private TextButtonStyle titleStyle = creating.creatingTextButtonStyles(skintitle, "garrafatitle_pt", new BitmapFont(Gdx.files.internal("default.fnt")));
	private TextButton title;
	private TextButton titlePT = creating.creatingTextButton("", titleStyle, true);
	private TextButton titleEN = creating.creatingTextButton("", titleStyleEN, true);
	
	private BitmapFont bitmapFont;
	private Table table;
	private TextButton description;
	private TextButton tutorial;
	private TextButton survival;
	private TextButton play;
	private TextButton back;
		
	private Texture background;
	private FitViewport fitViewport;
	
	private LanguageManager languageManager;
	public String language;
	
	@Override
	public void show() {
		this.game = (ShakeThisBottle) Gdx.app.getApplicationListener();
		
		fitViewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		languageManager = LanguageManager.getInstance();
		try {
			language = languageManager.getLanguage();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		stage = new Stage();
		stage.setViewport(fitViewport);
		
		Gdx.input.setInputProcessor(stage);
		
		background = new Texture(Gdx.files.internal("games/menu_background.png"));//new Texture(Gdx.files.internal("menu.png"));
		
		atlas = new TextureAtlas(Gdx.files.internal("button.atlas"));
		
		skin = new Skin(atlas);
		
		bitmapFont = new BitmapFont(Gdx.files.internal("default.fnt"));
		
		TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = bitmapFont;
		
		table = new Table();
		table.setFillParent(true);
		table.setBounds(-Gdx.graphics.getWidth()/2, -Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage.addActor(table);
		
		if(language.equals(languageManager.languageEN)){title = titleEN;}
		else{title=titlePT;}
		
		description = new TextButton((language.equals(languageManager.languageEN)?"DESCRIPTION ":"DESCRICAO "), textButtonStyle);
		description.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new ShakeTheBottleDescription());
				dispose();
			}
		});
		description.pad(15);
		
		tutorial = new TextButton((language.equals(languageManager.languageEN)?"TUTORIAL ":"TUTORIAL "), textButtonStyle);
		tutorial.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new ColorTutorial());
				dispose();
			}
		});
		tutorial.pad(15);
		
		survival = new TextButton((language.equals(languageManager.languageEN)?"SURVIVAL PLAY ":"JOGAR SOBREVIVENCIA "), textButtonStyle);
		survival.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				ShakeTheBottleGameScreen neo = new ShakeTheBottleGameScreen();
				neo.setSurvival(true);
				game.setScreen(new ShakeTheBottleDifficultyMenu(neo));
				dispose();
			}
		});
		survival.pad(15);
		
		play = new TextButton((language.equals(languageManager.languageEN)?"PLAY ":"JOGAR "), textButtonStyle);
		play.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new ShakeTheBottleDifficultyMenu(new ShakeTheBottleGameScreen()));
				dispose();
			}
		});
		play.pad(15);
		
		back = new TextButton((language.equals(languageManager.languageEN)?"BACK ":"VOLTAR "), textButtonStyle);
		back.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new GameSelectionScreen());
				dispose();
			}
		});
		back.pad(15);
		table.add(title);
		table.getCell(title).spaceBottom(10);
		table.row();
		back.pad(15);
		table.add(play);
		table.getCell(play).spaceBottom(10);
		table.row();
		table.add(survival);
		table.getCell(survival).spaceBottom(10);
		table.row();
		table.add(tutorial);
		table.getCell(tutorial).spaceBottom(10);
		table.row();
		table.add(description);
		table.getCell(description).spaceBottom(10);
		table.row();
		table.add(back);
		table.getCell(back).spaceBottom(10);
		table.row();
		
		
	}

	@Override
	public void render(float delta) {
		GL20 gl = Gdx.gl;
		gl.glClearColor(1, 1, 1, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.begin();
		game.batch.draw(background, 0, 0);
		game.batch.end();
		if(language.equals(languageManager.languageEN)){title = titleEN;}
		else{title=titlePT;}
		
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		fitViewport.update(width, height);
	}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {}

	@Override
	public void dispose() {
		background.dispose();
		atlas.dispose();
		skin.dispose();
		stage.dispose();
		bitmapFont.dispose();
	}

}
