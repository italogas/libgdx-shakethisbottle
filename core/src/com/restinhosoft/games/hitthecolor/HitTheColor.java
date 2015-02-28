package com.restinhosoft.games.hitthecolor;

import java.util.ArrayList;

import com.badlogic.gdx.Screen;
import com.restinhosoft.options.LanguageManager;

public class HitTheColor implements Screen {
	
	private static class GameManager{
		public int score;
		public int level;
		public int difficulty;
		public boolean survival;
		
		public static ArrayList<Colours> colors = new ArrayList<Colours>();
		
		public LanguageManager languageManager;
		public String language;
		public String name;
		
		
		
		
		public void LanguageManagerInstance(){
			languageManager = LanguageManager.getInstance();
			try {
				language = languageManager.getLanguage();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
				
		public GameManager(int score, int level,int difficulty, boolean survival){
			this.score = score;
			this.level = level;
			this.difficulty = difficulty;
			this.survival = survival;
			
			LanguageManagerInstance();
			
			if(language.equals(languageManager.languageEN))	this.name = "HIT THE COLOR";
			else this.name = "ACERTE NA COR";
			
		}
		
		
		
	}
	
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
