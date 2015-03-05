package com.restinhosoft.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.Gdx;
import com.restinhosoft.main.ScoresManager;

@RunWith (GdxTestRunner.class)
public class ScoresTest {

	@Test
	public void scoreFilesExistsTest() {
		assertTrue(Gdx.files.local("scores_eng.txt").exists());
		assertTrue(Gdx.files.local("scores_pt.txt").exists());
		
	}
	
	@Test
	public void ScoresManagerTest() {
		ScoresManager scm = new ScoresManager("test.txt");
		
		assertTrue(scm.saveUniqueScore("test.txt", 100));
		assertTrue(scm.saveMultipleScore("teste.txt","GAMETEST", 100));
		
		assertFalse(scm.saveUniqueScore("test.txt", -100));
		assertFalse(scm.saveMultipleScore("teste.txt","GAMETEST", -100));
		assertFalse(scm.saveUniqueScore("", 100));
		assertFalse(scm.saveUniqueScore(null, 100));
		assertFalse(scm.saveMultipleScore("","GAMETEST", 100));
		assertFalse(scm.saveMultipleScore(null,"GAMETEST", 100));
		assertFalse(scm.saveMultipleScore("teste.txt","", 100));
		assertFalse(scm.saveMultipleScore("teste.txt",null, 100));
		
		assertEquals("100",scm.loadScoresList("test.txt"));
		assertTrue(scm.saveUniqueScore("test.txt", 1000));
		assertEquals("1000",scm.loadScoresList("test.txt"));
		assertFalse(scm.saveUniqueScore("test.txt", 10));
		assertEquals("1000",scm.loadScoresList("test.txt"));
	}

}
