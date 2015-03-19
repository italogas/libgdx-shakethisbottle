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
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.restinhosoft.main.LanguageManager;
import com.restinhosoft.main.ShakeThisBottle;
import com.restinhosoft.ui.AuxScreenCreation;
import com.restinhosoft.ui.LanguageOptionsScreen;
import com.restinhosoft.ui.MainMenuScreen;
import com.restinhosoft.ui.SoundOptionsScreen;


public class ShakeTHeBottleTutorial implements Screen {

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
	
	private final TextButtonStyle colorStylehitOne  = creating.creatingTextButtonStyles(colorSkin, "tutorial_color_hit_1", bitmapFont);
	private final TextButtonStyle colorStylehittwo   = creating.creatingTextButtonStyles(colorSkin,"tutorial_color_hit_2", bitmapFont);
	private final TextButtonStyle colorStyleNoHit = creating.creatingTextButtonStyles(colorSkin,"tutorial_color_not_hit", bitmapFont);
	private final TextButtonStyle colorStyleStart= creating.creatingTextButtonStyles(colorSkin, "tutorial_color_start", bitmapFont);
	
	private TextButton hitShow = creating.creatingTextButton("hit", colorStyleStart, false);
	
	private final String descStartPt = "clique no nome 'inicio'\n para iniciar o jogo\nAgite o aparelho\n para marcar pontos";
	private final String descHitOnePt= "clique na cor correta\n para marcar pontos";
	private final String descHitTwoPt= "ao clicar na cor correta\n a cor desaparece";
	private final String descNoHitPt = "ao clicar em uma cor \ndiferente da pedida,\n a pontuação é decrescida.";
	
	private final String descStartEn = "click the name 'start'\n to start the game \n shake the smartphone\n to score points.";
	private final String descHitOneEn= "click the correct color\n to score points";
	private final String descHitTwoEn= "by clicking on the correct\n color the color disappears";
	private final String descNoHitEn = "when you click a different\n color requested,\n the score is decreased.";
	
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
		
		textButtonDesc = new TextButton((language.equals(languageManager.languageEN)?descStartEn:descStartPt), textButtonStyle);
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
				game.setScreen(new ShakeTheBottleGameMenu());
				dispose();
			}
		});
		textButtonBack.pad(15);
		
		table.align(Align.center);
		//table.add(hitShow);
		table.row().pad(20);
		table.add(textButtonDesc);
		table.getCell(textButtonDesc).spaceBottom(10);
		table.row();
		//table.add(textButtonContinue);
		//table.getCell(textButtonContinue).spaceBottom(10);
		table.row();
		table.add(textButtonBack);
		
	}

	@Override
	public void render(float delta) {
		GL20 gl = Gdx.gl;
		gl.glClearColor(1, 1, 1, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		textButtonDesc.setText((language.equals(languageManager.languageEN)?descStartEn:descStartPt));


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
