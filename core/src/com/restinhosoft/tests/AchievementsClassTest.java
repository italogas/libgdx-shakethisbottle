package com.restinhosoft.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.restinhosoft.shakethisbottle.Achievements;
import com.restinhosoft.shakethisbottle.remendogametest.gametest;
import com.sun.corba.se.impl.corba.AsynchInvoke;

/**
 * @author Mailson
 *
 */
public class AchievementsClassTest {
	private Achievements achieviementJames = new Achievements(1, "teste", "conquista de teste");
	private gametest gt = new gametest();
	
	@Test
	public void initialAchievementsTest() {
		assertEquals(achieviementJames.getID(),1);
		assertEquals(achieviementJames.getName(),"teste");
		assertEquals(achieviementJames.getDescription(),"conquista de teste");
		
	}
	
	@Test
	public void achievementsManipulationTest() {
		achieviementJames.setName("funcionou");
		assertEquals(achieviementJames.getID(),1);
		assertEquals(achieviementJames.getName(),"funcionou");
		assertEquals(achieviementJames.getDescription(),"conquista de teste");
		
		achieviementJames.setID(2);
		assertEquals(achieviementJames.getID(),2);
		
		assertTrue(gt.addAchievements(achieviementJames));
		assertTrue(gt.addAchievements(new Achievements(1, "oto", "description oto")));
		assertFalse(gt.addAchievements(new Achievements(1, "oto", "description oto")));
		
		assertFalse(gt.removeAchievements(new Achievements(2, "oto", "description oto")));
		assertTrue(gt.removeAchievements(achieviementJames));
		
		
	}
	
	
}
