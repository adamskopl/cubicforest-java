package org.adamsko.cubicforest.world.objectsMasters.interactionMaster.result;

import java.util.List;

import org.adamsko.cubicforest.roundsMaster.GameResult;
import org.adamsko.cubicforest.roundsMaster.RoundsMaster;
import org.adamsko.cubicforest.roundsMaster.phaseEnemies.PhaseEnemies;
import org.adamsko.cubicforest.roundsMaster.phaseHeroes.PhaseHeroes;
import org.adamsko.cubicforest.world.objectsMasters.entities.enemies.EnemiesMaster;
import org.adamsko.cubicforest.world.objectsMasters.entities.enemies.Enemy;
import org.adamsko.cubicforest.world.objectsMasters.entities.heroes.Hero;
import org.adamsko.cubicforest.world.objectsMasters.entities.heroes.HeroesMaster;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.result.InteractionResult;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCube;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroTool;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroesToolsMaster;

public class InteractionResultProcessor {

	private HeroesMaster heroesMaster;
	private EnemiesMaster enemiesMaster;
	private HeroesToolsMaster heroesToolsMaster;
	private GatherCubesMaster gatherCubesMaster;

	private RoundsMaster roundsMaster;

	private PhaseEnemies phaseEnemies;
	private PhaseHeroes phaseHeroes;

	public InteractionResultProcessor(HeroesMaster heroesMaster,
			EnemiesMaster enemiesMaster, HeroesToolsMaster heroesToolsMaster,
			GatherCubesMaster gatherCubesMaster) {

		this.heroesMaster = heroesMaster;
		this.enemiesMaster = enemiesMaster;
		this.heroesToolsMaster = heroesToolsMaster;
		this.gatherCubesMaster = gatherCubesMaster;

	}

	public void initPhases(RoundsMaster roundsMaster,
			PhaseEnemies phaseEnemies, PhaseHeroes phaseHeroes) {
		this.roundsMaster = roundsMaster;
		this.phaseEnemies = phaseEnemies;
		this.phaseHeroes = phaseHeroes;
	}

	public void resolve(InteractionResult interactionResult) {
		removeHeroes(interactionResult.getRemovalsHeroes());
		removeEnemies(interactionResult.getRemovalsEnemies());
		removeHeroTools(interactionResult.getRemovalsHeroTools());
		removeGatherCubes(interactionResult.getRemovalsGatherCubes());
		
		roundsMaster.setGameResultSingle(interactionResult.getGameResult());
	}

	private void removeHeroes(List<Hero> toRemoveHeroes) {
		for (Hero hero : toRemoveHeroes) {
			heroesMaster.removeHero(hero);
			phaseHeroes.removeObject(hero);
		}
	}

	private void removeEnemies(List<Enemy> toRemoveEnemies) {
		for (Enemy enemy : toRemoveEnemies) {
			enemiesMaster.removeEnemy(enemy);
			phaseEnemies.removeObject(enemy);
		}
	}

	private void removeHeroTools(List<HeroTool> toRemoveHeroTools) {
		for (HeroTool heroTool : toRemoveHeroTools) {
			heroesToolsMaster.removeTool(heroTool);
		}
	}

	private void removeGatherCubes(List<GatherCube> toRemoveGatherCubes) {
		for (GatherCube gatherCube : toRemoveGatherCubes) {
			gatherCubesMaster.removeCube(gatherCube);
		}
	}
}
