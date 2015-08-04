package org.adamsko.cubicforest.render.world.object.tileDirection;

import org.adamsko.cubicforest.helpTools.CLog;
import org.adamsko.cubicforest.render.texturesManager.CTextureRegion;
import org.adamsko.cubicforest.render.texturesManager.TexturesManager;
import org.adamsko.cubicforest.render.world.object.RenderableObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.tile.TileDirection;

public class TileDirectionChangerDefault implements TileDirectionChanger {

	/**
	 * handled object
	 */
	private final RenderableObject renderableObject;

	/**
	 * world object type indicating which texture to choose
	 */
	private final WorldObjectType worldObjectType;
	private TileDirection tileDirection;
	private final TexturesManager texturesManager;

	public TileDirectionChangerDefault(final RenderableObject renderableObject,
			final WorldObjectType worldObjectType,
			final TexturesManager texturesManager) {
		this.renderableObject = renderableObject;
		this.worldObjectType = worldObjectType;
		// this.tileDirection = TileDirection.randomTileDirection();
		this.tileDirection = TileDirection.E;
		this.texturesManager = texturesManager;

		CLog.addObject(this, "TileDirectionChanger");
		CLog.setUsage(this, false);
	}

	@Override
	public void changeDirection(final TileDirection tileDirection) {
		this.tileDirection = tileDirection;
		final CTextureRegion newTextureRegion = texturesManager
				.getTextureRegion(worldObjectType, renderableObject
						.visualState().getState(), tileDirection);
		renderableObject.setTextureRegion(newTextureRegion);

		CLog.debug(this, "changeDirection " + worldObjectType.toString());
	}

	@Override
	public TileDirection getDirection() {
		return tileDirection;
	}

}
