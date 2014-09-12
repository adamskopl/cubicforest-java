package org.adamsko.cubicforest.world.object.collision.visitors.concrete;

import org.adamsko.cubicforest.world.object.collision.handler.CollisionsHandler;
import org.adamsko.cubicforest.world.object.collision.visitors.CollisionVisitorDefault;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCube;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.portals.Portal;
import org.adamsko.cubicforest.world.objectsMasters.items.portals.PortalsMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.prizes.Prize;
import org.adamsko.cubicforest.world.objectsMasters.items.prizes.PrizesMaster;

public class HeroCollisionVisitorLeave extends CollisionVisitorDefault {

	private final GatherCubesMaster gatherCubesMaster;
	private final PortalsMaster portalsMaster;
	private final PrizesMaster prizesMaster;

	public HeroCollisionVisitorLeave(final CollisionsHandler collisionsHandler,
			final GatherCubesMaster gatherCubesMaster,
			final PortalsMaster portalsMaster, final PrizesMaster prizesMaster) {
		super(collisionsHandler);
		this.gatherCubesMaster = gatherCubesMaster;
		this.portalsMaster = portalsMaster;
		this.prizesMaster = prizesMaster;
	}

	@Override
	public void visitGatherCube(final GatherCube gatherCube) {
		super.visitGatherCube(gatherCube);
		gatherCubesMaster.cubeUnHighlight(gatherCube);
	}

	@Override
	public void visitPortal(final Portal portal) {
		super.visitPortal(portal);
		portalsMaster.portalUnHighlight(portal);
	}

	@Override
	public void visitPrize(final Prize prize) {
		super.visitPrize(prize);
		prizesMaster.prizeUnHighlight(prize);
	}

}
