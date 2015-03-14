package org.adamsko.cubicforest.render.world.renderList;

import java.util.List;

import org.adamsko.cubicforest.render.world.object.RenderableObject;

/**
 * Manager of the {@link RenderableObject} objects. Responsible for adding,
 * removing and sorting {@link RenderableObject} objects.
 * 
 * @author adamsko
 */
public interface RenderList {

	/**
	 * Sort renderList for rendering. After that, get() should return list of
	 * objects, which displayed one after another will create proper scene.
	 */
	void sort();

	/**
	 * Add new {@link RenderableObject} objects to the render list.
	 * 
	 * @param list
	 *            new objects, not sorted, added to the render list
	 */
	void add(List<RenderableObject> list);

	/**
	 * Returns list of {@link RenderableObject} objects. List is not sorted, if
	 * sort() was not invoked.
	 * 
	 * @return {@link RenderableObject} list
	 */
	List<RenderableObject> get();

	/**
	 * Receiving the list of objects, which are already on renderList, but have
	 * changes their parameters (e.g. height, tilePos, texture). Update those
	 * objects positions on the renderList.
	 * 
	 * @param list
	 *            List with {@link RenderableObject} objects to be updated.
	 */
	void update(List<RenderableObject> list);

	/**
	 * 
	 * @param list
	 *            list with {@link RenderableObject} objects to be removed from
	 *            the renderList.
	 */
	void remove(List<RenderableObject> list);

	/**
	 * Get the amount of the {@link RenderableObject} objects on the render list
	 */
	int size();

	String getName();

}
