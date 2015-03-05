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
	
	@Test
	public void hitTheColourTest() throws Exception {
		assertTrue(Gdx.files.internal("..\\android\\assets\\hitthecolour\\blue.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\hitthecolour\\green.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\hitthecolour\\orange.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\hitthecolour\\pink.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\hitthecolour\\purple.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\hitthecolour\\red.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\hitthecolour\\yellow.png").exists());
	}
	
	@Test
	public void doNotshakeThisBottleTest() throws Exception {
		assertTrue(Gdx.files.internal("..\\android\\assets\\donotshakethisbottle\\donotshake_intro.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\donotshakethisbottle\\shakethisbottle_gameover.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\donotshakethisbottle\\shakethisbottle_levelup_red.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\donotshakethisbottle\\shakethisbottle_levelup_yellow.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\donotshakethisbottle\\garrafa.png").exists());
	}
	
	@Test
	public void menuTest() throws Exception {
		assertTrue(Gdx.files.internal("..\\android\\assets\\background_button.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\background_profile_screen.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\backtomenu.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\badge.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\button.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\circle1.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\conquistas_00.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\conquistas_01.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\conquistas_02.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\conquistas_03.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\conquistas_04.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\conquistas_05.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\conquistas_06.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\conquistas_07.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\conquistas_08.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\default.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\donot_shakeintro.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\exit.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\garrafa.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\language.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\menu.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\music.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\offon.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\onoff.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\options.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\play.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\register.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\selectgame.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\volume.png").exists());
		assertTrue(Gdx.files.internal("..\\android\\assets\\sound.png").exists());
	}

}


