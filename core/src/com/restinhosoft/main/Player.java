package com.restinhosoft.main;

import java.util.Random;

import com.restinhosoft.exception.exceededCharsException;

public class Player {
	private String name;
	private String ID;
	
	private int numberOfAchievements;
	private int highScoreEver;
	
	private StringFileManager profile;
		
	private String[] playerProfile;
	
	private final String fileName = "player_profile.txt";
	private final String pula = "\n";
	
	
	private String generateID(){
		String tempString ="";
		Random r = new Random(9);
		tempString+=r.nextInt();
		ID = tempString;
		return tempString;
	}
	
	public Player(String name) throws exceededCharsException{
		if(name.length()>16)throw new exceededCharsException();
		if(name!= null || name!="") this.name = name;
		
		profile = new StringFileManager();
		playerProfile = profile.loadFile(fileName).split(pula);
		
		if(playerProfile.equals("") ||playerProfile==null){
			System.out.println("aui");
			this.ID = generateID();
			this.numberOfAchievements = getNumberOfAchievements();
			this.highScoreEver = highScoreEver();
			profile.saveFile(fileName,name+pula+
										ID+pula+
										numberOfAchievements+pula+
										highScoreEver,false);
		}else if(playerProfile.length>1){
			this.name = playerProfile[0];
			this.ID = playerProfile[1];
			this.numberOfAchievements = Integer.parseInt(playerProfile[2]);
			this.highScoreEver = Integer.parseInt(playerProfile[3]);
		}else{
			System.out.println("ats");
			this.ID = generateID();
			this.numberOfAchievements = 0;
			this.highScoreEver = 0;
			profile.saveFile(fileName,name+pula+
										ID+pula+
										numberOfAchievements+pula+
										highScoreEver,false);
		}
		playerProfile = profile.loadFile(fileName).split(pula);
	}
	
	public String getName(){ return name;}
	
	public String getID(){
		if(playerProfile.equals("")|| playerProfile ==null) return ID;
		return playerProfile[1];
	}
	
	public int getNumberOfAchievements(){
		if(playerProfile.equals("")|| playerProfile ==null) return 0;
		return Integer.parseInt(playerProfile[2]);
	}
	
	public int highScoreEver(){
		if(playerProfile.equals("")|| playerProfile ==null) return 0;
		return Integer.parseInt(playerProfile[3]);
	}
	
}
