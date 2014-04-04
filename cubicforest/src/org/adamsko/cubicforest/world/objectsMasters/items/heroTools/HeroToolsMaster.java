package org.adamsko.cubicforest.world.objectsMasters.items.heroTools;

import java.util.List;

import org.adamsko.cubicforest.render.world.RenderableObject;
import org.adamsko.cubicforest.render.world.RenderableObjectsContainer.ROListType_e;
import org.adamsko.cubicforest.world.WorldObjectsMaster;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType_e;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.InteractionObjectsMaster;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;

/**
 * Manages tools built by heroes.
 * 
 * @author adamsko
 * 
 */
public class HeroToolsMaster extends InteractionObjectsMaster implements
		WorldObjectsMaster {

	public HeroToolsMaster(TilesMaster TM, String textureName, int tileW,
			int tileH) {
		super(TM, WorldObjectType_e.OBJECT_ITEM, textureName, tileW, tileH);

	}

	@Override
	public List<RenderableObject> getRenderableObjects(ROListType_e type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RenderableObject> popRenderableObjects(ROListType_e type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<WorldObject> getWorldObjects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void tileEvent(TileEvent_e evenType, Tile eventTile,
			WorldObject eventObject) {
		// TODO Auto-generated method stub

	}

}
