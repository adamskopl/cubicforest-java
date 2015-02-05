package org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.trap;

import org.adamsko.cubicforest.helpTools.ConditionalLog;
import org.adamsko.cubicforest.world.WorldObjectsMaster;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectVisitor;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.CubicHeroTool;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class HeroToolTrap extends CubicHeroTool {

	public HeroToolTrap(final TextureRegion tr, final int texNum,
			final WorldObjectsMaster container) {
		super(tr, texNum, container, WorldObjectType.TOOLTRAP);
		ConditionalLog.addObject(this, "HeroToolTrap");
		ConditionalLog.setUsage(this, true);
	}

	@Override
	public void accept(final WorldObjectVisitor visitor) {
		visitor.visitToolTrap(this);
	}

	@Override
	public void setRenderVector(final Vector2 vec) {
		ConditionalLog.debug(this, "setRenderVector " + vec.toString());
		super.setRenderVector(vec);
	}

	@Override
	public void setVerticalPos(final Float height) {
		ConditionalLog.debug(this, "setVerticalPos " + Float.toString(height));
		super.setVerticalPos(height);
	}

	@Override
	public void setTilesPos(final Vector2 pos) {
		ConditionalLog.debug(this, "setTilesPos " + pos.toString());
		super.setTilesPos(pos);
	}

	@Override
	public void setTilesPosX(final float x) {
		ConditionalLog.debug(this, "setTilesPosX " + Float.toString(x));
		super.setTilesPosX(x);
	}

	@Override
	public void setTilesPosY(final float y) {
		ConditionalLog.debug(this, "setTilesPosY " + Float.toString(y));
		super.setTilesPosY(y);
	}

}
