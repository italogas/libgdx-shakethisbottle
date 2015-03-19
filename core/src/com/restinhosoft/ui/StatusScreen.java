package com.restinhosoft.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.restinhosoft.exception.exceededCharsException;
import com.restinhosoft.main.LanguageManager;
import com.restinhosoft.main.Player;
import com.restinhosoft.main.ShakeThisBottle;
import com.sun.javafx.property.adapter.PropertyDescriptor.Listener;

public class StatusScreen implements Screen {

	public ShakeThisBottle game;
	public Stage stage;
	public TextureAtlas atlas;
	public Skin skin;
	public BitmapFont bitmapFont;
	public Label selectLabel;
	public Table table;
	public TextArea text;
	public TextButton backButton;
	public TextButton setNameButton;
	public FitViewport fitViewport;

	private LanguageManager languageManager;
	public String language;

	private TextButton nameBT;
	private TextButton idBT;
	private TextButton nachievementBT;
	private TextButton highBT;
	
	private Texture backGround;
	
	private Player player;
	
	private final AuxScreenCreation creating = new AuxScreenCreation();

	@Override
	public void show() {
		this.game = (ShakeThisBottle) Gdx.app.getApplicationListener();
		languageManager = LanguageManager.getInstance();		
	
		try {
			player = new Player("NoName");
		} catch (exceededCharsException e1) {
			e1.printStackTrace();
		}
	
		try {
			language = languageManager.getLanguage();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		fitViewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		stage = new Stage();
		stage.setViewport(fitViewport);
		
		Gdx.input.setInputProcessor(stage);
		
		backGround = new Texture(Gdx.files.internal("icons/sub_menu.png"));
		
		atlas = new TextureAtlas(Gdx.files.internal("uiskin.atlas"));
		
		skin = new Skin(atlas);
		
		bitmapFont = new BitmapFont(Gdx.files.internal("neuropol-x-free-small.fnt"));
		
		LabelStyle labelStyle = new Label.LabelStyle();
		labelStyle.font = bitmapFont;
		
		TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = bitmapFont;
		
		//text = new TextArea("", skin);
		
		
		nameBT = creating.creatingTextButton(
				(language.equals(languageManager.languageEN)?"NAME: ":"NOME: ")
												+ player.getName(), textButtonStyle, true);
		idBT   = creating.creatingTextButton("ID" + player.getID(), textButtonStyle, true);
		nachievementBT = creating.creatingTextButton(
				(language.equals(languageManager.languageEN)?"Number of Achievements: ":"Numero de Conquistas: ")
				 + "\n" + player.getNumberOfAchievements(), textButtonStyle, true);
		highBT         = creating.creatingTextButton(
				(language.equals(languageManager.languageEN)?"High Score EVER: ":"Melhor Pontuacao de TODAS: ")
				 + "\n"+player.highScoreEver(0), textButtonStyle, true);
		
		
				
		backButton = new TextButton((language.equals(languageManager.languageEN)?"Back ":"Voltar "), textButtonStyle);
		backButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new PlayerProfileScreen());
				dispose();
			}
		});
		
		setNameButton = new TextButton((language.equals(languageManager.languageEN)?"CHANGE NAME ":"MUDE O NOME "), textButtonStyle);
		setNameButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
				dispose();
			}
		});
		
		table = new Table();
		table.setBounds(-Gdx.graphics.getWidth()/2, -Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		table.setFillParent(true);
		stage.addActor(table);
		
		table.add(nameBT).pad(20);
		table.row();
		table.add(idBT).pad(20);
		table.row();
		table.add(nachievementBT).pad(20);
		table.row();
		table.add(highBT).pad(20);
		table.row();
		table.add(backButton);
		table.getCell(backButton).spaceBottom(15);
	
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.begin();
		game.batch.draw(backGround,0,0);
		game.batch.end();
		
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
		backGround.dispose();
		atlas.dispose();
		skin.dispose();
		bitmapFont.dispose();
	}

}
