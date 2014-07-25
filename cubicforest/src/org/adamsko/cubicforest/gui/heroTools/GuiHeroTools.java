package org.adamsko.cubicforest.gui.heroTools;

import org.adamsko.cubicforest.gui.GuiContainer;
import org.adamsko.cubicforest.gui.GuiType_e;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroToolType;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroesToolsMaster;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;
import com.badlogic.gdx.graphics.Color;

public class GuiHeroTools extends GuiContainer {

	public GuiHeroTools(TilesMaster TM, String textureName, int tileW,
			int tileH, float posX, float posY) {
		super("guiHeroTools", TM, GuiType_e.GUI_HERO_TOOLS, textureName, tileW,
				tileH, posX, posY);
		createGui();
	}

	private void createGui() {

		int seqNum = 0;
		for (HeroToolType type : HeroToolType.values()) {

			GuiElementHeroTool guiElementHeroTool = new GuiElementHeroTool(
					type, atlasRows.get(0)[seqNum], 0, this, seqNum * 70, 0);

			guiElementHeroTool.addLabel(HeroesToolsMaster
					.heroTooltypeToCost(type));
			guiElementHeroTool.altLabelLast(Color.WHITE, 1.0f, 15.0f, 0.0f);
			guiElementHeroTool.addLabel(type.toString());
			guiElementHeroTool.altLabelLast(Color.WHITE, 0.5f, -25.0f, 10.0f);

			addGuiElement(guiElementHeroTool);

			seqNum++;
		}
	}
}
