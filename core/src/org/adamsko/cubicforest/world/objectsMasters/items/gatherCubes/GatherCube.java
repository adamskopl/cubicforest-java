package org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes;

import org.adamsko.cubicforest.world.object.CubicObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectVisitor;
import org.adamsko.cubicforest.world.object.WorldObjectsMasterDefault;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GatherCube extends CubicObject {

	public GatherCube(final TextureRegion tr, final int texNum,
			final WorldObjectsMasterDefault container) {
		super(tr, texNum, container, WorldObjectType.GATHERCUBE);
	}

	@Override
	public void initTilePropertiesIndicator() {
		super.initTilePropertiesIndicator();
		getTilePropertiesIndicator().setTilePathSearchValid(true);
		getTilePropertiesIndicator().setTileHighlightedAsOccupied(false);
	}

	@Override
	public void accept(final WorldObjectVisitor visitor) {
		visitor.visitGatherCube(this);
	}

}