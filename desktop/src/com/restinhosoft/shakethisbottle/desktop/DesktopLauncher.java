package com.restinhosoft.shakethisbottle.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.restinhosoft.shakethisbottle.ShakeThisBottle;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Shake This Bottle";
		config.height = 480;
		config.width = 320;
		new LwjglApplication(new ShakeThisBottle(), config);
	}
}
