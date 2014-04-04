package org.adamsko.cubicforest.gui.heroTools;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.gui.GuiContainer;
import org.adamsko.cubicforest.gui.GuiType_e;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroToolType_e;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class GuiHeroTools extends GuiContainer {

	public GuiHeroTools(TilesMaster TM, GuiType_e guiType, String textureName,
			int tileW, int tileH, float posX, float posY) {
		super(TM, guiType, textureName, tileW, tileH, posX, posY);

		createGui();

	}

	private void createGui() {

		int seqNum = 0;
		for (HeroToolType_e type : HeroToolType_e.values()) {

			Gdx.app.error("ADD GUI ELEMENT: ", type.toString());
			

			GuiElementHeroTool guiElementHeroTool = new GuiElementHeroTool(
					type, atlasRows.get(0)[seqNum], 0, this, seqNum * 50, 0);

			addGuiElement(guiElementHeroTool);

			seqNum++;
		}
	}
}
