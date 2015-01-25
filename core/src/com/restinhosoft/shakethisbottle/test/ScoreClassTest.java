package com.restinhosoft.shakethisbottle.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.restinhosoft.shakethisbottle.impl.Scores;
import com.restinhosoft.shakethisbottle.impl.gametest;

/**
 * @author Mailson
 *
 */
public class ScoreClassTest {
	private Scores scoreJames = new Scores(1000,2);
	private gametest gt = new gametest();
	
	
	@Test
	public void initialScoreTest() {
		assertEquals(scoreJames.getScore(),1000);
		assertEquals(scoreJames.getGameID(),2);
		assertEquals(scoreJames.getID(),"2&2");
	}
	
	@Test
	public void scoreManipulationTest() {
		scoreJames.setGameID(3);
		assertEquals(scoreJames.getScore(),1000);
		assertEquals(scoreJames.getGameID(),3);
		assertEquals(scoreJames.getID(),"3&3");
		
		gt.addScores(scoreJames);
		scoreJames.setScore(500);
		
		assertFalse(gt.addScores(scoreJames));
		
		System.out.println(scoreJames.getScore());
		
		assertEquals(scoreJames.getScore(),1000);
		assertEquals(scoreJames.getGameID(),3);
		assertEquals(scoreJames.getID(),"3&3");

		scoreJames.setScore(1500);
		
		assertEquals(scoreJames.getScore(),1500);
		assertEquals(scoreJames.getGameID(),3);
		assertEquals(scoreJames.getID(),"3&3");
		
		assertTrue(gt.removeScores(scoreJames));

	}
	
	@Test
	public void setScoreTest() {
		scoreJames.setScore(-1);
		assertEquals(scoreJames.getScore(),1000);
	}
	
}
