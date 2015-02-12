package org.adamsko.cubicforest.render.cubicModel;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.world.object.CubicObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMaster;

public class CubicModelDefault extends CubicObject implements CubicModel {

	/**
	 * List of cubes in a model.
	 */
	List<CubicAtom> cubes;

	public CubicModelDefault(final WorldObjectsMaster container,
			final WorldObjectType type) {
		super(container, type);
		cubes = new ArrayList<CubicAtom>();
	}

	@Override
	public void addCube(final CubicAtom cube) {
		cubes.add(cube);
	}

	@Override
	public boolean tempIsCubic() {
		return true;
	}

}
