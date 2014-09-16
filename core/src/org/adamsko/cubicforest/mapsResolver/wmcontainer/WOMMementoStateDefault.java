package org.adamsko.cubicforest.mapsResolver.wmcontainer;

import java.util.List;

import org.adamsko.cubicforest.world.WorldObjectsMaster;
import org.adamsko.cubicforest.world.object.WorldObject;

import com.badlogic.gdx.math.Vector2;

public class WOMMementoStateDefault implements WOMMementoState {

	private List<Vector2> tilePositions;

	public WOMMementoStateDefault(final WorldObjectsMaster worldObjectsMaster) {
		for (final WorldObject worldObject : worldObjectsMaster
				.getWorldObjects()) {
			final Vector2 pos = new Vector2(worldObject.getTilesPos());
			tilePositions.add(pos);
		}
	}

	@Override
	public List<Vector2> getTilePositions() {
		return tilePositions;
	}

}
