package org.adamsko.cubicforest.render.cubicModel.jsonLoader.cube;

import java.util.Comparator;

/**
 * Comparator for {@link CubicJsonCube}. Used to indicate the render order of
 * the cubes in a model: 0: cubes have the same position, -1: render first cube
 * first, 1: render second cube first.
 */
public class CubicJsonCubeRenderComparator implements Comparator<CubicJsonCube> {

	@Override
	public int compare(final CubicJsonCube o1, final CubicJsonCube o2) {
		if (o1.equalPos(o2)) {
			return 0;
		}
		final CubePosition pos1 = o1.getPos();
		final CubePosition pos2 = o2.getPos();

		final int posXdiff = pos1.getX() - pos2.getX();
		final int posYdiff = pos1.getY() - pos2.getY();
		final int posZdiff = pos1.getZ() - pos2.getZ();

		if (posZdiff < 0) {
			return -1;
		}
		if (posZdiff > 0) {
			return 1;
		}
		// cubes has the same Z position
		if (posYdiff < 0) {
			return 1;
		}
		if (posYdiff > 0) {
			return -1;
		}
		// cubes has the same Z, Y positions
		if (posXdiff < 0) {
			return -1;
		}
		// cubes has the same Z, Y positions and second cube has smaller X
		// position
		return 1;
	}
}
