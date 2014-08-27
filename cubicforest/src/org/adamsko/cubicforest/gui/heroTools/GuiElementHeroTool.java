package org.adamsko.cubicforest.gui.heroTools;

import org.adamsko.cubicforest.gui.GuiContainer;
import org.adamsko.cubicforest.gui.GuiElement;
import org.adamsko.cubicforest.world.object.WorldObjectType;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GuiElementHeroTool extends GuiElement {

	private final WorldObjectType type;

	public GuiElementHeroTool(final WorldObjectType heroToolType,
			final TextureRegion tr, final int texNum,
			final GuiContainer pareContainer, final float posX, final float posY) {
		super(tr, texNum, pareContainer, posX, posY);

		this.type = heroToolType;

	}

	public WorldObjectType getType() {
		return type;
	}

}
