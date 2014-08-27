package org.adamsko.cubicforest.world.objectsMasters.items.heroTools;

import java.util.List;

import org.adamsko.cubicforest.world.mapsLoader.CFMap;
import org.adamsko.cubicforest.world.mapsLoader.tiled.TiledObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectsContainer;
import org.adamsko.cubicforest.world.objectsMasters.entities.heroes.HeroesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesMaster;
import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tile.TilesMaster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * Manages tools built by heroes.
 * 
 * @author adamsko
 * 
 *         TODO: add tool marker master (class responsible for tool marker)
 * 
 */
public class HeroesToolsMaster extends WorldObjectsContainer {

	private HeroTool heroToolMarker;
	// indicates a type of the heroToolMarker
	private HeroToolType heroToolMarkerType;
	private final HeroToolsFactory heroToolsFactory;

	public HeroesToolsMaster(final TilesMaster TM,
			final GatherCubesMaster gatherCubesMaster,
			final HeroesMaster heroesMaster, final String textureName,
			final int tileW, final int tileH) {
		super("HeroesToolsMaster", TM, textureName, tileW, tileH);

		// addTestObjects();
		heroToolMarker = null;
		heroToolMarkerType = null;

		heroToolsFactory = new HeroToolsFactory(atlasRows, heroesMaster);
	}

	/**
	 * Add hero tool in 'construction' state for marking purpose.
	 */
	public void heroToolMarkerAdd(final Tile heroToolTile) {

		if (heroToolTile == null) {
			Gdx.app.error("addHeroToolMarker", "heroToolTile == null");
			return;
		}

		if (Tile.occupantsRefactor) {

		} else {
			if (heroToolTile.hasItem()) {
				Gdx.app.error("addHeroToolMarker", "heroToolTile.hasItem()");
				return;
			}
		}

		if (heroToolMarkerType == null) {
			Gdx.app.error("addHeroToolMarker", "heroToolMarkerType == null");
			return;
		}

		final Vector2 heroToolTilePos = heroToolTile.getTilesPos();

		heroToolMarker = heroToolsFactory.createHeroTool(heroToolMarkerType,
				heroToolTilePos, this);

		addObject(heroToolMarker);
	}

	/**
	 * @throws Exception
	 * 
	 */
	public void heroToolMarkerRemove() throws Exception {
		if (heroToolMarker != null) {
			removeObjectFromContainer(heroToolMarker);
			heroToolMarker = null;
		}
	}

	/**
	 * HeroToolMarker is accepted: add it to standard HeroTool collection.
	 */
	public void heroToolMarkerAccept() {

		// heroToolMarker just stays in master's collection
		heroToolMarker = null;
	}

	public void setToolTexture(final HeroTool tool, final int index) {
		tool.setTextureRegion(atlasRows.get(index)[tool.getTexNum()]);
	}

	public static int heroTooltypeToCost(final HeroToolType type) {
		switch (type) {
		case TOOL_ORANGE:
			return 0;
		case TOOL_RED:
			return 1;
		case TOOL_TURRET:
			return 3;
		case TOOL_TRAP:
			return 5;
		case TOOL_PORTAL:
			return 10;
		default:
			Gdx.app.error("heroTooltypeToCost", "unknown type");
			return 0;
		}
	}

	public void setHeroToolMarkerType(final HeroToolType heroToolMarkerType) {
		this.heroToolMarkerType = heroToolMarkerType;
	}

	public void removeTool(final HeroTool heroToolToRemove) {
		try {
			removeObjectFromContainer(heroToolToRemove);
		} catch (final Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(final float deltaTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadMapObjects(final CFMap map) {
		final List<Vector2> coordsTraps = map
				.getObjectTypeCoords(TiledObjectType.TILED_HERO_TOOL_TRAP);

		final List<Vector2> coordsPortals = map
				.getObjectTypeCoords(TiledObjectType.TILED_HERO_TOOL_PORTAL);

		HeroTool heroTool = null;
		for (final Vector2 pos : coordsTraps) {
			heroTool = heroToolsFactory.createHeroTool(HeroToolType.TOOL_TRAP,
					pos, this);
			heroTool.setState(HeroToolStates_e.STATE_READY);
			setToolTexture(heroTool, 0);
			addObject(heroTool);
		}
		for (final Vector2 pos : coordsPortals) {
			heroTool = heroToolsFactory.createHeroTool(
					HeroToolType.TOOL_PORTAL, pos, this);
			heroTool.setState(HeroToolStates_e.STATE_READY);
			setToolTexture(heroTool, 0);
			addObject(heroTool);
		}

	}

	@Override
	public void unloadMapObjects() throws Exception {
		removeWorldObjects();
	}

}
