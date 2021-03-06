package com.restinhosoft.game.memorizefast;

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
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.restinhosoft.main.LanguageManager;
import com.restinhosoft.main.ShakeThisBottle;
import com.restinhosoft.ui.AuxScreenCreation;
import com.restinhosoft.ui.LanguageOptionsScreen;
import com.restinhosoft.ui.MainMenuScreen;
import com.restinhosoft.ui.SoundOptionsScreen;


public class NovaGeniusTutorial implements Screen {

	private ShakeThisBottle game;
	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private final BitmapFont bitmapFont = new BitmapFont(Gdx.files.internal("default.fnt"));
	private Table table;
	private TextButton textButtonDesc;
	private TextButton textButtonContinue;
	private TextButton textButtonBack;
	private Texture background;
	private FitViewport fitViewport;
	
	private LanguageManager languageManager;
	public String language;
	private final AuxScreenCreation creating = new AuxScreenCreation();
	private final TextureAtlas colorAtlas = creating.creatingAtlas("hitthecolour/color_tutorial_button.atlas");
	private final Skin colorSkin = creating.creatingSkin(colorAtlas);
	
	private final TextButtonStyle colorStylehitOne  = creating.creatingTextButtonStyles(colorSkin, "tutorial_memory_hit_1", bitmapFont);
	private final TextButtonStyle colorStylehittwo   = creating.creatingTextButtonStyles(colorSkin,"tutorial_memory_hit_2", bitmapFont);
	private final TextButtonStyle colorStyleNoHit = creating.creatingTextButtonStyles(colorSkin,"tutorial_memory_not_hit", bitmapFont);
	private final TextButtonStyle colorStyleStart= creating.creatingTextButtonStyles(colorSkin, "tutorial_memory_start", bitmapFont);
	
	private TextButton hitShow = creating.creatingTextButton("", colorStyleStart, false);
	
	private final String memoryStartPt = "clique no nome 'inicio'\n para iniciar o jogo";
	private final String memoryHitOnePt= "clique na cor correta\n para marcar pontos";
	private final String memoryHitTwoPt= "ao clicar na cor correta\n a cor aparece";
	private final String memoryNoHitPt = "ao clicar em uma cor \ndiferente da sequencia,\n ocorre gameover.";
	
	private final String memoryStartEn = "click the name 'start'\n to start the game";
	private final String memoryHitOneEn= "click the correct color\n to score points";
	private final String memoryHitTwoEn= "by clicking on the correct\n color the color appears";
	private final String memoryNoHitEn = "when you click a different\n color of the sequence,\n is gameover.";
	
	private int nextPage = 0;
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
		
		background = new Texture(Gdx.files.internal("icons/sub_menu.png"));//new Texture(Gdx.files.internal("menu.png"));
		
		atlas = new TextureAtlas(Gdx.files.internal("button.atlas"));
		
		skin = new Skin(atlas);
		
		
		
		TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		//textButtonStyle.up = skin.getDrawable("blue_button");
		//textButtonStyle.down = skin.getDrawable("blue_button");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = bitmapFont;
		
		
		table = new Table();
		table.setFillParent(true);
		table.setBounds(-Gdx.graphics.getWidth()/2, -Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage.addActor(table);
		
		textButtonDesc = new TextButton((language.equals(languageManager.languageEN)?memoryStartEn:memoryStartPt), textButtonStyle);
		textButtonDesc.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new LanguageOptionsScreen());
			}
		});
		textButtonDesc.pad(15);
		
		hitShow.setDisabled(true);
		
		textButtonContinue = new TextButton((language.equals(languageManager.languageEN)?"NEXT ":"PROXIMO "), textButtonStyle);
		textButtonContinue.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(nextPage==3)nextPage=0;
				else nextPage++;
			}
		});
		textButtonContinue.pad(15);
		
		textButtonBack = new TextButton((language.equals(languageManager.languageEN)?"Back ":"Voltar "), textButtonStyle);
		textButtonBack.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new NovaGeniusGameMenu());
				dispose();
			}
		});
		textButtonBack.pad(15);
		
		table.align(Align.center);
		table.add(hitShow);
		table.row().pad(20);
		table.add(textButtonDesc);
		table.getCell(textButtonDesc).spaceBottom(10);
		table.row();
		table.add(textButtonContinue);
		table.getCell(textButtonContinue).spaceBottom(10);
		table.row();
		table.add(textButtonBack);
		
	}

	@Override
	public void render(float delta) {
		GL20 gl = Gdx.gl;
		gl.glClearColor(1, 1, 1, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if(nextPage==0){
			textButtonDesc.setText((language.equals(languageManager.languageEN)?memoryStartEn:memoryStartPt));
			hitShow.setStyle(colorStyleStart);
		}
		if(nextPage==1){
			textButtonDesc.setText((language.equals(languageManager.languageEN)?memoryHitOneEn:memoryHitOnePt));
			hitShow.setStyle(colorStylehitOne);
		}
		if(nextPage==2){
			textButtonDesc.setText((language.equals(languageManager.languageEN)?memoryHitTwoEn:memoryHitTwoPt));
			hitShow.setStyle(colorStylehittwo);
		}
		if(nextPage==3){
			textButtonDesc.setText((language.equals(languageManager.languageEN)?memoryNoHitEn:memoryNoHitPt));
			hitShow.setStyle(colorStyleNoHit);
		}
		
		game.batch.begin();
		game.batch.draw(background, 0, 0);
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
		background.dispose();
		atlas.dispose();
		skin.dispose();
		stage.dispose();
		bitmapFont.dispose();
	}

}
