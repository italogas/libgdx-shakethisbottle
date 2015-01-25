package com.restinhosoft.shakethisbottle.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.restinhosoft.shakethisbottle.ui.ShakeThisBottle;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(320, 420);
        }

        @Override
        public ApplicationListener getApplicationListener () {
                return new ShakeThisBottle();
        }
}