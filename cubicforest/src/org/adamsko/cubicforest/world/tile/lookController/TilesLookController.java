package org.adamsko.cubicforest.world.tile.lookController;

import java.util.List;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.tile.Tile;

import com.badlogic.gdx.math.Vector2;

public interface TilesLookController extends Nullable {

	void highlightTilesObjectsRange(WorldObject object, Vector2 texCoords1,
			List<WorldObject> otherObjects, Vector2 texCoords2,
			Vector2 commomTexCoords);

	void changeTilesTexture(List<Tile> tilesToChange, Vector2 texCoords);

	void changeTileTexture(Tile tile, Vector2 texCoords);

	void resetTilesTextures();

}