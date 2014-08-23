package org.adamsko.cubicforest.world.object;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class NullWorldObject extends WorldObject {

	public NullWorldObject(final TextureRegion tr, final int texNum,
			final WorldObjectsContainer container, final WorldObjectType type,
			final WorldObjectType refactorType) {
		super(tr, texNum, container, type, refactorType);
	}

	@Override
	public boolean isNull() {
		return true;
	}

}
