package org.adamsko.cubicforest.render.world.object.tileDirection;

import org.adamsko.cubicforest.render.world.object.RenderableObject;
import org.adamsko.cubicforest.render.world.objectsTextureChanger.ObjectsTextureChanger;
import org.adamsko.cubicforest.world.tile.TileDirection;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TileDirectionChangerDefault implements TileDirectionChanger {

	/**
	 * handled object
	 */
	RenderableObject renderableObject;
	private TileDirection tileDirection;
	private final ObjectsTextureChanger objectsTextureChanger;

	public TileDirectionChangerDefault(final RenderableObject renderableObject,
			final ObjectsTextureChanger objectsTextureChanger) {
		this.renderableObject = renderableObject;
		// this.tileDirection = TileDirection.randomTileDirection();
		this.tileDirection = TileDirection.E;
		this.objectsTextureChanger = objectsTextureChanger;
	}

	@Override
	public void changeDirection(final TileDirection tileDirection) {
		this.tileDirection = tileDirection;
		final TextureRegion newTextureRegion = objectsTextureChanger
				.getTextureRegion(tileDirection, renderableObject.visualState()
						.getState());
		renderableObject.setTextureRegionCubic(newTextureRegion);
	}

	@Override
	public TileDirection getDirection() {
		return tileDirection;
	}

}
