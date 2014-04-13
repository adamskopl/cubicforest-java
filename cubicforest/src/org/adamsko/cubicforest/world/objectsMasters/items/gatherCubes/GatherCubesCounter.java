package org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes;

import org.adamsko.cubicforest.gui.GuiElement;
import org.adamsko.cubicforest.gui.GuiContainer;
import org.adamsko.cubicforest.gui.GuiType_e;
import org.adamsko.cubicforest.world.object.WorldObjectType_e;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroTool;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroToolType_e;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class GatherCubesCounter extends GuiContainer {

	private Vector2 counter;

	public GatherCubesCounter(TilesMaster TM, String textureName, int tileW,
			int tileH, int posX, int posY) {
		super("gatherCubesCounter", TM, GuiType_e.GUI_CUBES_COUNTER, textureName, tileW, tileH, posX,
				posY);

		counter = new Vector2(4, 0);

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
		counter.add(value, 0);
	}
	
	public Integer getCounter() {
		return (int) counter.x;
	}

	public void reset() {
		counter.set(4, 0);
	}

}
