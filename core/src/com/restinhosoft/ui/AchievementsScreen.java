/**
 * 
 */
package com.restinhosoft.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.restinhosoft.main.AchievementsManager;
import com.restinhosoft.main.LanguageManager;
import com.restinhosoft.main.ShakeThisBottle;

/**
 * @author Italo
 *
 */
public class AchievementsScreen implements Screen {

	private ShakeThisBottle game;
	
	private Texture background;
	private Texture button;
	private Texture backtomenu;
	
	public Label selectLabel;
	
	private Stage stage;
	
	private Texture menuImg;
	
	private TextureAtlas atlas;
		
	private Skin skin;
		
	private final BitmapFont bitmapFont =new BitmapFont(Gdx.files.internal("neuropol-x-free-small.fnt"));
	
	private Table table;
	
	private Actor imageButton6;
	
	private TextButton backtxBT;
	private TextButton descBT;
	
	private FitViewport fitViewport;

	private final AuxScreenCreation creatingButton = new AuxScreenCreation();
	
	private final AchievementsManager aManager = new AchievementsManager();
	
	private final TextureAtlas atlasBalloon = creatingButton.creatingAtlas("icons/achieviement_balloons.atlas");
	private final TextureAtlas atlasMemory  = creatingButton.creatingAtlas("icons/achieviement_genius.atlas");
	private final TextureAtlas atlasColour  = creatingButton.creatingAtlas("icons/achieviement_colours.atlas");
	private final TextureAtlas atlasBottle  = creatingButton.creatingAtlas("icons/achieviement_happy.atlas");
	private final TextureAtlas atlasLoser   = creatingButton.creatingAtlas("icons/achieviement_loser.atlas");
	private final TextureAtlas atlasChampion= creatingButton.creatingAtlas("icons/achieviement_medal.atlas");
		
	private final Skin skinBalloon= creatingButton.creatingSkin(atlasBalloon);
	private final Skin skinMemory = creatingButton.creatingSkin(atlasMemory);
	private final Skin skinColour = creatingButton.creatingSkin(atlasColour);
	private final Skin skinBottle = creatingButton.creatingSkin(atlasBottle);
	private final Skin skinLoser = creatingButton.creatingSkin(atlasLoser);
	private final Skin skinChampion = creatingButton.creatingSkin(atlasChampion);
		
	private final TextButtonStyle balloonStyle= creatingButton.creatingTextButtonStyles(skinBalloon,"achieviements_balloons",  bitmapFont);
	private final TextButtonStyle memoryStyle = creatingButton.creatingTextButtonStyles(skinMemory ,"achieviements_genius",bitmapFont);
	private final TextButtonStyle colourStyle = creatingButton.creatingTextButtonStyles(skinColour ,"achieviements_colours", bitmapFont);
	private final TextButtonStyle bottleStyle = creatingButton.creatingTextButtonStyles(skinBottle ,"achieviements_happy",bitmapFont);
	private final TextButtonStyle loserStyle  = creatingButton.creatingTextButtonStyles(skinLoser ,"achieviements_loser", bitmapFont);
	private final TextButtonStyle championStyle=creatingButton.creatingTextButtonStyles(skinChampion ,"achieviements_medal",bitmapFont);
	
	private final TextButton level10Balloon= creatingButton.creatingTextButton("", balloonStyle, false);
	private final TextButton level10Colour= creatingButton.creatingTextButton("", colourStyle, false);
	private final TextButton level10Bottle= creatingButton.creatingTextButton("", bottleStyle, false);
	private final TextButton level10Memory= creatingButton.creatingTextButton("", memoryStyle, false);
	private final TextButton         loser= creatingButton.creatingTextButton("", loserStyle, false);
	private final TextButton champion     = creatingButton.creatingTextButton("", championStyle, false);	
	
	private LanguageManager languageManager;
	public String language;
	
	private String name ="";
	private String desc ="";
		
	private void showAchievements(){
		level10Balloon.setVisible(false);
		level10Colour.setVisible(false);
		level10Bottle.setVisible(false);
		level10Memory.setVisible(false);
		loser.setVisible(false);
		champion.setVisible(false);
		String[] achievements = aManager.getAllAchievements();
		for(int i=0;i<aManager.getNumberOfAchievements();i++){
			if(achievements[i].equals("superHitter")) level10Balloon.setVisible(true);
			if(achievements[i].equals("superVision")) level10Colour.setVisible(true);
			if(achievements[i].equals("superShake")) level10Bottle.setVisible(true);
			if(achievements[i].equals("superGenius")) level10Memory.setVisible(true);
			if(achievements[i].equals("Loser")) loser.setVisible(true);
			if(achievements[i].equals("Lucky")) champion.setVisible(true);
		}
	}
	
	private void buttonClicked(){
		level10Balloon.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				name = "superHitter";
				desc = aManager.getAchievementDescription(name);
			}
		});
		
		level10Colour.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				name = "superVision";
				desc = aManager.getAchievementDescription(name);
			}
		});
		
		level10Bottle.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				name = "superShake";
				desc = aManager.getAchievementDescription(name);
			}
		});
		
		level10Memory.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				name = "superGenius";
				desc = aManager.getAchievementDescription(name);
			}
		});
		
		loser.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				name = "Loser";
				desc = aManager.getAchievementDescription(name);
			}
		});
		
		champion.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				name = "Lucky";
				desc = aManager.getAchievementDescription(name);
			}
		});
	}
	
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
			System.err.println(e.getMessage());
		}
		
		fitViewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage = new Stage();
		stage.setViewport(fitViewport);
		
		Gdx.input.setInputProcessor(stage);
		
		
		LabelStyle labelStyle = new Label.LabelStyle();
		labelStyle.font = bitmapFont;
		selectLabel = new Label(
				(language.equals(languageManager.languageEN)?
						"ACHIEVEMENTS ":"CONQUISTAS "), 
				labelStyle);
		
		menuImg = new Texture(Gdx.files.internal("icons/sub_menu.png"));
		
		atlas = new TextureAtlas(Gdx.files.internal("button.atlas"));
				
		skin = new Skin(atlas);

		table = new Table();
		table.setFillParent(true);
		table.setBounds(-Gdx.graphics.getWidth()/2, -Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage.addActor(table);
		
		TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = bitmapFont;
		
		backtxBT = new TextButton(language.equals(languageManager.languageEN)?"Back ":"Voltar ", textButtonStyle);
		backtxBT.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new PlayerProfileScreen());
			}
		});
		
		descBT = new TextButton(
				language.equals(languageManager.languageEN)?"Name: "+ name+"\nDescription:\n"+desc:
								"Nome: "+ name+"\nDescricao:\n"+desc, textButtonStyle);
		descBT.setDisabled(true);

	
		table.add(selectLabel);
		table.row().pad(10);
		table.add(descBT);
		table.row().pad(15);
		table.add(level10Balloon).pad(10);
		table.add(level10Colour);
		table.row().pad(15);
		table.add(level10Bottle).pad(10);
		table.add(level10Memory);
		table.row().pad(15);
		table.add(loser).pad(10);
		table.add(champion);
		table.row().pad(15);
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
		
		showAchievements();
		buttonClicked();
		
		descBT.setText(language.equals(languageManager.languageEN)?"Name: "+ name+"\nDescription:\n"+desc:
								"Nome: "+ name+"\nDescricao:\n"+desc);
		
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
		background.dispose();
		button.dispose();
		backtomenu.dispose();
	}

}
