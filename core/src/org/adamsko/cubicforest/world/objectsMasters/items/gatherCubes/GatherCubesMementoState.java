package org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes;

import org.adamsko.cubicforest.helpTools.ConditionalLog;
import org.adamsko.cubicforest.mapsResolver.wmcontainer.WOMMementoState;
import org.adamsko.cubicforest.mapsResolver.wmcontainer.WOMMementoStateDefault;

import com.badlogic.gdx.math.Vector2;

public class GatherCubesMementoState extends WOMMementoStateDefault {

	private final Vector2 counter;

	public GatherCubesMementoState(final GatherCubesMaster gatherCubesMaster) {
		super(gatherCubesMaster);
		counter = new Vector2(gatherCubesMaster.getGatherCubesCounter()
				.getCounter(), 0);
		ConditionalLog.addObject(this, "GatherCubesMementoState");
		ConditionalLog.setUsage(this, false);

		ConditionalLog.debug(this, Float.toString(counter.x));
	}

	public Vector2 getCounter() {
		return counter;
	}

	@Override
	public boolean isEqual(final WOMMementoState womMementoState) {
		final GatherCubesMementoState comparedState = (GatherCubesMementoState) womMementoState;
		if (counter.x != comparedState.getCounter().x) {
			return false;
		}
		return super.isEqual(womMementoState);
	}

}
