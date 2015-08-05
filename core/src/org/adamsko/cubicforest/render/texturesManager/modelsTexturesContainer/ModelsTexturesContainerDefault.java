package org.adamsko.cubicforest.render.texturesManager.modelsTexturesContainer;

import java.util.HashMap;
import java.util.Map;

import org.adamsko.cubicforest.helpTools.CLog;
import org.adamsko.cubicforest.render.texturesManager.CTextureRegion;
import org.adamsko.cubicforest.render.world.object.visualState.RenderableObjectVisualState;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.tile.TileDirection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

class DirectionTexture {
	public Map<TileDirection, CTextureRegion> map = new HashMap<TileDirection, CTextureRegion>();
}

class ObjectTexturesCollectionTileDirection {
	public Map<RenderableObjectVisualState, DirectionTexture> map = new HashMap<RenderableObjectVisualState, DirectionTexture>();
}

class ObjectTexturesCollectionDimensions {
	public Map<RenderableObjectVisualState, CTextureRegion> map = new HashMap<RenderableObjectVisualState, CTextureRegion>();
}

public class ModelsTexturesContainerDefault implements ModelsTexturesContainer {

	private final Map<WorldObjectType, ObjectTexturesCollectionTileDirection> mapWorldObject = new HashMap<WorldObjectType, ObjectTexturesCollectionTileDirection>();
	private final Map<Vector2, ObjectTexturesCollectionDimensions> mapDimension = new HashMap<Vector2, ObjectTexturesCollectionDimensions>();

	// help variables
	private ObjectTexturesCollectionTileDirection objectTexturesCollectionDirections;
	private ObjectTexturesCollectionDimensions objectTexturesCollectionDimensions;
	private DirectionTexture directionTexture;
	private final Vector2 vector2;

	public ModelsTexturesContainerDefault() {
		CLog.addObject(this, "ModelsTexturesContainer");
		CLog.setUsage(this, true);

		vector2 = new Vector2();
	}

	@Override
	public CTextureRegion get(final WorldObjectType worldObjectType,
			final RenderableObjectVisualState renderableObjectVisualState,
			final TileDirection tileDirection) {
		objectTexturesCollectionDirections = mapWorldObject
				.get(worldObjectType);
		if (objectTexturesCollectionDirections != null) {
			directionTexture = objectTexturesCollectionDirections.map
					.get(renderableObjectVisualState);
			if (directionTexture != null) {
				return directionTexture.map.get(tileDirection);
			}
		}
		return null;
	}

	@Override
	public CTextureRegion get(final int width, final int height,
			final RenderableObjectVisualState renderableObjectVisualState) {
		vector2.set(width, height);
		objectTexturesCollectionDimensions = mapDimension.get(vector2);
		if (objectTexturesCollectionDimensions != null) {
			return objectTexturesCollectionDimensions.map
					.get(renderableObjectVisualState);
		}
		return null;
	}

	@Override
	public void put(final WorldObjectType worldObjectType,
			final RenderableObjectVisualState renderableObjectVisualState,
			final TileDirection tileDirection,
			final CTextureRegion textureRegion) {

		CLog.debug(this,
				"put " + worldObjectType.toString() + " "
						+ renderableObjectVisualState.toString() + " "
						+ tileDirection.toString());

		if (!mapWorldObject.containsKey(worldObjectType)) {
			mapWorldObject.put(worldObjectType,
					new ObjectTexturesCollectionTileDirection());
		}
		objectTexturesCollectionDirections = mapWorldObject
				.get(worldObjectType);

		if (!objectTexturesCollectionDirections.map
				.containsKey(renderableObjectVisualState)) {
			objectTexturesCollectionDirections.map.put(
					renderableObjectVisualState, new DirectionTexture());
		}
		directionTexture = objectTexturesCollectionDirections.map
				.get(renderableObjectVisualState);

		if (!directionTexture.map.containsKey(tileDirection)) {
			directionTexture.map.put(tileDirection, textureRegion);
		} else {
			Gdx.app.error("ModelsTexturesContainerDefault::put",
					"duplication put: " + worldObjectType.toString() + ","
							+ renderableObjectVisualState.toString() + ","
							+ tileDirection.toString());
		}
	}

	@Override
	public void put(final int width, final int height,
			final RenderableObjectVisualState renderableObjectVisualState,
			final CTextureRegion textureRegion) {
		vector2.set(width, height);

		CLog.debug(this, "put " + vector2.toString() + " "
				+ renderableObjectVisualState.toString());

		if (!mapDimension.containsKey(vector2)) {
			mapDimension.put(vector2, new ObjectTexturesCollectionDimensions());
		}
		objectTexturesCollectionDimensions = mapDimension.get(vector2);
		if (!objectTexturesCollectionDimensions.map
				.containsKey(renderableObjectVisualState)) {
			objectTexturesCollectionDimensions.map.put(
					renderableObjectVisualState, textureRegion);
		} else {
			Gdx.app.error("ModelsTexturesContainerDefault::put",
					"duplication put: " + vector2.toString() + ","
							+ renderableObjectVisualState.toString());
		}
	}
}
