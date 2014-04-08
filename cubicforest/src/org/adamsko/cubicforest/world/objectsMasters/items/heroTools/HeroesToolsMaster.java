package org.adamsko.cubicforest.world.objectsMasters.items.heroTools;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.DocumentEvent.EventType;

import org.adamsko.cubicforest.render.world.RenderableObject;
import org.adamsko.cubicforest.render.world.RenderableObjectsContainer.ROListType_e;
import org.adamsko.cubicforest.world.WorldObjectsMaster;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType_e;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.InteractionObjectsMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.ItemObject;
import org.adamsko.cubicforest.world.objectsMasters.items.ItemObjectType_e;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCubesMaster;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroToolOrange;
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
 * TODO: add tool marker master (class responsible for tool marker)
 * 
 */
public class HeroesToolsMaster extends InteractionObjectsMaster implements
		WorldObjectsMaster {

	private HeroTool heroToolMarker;

	// indicates a type of the heroToolMarker 
	private HeroToolType_e heroToolMarkerType;

	private HeroToolsFactory heroToolsFactory;
	
	private GatherCubesMaster gatherCubesMaster;

	public HeroesToolsMaster(TilesMaster TM, GatherCubesMaster gatherCubesMaster,String textureName, int tileW,
			int tileH) {
		super(TM, WorldObjectType_e.OBJECT_ITEM, textureName, tileW, tileH);

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

		if(heroToolTile == null) {
			Gdx.app.error("addHeroToolMarker", "heroToolTile == null");
			return;
		}
		
		if(heroToolTile.hasItem()) {
			Gdx.app.error("addHeroToolMarker", "heroToolTile.hasItem()");
			return;
		}
		
		if(heroToolMarkerType == null) {
			Gdx.app.error("addHeroToolMarker", "heroToolMarkerType == null");
			return;
		}
		
		Vector2 heroToolTilePos = heroToolTile.getTilesPos();

		heroToolMarker = heroToolsFactory.createHeroTool(
				heroToolMarkerType, heroToolTilePos);

		addObject(heroToolMarker);
	}

	/**
	 * @throws Exception 
	 * 
	 */
	public void heroToolMarkerRemove() throws Exception {
		if(heroToolMarker != null) {
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
		case TOOL_BLUE:
			return 3;
		case TOOL_BLACK:
			return 5;
		case TOOL_WHITE:
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
	public void tileEvent(TileEvent_e eventType, Tile eventTile,
			WorldObject eventObject) {
		
		ItemObject item = (ItemObject) eventTile.getItem();
		if (item.getItemType() != ItemObjectType_e.ITEM_HERO_TOOL) {
			return;
		}
		
		HeroTool tileTool = (HeroTool) item;

		Gdx.app.error("tileevent", "");
		
		switch (eventType) {
		case OCCUPANT_ENTERS:
			break;

		case OCCUPANT_LEAVES:
			break;
			
		case OCCUPANT_STOPS:
			Gdx.app.error("occ stops", "");
			HeroToolStates_e toolState = tileTool.getState();
			switch (toolState) {
			case STATE_CONSTRUCTION:
				tileTool.setTextureRegion(atlasRows.get(0)[tileTool.getTexNum()]);
				gatherCubesMaster.counterAddValue(-tileTool.getBuildCost());
				break;

			default:
				break;
			}
			
			break;

		default:
			break;
		}

	}

}
