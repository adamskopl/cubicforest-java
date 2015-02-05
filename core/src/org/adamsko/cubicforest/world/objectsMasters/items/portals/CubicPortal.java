package org.adamsko.cubicforest.world.objectsMasters.items.portals;

import org.adamsko.cubicforest.world.object.CubicObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectVisitor;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMasterDefault;
import org.adamsko.cubicforest.world.tile.NullCubicTile;
import org.adamsko.cubicforest.world.tile.Tile;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class CubicPortal extends CubicObject implements Portal {

	private Portal twinPortal;
	private final Tile parentTile;

	// for NullCubicPortal
	CubicPortal() {
		parentTile = NullCubicTile.instance();
	}

	public CubicPortal(final TextureRegion tr, final int texNum,
			final WorldObjectsMasterDefault container, final Tile parentTile) {
		super(tr, texNum, container, WorldObjectType.PORTAL);
		this.parentTile = parentTile;
	}

	@Override
	public void initTilePropertiesIndicator() {
		super.initTilePropertiesIndicator();
		getTilePropertiesIndicator().setTilePathSearchValid(true);
		getTilePropertiesIndicator().setTileHeroesRangeValid(true);
		getTilePropertiesIndicator().setTileHighlightedAsOccupied(true);
	}

	@Override
	public void accept(final WorldObjectVisitor visitor) {
		visitor.visitPortal(this);
	}

	@Override
	public Portal getTwinPortal() {
		return twinPortal;
	}

	@Override
	public void setTwinPortal(final Portal twinPortal) {
		this.twinPortal = twinPortal;
	}

	@Override
	public Tile getTile() {
		return parentTile;
	}

}