package org.adamsko.cubicforest.world.objectsMasters.items.heroTools;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.helpTools.ConditionalLog;
import org.adamsko.cubicforest.render.cubicModel.texturesController.CubicTextureController;
import org.adamsko.cubicforest.render.world.RenderableObjectsMaster;
import org.adamsko.cubicforest.world.mapsLoader.CFMap;
import org.adamsko.cubicforest.world.mapsLoader.tiled.TiledMapProperties;
import org.adamsko.cubicforest.world.object.NullCubicObject;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.objectsMasters.NullWorldObjectsContainer;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMaster;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMasterDefault;
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
	private GatherCubesMaster gatherCubesMaster;

	// specific tools masters
	private ToolTrapsMaster toolTrapsMaster;
	private ToolExitsMaster toolExitsMaster;

	private List<WorldObjectType> toolTypes;
	/**
	 * Tool types available for current level. Set after map reload.
	 */
	private List<WorldObjectType> mapAvailableTools;

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
		heroToolMarker = NullHeroTool.instance();
		heroToolMarkerType = null;

		toolTypes = new ArrayList<WorldObjectType>();
		setToolTypes();
		mapAvailableTools = new ArrayList<WorldObjectType>();

		ConditionalLog.addObject(this, "HeroesToolsMasterDefault");
		ConditionalLog.setUsage(this, true);
	}

	@Override
	public void initToolsMasters(
			final WorldObjectsMastersContainer worldObjectsMastersContainer,
			final TilesMaster tilesMaster,
			final CubicTextureController cubicTextureController) {
		toolTrapsMaster = new ToolTrapsMaster(tilesMaster,
				"traps-atlas-medium", 40, 45);
		toolTrapsMaster.initCubicModel(cubicTextureController);
		worldObjectsMastersContainer.addWorldObjectsMaster(toolTrapsMaster);
		worldObjectsMastersContainer
				.addRenderableObjectsMaster(toolTrapsMaster);

		toolExitsMaster = new ToolExitsMaster(tilesMaster,
				"exits-atlas-medium", 40, 45);
		toolExitsMaster.initCubicModel(cubicTextureController);
		worldObjectsMastersContainer.addWorldObjectsMaster(toolExitsMaster);
		worldObjectsMastersContainer
				.addRenderableObjectsMaster(toolExitsMaster);
	}

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public void addHeroToolMarker(final Tile heroToolTile) {
		if (heroToolTile.isNull()) {
			Gdx.app.error("HeroesToolsMasterDefault::addHeroToolMarker()",
					"heroToolTile.isNull()");
			return;
		}

		final Vector2 heroToolTilePos = heroToolTile.getTilesPos();

		heroToolMarker = (HeroTool) getToolMaster(heroToolMarkerType)
				.factoryMethod(heroToolTilePos);
		heroToolMarker.setState(HeroToolStates.STATE_CONSTRUCTION);
		((RenderableObjectsMaster) getToolMaster(heroToolMarkerType))
				.changeTexture(heroToolMarker, new Vector2(1, 0));

		getToolMaster(heroToolMarker.getType()).addObject(heroToolMarker);
	}

	@Override
	public void removeHeroToolMarker() {
		if (!heroToolMarker.isNull()) {
			getToolMaster(heroToolMarker.getType()).removeObjectFromTile(
					heroToolMarker);
			getToolMaster(heroToolMarker.getType()).removeObjectFromContainer(
					heroToolMarker);
			heroToolMarker = NullHeroTool.instance();
		}
	}

	@Override
	public void heroToolMarkerAccept() {
		// heroToolMarker just stays in master's collection
		heroToolMarker = NullHeroTool.instance();
	}

	@Override
	public void setToolTexture(final HeroTool tool, final int index) {
		final RenderableObjectsMaster master = (RenderableObjectsMaster) getToolMaster(tool
				.getType());
		master.changeTexture(tool, new Vector2(0, 0));
	}

	@Override
	public void setHeroToolMarkerType(final WorldObjectType heroToolMarkerType) {
		this.heroToolMarkerType = heroToolMarkerType;
	}

	@Override
	public void update(final float deltaTime) {
	}

	@Override
	public WorldObject factoryMethod(final Vector2 tilePos) {
		return NullCubicObject.instance();
	}

	@Override
	public void loadMapObjects(final List<Vector2> tilePositions) {
	}

	@Override
	public void loadMapObjects(final CFMap map) {
		setMapAvailableTools(map);
	}

	@Override
	public void unloadMapObjects() {
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
	public boolean tileContainsTool(final Tile tile) {
		for (final WorldObject worldObject : tile.getOccupants()) {
			if (toolTypes.contains(worldObject.getType())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Get {@link WorldObjectsMaster} for specific {@link HeroTool}.
	 * 
	 * @return
	 */
	private WorldObjectsMaster getToolMaster(
			final WorldObjectType worldObjectType) {
		switch (worldObjectType) {
		case TOOLTRAP:
			return toolTrapsMaster;
		case TOOLEXIT:
			return toolExitsMaster;
		default:
			Gdx.app.error("HeroesToolsMaster::getToolMaster()",
					"unhandled world object type");
			return NullWorldObjectsContainer.instance();
		}
	}

	@Override
	public List<WorldObjectType> getPossibleToolChoices() {
		final List<WorldObjectType> possibleTools = new ArrayList<WorldObjectType>();
		for (final WorldObjectType availableToolType : mapAvailableTools) {
			if (gatherCubesMaster.getGatherCubesCounter().isToolAffordable(
					availableToolType)) {
				possibleTools.add(availableToolType);
			}
		}
		return possibleTools;
	}

	/**
	 * Set the list of available {@link HeroTool} on the map from the Tiled
	 * properties.
	 */
	private void setMapAvailableTools(final CFMap cfMap) {
		mapAvailableTools.clear();
		final TiledMapProperties tiledMapProperties = cfMap.getProperties();
		if (tiledMapProperties.getToolExit()) {
			mapAvailableTools.add(WorldObjectType.TOOLEXIT);
		}
		if (tiledMapProperties.getToolTrap()) {
			mapAvailableTools.add(WorldObjectType.TOOLTRAP);
		}
	}

	private void setToolTypes() {
		toolTypes.add(WorldObjectType.TOOLEXIT);
		toolTypes.add(WorldObjectType.TOOLTRAP);
	}
}
