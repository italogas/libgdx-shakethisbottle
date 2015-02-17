package com.restinhosoft.player;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 * @author Mailson
 *
 */
public class PlayerScoresIOBuffer {
	private File file;
	
	private ArrayList<String> gameScores;
	private ArrayList<String> gameNames;
	
	
	public PlayerScoresIOBuffer(){
		this.file       = new File("player_scores.txt");
		this.gameScores = new ArrayList<String>();
		this.gameNames  = new ArrayList<String>();
		
		this.file.setWritable(true);
		this.file.setReadable(true);
	}
	
	private int gettingPosition(String name){
		int position = -1;
		for(int i=0; i < gameNames.size();i++){
			if(gameNames.get(i).equals(name)){
				position = i;
			}
		}
		return position;
	}
	
	public void addScore(String gameName, int score){
		loadIO();
		if(gameName!= null && gameName!=""&& score>=0){
			int position = (gettingPosition(gameName));
			if(gettingPosition(gameName)!=-1){
				String[] temp = gameScores.get(position).split(":");
				temp[1] = ""+score;
				gameScores.set(position, temp[0]+":"+temp[1]);
				saveIO();
			}else{
				gameScores.add(gameName+":"+score);
				gameNames.add (gameName);
				saveIO();
			}
		}
	}
	
	public ArrayList<String> getScores(){
		loadIO();
		return gameScores;
	}
	
	public void clearScores(){
		loadIO();
		for(int i=0; i < gameScores.size();i++){
			String[] temp = gameScores.get(i).split(":");
			temp[1] = "0";
			gameScores.set(i,temp[0]+":"+temp[1]);
		}
		saveIO();
		//System.out.println(gameScores);
	}
	//************************************IO BUFFER************************************************
	private String convertToOneString(ArrayList<String> array){
		String one = "";
		for(int i=0; i < array.size();i++){
			one+= array.get(i)+" ";
		}
		return one;
	}
	
	private ArrayList<String> convertToArrayString(String one){
		String[] array = one.split(" ");
		ArrayList<String> temp = new ArrayList<String>();
		for(int i=0; i< array.length;i++){
			temp.add(array[i]);
		}
		return temp;
	}
	
	private void saveIO(){
		Charset charset = Charset.forName("US-ASCII");
		String s = convertToOneString(gameScores);
		try (BufferedWriter writer = Files.newBufferedWriter(file.toPath(), charset)) {
		    writer.write(s, 0, s.length());
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
	}
	
	private ArrayList<String> loadIO(){
		Charset charset = Charset.forName("US-ASCII");
		String read = "";
		try (BufferedReader reader = Files.newBufferedReader(file.toPath(), charset)) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		       read = line+" "; 
		    }
		    gameScores = convertToArrayString(read);
		    return gameScores;
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}
		gameScores = convertToArrayString(read);
	    return gameScores;
	}

}
