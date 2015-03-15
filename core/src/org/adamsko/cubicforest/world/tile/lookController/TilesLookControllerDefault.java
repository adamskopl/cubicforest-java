package org.adamsko.cubicforest.world.tile.lookController;

import java.util.List;

import org.adamsko.cubicforest.render.world.object.RenderableObject;
import org.adamsko.cubicforest.render.world.object.RenderableObjectsMaster;
import org.adamsko.cubicforest.render.world.object.visualState.RenderableObjectVisualState;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tile.TilesContainer;
import org.adamsko.cubicforest.world.tile.TilesMaster;

import com.badlogic.gdx.math.Vector2;

public class TilesLookControllerDefault implements TilesLookController {

	/**
	 * {@link RenderableObjectsMaster} responsible for holding
	 * {@link RenderableObject} objects representing tiles
	 */
	private RenderableObjectsMaster renderableMasterTiles;

	TilesRangeTextureChanger tilesRangeTextureChanger;

	private TilesContainer tilesContainer;

	/**
	 * For {@link NullTilesLookControllerDefault}
	 */
	TilesLookControllerDefault() {
	}

	public TilesLookControllerDefault(final TilesMaster tilesMaster,
			final RenderableObjectsMaster renderableMasterTiles) {
		tilesMaster.isNull();
		this.renderableMasterTiles = renderableMasterTiles;
		this.tilesRangeTextureChanger = new TilesRangeTextureChanger(
				tilesMaster, this);
		this.tilesContainer = tilesMaster.getTilesContainer();
	}

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public void changeTilesTexture(final List<Tile> tilesToChange,
			final RenderableObjectVisualState objectVisualState) {
		for (final Tile tile : tilesToChange) {
			tile.visualState().changeState(objectVisualState);
			// renderableMasterTiles.changeTexture(tile, texCoords);
		}
	}

	@Override
	public void highlightTilesObjectsRange(final WorldObject object,
			final Vector2 texCoords1, final List<WorldObject> otherObjects,
			final Vector2 texCoords2, final Vector2 commomTexCoords) {

		tilesRangeTextureChanger.highlightTilesObjectsRange(object, texCoords1,
				otherObjects, texCoords2, commomTexCoords);

	}

	@Override
	public void changeTileTexture(final Tile tile, final Vector2 texCoords) {
		renderableMasterTiles.changeTexture(tile, texCoords);
	}

	@Override
	public void resetTilesTextures() {
		for (final WorldObject worldObject : tilesContainer.getWorldObjects()) {
			worldObject.refreshTexture();
		}
	}
}
