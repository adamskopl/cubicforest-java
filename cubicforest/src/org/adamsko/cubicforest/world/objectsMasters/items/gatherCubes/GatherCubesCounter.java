package org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes;

import org.adamsko.cubicforest.gui.GuiObject;
import org.adamsko.cubicforest.gui.GuiObjectsContainer;
import org.adamsko.cubicforest.world.object.WorldObjectType_e;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;

public class GatherCubesCounter extends GuiObjectsContainer {

	public GatherCubesCounter(TilesMaster TM,
			WorldObjectType_e worldObjectsType, String textureName, int tileW,
			int tileH) {
		super(TM, worldObjectsType, textureName, tileW, tileH);

		createGui();
	}

	private void createGui() {
		
		GuiObject testObject;
		testObject = new GuiObject(atlasRows.get(0)[0], 0);
		testObject.addLabel(new String("COUNTER"));
		addGuiObject(testObject);
		
	}
	
}
