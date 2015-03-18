package com.restinhosoft.main;

public class AchievementsManager {
	private StringFileManager fileManager;
	
	private final String fileAchievementName = "achievement_name.txt";
	private final String fileAchievementDesc = "achievement_desc.txt";
	private final String pula = "\n";
	private final String start = "sta1";
	private final String end   = "1end";
	
	private String[] allAchievementsNames;
	private String[] allAchievementsDescs;
	
	public AchievementsManager(){
		this.fileManager = new StringFileManager();
		this.allAchievementsNames = fileManager.loadFile(fileAchievementName).split("\n");
		this.allAchievementsDescs = fileManager.loadFile(fileAchievementDesc).split("\n");
	}
	
	public int getNumberOfAchievements(){return allAchievementsNames.length;}
	
	public void addAchievement(String achievementName, String description){
		if(achievementName!=null && !achievementName.equals("")
		&& description!=null     && !description.equals("")){
			fileManager.saveFile(fileAchievementName,achievementName, true);
			fileManager.saveFile(fileAchievementDesc,start+description+end, true);
		}
	}
	
	public String getAchievementDescription(String achievementName){
		fileManager.loadFile(fileAchievementDesc).split("\n");
		if(achievementName==null || achievementName.equals(""))	return "have no description";
		int position = allAchievementsDescs.length-1;
		for(int i=0;i<allAchievementsNames.length;i++){
			if(allAchievementsNames[i].equals(achievementName))position =i;
		}
		if(position >=0)return allAchievementsDescs[position].split("1")[2];
		return "have no description";
	}
	
	public String[] getAllAchievements(){
		fileManager.loadFile(fileAchievementName).split("\n");
		return allAchievementsNames;
	}
}
