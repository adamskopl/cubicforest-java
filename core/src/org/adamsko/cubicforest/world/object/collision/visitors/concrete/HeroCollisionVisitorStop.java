package org.adamsko.cubicforest.world.object.collision.visitors.concrete;

import org.adamsko.cubicforest.roundsMaster.GameResult;
import org.adamsko.cubicforest.world.object.collision.handler.CollisionsHandler;
import org.adamsko.cubicforest.world.object.collision.visitors.CollisionVisitorDefault;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCube;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroTool;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroToolStates_e;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroesToolsMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroToolPortal;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroToolTurret;

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
			gatherCubesMaster.getGatherCubesCounter().addValue(
					-heroTool.getBuildCost());
		}
	}

	@Override
	public void visitToolPortal(final HeroToolPortal heroToolPortal) {
		if (heroToolPortal.getToolState() == HeroToolStates_e.STATE_CONSTRUCTION) {
			super.visitToolPortal(heroToolPortal);
			return;
		}
		collision().gameResultOperation().setGameResult(GameResult.GAME_WON);
	}

	@Override
	public void visitToolTurret(final HeroToolTurret heroToolTurret) {
		super.visitToolTurret(heroToolTurret);
	}

	@Override
	public void visitGatherCube(final GatherCube gatherCube) {
		super.visitGatherCube(gatherCube);
		gatherCubesMaster.getGatherCubesCounter().addValue(1);
		collision().wordlObjectOperation().remove(gatherCube);
	}

}
