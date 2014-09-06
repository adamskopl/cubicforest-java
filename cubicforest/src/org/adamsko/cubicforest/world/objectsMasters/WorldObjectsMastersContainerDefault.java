package org.adamsko.cubicforest.world.objectsMasters;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.render.world.GameRenderer;
import org.adamsko.cubicforest.world.WorldObjectsMaster;
import org.adamsko.cubicforest.world.objectsMasters.entities.enemies.EnemiesMaster;
import org.adamsko.cubicforest.world.objectsMasters.entities.heroes.HeroesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesMasterDefault;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroesToolsMaster;
import org.adamsko.cubicforest.world.objectsMasters.terrain.TerrainMaster;
import org.adamsko.cubicforest.world.tile.TilesMaster;
import org.adamsko.cubicforest.world.tile.TilesMasterDefault;

public class WorldObjectsMastersContainerDefault implements
		WorldObjectsMastersContainer {

	private final List<WorldObjectsMaster> worldObjectsMasters;

	private TilesMaster tilesMaster;
	private TerrainMaster terrainObjectsMaster;
	private HeroesMaster heroesMaster;
	private EnemiesMaster enemiesMaster;
	private GatherCubesMaster gatherCubesMaster;
	private HeroesToolsMaster heroesToolsMaster;

	public WorldObjectsMastersContainerDefault(final GameRenderer renderer) {
		worldObjectsMasters = new ArrayList<WorldObjectsMaster>();
		initMasters(renderer);
	}

	@Override
	public void initMasters(final GameRenderer renderer) {
		initTilesMaster();

		terrainObjectsMaster = new TerrainMaster(tilesMaster,
				"terrain-atlas-medium", 42, 50);
		heroesMaster = new HeroesMaster(tilesMaster, "heroes-atlas-medium", 30,
				35);
		enemiesMaster = new EnemiesMaster(tilesMaster, "enemies-atlas-medium",
				30, 35);
		gatherCubesMaster = new GatherCubesMasterDefault(tilesMaster,
				"cubes-atlas-medium", 25, 40);

		heroesToolsMaster = new HeroesToolsMaster(tilesMaster,
				gatherCubesMaster, heroesMaster, "tools-atlas-medium", 40, 45);

		// tiles container has to be added first, because objects are
		// removed/added to tiles
		worldObjectsMasters.add(tilesMaster.getTilesContainer());

		renderer.addROMWorld(tilesMaster.getTilesContainer());
		worldObjectsMasters.add(terrainObjectsMaster);
		renderer.addROMWorld(terrainObjectsMaster);
		worldObjectsMasters.add(heroesMaster);
		renderer.addROMWorld(heroesMaster);
		worldObjectsMasters.add(enemiesMaster);
		renderer.addROMWorld(enemiesMaster);
		worldObjectsMasters.add(gatherCubesMaster);
		renderer.addROMWorld(gatherCubesMaster);
		worldObjectsMasters.add(heroesToolsMaster);
		renderer.addROMWorld(heroesToolsMaster);

		renderer.addROMGui(gatherCubesMaster.getGatherCubesCounter());
	}

	@Override
	public boolean allMastersInitialized() {
		for (final WorldObjectsMaster worldObjectsMaster : worldObjectsMasters) {
			if (worldObjectsMaster.isNull()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public List<WorldObjectsMaster> getWorldObjectsMasters() {
		return worldObjectsMasters;
	}

	@Override
	public EnemiesMaster getEnemiesMaster() {
		return enemiesMaster;
	}

	@Override
	public GatherCubesMaster getGatherCubesMaster() {
		return gatherCubesMaster;
	}

	@Override
	public HeroesMaster getHeroesMaster() {
		return heroesMaster;
	}

	@Override
	public HeroesToolsMaster getHeroesToolsMaster() {
		return heroesToolsMaster;
	}

	@Override
	public TerrainMaster getTerrainMaster() {
		return terrainObjectsMaster;
	}

	@Override
	public TilesMaster getTilesMaster() {
		return tilesMaster;
	}

	private void initTilesMaster() {
		tilesMaster = new TilesMasterDefault(100);
	}

}
