package com.restinhosoft.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.junit.Test;

import com.restinho.exceptions.invalidGameNameException;
import com.restinhosoft.minigames.DonotShakeThisBottleMG;
import com.restinhosoft.minigames.HitTheCircleMG;
import com.restinhosoft.minigames.MemorizeFastMG;

/**
 * @author Mailson
 *
 */
public class MemorizeFastMiniGameTest {
	private MemorizeFastMG gameEng  = new MemorizeFastMG(1,1,"eng");
	private MemorizeFastMG gamePtBR = new MemorizeFastMG(3,1,"pt-br");
	
	private Timer counterTimer; 
	private int timer = 10;
	private TimerTask task;
	private final long second = 1000;
	
	@Test
	public void miniGameCreationTest() {
		assertEquals(gameEng.language(),"eng");
		assertEquals(gamePtBR.language(),"pt-br");
		
		assertEquals(gameEng.getGameDifficulty(),1);
		assertEquals(gamePtBR.getGameDifficulty(),3);
		
		gameEng.setGameLevel(2);
		
		assertEquals(gameEng.getGamelevel(),2);
		assertEquals(gamePtBR.getGamelevel(),1);
	}
	
	@Test
	public void miniGameGameMethodTest() {//problema com a pausa
		if (counterTimer == null) {  
		   	counterTimer = new Timer();  
		    task = new TimerTask() {  
		    	public void run() {  
	                try { 
	                  	if(timer > 0) timer--;
	                } catch (Exception e) {  
	                    e.printStackTrace();  
	                }  
	            }  
		    };  
		    counterTimer.scheduleAtFixedRate(task, second, second);  
		}	
	    
		assertEquals(timer, gameEng.getGameTimer());
		assertEquals(timer-4, gamePtBR.getGameTimer());
		
		for(int i=0; i<gameEng.getVillainSequence().size();i++){
			gameEng.addPlayerSequence(gameEng.getVillainSequence().get(i));
		}
		
		gameEng.theGame();
		gamePtBR.theGame();
		
		assertTrue(gameEng.congrats());
		assertFalse(gamePtBR.gameOver());
		
		try {
			Thread.sleep(7000);
			gamePtBR.theGame();
			assertTrue(gamePtBR.gameOver());	
		} catch (Exception e) {	}
		
		MemorizeFastMG hueBR = new MemorizeFastMG(1,1,"pt-br");
		
		hueBR.addPlayerSequence(hueBR.getVillainSequence().get(2));//posicao differente no array
		hueBR.theGame();
		assertTrue(hueBR.gameOver());
	
	}
	

	@Test
	public void settingGameNameTest(){
		try {
			gamePtBR.setGameName(null);
		} catch (Exception e) {
			assertEquals("This is not a valid game name.",e.getMessage());
		}
	}
	
	@Test
	public void settingGameDifficultyTest(){
		gamePtBR.setGameDifficulty(-1);
		assertEquals(gamePtBR.getGameDifficulty(),1);
		
		gamePtBR.setGameDifficulty(3);
		assertEquals(gamePtBR.getGameDifficulty(),3);
		
		gamePtBR.setGameDifficulty(0);
		assertEquals(gamePtBR.getGameDifficulty(),1);
	}
	
	@Test
	public void settingGameLevelTest(){
		gamePtBR.setGameLevel(-1);
		assertEquals(gamePtBR.getGamelevel(),1);
		
		gamePtBR.setGameLevel(0);
		assertEquals(gamePtBR.getGamelevel(),1);
		
		gamePtBR.setGameLevel(2);
		assertEquals(gamePtBR.getGamelevel(),2);
	}
	
	@Test
	public void gameMessagesTest(){
		assertEquals("Wow, you really have a great memory.", gameEng.congratsMessage());
		assertEquals("Oh no, the missiles were shot ", gameEng.gameOverMessage());
		
		assertEquals("Cara, você realmente tem uma boa memória.", gamePtBR.congratsMessage());
		assertEquals("Oh não, os mísseis foram disparados", gamePtBR.gameOverMessage());
		
	}
	
	
	
	
}
