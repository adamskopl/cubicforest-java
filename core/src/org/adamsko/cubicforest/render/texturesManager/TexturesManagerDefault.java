package org.adamsko.cubicforest.render.texturesManager;

import java.util.List;

import org.adamsko.cubicforest.helpTools.CLog;
import org.adamsko.cubicforest.render.cubicModel.jsonLoader.cube.CubicJsonCube;
import org.adamsko.cubicforest.render.texturesManager.ModelsTexturesContainer.ModelsTexturesContainer;
import org.adamsko.cubicforest.render.texturesManager.ModelsTexturesContainer.ModelsTexturesContainerDefault;
import org.adamsko.cubicforest.render.texturesManager.TexturesGenerator.TexturesGenerator;
import org.adamsko.cubicforest.render.texturesManager.TexturesGenerator.TexturesGeneratorDefault;
import org.adamsko.cubicforest.render.world.object.visualState.RenderableObjectVisualState;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.tile.TileDirection;

public class TexturesManagerDefault implements TexturesManager {

	private final TexturesGenerator texturesGenerator;
	private final ModelsManager modelsManager;
	private final ModelsTexturesContainer modelsTexturesContainer;

	// help variables
	private CTextureRegion textureRegion;

	TexturesManagerDefault(final boolean nullConstructor) {
		this.texturesGenerator = null;
		this.modelsManager = null;
		this.modelsTexturesContainer = null;
	}

	public TexturesManagerDefault() {
		this.texturesGenerator = new TexturesGeneratorDefault();
		this.modelsManager = new ModelsManagerDefault();
		this.modelsTexturesContainer = new ModelsTexturesContainerDefault();

		CLog.addObject(this, "TexturesManager");
		CLog.setUsage(this, true);
	}

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public void loadTextures(final WorldObjectType objectType) {
		modelsManager.loadModel(objectType);
	}

	@Override
	public CTextureRegion getTextureRegion(
			final WorldObjectType worldObjectType,
			final RenderableObjectVisualState renderableObjectVisualState,
			final TileDirection tileDirection) {

		textureRegion = modelsTexturesContainer.get(worldObjectType,
				renderableObjectVisualState, tileDirection);
		if (textureRegion == null) {
			// first request for texture, generate it
			final List<CubicJsonCube> directionCubes = modelsManager
					.getModelCubes(worldObjectType, tileDirection);
			if (directionCubes == null) {
				return null;
			}
			textureRegion = texturesGenerator.generate(directionCubes,
					renderableObjectVisualState);
			modelsTexturesContainer.put(worldObjectType,
					renderableObjectVisualState, tileDirection, textureRegion);
		}

		return textureRegion;
	}
}
