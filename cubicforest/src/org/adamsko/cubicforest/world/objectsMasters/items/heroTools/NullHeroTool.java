package org.adamsko.cubicforest.world.objectsMasters.items.heroTools;

public class NullHeroTool extends HeroTool {

	private static NullHeroTool instance = null;

	private NullHeroTool() {
		super();
	}

	public static NullHeroTool instance() {
		if (instance == null) {
			instance = new NullHeroTool();
		}
		return instance;
	}

	@Override
	public boolean isNull() {
		return true;
	}
}
