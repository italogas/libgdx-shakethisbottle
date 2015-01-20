package com.restinhosoft.shakethisbottle;

/**
 * @author Mailson
 * 
 */
public class Achievements {
	private int id;
	private String name;
	private String description;
	
	public Achievements(int id, String name, String description){
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public int getID(){return this.id;}
	
	public String getName(){return this.name;}
	
	public String getDescription(){return this.description;}
	
	public void setID(int id){this.id = id;}
	
	public void setName(String name){ this.name = name;}
	
	public void setDescription(String descritpion) {this.description = descritpion;}
	
	public boolean getEquals(Achievements achievement){
		if(this.id == achievement.getID()){return true;}
		return false;
	}
	
	
}
