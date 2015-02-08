package org.adamsko.cubicforest.world.objectsMasters.items.heroTools;

import java.util.List;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.render.world.RenderableObjectsMaster;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMaster;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMastersContainer;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroTool;
import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tile.TilesMaster;

public interface HeroesToolsMaster extends WorldObjectsMaster,
		RenderableObjectsMaster, Nullable {

	void initToolsMasters(
			WorldObjectsMastersContainer worldObjectsMastersContainer,
			TilesMaster tilesMaster);

	/**
	 * Add hero tool in 'construction' state for marking purpose.
	 */
	void addHeroToolMarker(final Tile heroToolTile);

	void removeHeroToolMarker();

	/**
	 * HeroToolMarker is accepted: add it to standard HeroTool collection.
	 */
	void heroToolMarkerAccept();

	void setToolTexture(final HeroTool tool, final int index);

	void setHeroToolMarkerType(final WorldObjectType heroToolMarkerType);

	/**
	 * Get list of {@link HeroTool} objects that can be chosen by a player.
	 */
	List<WorldObjectType> getPossibleToolChoices();

	/**
	 * Check if given tile has a {@link HeroTool}.
	 * 
	 * @param tile
	 *            check tile
	 * @return true, if tile contains a hero tool
	 */
	boolean tileContainsTool(Tile tile);

}
