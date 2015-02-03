package org.adamsko.cubicforest.mapsResolver.wmcontainer;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.world.WorldObjectsMaster;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.tile.TilesContainer;

import com.badlogic.gdx.math.Vector2;

public class WOMMementoStateDefault implements WOMMementoState {

	private final List<Vector2> tilePositions;

	private final String worldObjectsMasterName;

	@Override
	public String getName() {
		return worldObjectsMasterName;
	}

	public WOMMementoStateDefault(final TilesContainer tilesContainer) {
		this.worldObjectsMasterName = tilesContainer.getName();
		this.tilePositions = new ArrayList<Vector2>();
		for (final WorldObject worldObject : tilesContainer.getWorldObjects()) {
			final Vector2 pos = new Vector2(worldObject.getTilesPos());
			tilePositions.add(pos);
		}
	}

	public WOMMementoStateDefault(final WorldObjectsMaster worldObjectsMaster) {
		this.worldObjectsMasterName = worldObjectsMaster.getName();
		this.tilePositions = new ArrayList<Vector2>();
		for (final WorldObject worldObject : worldObjectsMaster
				.getWorldObjects()) {
			Vector2 pos = new Vector2(worldObject.getTilesPos());
			pos = pos.sub(0.5f, 0.5f);
			tilePositions.add(pos);
		}
	}

	@Override
	public List<Vector2> getTilePositions() {
		final List<Vector2> copy = new ArrayList<Vector2>();
		for (final Vector2 vec : tilePositions) {
			copy.add(new Vector2(vec));
		}
		return copy;
	}

	@Override
	public boolean isEqual(final WOMMementoState womMementoState) {
		int i = 0;

		final List<Vector2> comparedPositions = womMementoState
				.getTilePositions();

		if (comparedPositions.size() != getTilePositions().size()) {
			return false;
		}

		for (final Vector2 vec : comparedPositions) {
			if (!vec.equals(getTilePositions().get(i))) {
				return false;
			}
			i++;
		}
		return true;
	}
}
