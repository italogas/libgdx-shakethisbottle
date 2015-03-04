package com.restinhosoft.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.Gdx;

@RunWith (GdxTestRunner.class)
public class AssetsTest {

	@Test
	public void hitTheBallonAssetsTest() {
		assertTrue(Gdx.files.internal("hittheballoon\blue_balloon.png").exists());
	}

}
