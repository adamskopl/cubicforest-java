package org.adamsko.cubicforest.world.objectsMasters.items.heroTools;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.render.world.RenderableObject;
import org.adamsko.cubicforest.render.world.RenderableObjectsContainer.ROListType_e;
import org.adamsko.cubicforest.world.WorldObjectsMaster;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType_e;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.InteractionObjectsMaster;
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
 */
public class HeroesToolsMaster extends InteractionObjectsMaster implements
		WorldObjectsMaster {

	private HeroTool heroToolMarker;
	private HeroToolsFactory heroToolsFactory;

	public HeroesToolsMaster(TilesMaster TM, String textureName, int tileW,
			int tileH) {
		super(TM, WorldObjectType_e.OBJECT_ITEM, textureName, tileW, tileH);

		// addTestObjects();
		heroToolMarker = null;

		heroToolsFactory = new HeroToolsFactory(atlasRows);

	}

	/**
	 * Add hero tool in 'construction' state for marking purpose.
	 */
	public void addHeroToolMarker(Tile heroToolTile, HeroToolType_e heroToolType) {

		if(heroToolTile.hasItem()) {
			return;
		}
		
		Vector2 heroToolTilePos = heroToolTile.getTilesPos();

		heroToolMarker = heroToolsFactory.createHeroTool(
				heroToolType, heroToolTilePos);

		addObject(heroToolMarker);
	}

	/**
	 * @throws Exception 
	 * 
	 */
	public void removeHeroToolMarker() throws Exception {

		if(heroToolMarker != null) {
			removeObject(heroToolMarker);
			heroToolMarker = null;
		}
		
	}

	/**
	 * HeroToolMarker is accepted: add it to standard HeroTool collection.
	 */
	public void acceptHeroToolMarker() {

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

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void tileEvent(TileEvent_e evenType, Tile eventTile,
			WorldObject eventObject) {
		// TODO Auto-generated method stub

	}

}
