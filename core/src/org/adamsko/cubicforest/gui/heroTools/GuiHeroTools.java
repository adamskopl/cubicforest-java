package org.adamsko.cubicforest.gui.heroTools;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.gui.GuiElementsContainerDefault;
import org.adamsko.cubicforest.gui.GuiType_e;
import org.adamsko.cubicforest.world.mapsLoader.CFMap;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroesToolsMasterDefault;

import com.badlogic.gdx.graphics.Color;

public class GuiHeroTools extends GuiElementsContainerDefault {

	/**
	 * List containing all available gui elements representing tools. Loaded to
	 * container depending on the current map properties.
	 */
	private List<GuiElementHeroTool> availableGuiTools;

	public GuiHeroTools(final float posX, final float posY) {
		super("guiHeroTools", GuiType_e.GUI_HERO_TOOLS, posX, posY);
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

	@Override
	public void createGui() {

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
					type, this, seqNum * 70, 0);
			guiElementHeroTool.setTexturesManager(getTexturesManager(), type);

			guiElementHeroTool.addLabel(HeroesToolsMasterDefault
					.heroTooltypeToCost(type));
			guiElementHeroTool.altLabelLast(Color.WHITE, 1.0f, 15.0f, 0.0f);
			guiElementHeroTool.addLabel(type.toString());
			guiElementHeroTool.altLabelLast(Color.WHITE, 0.5f, -25.0f, 10.0f);

			availableGuiTools.add(guiElementHeroTool);

			seqNum++;
		}
	}
}
