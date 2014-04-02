package org.adamsko.cubicforest.render.world;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.render.world.queue.RenderListMaster;
import org.adamsko.cubicforest.world.object.WorldObjectType_e;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * @author adamsko
 * 
 */
public class RenderableObjectsContainer {

	/**
	 * Types of {@link RenderableObject} objects lists in container.
	 * 
	 * @author adamsko
	 */
	public enum ROListType_e {
		/**
		 * list with {@link RenderableObject} objects, that are not in
		 * {@link RenderListMaster} yet
		 */
		RO_UNSERVED,
		/**
		 * list with {@link RenderableObject} objects that are already in
		 * {@link RenderListMaster} and need to be updated (render order
		 * recalculated)
		 */
		RO_TO_UPDATE,
		RO_TO_REMOVE,
		/**
		 * list with all {@link RenderableObject} objects in container
		 */
		RO_ALL
	}

	/**
	 * Actual list of {@link RenderableObject} objects. In contrary to
	 * renderableObjectsToServe, it contains all objects which should be
	 * rendered.
	 */
	private List<RenderableObject> renderableObjects;
	/**
	 * List of {@link RenderableObject}. Indicates which objects from
	 * renderableObjects objects should be added to {@link RenderListMaster}
	 * (e.g. objects just created). The purpose is to separate new objects from
	 * those being already in {@link RenderListMaster}.
	 */
	private List<RenderableObject> renderableObjectsUnserved;

	/**
	 * List of {@link RenderableObject}. Indicates which objects from
	 * renderableObjects objects should be updated (sorted) in
	 * {@link RenderListMaster}. The purpose is to separate objects needing to
	 * be updated from those staying unchanged.
	 */
	private List<RenderableObject> renderableObjectsToUpdate;
	
	private List<RenderableObject> renderableObjectsToRemove;

	protected Texture objectsTexture;

	/**
	 * Temporary solution. Keep rows of atlas in a list.
	 */
	protected List<TextureRegion[]> atlasRows;

	public RenderableObjectsContainer(TilesMaster TM, String textureName,
			int tileW, int tileH) {
		renderableObjects = new ArrayList<RenderableObject>();
		renderableObjectsUnserved = new ArrayList<RenderableObject>();
		renderableObjectsToRemove = new ArrayList<RenderableObject>();
		
		objectsTexture = new Texture(Gdx.files.internal("data/" + textureName
				+ ".png"));
		
		atlasRows = new ArrayList<TextureRegion[]>();
		atlasRows.add(new TextureRegion(objectsTexture).split(tileW, tileH)[0]);
		atlasRows.add(new TextureRegion(objectsTexture).split(tileW, tileH)[1]);
	}

	public void addRenderableObject(RenderableObject newObject) {
		// add newObject to other RenderableObject objects
		renderableObjects.add(newObject);
		// add newObject to RenderableObject objects, which are not in
		// RenderListMaster yet
		renderableObjectsUnserved.add(newObject);
	}
	
	public void removeRenderableObject(RenderableObject newObject) {
		renderableObjects.remove(newObject);
		renderableObjectsToRemove.add(newObject);
	}

	/**
	 * Get {@link RenderableObject} list of given {@link ROListType_e} type.
	 * 
	 * @param type
	 *            type of {@link RenderableObject} objects list.
	 * @return {@link RenderableObject} objects list of given {@link ROListType_e}
	 *         type.
	 */
	public List<RenderableObject> getRenderableObjects(ROListType_e type) {
		switch (type) {
		case RO_TO_UPDATE: {
			return renderableObjectsToUpdate;
		}

		case RO_UNSERVED: {
			return renderableObjectsUnserved;
		}

		case RO_TO_REMOVE: {
			return renderableObjectsToRemove;
		}
		
		
		case RO_ALL: {
			return renderableObjects;
		}

		default:
			Gdx.app.error("getRenderableObjects", "type not supported");
			return null;
		}
	}

	/**
	 * Get {@link RenderableObject} list of given {@link ROListType_e} type. Clear
	 * corresponding list in container afterwards.
	 * 
	 * @param type
	 *            type of {@link RenderableObject} objects list.
	 * 
	 * @return Copy of the {@link RenderableObject} objects list of given
	 *         {@link ROListType_e} type.
	 */
	public List<RenderableObject> popRenderableObjects(ROListType_e type) {
		List<RenderableObject> listOriginal = getRenderableObjects(type);
		List<RenderableObject> listCopy = new ArrayList<RenderableObject>(
				listOriginal);
		listOriginal.clear();

		return listCopy;
	}

}