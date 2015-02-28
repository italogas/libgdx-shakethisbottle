package com.restinhosoft.player;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * @author Mailson
 *
 */
public class ScoresManager {
	private static final String fileName = "scores.txt";
	private static String scores = "";
	
	private static ArrayList<String> gameScores= new ArrayList<String>();

	private int gettingScoreGamePosition(String name){
		int position = -1;
		for(int i=0; i < gameScores.size();i++){
			System.out.println(gameScores.get(i).split(":")[0]);
			if(gameScores.get(i).split(":")[0].equals(name)){
				position = i;
			}
		}
		return position;
	}
	
	private static void loadingIntoGameScores(){
		String[] gScores = loadPlayersScores().split("\n");
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
		try{
			FileHandle local = Gdx.files.local(fileName);
			local.writeString(scores,false);
		}  catch (RuntimeException re){
			System.err.println(re.getMessage());
		}
	}
	
	public static String loadPlayersScores(){
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
	
	public void addScore(String gameName, int score){
		FileHandle local = Gdx.files.local(fileName);
		
		if(local.exists()){
			scores  = loadPlayersScores();
			savingScore();
		}else{
			savingScore();	
		}
		
		loadingIntoGameScores();
	
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
}
