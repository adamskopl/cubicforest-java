package org.adamsko.cubicforest.world.object.collision.visitors.concrete;

import org.adamsko.cubicforest.world.object.collision.handler.CollisionsHandler;
import org.adamsko.cubicforest.world.object.collision.visitors.CollisionVisitorDefault;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCube;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroToolOrange;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroToolTrap;
import org.adamsko.cubicforest.world.objectsMasters.items.portals.Portal;
import org.adamsko.cubicforest.world.objectsMasters.items.portals.PortalsMaster;

public class HeroCollisionVisitorEnter extends CollisionVisitorDefault {

	private final GatherCubesMaster gatherCubesMaster;
	private final PortalsMaster portalsMaster;

	public HeroCollisionVisitorEnter(final CollisionsHandler collisionsHandler,
			final GatherCubesMaster gatherCubesMaster,
			final PortalsMaster portalsMaster) {
		super(collisionsHandler);
		this.gatherCubesMaster = gatherCubesMaster;
		this.portalsMaster = portalsMaster;
	}

	@Override
	public void visitToolOrange(final HeroToolOrange heroToolOrange) {
		super.visitToolOrange(heroToolOrange);
	}

	@Override
	public void visitToolTrap(final HeroToolTrap heroToolTrap) {
		super.visitToolTrap(heroToolTrap);
	}

	@Override
	public void visitGatherCube(final GatherCube gatherCube) {
		super.visitGatherCube(gatherCube);
		gatherCubesMaster.cubeHighlight(gatherCube);
	}

	@Override
	public void visitPortal(final Portal portal) {
		super.visitPortal(portal);
		portalsMaster.portalHighlight(portal);
	}
}
