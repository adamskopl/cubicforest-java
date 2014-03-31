package org.adamsko.cubicforest.render.gui;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.render.world.RenderableObject;
import org.adamsko.cubicforest.render.world.RenderableObjectsContainer;
import org.adamsko.cubicforest.render.world.queue.RenderListMaster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Doc: {@link RenderableObjectsContainer}
 * 
 * @author adamsko
 * 
 */
public class GuiElementsContainer {

	public enum GEListType_e {

		GE_UNSERVED,

		GE_TO_UPDATE,

		GE_ALL
	}

	private List<GuiElement> guiElements;
	private List<GuiElement> guiElementsUnserved;
	private List<GuiElement> guiElementsToUpdate;

	protected Texture objectsTexture;
	
	/**
	 * Temporary solution. Keep rows of atlas in a list.
	 */
	protected List<TextureRegion[]> atlasRows;

	public GuiElementsContainer(String textureName, int tileW, int tileH) {
		
		guiElements = new ArrayList<GuiElement>();
		guiElementsUnserved = new ArrayList<GuiElement>();
		
		objectsTexture = new Texture(Gdx.files.internal("data/" + textureName
				+ ".png"));
		
		atlasRows = new ArrayList<TextureRegion[]>();
		atlasRows.add(new TextureRegion(objectsTexture).split(tileW, tileH)[0]);
		atlasRows.add(new TextureRegion(objectsTexture).split(tileW, tileH)[1]);
		
	}
	
	public void addGuiElement(GuiElement newElement) {
		guiElements.add(newElement);
		guiElementsUnserved.add(newElement);
	}
	
	public List<GuiElement> getGuiElements(GEListType_e type) {
		switch (type) {
		case GE_ALL:
			return guiElements;
		case GE_TO_UPDATE:
			return guiElementsToUpdate;
		case GE_UNSERVED:
			return guiElementsUnserved;
		default:
			return null;
		}
	}
	
	public List<GuiElement> popGuiElements(GEListType_e type) {
		List<GuiElement> listOriginal = getGuiElements(type);
		List<GuiElement> listCopy = new ArrayList<GuiElement>(
				listOriginal);
		listOriginal.clear();

		return listCopy;
	}

}
