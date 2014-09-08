package org.adamsko.cubicforest.desktop;

import org.adamsko.cubicforest.Cubicforest;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main(final String[] arg) {
		final LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "cubicforest";
		cfg.width = 780;
		cfg.height = 460;

		new LwjglApplication(new Cubicforest(), cfg);
	}
}
