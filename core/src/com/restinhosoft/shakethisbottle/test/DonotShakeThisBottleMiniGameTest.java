package com.restinhosoft.shakethisbottle.test;

import static org.junit.Assert.*;

import java.util.Timer;
import java.util.TimerTask;

import org.junit.Test;

import com.restinhosoft.shakethisbottle.impl.DonotShakeThisBottleMG;

/**
 * @author Mailson
 *
 */
public class DonotShakeThisBottleMiniGameTest {
	private DonotShakeThisBottleMG gameEng  = new DonotShakeThisBottleMG(1,1,"eng");
	private DonotShakeThisBottleMG gamePtBR = new DonotShakeThisBottleMG(1,1,"pt-br");
	
	private Timer counterTimer; 
	private int timer = 30;
	private TimerTask task;
	private final long second = 1000;
	
	@Test
	public void miniGameCreationTest() {
		assertEquals(gameEng.language(),"eng");
		assertEquals(gamePtBR.language(),"pt-br");
		
		assertEquals(gameEng.getGameDifficulty(),1);
		assertEquals(gamePtBR.getGameDifficulty(),1);
		
		gameEng.setGameLevel(2);
		
		assertEquals(gameEng.getGamelevel(),2);
		assertEquals(gamePtBR.getGamelevel(),1);
	}
	
	@Test
	public void miniGameGameMethodTest() {//problema com a pausa
		//********************************COUNTER
		 if (counterTimer == null) {  
		   	counterTimer = new Timer();  
		    task = new TimerTask() {  
		    	public void run() {  
	                try { 
	                  	if(timer > 0) timer--; System.out.println("Timer: "+timer);
	                } catch (Exception e) {  
	                    e.printStackTrace();  
	                }  
	            }  
		    };  
		    counterTimer.scheduleAtFixedRate(task, second, second);  
		}	
	    //**************************************************************************	
		
		assertEquals(timer, gameEng.getGameTimer());
		assertEquals(timer, gamePtBR.getGameTimer());
		
		
		try {
			Thread.sleep(15000);
			gamePtBR.shake();
			//gamePtBR.theGame();
			assertTrue(gamePtBR.gameOver());
			Thread.sleep(15000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		gameEng.theGame();
		assertTrue(gameEng.congrats());
		
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
		
		
	}
	
	
	
	
}
