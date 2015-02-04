package org.adamsko.cubicforest.world.objectsMasters.items.heroTools;

import java.util.List;

import org.adamsko.cubicforest.world.mapsLoader.CFMap;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectsMasterDefault;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMastersContainer;
import org.adamsko.cubicforest.world.objectsMasters.entities.heroes.HeroesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroTool;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.exit.ToolExitsMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.trap.ToolTrapsMaster;
import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tile.TilesMaster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * Manages tools built by heroes.
 * 
 * @author adamsko
 * 
 */
public class HeroesToolsMasterDefault extends WorldObjectsMasterDefault
		implements HeroesToolsMaster {

	private HeroTool heroToolMarker;
	// indicates a type of the heroToolMarker
	private WorldObjectType heroToolMarkerType;
	private HeroToolsFactory heroToolsFactory;
	private GatherCubesMaster gatherCubesMaster;

	// specific tools masters
	private ToolTrapsMaster toolTrapsMaster;
	private ToolExitsMaster toolExitsMaster;

	// indicates borders between tools positions in heroToolsPositions vector
	private Vector2 newToolSeparator;

	WorldObjectType test;

	public HeroesToolsMasterDefault() {
		super(0);
	}

	public HeroesToolsMasterDefault(final TilesMaster TM,
			final GatherCubesMaster gatherCubesMaster,
			final HeroesMaster heroesMaster, final String textureName,
			final int tileW, final int tileH) {
		super("HeroesToolsMaster", TM, textureName, tileW, tileH);

		this.gatherCubesMaster = gatherCubesMaster;
		heroToolMarker = null;
		heroToolMarkerType = null;

		heroToolsFactory = new HeroToolsFactory(atlasRows, heroesMaster);

		newToolSeparator = new Vector2();
	}

	@Override
	public void initToolsMasters(
			final WorldObjectsMastersContainer worldObjectsMastersContainer,
			final TilesMaster tilesMaster) {
		toolTrapsMaster = new ToolTrapsMaster(tilesMaster,
				"traps-atlas-medium", 40, 45);
		worldObjectsMastersContainer.addWorldObjectsMaster(toolTrapsMaster);
		worldObjectsMastersContainer
				.addRenderableObjectsMaster(toolTrapsMaster);

		toolExitsMaster = new ToolExitsMaster(tilesMaster,
				"exits-atlas-medium", 40, 45);
		worldObjectsMastersContainer.addWorldObjectsMaster(toolExitsMaster);
		worldObjectsMastersContainer
				.addRenderableObjectsMaster(toolExitsMaster);
	}

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public Vector2 getNewToolSeparator() {
		return newToolSeparator;
	}

	@Override
	public void heroToolMarkerAdd(final Tile heroToolTile) {
		if (heroToolTile.isNull()) {
			Gdx.app.error("addHeroToolMarker", "heroToolTile == null");
			return;
		}

		final Vector2 heroToolTilePos = heroToolTile.getTilesPos();

		heroToolMarker = heroToolsFactory.createHeroTool(heroToolMarkerType,
				heroToolTilePos, this);

		addObject(heroToolMarker);
	}

	@Override
	public void heroToolMarkerRemove() {
		if (heroToolMarker != null) {
			removeObjectFromContainer(heroToolMarker);
			heroToolMarker = null;
		}
	}

	@Override
	public void heroToolMarkerAccept() {
		// heroToolMarker just stays in master's collection
		heroToolMarker = null;
	}

	@Override
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

	@Override
	public void setHeroToolMarkerType(final WorldObjectType heroToolMarkerType) {
		this.heroToolMarkerType = heroToolMarkerType;
	}

	@Override
	public void update(final float deltaTime) {
	}

	@Override
	public void loadMapObjects(final List<Vector2> tilePositions) {
	}

	@Override
	public void loadMapObjects(final CFMap map) {
	}

	@Override
	public void unloadMapObjects() {
	}

}
