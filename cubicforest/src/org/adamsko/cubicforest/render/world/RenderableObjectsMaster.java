package org.adamsko.cubicforest.render.world;

import java.util.List;

import org.adamsko.cubicforest.render.world.RenderableObjectsContainer.ROListType_e;
import org.adamsko.cubicforest.render.world.queue.RenderListMaster;
import org.adamsko.cubicforest.world.WorldObjectsMaster;

public interface RenderableObjectsMaster extends WorldObjectsMaster {

	/**
	 * @return all {@link RenderableObject} in implementing class.
	 */
	public List<RenderableObject> getRenderableObjects(ROListType_e type);
	
	/**
	 * @return {@link RenderableObject} objects, which are not added to
	 *         {@link RenderListMaster} yet. Clean objects afterwards.
	 */
	public List<RenderableObject> popRenderableObjects(ROListType_e type);
	
}