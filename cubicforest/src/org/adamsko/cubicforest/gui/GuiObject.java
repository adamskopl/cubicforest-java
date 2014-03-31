package org.adamsko.cubicforest.gui;

import org.adamsko.cubicforest.render.text.LabelsContainer;
import org.adamsko.cubicforest.render.text.ROLabel_e;
import org.adamsko.cubicforest.render.world.RenderableObject;
import org.adamsko.cubicforest.render.world.RenderableObjectType_e;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GuiObject extends RenderableObject {
	
	public GuiObject(TextureRegion tr, int texNum) {
		super(tr, texNum);
		this.renderType = RenderableObjectType_e.TYPE_GUI;
	}

	public void addLabel(ROLabel_e type) throws Exception {
		
	}
}