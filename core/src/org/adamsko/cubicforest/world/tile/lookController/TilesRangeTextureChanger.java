package org.adamsko.cubicforest.world.tile.lookController;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.render.world.object.visualState.RenderableObjectVisualState;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tile.TilesMaster;
import org.adamsko.cubicforest.world.tile.tilesSearcher.searchParameter.TilesSearchParameterFactory;

import com.badlogic.gdx.math.Vector2;

public class TilesRangeTextureChanger {

	private final TilesMaster tilesMaster;
	private final TilesLookController tilesLookController;
	// don't create: its for holding list returned from TilesMaster
	private List<Tile> objectTilesInRange;
	private final List<Tile> groupTilesInRange;
	// tiles common to object and a group of objects
	private final List<Tile> commonTilesInRange;

	public TilesRangeTextureChanger(final TilesMaster tilesMaster,
			final TilesLookController tilesLookController) {
		this.tilesMaster = tilesMaster;
		this.groupTilesInRange = new ArrayList<Tile>();
		this.commonTilesInRange = new ArrayList<Tile>();
		this.tilesLookController = tilesLookController;
	}

	public void highlightTilesObjectsRange(final WorldObject object,
			final Vector2 texCoords1, final List<WorldObject> otherObjects,
			final Vector2 texCoords2, final Vector2 commomTexCoords) {

		final TilesSearchParameterFactory tilesSearchParameterFactory = tilesMaster
				.getTilesSearchParameterFactory();

		groupTilesInRange.clear();
		commonTilesInRange.clear();

		objectTilesInRange = tilesMaster.getTilesInRange(object,
				object.getSpeed(),
				tilesSearchParameterFactory.create(WorldObjectType.HERO));

		for (final WorldObject worldObject : otherObjects) {
			final List<Tile> othersTilesInRange = tilesMaster.getTilesInRange(
					worldObject, worldObject.getSpeed(),
					tilesSearchParameterFactory.create(WorldObjectType.ENEMY));
			addTilesToGroupAndCommon(othersTilesInRange);
		}

		tilesLookController.changeTilesTexture(objectTilesInRange,
				RenderableObjectVisualState.ALLOWED);
		// uncomment to highlight enemies range
		// tilesLookController.changeTilesTexture(groupTilesInRange,
		// texCoords2);
		tilesLookController.changeTilesTexture(commonTilesInRange,
				RenderableObjectVisualState.ALLOWED2);
	}

	/**
	 * Add tiles from the range of other objects to {@link #commonTilesInRange},
	 * if tiles are also in {@link #objectTilesInRange}. Otherwise add them to
	 * {@link #groupTilesInRange} (don't add duplicates).
	 */
	private void addTilesToGroupAndCommon(final List<Tile> othersTilesInRange) {
		for (final Tile tile : othersTilesInRange) {
			// add tile either to commonTiles or to groupTiles or nowhere
			if (objectTilesInRange.contains(tile)) {
				commonTilesInRange.add(tile);
			} else if (!groupTilesInRange.contains(tile)) {
				groupTilesInRange.add(tile);
			}
		}
	}
}
