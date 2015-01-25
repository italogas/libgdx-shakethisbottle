package com.restinhosoft.shakethisbottle.test;

import static org.junit.Assert.*;

import java.util.Timer;
import java.util.TimerTask;

import org.junit.Test;

import com.restinhosoft.shakethisbottle.impl.ShakeThisBottleMG;


/**
 * @author Mailson
 *
 */
public class ShakeThisBottleMiniGameTest {
	private ShakeThisBottleMG gameEng = new ShakeThisBottleMG(1,1,"eng");
	private ShakeThisBottleMG gamePtBR = new ShakeThisBottleMG(1,1,"pt-br");
	
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
		
		gamePtBR.setGameLevel(2);
		
		assertEquals(gameEng.getGamelevel(),1);
		assertEquals(gamePtBR.getGamelevel(),2);
	}
	
	@Test
	public void miniGameGameMethodTest() {//problema com a pausa
		//********************************COUNTER
		 if (counterTimer == null) {  
		   	counterTimer = new Timer();  
		    task = new TimerTask() {  
		    	public void run() {  
	                try { 
	                  	if(timer > 0) timer--; //System.out.println("Timer: "+timer);
	                } catch (Exception e) {  
	                    e.printStackTrace();  
	                }  
	            }  
		    };  
		    counterTimer.scheduleAtFixedRate(task, second, second);  
		}	
	  //**************************************************************************	
		
		assertEquals(gameEng.getGameTimer(),timer); 
		 
		assertFalse(gameEng.gamePaused());
		assertTrue(gameEng.gameResume());
		
		assertEquals(gameEng.getGameTimer(),timer);
		
		gameEng.setPause();
		
		assertTrue(gameEng.gamePaused());
		assertFalse(gameEng.gameResume());

		gameEng.setResume();
		
		assertFalse(gameEng.gamePaused());
		assertTrue(gameEng.gameResume());
		
		assertEquals(gameEng.getGameTimer(),timer);
		
		assertFalse(gameEng.congrats());
		
		for(int i=0;i<29;i++){
			gameEng.shake();
			gameEng.theGame();
		}
		
		assertTrue(!gameEng.congrats());
	
		gameEng.shake();
		gameEng.theGame();
		assertTrue(gameEng.congrats());
		
		gameEng.setPause();
		gameEng.theGame();
		
		assertTrue(gameEng.congrats());
		assertFalse(gameEng.gameOver());
		
		for(int i=0;i<29;i++){
			gamePtBR.shake();
			gamePtBR.theGame();
		}
		
		try {
			Thread.sleep(31000);
			gamePtBR.theGame();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(gamePtBR.gameOver());
		assertFalse(gamePtBR.congrats());
		
		assertTrue(gameEng.getGameTimer()!=timer);
		//problema com pausa
		
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
