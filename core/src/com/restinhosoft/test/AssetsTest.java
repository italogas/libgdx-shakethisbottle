package com.restinhosoft.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.Gdx;

@RunWith (GdxTestRunner.class)
public class AssetsTest {

	@Test
	public void hitTheBallonAssetsTest() {
		assertTrue(Gdx.files.internal("..\\android\\assets\\hittheballoon\\blue_balloon.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\hittheballoon\\green_balloon.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\hittheballoon\\orange_balloon.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\hittheballoon\\pink_balloon.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\hittheballoon\\purple_balloon.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\hittheballoon\\red_balloon.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\hittheballoon\\yellow_balloon.png").exists());
	}
	
	@Test
	public void hitTheCircleAssetsTest() throws Exception {
		assertTrue(Gdx.files.internal("..\\android\\assets\\hitthecircle\\hitthecircle_background.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\hitthecircle\\hitthecircle_gameover.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\hitthecircle\\hitthecircle_intro.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\hitthecircle\\hitthecircle_left.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\hitthecircle\\hitthecircle_levelup_red.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\hitthecircle\\hitthecircle_levelup_yellow.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\hitthecircle\\hitthecircle_middle.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\hitthecircle\\hitthecircle_right.png").exists());
	}
	
	@Test
	public void memorizeFastTest() throws Exception {
		assertTrue(Gdx.files.internal("..\\android\\assets\\memorizefast\\memorizefast_background.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\memorizefast\\memorizefast_gameover.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\memorizefast\\memorizefast_intro.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\memorizefast\\memorizefast_square.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\memorizefast\\memorizefast_levelup_red.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\memorizefast\\memorizefast_levelup_yellow.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\memorizefast\\memorizefast_circle.png").exists());
	}
	
	@Test
	public void shakeThisBottleTest() throws Exception {
		assertTrue(Gdx.files.internal("..\\android\\assets\\shakethisbottle\\shakethisbottle_background.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\shakethisbottle\\shakethisbottle_gameover.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\shakethisbottle\\shakethisbottle_levelup_red.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\shakethisbottle\\shakethisbottle_levelup_yellow.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\shakethisbottle\\garrafa.png").exists());
	}

}
