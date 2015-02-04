package org.adamsko.cubicforest.gui.heroTools;

import org.adamsko.cubicforest.gui.GuiElementDefault;
import org.adamsko.cubicforest.gui.GuiElementsContainerDefault;
import org.adamsko.cubicforest.world.mapsLoader.tiled.TiledMapProperties;
import org.adamsko.cubicforest.world.object.WorldObjectType;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GuiElementHeroTool extends GuiElementDefault {

	private final WorldObjectType type;

	public GuiElementHeroTool(final WorldObjectType heroToolType,
			final TextureRegion tr, final int texNum,
			final GuiElementsContainerDefault pareContainer, final float posX,
			final float posY) {
		super(tr, texNum, pareContainer, posX, posY);

		this.type = heroToolType;

	}

	public WorldObjectType getType() {
		return type;
	}

	@Override
	public boolean mapValid(final TiledMapProperties tiledMapProperties) {
		switch (type) {
		case TOOLORANGE:
			return tiledMapProperties.getToolOrange();
		case TOOLEXIT:
			return tiledMapProperties.getToolExit();
		case TOOLRED:
			return tiledMapProperties.getToolRed();
		case TOOLTRAP:
			return tiledMapProperties.getToolTrap();
		case TOOLTURRET:
			return tiledMapProperties.getToolTurret();
		default:
			return false;
		}

	}
}
