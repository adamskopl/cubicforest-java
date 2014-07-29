package org.adamsko.cubicforest.world.objectsMasters.collisionsMaster;

import org.adamsko.cubicforest.world.objectsMasters.entities.enemies.CollisionResolverEnemies;
import org.adamsko.cubicforest.world.objectsMasters.entities.enemies.EnemiesMaster;
import org.adamsko.cubicforest.world.objectsMasters.entities.heroes.CollisionResolverHeroes;
import org.adamsko.cubicforest.world.objectsMasters.entities.heroes.HeroesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.CollisionResolverGatherCubes;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.CollisionResolverHeroTools;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroesToolsMaster;

import com.badlogic.gdx.Gdx;

public class CollisionResolverFactory {

	private final HeroesMaster heroesMaster;
	private final EnemiesMaster enemiesMaster;
	private final HeroesToolsMaster heroesToolsMaster;
	private final GatherCubesMaster gatherCubesMaster;

	public CollisionResolverFactory(final HeroesMaster heroesMaster,
			final EnemiesMaster enemiesMaster,
			final HeroesToolsMaster heroesToolsMaster,
			final GatherCubesMaster gatherCubesMaster) {
		this.heroesMaster = heroesMaster;
		this.enemiesMaster = enemiesMaster;
		this.heroesToolsMaster = heroesToolsMaster;
		this.gatherCubesMaster = gatherCubesMaster;
	}

	public CollisionResolver createFactory(final CollisionResolverType_e type) {
		switch (type) {
		case RESOLVER_ENEMIES:
			return new CollisionResolverEnemies();
		case RESOLVER_HEROES:
			return new CollisionResolverHeroes(heroesMaster);
		case RESOLVER_HERO_TOOLS:
			return new CollisionResolverHeroTools(heroesToolsMaster,
					gatherCubesMaster, enemiesMaster);
		case RESOLVER_GATHER_CUBES:
			return new CollisionResolverGatherCubes(gatherCubesMaster);
		default:
			Gdx.app.error(toString(), "unknown CollisionResolverType");
			return null;
		}
	}

}
