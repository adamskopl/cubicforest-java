package org.adamsko.cubicforest.world.objectsMasters.collisionsMaster.result;

import java.util.List;

import org.adamsko.cubicforest.roundsMaster.GameResult;
import org.adamsko.cubicforest.roundsMaster.RoundsMaster;
import org.adamsko.cubicforest.roundsMaster.phaseEnemies.PhaseEnemies;
import org.adamsko.cubicforest.roundsMaster.phaseHeroes.PhaseHeroes;
import org.adamsko.cubicforest.world.objectsMasters.collisionsMaster.result.CollisionResult;
import org.adamsko.cubicforest.world.objectsMasters.entities.enemies.EnemiesMaster;
import org.adamsko.cubicforest.world.objectsMasters.entities.enemies.Enemy;
import org.adamsko.cubicforest.world.objectsMasters.entities.heroes.Hero;
import org.adamsko.cubicforest.world.objectsMasters.entities.heroes.HeroesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCube;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroTool;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroesToolsMaster;

public class CollisionResultProcessor {

	private HeroesMaster heroesMaster;
	private EnemiesMaster enemiesMaster;
	private HeroesToolsMaster heroesToolsMaster;
	private GatherCubesMaster gatherCubesMaster;

	private RoundsMaster roundsMaster;

	private PhaseEnemies phaseEnemies;
	private PhaseHeroes phaseHeroes;

	public CollisionResultProcessor(HeroesMaster heroesMaster,
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

	public void resolve(CollisionResult collisionResult) {
		removeHeroes(collisionResult.getRemovalsHeroes());
		removeEnemies(collisionResult.getRemovalsEnemies());
		removeHeroTools(collisionResult.getRemovalsHeroTools());
		removeGatherCubes(collisionResult.getRemovalsGatherCubes());
		
		roundsMaster.setGameResultSingle(collisionResult.getGameResult());
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
