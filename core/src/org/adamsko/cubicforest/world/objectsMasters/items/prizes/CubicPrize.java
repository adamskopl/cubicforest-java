package org.adamsko.cubicforest.world.objectsMasters.items.prizes;

import org.adamsko.cubicforest.render.world.objectsTextureChanger.ObjectsTextureChanger;
import org.adamsko.cubicforest.world.object.CubicObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectVisitor;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMasterDefault;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class CubicPrize extends CubicObject implements Prize {

	public CubicPrize(final ObjectsTextureChanger objectsTextureChanger,
			final TextureRegion tr, final int texNum,
			final WorldObjectsMasterDefault container) {
		super(objectsTextureChanger, tr, texNum, container,
				WorldObjectType.PRIZE);
	}

	@Override
	public void initTilePropertiesIndicator() {
		super.initTilePropertiesIndicator();
		getTilePropertiesIndicator().setTilePathSearchValid(true);
		getTilePropertiesIndicator().setTileHeroesRangeValid(true);
		getTilePropertiesIndicator().setTileHighlightedAsOccupied(true);
	}

	@Override
	public void accept(final WorldObjectVisitor visitor) {
		visitor.visitPrize(this);
	}

}
