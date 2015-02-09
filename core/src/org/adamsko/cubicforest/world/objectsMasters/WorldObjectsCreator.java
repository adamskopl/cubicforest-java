package org.adamsko.cubicforest.world.objectsMasters;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.world.object.WorldObject;

import com.badlogic.gdx.math.Vector2;

/**
 * Interface for classes creating {@link WorldObject} objects.
 */
public interface WorldObjectsCreator extends Nullable {

	/**
	 * @param tilePos
	 *            tile on which object is placed
	 * @return created {@link WorldObject}
	 */
	WorldObject factoryMethod(final Vector2 tilePos);

}
