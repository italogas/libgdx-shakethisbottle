package com.restinhosoft.shakethisbottle.remendogametest;

import java.util.ArrayList;

import com.restinhosoft.shakethisbottle.Player;

public class gametest {
	private static ArrayList<String> idList;
	private ArrayList<String> nameList;
	private ArrayList<Player> playerList;
	
	public gametest(){
		this.idList     = new ArrayList<String>();
		this.nameList   =  new ArrayList<String>();
		this.playerList = new ArrayList<Player>();
	}

	public boolean addPlayer(Player player){
		if(!idList.contains(player.getID()) && !nameList.contains(player.getName())){
			playerList.add(player);
			idList.add(player.getID());
			nameList.add(player.getName());
			return true;
		}
		return false;
	}
	
	public boolean removePlayer(Player player){
		if(playerList.contains(player)){
			for(int i = 0; i < playerList.size();i++ ){
				if(playerList.get(i) == player){
					playerList.remove(i);
					idList.remove(i);
					nameList.remove(i);
				}
			}
			return true;
		}
		return false;
	}
	
	public static boolean validIDSet(String id){
		if(!idList.contains(id)){
			return true;
		}
		return false;
	}
	
}
