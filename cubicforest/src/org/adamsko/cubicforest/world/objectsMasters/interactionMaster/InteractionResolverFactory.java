package org.adamsko.cubicforest.world.objectsMasters.interactionMaster;

import org.adamsko.cubicforest.world.objectsMasters.entities.enemies.EnemiesMaster;
import org.adamsko.cubicforest.world.objectsMasters.entities.enemies.InteractionResolverEnemies;
import org.adamsko.cubicforest.world.objectsMasters.entities.heroes.HeroesMaster;
import org.adamsko.cubicforest.world.objectsMasters.entities.heroes.InteractionResolverHeroes;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.InteractionResolverGatherCubes;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroesToolsMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.InteractionResolverHeroTools;

import com.badlogic.gdx.Gdx;

public class InteractionResolverFactory {

	private HeroesMaster heroesMaster;
	private EnemiesMaster enemiesMaster;
	private HeroesToolsMaster heroesToolsMaster;
	private GatherCubesMaster gatherCubesMaster;

	public InteractionResolverFactory(HeroesMaster heroesMaster,
			EnemiesMaster enemiesMaster, HeroesToolsMaster heroesToolsMaster,
			GatherCubesMaster gatherCubesMaster) {
		this.heroesMaster = heroesMaster;
		this.enemiesMaster = enemiesMaster;
		this.heroesToolsMaster = heroesToolsMaster;
		this.gatherCubesMaster = gatherCubesMaster;
	}

	public InteractionResolver createFactory(InteractionResolverType_e type) {
		switch (type) {
		case RESOLVER_ENEMIES:
			return new InteractionResolverEnemies();
		case RESOLVER_HEROES:
			return new InteractionResolverHeroes(heroesMaster);
		case RESOLVER_HERO_TOOLS:
			return new InteractionResolverHeroTools(heroesToolsMaster,
					gatherCubesMaster, enemiesMaster);
		case RESOLVER_GATHER_CUBES:
			return new InteractionResolverGatherCubes(gatherCubesMaster);
		default:
			Gdx.app.error(toString(), "unknown InteractionResolverType");
			return null;
		}
	}

}
