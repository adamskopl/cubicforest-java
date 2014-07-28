package org.adamsko.cubicforest.world.ordersMaster;

import java.util.List;

import org.adamsko.cubicforest.world.object.WorldObject;

/**
 * Returns data about objects that can take orders in OrdersMaster (e.g. Heroes
 * and Enemies)
 * 
 * @author adamsko
 * 
 */
public interface OrderableObjectsContainer {

	List<WorldObject> getOrderableObjects();

}
