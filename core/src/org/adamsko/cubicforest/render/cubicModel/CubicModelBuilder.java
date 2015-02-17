package org.adamsko.cubicforest.render.cubicModel;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Interface for class managing cubic model made from cubes.
 */
public interface CubicModelBuilder {

	void loadModel(String modelName);

	/**
	 * Temporary method: get basic texture region for development purposes.
	 * Model builder is generating single texture region for now.
	 */
	List<TextureRegion[]> getAtlasRows();

}
