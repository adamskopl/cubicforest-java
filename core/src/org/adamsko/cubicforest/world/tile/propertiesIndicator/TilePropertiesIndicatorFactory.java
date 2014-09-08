package org.adamsko.cubicforest.world.tile.propertiesIndicator;

import org.adamsko.cubicforest.world.object.WorldObjectType;

public class TilePropertiesIndicatorFactory {

	private static TilePropertiesIndicatorFactory instance = null;

	private TilePropertiesIndicatorFactory() {
	}

	public static TilePropertiesIndicatorFactory instance() {
		if (instance == null) {
			instance = new TilePropertiesIndicatorFactory();
		}
		return instance;
	}

	/**
	 * Creates TilePropertiesIndicator object for given {@link WorldObjectType}.
	 * Every objects has its own indicator, because they can be modified (so two
	 * objects of the same {@link WorldObjectType} type can have different
	 * indicators).
	 */
	public TilePropertiesIndicator create(final WorldObjectType indicatorType) {
		final TilePropertiesIndicator newIndicator = new TilePropertiesIndicatorDefault();

		return newIndicator;
	}
}
