package org.adamsko.cubicforest.world.object;

/**
 * @author adamsko
 *
 */
//public enum WorldObjectType_e {
//	OBJECT_GENERIC,
//	OBJECT_HERO,
//	OBJECT_ENEMY,
//	OBJECT_GATHER_CUBE,
//	OBJECT_TERRAIN,
//	OBJECT_TILE
//}

public enum WorldObjectType_e {
	OBJECT_UNDEFINED,
	/**
	 * Heroes, enemies..
	 */
	OBJECT_ENTITY,
	/**
	 * GatherCubes...
	 */
	OBJECT_ITEM,
	/**
	 * Trees...
	 */
	OBJECT_TERRAIN
}