package org.adamsko.cubicforest.gui.levels;

import org.adamsko.cubicforest.gui.GuiContainer;
import org.adamsko.cubicforest.gui.GuiElement;
import org.adamsko.cubicforest.gui.GuiType_e;
import org.adamsko.cubicforest.world.mapsLoader.MapsLoader;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class GuiLevels extends GuiContainer {

	public GuiLevels(MapsLoader mapsLoader, TilesMaster TM, String textureName,
			int tileW, int tileH, float posX, float posY) {
		super("guiLevels", TM, GuiType_e.GUI_LEVELS, textureName, tileW, tileH,
				posX, posY);
		createGui(mapsLoader);
	}

	private void createGui(MapsLoader mapsLoader) {

		int levelsNumber = mapsLoader.size();
		int activeLevel = mapsLoader.getMapActiveIndex();
		
		int posX = 0;
		int posY = 40;
		for (int i = 0; i < levelsNumber; i++) {
			if(i % 2 == 0) {
				posX = 0;
				posY -= 40;
			}
			
			GuiElementLevel guiElementLevel = new GuiElementLevel(i,
					atlasRows.get(0)[0], 0, this, posX, posY);

			if(activeLevel == i) {
				higlightButton(guiElementLevel);
			}
			
			guiElementLevel.setRenderVector(new Vector2(0, 0));
			guiElementLevel.addLabel(i + 1);
			guiElementLevel.altLabelLast(Color.WHITE, 1.0f, 12.0f, 22.0f);
			addGuiElement(guiElementLevel);
			
			posX += 40;
		}
		
	}
	
	@Override
	protected void guiElementClicked(GuiElement clickedElement) {
		unhilightButtons();
		higlightButton(clickedElement);
	}
	
	public void levelClicked(int levelIndex) {
		GuiElement e = guiElements.get(levelIndex);
		e.setTextureRegion(atlasRows.get(1)[0]);
	}
	
	private void unhilightButtons() {
		for(GuiElement e: guiElements) {
			e.setTextureRegion(atlasRows.get(0)[0]);
		}
	}
	private void higlightButton(GuiElement element) {
		element.setTextureRegion(atlasRows.get(1)[0]);
	}
}
