package org.adamsko.cubicforest.render.texturesManager.ModelsTexturesContainer;

import java.util.HashMap;
import java.util.Map;

import org.adamsko.cubicforest.helpTools.CLog;
import org.adamsko.cubicforest.render.texturesManager.CTextureRegion;
import org.adamsko.cubicforest.render.world.object.visualState.RenderableObjectVisualState;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.tile.TileDirection;

import com.badlogic.gdx.Gdx;

class DirectionTexture {
	public Map<TileDirection, CTextureRegion> map = new HashMap<TileDirection, CTextureRegion>();
}

class ObjectTexturesCollection {
	public Map<RenderableObjectVisualState, DirectionTexture> map = new HashMap<RenderableObjectVisualState, DirectionTexture>();
}

public class ModelsTexturesContainerDefault implements ModelsTexturesContainer {

	private final Map<WorldObjectType, ObjectTexturesCollection> map = new HashMap<WorldObjectType, ObjectTexturesCollection>();

	// help variables
	private ObjectTexturesCollection objectTexturesCollection;
	private DirectionTexture directionTexture;

	public ModelsTexturesContainerDefault() {
		CLog.addObject(this, "ModelsTexturesContainer");
		CLog.setUsage(this, true);
	}

	@Override
	public CTextureRegion get(final WorldObjectType worldObjectType,
			final RenderableObjectVisualState renderableObjectVisualState,
			final TileDirection tileDirection) {
		if (map.containsKey(worldObjectType)) {
			objectTexturesCollection = map.get(worldObjectType);
			if (objectTexturesCollection.map
					.containsKey(renderableObjectVisualState)) {
				directionTexture = objectTexturesCollection.map
						.get(renderableObjectVisualState);
				return directionTexture.map.get(tileDirection);
			}
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

		if (!map.containsKey(worldObjectType)) {
			map.put(worldObjectType, new ObjectTexturesCollection());
		}
		objectTexturesCollection = map.get(worldObjectType);

		if (!objectTexturesCollection.map
				.containsKey(renderableObjectVisualState)) {
			objectTexturesCollection.map.put(renderableObjectVisualState,
					new DirectionTexture());
		}
		directionTexture = objectTexturesCollection.map
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
}
