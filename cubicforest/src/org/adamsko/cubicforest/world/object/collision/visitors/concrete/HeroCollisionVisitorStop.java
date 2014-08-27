package org.adamsko.cubicforest.world.object.collision.visitors.concrete;

import org.adamsko.cubicforest.world.object.collision.handler.CollisionsHandler;
import org.adamsko.cubicforest.world.object.collision.visitors.CollisionVisitorDefault;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCube;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroTool;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroToolStates_e;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroesToolsMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroToolPortal;

public class HeroCollisionVisitorStop extends CollisionVisitorDefault {

	private final HeroesToolsMaster heroesToolsMaster;
	private final GatherCubesMaster gatherCubesMaster;

	public HeroCollisionVisitorStop(final CollisionsHandler collisionsHandler,
			final GatherCubesMaster gatherCubesMaster,
			final HeroesToolsMaster heroesToolsMaster) {
		super(collisionsHandler);
		this.gatherCubesMaster = gatherCubesMaster;
		this.heroesToolsMaster = heroesToolsMaster;
	}

	@Override
	public void visitHeroTool(final HeroTool heroTool) {
		final HeroToolStates_e toolState = heroTool.getToolState();
		if (toolState == HeroToolStates_e.STATE_CONSTRUCTION) {
			heroTool.setState(HeroToolStates_e.STATE_READY);
			heroesToolsMaster.setToolTexture(heroTool, 0);
			gatherCubesMaster.counterAddValue(-heroTool.getBuildCost());
		}
	}

	@Override
	public void visitToolPortal(final HeroToolPortal heroToolPortal) {
		super.visitToolPortal(heroToolPortal);
	}

	@Override
	public void visitGatherCube(final GatherCube gatherCube) {
		super.visitGatherCube(gatherCube);
		gatherCubesMaster.counterAddValue(1);
		collision().wordlObjectOperation().remove(gatherCube);
	}

}
