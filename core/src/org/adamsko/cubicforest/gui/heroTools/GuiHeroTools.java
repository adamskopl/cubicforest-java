package org.adamsko.cubicforest.gui.heroTools;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.gui.GuiElementsContainerDefault;
import org.adamsko.cubicforest.gui.GuiType_e;
import org.adamsko.cubicforest.world.mapsLoader.CFMap;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroesToolsMaster;

import com.badlogic.gdx.graphics.Color;

public class GuiHeroTools extends GuiElementsContainerDefault {

	/**
	 * List containing all available gui elements representing tools. Loaded to
	 * container depending on the current map properties.
	 */
	private List<GuiElementHeroTool> availableGuiTools;

	public GuiHeroTools(final String textureName, final int tileW,
			final int tileH, final float posX, final float posY) {
		super("guiHeroTools", GuiType_e.GUI_HERO_TOOLS, textureName, tileW,
				tileH, posX, posY);
		createGui();
	}

	@Override
	public void reload(final CFMap cfMap) {

		clearGuiElements();

		int seqNum = 0;
		for (final GuiElementHeroTool guiElementHeroTool : availableGuiTools) {
			if (guiElementHeroTool.mapValid(cfMap.getProperties())) {
				guiElementHeroTool.setContainerPos(seqNum * 70, 0);
				addGuiElement(guiElementHeroTool);
				seqNum++;
			}
		}
	}

	private void createGui() {

		availableGuiTools = new ArrayList<GuiElementHeroTool>();

		final List<WorldObjectType> types = new ArrayList<WorldObjectType>();
		types.add(WorldObjectType.TOOLORANGE);
		types.add(WorldObjectType.TOOLRED);
		types.add(WorldObjectType.TOOLTURRET);
		types.add(WorldObjectType.TOOLTRAP);
		types.add(WorldObjectType.TOOLEXIT);

		int seqNum = 0;
		for (final WorldObjectType type : types) {

			final GuiElementHeroTool guiElementHeroTool = new GuiElementHeroTool(
					type, atlasRows.get(0)[seqNum], 0, this, seqNum * 70, 0);

			guiElementHeroTool.addLabel(HeroesToolsMaster
					.heroTooltypeToCost(type));
			guiElementHeroTool.altLabelLast(Color.WHITE, 1.0f, 15.0f, 0.0f);
			guiElementHeroTool.addLabel(type.toString());
			guiElementHeroTool.altLabelLast(Color.WHITE, 0.5f, -25.0f, 10.0f);

			availableGuiTools.add(guiElementHeroTool);

			seqNum++;
		}
	}
}
