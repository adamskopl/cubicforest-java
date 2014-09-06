package org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes;

import org.adamsko.cubicforest.gui.GuiElement;
import org.adamsko.cubicforest.gui.GuiElementsContainerDefault;
import org.adamsko.cubicforest.gui.GuiType_e;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroesToolsMaster;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class GatherCubesCounterDefault extends GuiElementsContainerDefault
		implements GatherCubesCounter {

	private final Vector2 counter;
	private int startingValue;

	public GatherCubesCounterDefault(final String textureName, final int tileW,
			final int tileH, final int posX, final int posY) {
		super("gatherCubesCounter", GuiType_e.GUI_CUBES_COUNTER, textureName,
				tileW, tileH, posX, posY);

		counter = new Vector2(0, 0);

		createGui();
	}

	@Override
	public void setStartingValue(final int startingValue) {
		this.startingValue = startingValue;
		counter.set(startingValue, 0);
	}

	@Override
	public void reset() {
		counter.set(startingValue, 0);
	}

	@Override
	public void addValue(final int value) {
		counter.add(value, 0);
	}

	@Override
	public int getCounter() {
		return (int) counter.x;
	}

	@Override
	public Boolean isToolAffordable(final WorldObjectType heroToolType) {
		final int toolCost = HeroesToolsMaster.heroTooltypeToCost(heroToolType);
		if (toolCost <= getCounter()) {
			return true;
		}
		return false;
	}

	private void createGui() {

		GuiElement testObject;
		testObject = new GuiElement(atlasRows.get(0)[3], 0, this, 0, 0);
		testObject.addLabel(counter);
		testObject.altLabelLast(Color.WHITE, 1.0f, 30.0f, 20.0f);
		addGuiElement(testObject);
	}

}