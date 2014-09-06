package org.adamsko.cubicforest.world.tile.tilesSearcher.searchParameter;

import org.adamsko.cubicforest.world.object.WorldObjectType;

public interface TilesSearchParameterFactory {

	TilesSearchParameter create(WorldObjectType worldObjectType);

}
