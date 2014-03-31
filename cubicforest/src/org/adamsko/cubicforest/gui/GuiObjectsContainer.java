package org.adamsko.cubicforest.gui;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.render.world.RenderableObjectsContainer;
import org.adamsko.cubicforest.render.world.RenderableObjectsMaster;
import org.adamsko.cubicforest.world.object.WorldObjectType_e;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;

public class GuiObjectsContainer extends RenderableObjectsContainer implements RenderableObjectsMaster {

	private List<GuiObject> guiObjects;
	
	public GuiObjectsContainer(TilesMaster TM,
			WorldObjectType_e worldObjectsType, String textureName, int tileW,
			int tileH) {
		super(TM, worldObjectsType, textureName, tileW, tileH);
		guiObjects = new ArrayList<GuiObject>();		
	}

	public void addGuiObject(GuiObject guiObject) {
		guiObjects.add(guiObject);
		addRenderableObject(guiObject);
	}
	
}
