package org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes;

import org.adamsko.cubicforest.gui.GuiElement;
import org.adamsko.cubicforest.gui.GuiElementsContainer;
import org.adamsko.cubicforest.world.object.WorldObjectType_e;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

public class GatherCubesCounter extends GuiElementsContainer {

	private Float counter;
	
	public GatherCubesCounter(TilesMaster TM,
			WorldObjectType_e worldObjectsType, String textureName, int tileW,
			int tileH, int posX, int posY) {
		super(TM, worldObjectsType, textureName, tileW, tileH, posX, posY);

		counter = new Float(10.0f);
		
		createGui();
	}

	private void createGui() {
		
		GuiElement testObject;
		testObject = new GuiElement(atlasRows.get(0)[3], 0, this, 0, 0);
		testObject.addLabel(counter);
		testObject.altLabelLast(Color.WHITE, 1.0f, 30.0f, 20.0f);
		addGuiElement(testObject);
	}
	
	public void addValue(int value) {
		counter += value;
		guiElements.get(0).testAdd();
		Gdx.app.error("add", Float.toString(counter));
	}
	
}
