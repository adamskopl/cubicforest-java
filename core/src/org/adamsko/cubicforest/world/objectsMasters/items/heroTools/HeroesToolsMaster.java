package org.adamsko.cubicforest.world.objectsMasters.items.heroTools;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.world.mapsLoader.CFMap;
import org.adamsko.cubicforest.world.mapsLoader.tiled.TiledObjectType;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectsMasterDefault;
import org.adamsko.cubicforest.world.objectsMasters.entities.heroes.HeroesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroTool;
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
public class HeroesToolsMaster extends WorldObjectsMasterDefault implements
		Nullable {

	private HeroTool heroToolMarker;
	// indicates a type of the heroToolMarker
	private WorldObjectType heroToolMarkerType;
	private HeroToolsFactory heroToolsFactory;

	/**
	 * {@link HeroesToolsMaster} in contrast to the other masters is managing
	 * many types of WorldObjects. This vector is used to combine all positions
	 * loaded from {@link CFMap}.
	 */
	private List<Vector2> heroToolsPositions;
	private List<Vector2> mapCoordsTraps;
	private List<Vector2> mapCoordsPortals;
	private List<Vector2> currentCoordsPortals;
	private List<Vector2> currentCoordsTraps;
	// indicates borders between tools positions in heroToolsPositions vector
	private Vector2 newToolSeparator;

	WorldObjectType test;

	public HeroesToolsMaster() {
		super(0);
	}

	public HeroesToolsMaster(final TilesMaster TM,
			final GatherCubesMaster gatherCubesMaster,
			final HeroesMaster heroesMaster, final String textureName,
			final int tileW, final int tileH) {
		super("HeroesToolsMaster", TM, textureName, tileW, tileH);

		heroToolMarker = null;
		heroToolMarkerType = null;

		heroToolsFactory = new HeroToolsFactory(atlasRows, heroesMaster);
		heroToolsPositions = new ArrayList<Vector2>();
		currentCoordsPortals = new ArrayList<Vector2>();
		currentCoordsTraps = new ArrayList<Vector2>();

		newToolSeparator = new Vector2();
	}

	@Override
	public boolean isNull() {
		return false;
	}

	public Vector2 getNewToolSeparator() {
		return newToolSeparator;
	}

	/**
	 * Add hero tool in 'construction' state for marking purpose.
	 */
	public void heroToolMarkerAdd(final Tile heroToolTile) {

		if (heroToolTile == null) {
			Gdx.app.error("addHeroToolMarker", "heroToolTile == null");
			return;
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
	public void heroToolMarkerRemove() {
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

	public static int heroTooltypeToCost(final WorldObjectType worldObjectType) {
		switch (worldObjectType) {
		case TOOLORANGE:
			return 0;
		case TOOLRED:
			return 1;
		case TOOLTURRET:
			return 3;
		case TOOLTRAP:
			return 5;
		case TOOLEXIT:
			return 10;
		default:
			Gdx.app.error("heroTooltypeToCost", "unknown type");
			return 0;
		}
	}

	public void setHeroToolMarkerType(final WorldObjectType heroToolMarkerType) {
		this.heroToolMarkerType = heroToolMarkerType;
	}

	@Override
	public void update(final float deltaTime) {
	}

	@Override
	public void loadMapObjects(final List<Vector2> tilePositions) {
		if (false) {
			/**
			 * In contrast to 'loadMapObjects' from other masters, this function
			 * loads many types with a help of newToolSeperator which should
			 * separate positions in passed tilePositions argument
			 */

			HeroTool heroTool = null;
			int typeIndex = 0;
			for (final Vector2 pos : heroToolsPositions) {
				if (pos == newToolSeparator) {
					typeIndex++;
					continue;
				}
				heroTool = heroToolsFactory.createHeroTool(
						indexToType(typeIndex), pos, this);
				heroTool.setState(HeroToolStates_e.STATE_READY);
				setToolTexture(heroTool, 0);
				addObject(heroTool);
			}
		}
	}

	private WorldObjectType indexToType(final int index) {
		switch (index) {
		case 0:
			return WorldObjectType.TOOLEXIT;
		case 1:
			return WorldObjectType.TOOLTRAP;
		default:
			Gdx.app.error("HeroesToolsMaster::indexToType()", "invalid index");
			return WorldObjectType.DEFAULT;
		}
	}

	@Override
	public void loadMapObjects(final CFMap map) {
		if (false) {
			mapCoordsPortals = map
					.getObjectTypeCoords(TiledObjectType.TILED_HERO_TOOL_EXIT);

			mapCoordsTraps = map
					.getObjectTypeCoords(TiledObjectType.TILED_HERO_TOOL_TRAP);

			combinePositions();

			loadMapObjects(heroToolsPositions);
		}
	}

	@Override
	public void unloadMapObjects() {
		// removeWorldObjects();
	}

	/**
	 * Combine all positions in one vector, which will be passed to loading
	 * function.
	 */
	private void combinePositions() {
		heroToolsPositions.clear();
		// order of the loading is important
		for (final Vector2 pos : mapCoordsPortals) {
			heroToolsPositions.add(pos);
		}
		heroToolsPositions.add(newToolSeparator);
		for (final Vector2 pos : mapCoordsTraps) {
			heroToolsPositions.add(pos);
		}
		heroToolsPositions.add(newToolSeparator);
	}

	/**
	 * Create list of positions of all the tools. Separate them with a
	 * separator, because there are different types of tools.
	 */
	public List<Vector2> getToolsPositionsSeparated() {
		currentCoordsPortals.clear();
		currentCoordsTraps.clear();

		for (final WorldObject tool : getWorldObjects()) {
			final Vector2 pos = tool.getTilesPos();
			switch (tool.getType()) {
			case TOOLEXIT:
				currentCoordsPortals.add(tool.getTilesPos());
				break;
			case TOOLTRAP:
				currentCoordsTraps.add(tool.getTilesPos());
				break;
			default:
				Gdx.app.error(
						"HeroesToolsMaster::getToolsPositionsSeparated()",
						"unknown tool type");
			}
		}

		heroToolsPositions.clear();
		for (final Vector2 pos : currentCoordsPortals) {
			heroToolsPositions.add(pos);
		}
		heroToolsPositions.add(newToolSeparator);
		for (final Vector2 pos : currentCoordsTraps) {
			heroToolsPositions.add(pos);
		}
		heroToolsPositions.add(newToolSeparator);
		return heroToolsPositions;
	}
}
