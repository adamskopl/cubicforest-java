package org.adamsko.cubicforest.world.objectsMasters.items.heroTools;

import java.util.List;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.world.mapsLoader.CFMap;
import org.adamsko.cubicforest.world.mapsLoader.tiled.TiledObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectsMasterDefault;
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
public class HeroesToolsMaster extends WorldObjectsMasterDefault implements
		Nullable {

	private HeroTool heroToolMarker;
	// indicates a type of the heroToolMarker
	private WorldObjectType heroToolMarkerType;
	private HeroToolsFactory heroToolsFactory;

	public HeroesToolsMaster() {
		super(0);
	}

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

	@Override
	public boolean isNull() {
		return false;
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
		case TOOLPORTAL:
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
	public void loadMapObjects(final CFMap map) {
		final List<Vector2> coordsTraps = map
				.getObjectTypeCoords(TiledObjectType.TILED_HERO_TOOL_TRAP);

		final List<Vector2> coordsPortals = map
				.getObjectTypeCoords(TiledObjectType.TILED_HERO_TOOL_PORTAL);

		HeroTool heroTool = null;
		for (final Vector2 pos : coordsTraps) {
			heroTool = heroToolsFactory.createHeroTool(
					WorldObjectType.TOOLTRAP, pos, this);
			heroTool.setState(HeroToolStates_e.STATE_READY);
			setToolTexture(heroTool, 0);
			addObject(heroTool);
		}
		for (final Vector2 pos : coordsPortals) {
			heroTool = heroToolsFactory.createHeroTool(
					WorldObjectType.TOOLPORTAL, pos, this);
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
