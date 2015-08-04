package org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes;

import org.adamsko.cubicforest.helpTools.CLog;
import org.adamsko.cubicforest.world.object.CubicObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectVisitor;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMasterDefault;

public class GatherCube extends CubicObject {

	public GatherCube(final WorldObjectsMasterDefault container) {
		super(container, WorldObjectType.GATHERCUBE);
		CLog.addObject(this, "GatherCube");
		CLog.setUsage(this, true);
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
