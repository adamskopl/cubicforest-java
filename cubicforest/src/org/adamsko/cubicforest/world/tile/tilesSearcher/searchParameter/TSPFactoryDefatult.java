package org.adamsko.cubicforest.world.tile.tilesSearcher.searchParameter;

import org.adamsko.cubicforest.world.object.WorldObjectType;

public class TSPFactoryDefatult implements TilesSearchParameterFactory {

	private final TSPEnemiesRange tspEnemiesRange;
	private final TSPHeroesRange tspHeroesRange;
	private final NullTSP nullTSP;

	public TSPFactoryDefatult() {
		tspEnemiesRange = new TSPEnemiesRange();
		tspHeroesRange = new TSPHeroesRange();
		nullTSP = NullTSP.instance();
	}

	@Override
	public TilesSearchParameter create(final WorldObjectType worldObjectType) {
		switch (worldObjectType) {
		case ENEMY:
			return tspEnemiesRange;
		case HERO:
			return tspHeroesRange;
		default:
			return nullTSP;
		}
	}
}
