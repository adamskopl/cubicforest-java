package org.adamsko.cubicforest.render.cubicModel.jsonLoader;

import java.util.List;

/**
 * Interface for class describing one frame of the Blender model. Frames are
 * used to animate models, if there will by any animations.
 */
public interface CubicJsonFrame {

	int getNumber();

	void setNumber(int number);

	List<CubicJsonGroup> getGroups();

	void setGroups(List<CubicJsonGroup> groups);

}
