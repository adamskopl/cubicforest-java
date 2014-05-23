package org.adamsko.cubicforest.world.objectsMasters.items.heroTools;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.DocumentEvent.EventType;

import org.adamsko.cubicforest.render.world.RenderableObject;
import org.adamsko.cubicforest.render.world.RenderableObjectsContainer.ROListType_e;
import org.adamsko.cubicforest.roundsMaster.RoundsMaster;
import org.adamsko.cubicforest.world.WorldObjectsMaster;
import org.adamsko.cubicforest.world.mapsLoader.MapsLoader;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType_e;
import org.adamsko.cubicforest.world.objectsMasters.entities.EntityObject;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.InteractionObjectsMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.ItemObject;
import org.adamsko.cubicforest.world.objectsMasters.items.ItemObjectType_e;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroToolOrange;
import org.adamsko.cubicforest.world.ordersMaster.OrderOperation_e;
import org.adamsko.cubicforest.world.ordersMaster.OrderableObjectsContainer;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;

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

	private HeroesToolsInteractionMaster heroesToolsInteractionMaster;

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

		heroesToolsInteractionMaster = new HeroesToolsInteractionMaster();
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

	private void addTestObjects() {

		List<Vector2> testPositions = new ArrayList<Vector2>();

		testPositions.add(new Vector2(8, 4));

		HeroTool heroTool;
		int atlasIndex = 0;
		for (Vector2 pos : testPositions) {

			heroTool = new HeroToolOrange(atlasRows.get(1)[atlasIndex], 0);
			heroTool.setRenderVector(new Vector2(-atlasRows.get(0)[0]
					.getRegionWidth() / 2, -12));

			pos.add(new Vector2(0.5f, 0.5f));
			pos.add(new Vector2(7, -3));

			heroTool.setTilesPos(pos);
			heroTool.setVerticalPos(0.5f);

			addObject(heroTool);

			if (atlasIndex == 2) {
				atlasIndex = 0;
			} else {
				atlasIndex++;
			}
		}
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

	@Override
	protected void processTileEventImplementation(TileEvent_e eventType,
			Tile eventTile, WorldObject eventObject) {
		// TODO Auto-generated method stub

		ItemObject item = (ItemObject) eventTile.getItem();
		if (item.getItemType() != ItemObjectType_e.ITEM_HERO_TOOL) {
			return;
		}

		HeroTool tileTool = (HeroTool) item;

		switch (eventType) {
		case OCCUPANT_ENTERS:
			break;

		case OCCUPANT_LEAVES:
			break;

		case OCCUPANT_PASSES:
			/*
			 * FIXME: class has no access to RoundsMaster. We are returning back
			 * the effect of the interaction.
			 */
			// orderOperation(OrderOperation_e.ORDER_PAUSE);
			orderOperation(OrderOperation_e.ORDER_PAUSE);

			if (eventObject.getWorldType() == WorldObjectType_e.OBJECT_ENTITY)
				tileTool.onEntityTileEvent((EntityObject) eventObject,
						eventType);
			break;
		case OCCUPANT_STOPS:
			HeroToolStates_e toolState = tileTool.getState();
			switch (toolState) {
			// for now only one situation: Hero is heading right now to finish
			// up a tool
			case STATE_CONSTRUCTION:
				tileTool.setTextureRegion(atlasRows.get(0)[tileTool.getTexNum()]);
				tileTool.setState(HeroToolStates_e.STATE_READY);
				gatherCubesMaster.counterAddValue(-tileTool.getBuildCost());
				break;

			default:
				if (eventObject.getWorldType() == WorldObjectType_e.OBJECT_ENTITY)
					tileTool.onEntityTileEvent((EntityObject) eventObject,
							eventType);
				break;
			}

			break;

		default:
			break;
		}

	}

}
