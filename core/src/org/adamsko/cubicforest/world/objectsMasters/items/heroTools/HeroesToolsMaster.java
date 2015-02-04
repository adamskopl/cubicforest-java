package org.adamsko.cubicforest.world.objectsMasters.items.heroTools;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.render.world.RenderableObjectsMaster;
import org.adamsko.cubicforest.world.WorldObjectsMaster;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMastersContainer;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroTool;
import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tile.TilesMaster;

import com.badlogic.gdx.math.Vector2;

public interface HeroesToolsMaster extends WorldObjectsMaster,
		RenderableObjectsMaster, Nullable {

	Vector2 getNewToolSeparator();

	void initToolsMasters(
			WorldObjectsMastersContainer worldObjectsMastersContainer,
			TilesMaster tilesMaster);

	/**
	 * Add hero tool in 'construction' state for marking purpose.
	 */
	void heroToolMarkerAdd(final Tile heroToolTile);

	void heroToolMarkerRemove();

	/**
	 * HeroToolMarker is accepted: add it to standard HeroTool collection.
	 */
	void heroToolMarkerAccept();

	void setToolTexture(final HeroTool tool, final int index);

	void setHeroToolMarkerType(final WorldObjectType heroToolMarkerType);

}
