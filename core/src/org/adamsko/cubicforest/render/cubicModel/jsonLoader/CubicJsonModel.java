package org.adamsko.cubicforest.render.cubicModel.jsonLoader;

import java.util.List;

/**
 * Interface for class representing JSON format of a model exported from
 * Blender.
 */
public interface CubicJsonModel {

	String getName();

	void setName(String name);

	List<CubicJsonFrame> getFrames();

	void setFrames(List<CubicJsonFrame> frames);

}
