package org.adamsko.cubicforest.gui.resolver;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.gui.GuiElementsContainerDefault;
import org.adamsko.cubicforest.gui.GuiType_e;
import org.adamsko.cubicforest.world.mapsLoader.CFMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class GuiResolverDefault extends GuiElementsContainerDefault implements
GuiResolver {

	private GuiElementResolver elementStart;
	private List<GuiElementResolverSolution> solutions;
	private int availableSolutions;
	private final int maxSolutions;

	public GuiResolverDefault(final String textureName, final int tileW,
			final int tileH, final float posX, final float posY) {
		super("GuiResolverDefault", GuiType_e.GUI_RESOLVER, textureName, tileW,
				tileH, posX, posY);

		maxSolutions = 10;

		createGui();
	}

	@Override
	public void reload(final CFMap cfMap) {
		// gui is not reloading when level is reloaded (because it's being
		// reloaded during resolving process)
	}

	private void createGui() {

		availableSolutions = 0;

		elementStart = new GuiElementResolver(GuiResolverType.RESOLVER_START,
				atlasRows.get(0)[0], 0, this, 0, 0);

		elementStart.setRenderVector(new Vector2(0, 0));
		elementStart.addLabel("S");
		elementStart.altLabelLast(Color.WHITE, 1.0f, 10.0f, 22.0f);

		addGuiElement(elementStart);

		solutions = new ArrayList<GuiElementResolverSolution>();
		for (int i = 0; i < maxSolutions; i++) {
			final GuiElementResolverSolution guiElementResolverSolution = new GuiElementResolverSolution(
					i, GuiResolverType.RESOLVER_SOLUTION, atlasRows.get(0)[0],
					0, this, i * 40 + 45, 0);
			guiElementResolverSolution.setRenderVector(new Vector2(0, 0));
			guiElementResolverSolution.addLabel(i + 1);
			guiElementResolverSolution.altLabelLast(Color.WHITE, 1.0f, 10.0f,
					22.0f);
			solutions.add(guiElementResolverSolution);
		}
	}

	@Override
	public void addNewSolution() {
		availableSolutions++;
		if (availableSolutions > solutions.size()) {
			Gdx.app.error("GuiResolverDefault::newSolution()",
					"maximum number of solutions exceeded");
			return;
		}
		addGuiElement(solutions.get(availableSolutions - 1));
	}

	@Override
	public void newResolveStarted() {
		clearGuiElements();
		addGuiElement(elementStart);
	}
}
