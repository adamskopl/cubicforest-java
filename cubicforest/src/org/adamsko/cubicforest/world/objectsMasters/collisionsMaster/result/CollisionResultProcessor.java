package org.adamsko.cubicforest.world.objectsMasters.collisionsMaster.result;

import java.util.List;

import org.adamsko.cubicforest.roundsMaster.RoundsMaster;
import org.adamsko.cubicforest.roundsMaster.phaseEnemies.PhaseEnemies;
import org.adamsko.cubicforest.roundsMaster.phaseHeroes.PhaseHeroes;
import org.adamsko.cubicforest.world.objectsMasters.entities.enemies.EnemiesMaster;
import org.adamsko.cubicforest.world.objectsMasters.entities.enemies.Enemy;
import org.adamsko.cubicforest.world.objectsMasters.entities.heroes.Hero;
import org.adamsko.cubicforest.world.objectsMasters.entities.heroes.HeroesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCube;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroTool;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroesToolsMaster;

public class CollisionResultProcessor {

	private final HeroesMaster heroesMaster;
	private final EnemiesMaster enemiesMaster;
	private final HeroesToolsMaster heroesToolsMaster;
	private final GatherCubesMaster gatherCubesMaster;

	private RoundsMaster roundsMaster;

	private PhaseEnemies phaseEnemies;
	private PhaseHeroes phaseHeroes;

	public CollisionResultProcessor(final HeroesMaster heroesMaster,
			final EnemiesMaster enemiesMaster,
			final HeroesToolsMaster heroesToolsMaster,
			final GatherCubesMaster gatherCubesMaster) {

		this.heroesMaster = heroesMaster;
		this.enemiesMaster = enemiesMaster;
		this.heroesToolsMaster = heroesToolsMaster;
		this.gatherCubesMaster = gatherCubesMaster;

	}

	public void initPhases(final RoundsMaster roundsMaster,
			final PhaseEnemies phaseEnemies, final PhaseHeroes phaseHeroes) {
		this.roundsMaster = roundsMaster;
		this.phaseEnemies = phaseEnemies;
		this.phaseHeroes = phaseHeroes;
	}

	public void resolve(final CollisionResult collisionResult) {
		removeHeroes(collisionResult.getRemovalsHeroes());
		removeEnemies(collisionResult.getRemovalsEnemies());
		removeHeroTools(collisionResult.getRemovalsHeroTools());
		removeGatherCubes(collisionResult.getRemovalsGatherCubes());

		roundsMaster.setGameResultSingle(collisionResult.getGameResult());
	}

	private void removeHeroes(final List<Hero> toRemoveHeroes) {
		for (final Hero hero : toRemoveHeroes) {
			heroesMaster.removeHero(hero);
			phaseHeroes.removeObject(hero);
		}
	}

	private void removeEnemies(final List<Enemy> toRemoveEnemies) {
		for (final Enemy enemy : toRemoveEnemies) {
			enemiesMaster.removeEnemy(enemy);
			phaseEnemies.removeObject(enemy);
		}
	}

	private void removeHeroTools(final List<HeroTool> toRemoveHeroTools) {
		for (final HeroTool heroTool : toRemoveHeroTools) {
			heroesToolsMaster.removeTool(heroTool);
		}
	}

	private void removeGatherCubes(final List<GatherCube> toRemoveGatherCubes) {
		for (final GatherCube gatherCube : toRemoveGatherCubes) {
			gatherCubesMaster.removeCube(gatherCube);
		}
	}
}
