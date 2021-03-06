package org.adamsko.cubicforest.world.object.collision.visitors.concrete;

import org.adamsko.cubicforest.world.object.collision.handler.CollisionsHandler;
import org.adamsko.cubicforest.world.object.collision.visitors.CollisionVisitorDefault;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCube;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroToolStates;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroesToolsMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroTool;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroToolTurret;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.exit.HeroToolExit;
import org.adamsko.cubicforest.world.objectsMasters.items.prizes.Prize;
import org.adamsko.cubicforest.world.objectsMasters.items.prizes.PrizesMaster;

public class HeroCollisionVisitorStop extends CollisionVisitorDefault {

	private final HeroesToolsMaster heroesToolsMaster;
	private final GatherCubesMaster gatherCubesMaster;
	private final PrizesMaster prizesMaster;

	public HeroCollisionVisitorStop(final CollisionsHandler collisionsHandler,
			final GatherCubesMaster gatherCubesMaster,
			final HeroesToolsMaster heroesToolsMaster,
			final PrizesMaster prizesMaster) {
		super(collisionsHandler);

		this.gatherCubesMaster = gatherCubesMaster;
		this.heroesToolsMaster = heroesToolsMaster;
		this.prizesMaster = prizesMaster;
	}

	@Override
	public void visitHeroTool(final HeroTool heroTool) {
		final HeroToolStates toolState = heroTool.getToolState();
		if (toolState == HeroToolStates.STATE_CONSTRUCTION) {
			heroTool.setState(HeroToolStates.STATE_READY);
			heroesToolsMaster.setToolTexture(heroTool, 0);
			gatherCubesMaster.getGatherCubesCounter().addValue(
					-heroTool.getBuildCost());
		}
	}

	@Override
	public void visitToolExit(final HeroToolExit heroToolExit) {
		if (heroToolExit.getToolState() == HeroToolStates.STATE_CONSTRUCTION) {
			super.visitToolExit(heroToolExit);
			return;
		}
		collision().wordlObjectOperation().remove(heroToolExit);
		collision().wordlObjectOperation().remove(getVisitingObject());
		// collision().gameResultOperation().setGameResult(GameResult.GAME_WON);
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

	@Override
	public void visitPrize(final Prize prize) {
		super.visitPrize(prize);
		prizesMaster.onPrizeCollected();
		collision().wordlObjectOperation().remove(prize);
	}

}
