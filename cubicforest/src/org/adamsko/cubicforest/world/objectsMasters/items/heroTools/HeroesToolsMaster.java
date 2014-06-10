package org.adamsko.cubicforest.world.objectsMasters.items.heroTools;

import org.adamsko.cubicforest.world.WorldObjectsMaster;
import org.adamsko.cubicforest.world.mapsLoader.MapsLoader;
import org.adamsko.cubicforest.world.object.WorldObjectType_e;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.InteractionObjectsMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesMaster;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;

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
public class HeroesToolsMaster extends InteractionObjectsMaster implements
		WorldObjectsMaster {

	private HeroTool heroToolMarker;

	// indicates a type of the heroToolMarker
	private HeroToolType_e heroToolMarkerType;

	private HeroToolsFactory heroToolsFactory;

	private GatherCubesMaster gatherCubesMaster;

	public HeroesToolsMaster(MapsLoader mapsLoader, TilesMaster TM,
			GatherCubesMaster gatherCubesMaster, String textureName, int tileW,
			int tileH) {
		super("HeroesToolsMaster", mapsLoader, TM,
				WorldObjectType_e.OBJECT_ITEM, textureName, tileW, tileH);

		// addTestObjects();
		heroToolMarker = null;
		heroToolMarkerType = null;

		heroToolsFactory = new HeroToolsFactory(atlasRows);
		this.gatherCubesMaster = gatherCubesMaster;
	}

	/**
	 * Add hero tool in 'construction' state for marking purpose.
	 */
	public void heroToolMarkerAdd(Tile heroToolTile) {

		if (heroToolTile == null) {
			Gdx.app.error("addHeroToolMarker", "heroToolTile == null");
			return;
		}

		if (heroToolTile.hasItem()) {
			Gdx.app.error("addHeroToolMarker", "heroToolTile.hasItem()");
			return;
		}

		if (heroToolMarkerType == null) {
			Gdx.app.error("addHeroToolMarker", "heroToolMarkerType == null");
			return;
		}

		Vector2 heroToolTilePos = heroToolTile.getTilesPos();

		heroToolMarker = heroToolsFactory.createHeroTool(heroToolMarkerType,
				heroToolTilePos);

		addObject(heroToolMarker);
	}

	/**
	 * @throws Exception
	 * 
	 */
	public void heroToolMarkerRemove() throws Exception {
		if (heroToolMarker != null) {
			removeObject(heroToolMarker);
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

	public void setToolTexture(HeroTool tool, int index) {
		tool.setTextureRegion(atlasRows.get(index)[tool.getTexNum()]);
	}
	
	public static Integer heroTooltypeToCost(HeroToolType_e type) {
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

	public void setHeroToolMarkerType(HeroToolType_e heroToolMarkerType) {
		this.heroToolMarkerType = heroToolMarkerType;
	}

	void removeTool(HeroTool heroToolToRemove) {
		try {
			removeObject(heroToolToRemove);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadMapObjects(MapsLoader mapsLoader) {
		// TODO Auto-generated method stub

	}

	@Override
	public void reload(MapsLoader mapsLoader) {
		try {
			removeWorldObjects();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
