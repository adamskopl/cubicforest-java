package org.adamsko.cubicforest.render.world.objectsTextureChanger;

import org.adamsko.cubicforest.render.cubicModel.CubicModelBuilder;
import org.adamsko.cubicforest.render.cubicModel.CubicModelBuilderDefault;
import org.adamsko.cubicforest.render.cubicModel.texturesController.CubicTextureController;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ObjectsTextureChangerDefault implements ObjectsTextureChanger {

	private final String modelName;
	private final CubicTextureController cubicTextureController;
	private final CubicModelBuilder cubicModelBuilder;

	private final TextureRegion tempCubicTextureRegion;

	public ObjectsTextureChangerDefault(final String modelName,
			final CubicTextureController cubicTextureController) {
		this.modelName = modelName;
		this.cubicTextureController = cubicTextureController;
		this.cubicModelBuilder = new CubicModelBuilderDefault(
				this.cubicTextureController);
		this.cubicModelBuilder.loadModel(this.modelName);
		this.tempCubicTextureRegion = cubicModelBuilder.getAtlasRows().get(0)[0];
	}

	@Override
	public TextureRegion tempGetCubicTextureRegion() {
		return this.tempCubicTextureRegion;
	}

}
