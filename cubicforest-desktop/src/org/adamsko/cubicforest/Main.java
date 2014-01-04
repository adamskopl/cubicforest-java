package org.adamsko.cubicforest;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "cubicforest";
		cfg.useGL20 = false;
		cfg.width = 780;
		cfg.height = 460;
		
		new LwjglApplication(new Cubicforest(), cfg);
	}
}
