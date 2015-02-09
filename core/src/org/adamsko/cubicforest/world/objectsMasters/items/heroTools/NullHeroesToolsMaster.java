package org.adamsko.cubicforest.world.objectsMasters.items.heroTools;

public class NullHeroesToolsMaster extends HeroesToolsMasterDefault {
	private static NullHeroesToolsMaster instance = null;

	private NullHeroesToolsMaster() {
		super();
	}

	public static NullHeroesToolsMaster instance() {
		if (instance == null) {
			instance = new NullHeroesToolsMaster();
		}
		return instance;
	}

	@Override
	public boolean isNull() {
		return true;
	}
}
