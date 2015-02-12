package org.adamsko.cubicforest.render.cubicModel;

import com.badlogic.gdx.math.Vector3;

public class CubicAtomDefault implements CubicAtom {

	/**
	 * position of the cube in a model
	 */
	Vector3 position;

	public CubicAtomDefault(final int posX, final int posY, final int posZ) {
		position = new Vector3(posX, posY, posZ);
	}

}
