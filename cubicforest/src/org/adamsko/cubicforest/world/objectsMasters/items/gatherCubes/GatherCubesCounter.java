package org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes;

import org.adamsko.cubicforest.render.world.RenderableObjectsContainer;
import org.adamsko.cubicforest.render.world.RenderableObjectsMaster;
import org.adamsko.cubicforest.world.object.WorldObjectType_e;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;

public class GatherCubesCounter extends RenderableObjectsContainer implements
		RenderableObjectsMaster {

	public GatherCubesCounter(TilesMaster TM,
			WorldObjectType_e worldObjectsType, String textureName, int tileW,
			int tileH) {
		super(TM, worldObjectsType, textureName, tileW, tileH);

	}

	@Override
	public void update(float deltaTime) {
		
	}
	

}
