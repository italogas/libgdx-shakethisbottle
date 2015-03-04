package com.restinhosoft.main;

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
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.restinhosoft.ui.PlayerProfileScreen;

/**
 * @author Mailson
 *
 */
public class ScoresManager{
	private static String fileName;
	private static String scores = "";
	
	private static ArrayList<String> gameScores= new ArrayList<String>();

	private int gettingScoreGamePosition(String name){
		int position = -1;
		for(int i=0; i < gameScores.size();i++){
			if(gameScores.get(i).split(":")[0].equals(name)){
				position = i;
			}
		}
		return position;
	}
	
	private static void loadMultipleScore(){
		String[] gScores = loadScoresList(fileName).split("\n");
		gameScores = new ArrayList<String>();
		for(int i=0; i < gScores.length;i++){
			gameScores.add(gScores[i]);
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
		try {
			FileHandle local = Gdx.files.local(fileName);
			local.writeString(scores,false);	
		} catch (RuntimeException re){
			System.err.println(re.getMessage());
		}
	}
	
	private static LanguageManager languageManager;
	private static String language;
	
	public ScoresManager(String fileName){
		if(fileName!="" && fileName!=null) this.fileName = fileName;
		else this.fileName = "scores.txt";
	}
	
	public String getDefaultFileName(){
		return fileName;
	}
	
	public static String loadScoresList(String fileName){
		return loadUniqueScore(fileName);
	}
	
	public void saveDefaultMultipleScore(String gameName, int score){
		saveMultipleScore(fileName,gameName, score);
	}
	
	public void saveMultipleScore(String fileName,String gameName, int score){
		FileHandle local = Gdx.files.local(fileName);
		
		if(local.exists()){
			scores  = loadScoresList(fileName);
			savingScore();
		}else{
			savingScore();	
		}
		
		loadMultipleScore();
	
		if(gameName!= null && gameName!=""&& score>=0){
			int position = (gettingScoreGamePosition(gameName));
			if(gettingScoreGamePosition(gameName)!=-1){
				String[] temp = gameScores.get(position).split(":");
				
				if(temp[1].equals("") || temp== null) temp[1] = ""+score;
				else if(Integer.parseInt(temp[1])< score){temp[1] = ""+score;}
				
				gameScores.set(position, gameName+":"+temp[1]);
				savePlayersScores();
			}else{
				gameScores.add(gameName+":"+score);
				savePlayersScores();
			}
		}
		savePlayersScores();
	}
	
	public static void saveUniqueScore(String fileName,int score){
		FileHandle local = Gdx.files.local(fileName);
		String previousScore ="";
		if(local.exists()){
			previousScore = loadUniqueScore(fileName);
		}
		try{
			if(fileName!= null && fileName!="" && Integer.parseInt(previousScore)<score){
				local.writeString(""+score,false);	
			}
		}  catch (RuntimeException re){
			System.err.println(re.getMessage());
		}
	}
	
	public static String loadUniqueScore(String localfileName){
		if(localfileName!= null && localfileName !=""){	
			FileHandle call = Gdx.files.local(localfileName);
			
			if(!call.exists()){
				call.writeString("",false);
			}
			
			String readString = null;
			
			try {
				FileHandle local = Gdx.files.local(localfileName);
				readString = local.readString();
			} catch (RuntimeException re){
				System.err.println(re.getMessage());
			}
			return readString;	
		}return "";
	}

}


