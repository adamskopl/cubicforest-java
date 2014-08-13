package org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes;

import org.adamsko.cubicforest.gui.GuiContainer;
import org.adamsko.cubicforest.gui.GuiElement;
import org.adamsko.cubicforest.gui.GuiType_e;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class GatherCubesCounter extends GuiContainer {

	private final Vector2 counter;
	private final int startVal = 0;

	public GatherCubesCounter(final TilesMaster TM, final String textureName,
			final int tileW, final int tileH, final int posX, final int posY) {
		super("gatherCubesCounter", GuiType_e.GUI_CUBES_COUNTER, textureName,
				tileW, tileH, posX, posY);

		counter = new Vector2(startVal, 0);

		createGui();
	}

	private void createGui() {

		GuiElement testObject;
		testObject = new GuiElement(atlasRows.get(0)[3], 0, this, 0, 0);
		testObject.addLabel(counter);
		testObject.altLabelLast(Color.WHITE, 1.0f, 30.0f, 20.0f);
		addGuiElement(testObject);
	}

	public void addValue(final int value) {
		counter.add(value, 0);
	}

	public int getCounter() {
		return (int) counter.x;
	}

	public void reset() {
		counter.set(startVal, 0);
	}

}
