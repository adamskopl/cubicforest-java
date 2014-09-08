package org.adamsko.cubicforest.world.object.collision.visitors.concrete;

import org.adamsko.cubicforest.world.object.collision.handler.CollisionsHandler;
import org.adamsko.cubicforest.world.object.collision.visitors.CollisionVisitorDefault;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCube;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesMaster;

public class HeroCollisionVisitorLeave extends CollisionVisitorDefault {

	private final GatherCubesMaster gatherCubesMaster;

	public HeroCollisionVisitorLeave(final CollisionsHandler collisionsHandler,
			final GatherCubesMaster gatherCubesMaster) {
		super(collisionsHandler);
		this.gatherCubesMaster = gatherCubesMaster;
	}

	@Override
	public void visitGatherCube(final GatherCube gatherCube) {
		super.visitGatherCube(gatherCube);
		gatherCubesMaster.cubeUnHighlight(gatherCube);
	}

}
