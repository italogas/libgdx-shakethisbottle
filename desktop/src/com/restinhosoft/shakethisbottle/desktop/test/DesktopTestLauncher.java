package com.restinhosoft.shakethisbottle.desktop.test;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.restinhosoft.shakethisbottle.ui.TestGame;

public class DesktopTestLauncher {

	public DesktopTestLauncher(TestGame testGame) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Test Game";
		config.height = 480;
		config.width = 320;
		LwjglApplicationConfiguration.disableAudio = true;
		new LwjglApplication(testGame, config);
	}

}
