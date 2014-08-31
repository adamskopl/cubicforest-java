package org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectVisitor;
import org.adamsko.cubicforest.world.object.WorldObjectsMasterDefault;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GatherCube extends WorldObject {

	public GatherCube(final TextureRegion tr, final int texNum,
			final WorldObjectsMasterDefault container) {
		super(tr, texNum, container, WorldObjectType.GATHERCUBE);
	}

	@Override
	protected void initTilePropertiesIndicator() {
		super.initTilePropertiesIndicator();
		getTilePropertiesIndicator().setTilePathSearchValid(true);
	}

	@Override
	public void accept(final WorldObjectVisitor visitor) {
		visitor.visitGatherCube(this);
	}

}
