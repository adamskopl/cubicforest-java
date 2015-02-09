package org.adamsko.cubicforest.players.resolver;

public class NullMapsResolver extends MapsResolverDefault {
	private static NullMapsResolver instance = null;

	private NullMapsResolver() {
		super(false);
	}

	public static NullMapsResolver instance() {
		if (instance == null) {
			instance = new NullMapsResolver();
		}
		return instance;
	}

	@Override
	public boolean isNull() {
		return true;
	}
}
