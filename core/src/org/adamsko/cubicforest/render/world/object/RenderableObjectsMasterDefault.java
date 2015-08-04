package org.adamsko.cubicforest.render.world.object;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.render.texturesManager.CTextureRegion;
import org.adamsko.cubicforest.render.texturesManager.NullTexturesManager;
import org.adamsko.cubicforest.render.texturesManager.TexturesManager;
import org.adamsko.cubicforest.render.world.renderList.RenderList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class RenderableObjectsMasterDefault implements RenderableObjectsMaster {

	private String name;

	public String getName() {
		return name;
	}

	/**
	 * Types of {@link RenderableObject} objects lists in container.
	 *
	 * @author adamsko
	 */
	public enum ROListType_e {
		/**
		 * list with {@link RenderableObject} objects, that are not in
		 * {@link RenderList} yet
		 */
		RO_UNSERVED, RO_TO_REMOVE,
		/**
		 * list with all {@link RenderableObject} objects in container
		 */
		RO_ALL
	}

	/**
	 * Current list of {@link RenderableObject} objects. In contrary to
	 * renderableObjectsToServe, it contains all objects which should be
	 * rendered.
	 */
	private List<RenderableObject> renderableObjects;
	/**
	 * List of {@link RenderableObject}. Indicates which objects from
	 * renderableObjects objects should be added to {@link RenderList} (e.g.
	 * objects just created). The purpose is to separate new objects from those
	 * being already in {@link RenderList}.
	 */
	private List<RenderableObject> renderableObjectsUnserved;

	private List<RenderableObject> renderableObjectsToRemove;

	protected Texture objectsTexture;

	private TexturesManager texturesManager;

	/**
	 * Temporary texture region for an experimental cubic texture.
	 */
	protected CTextureRegion cubicTextureRegion;

	public RenderableObjectsMasterDefault(final int nullConstructor) {
		this.texturesManager = NullTexturesManager.instance();
	}

	/**
	 * @param name
	 * @param modelName
	 *            name of the model representing objects in this master
	 * @param textureName
	 * @param tileW
	 * @param tileH
	 */
	public RenderableObjectsMasterDefault(final String name,
			final String modelName) {
		this.name = new String(name);

		this.texturesManager = NullTexturesManager.instance();

		renderableObjects = new ArrayList<RenderableObject>();
		renderableObjectsUnserved = new ArrayList<RenderableObject>();
		renderableObjectsToRemove = new ArrayList<RenderableObject>();
	}

	@Override
	public void setTexturesManager(final TexturesManager texturesManager) {
		this.texturesManager = texturesManager;
	}

	@Override
	public TexturesManager getTexturesManager() {
		return this.texturesManager;
	}

	public void addRenderableObject(final RenderableObject newObject) {
		// add newObject to other RenderableObject objects
		renderableObjects.add(newObject);
		// add newObject to RenderableObject objects, which are not in
		// RenderListMaster yet
		renderableObjectsUnserved.add(newObject);
	}

	public void removeRenderableObject(final RenderableObject newObject) {
		renderableObjects.remove(newObject);
		renderableObjectsToRemove.add(newObject);
	}

	@Override
	public List<RenderableObject> getRenderableObjects(final ROListType_e type) {
		switch (type) {
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

	@Override
	public List<RenderableObject> popRenderableObjects(final ROListType_e type) {
		final List<RenderableObject> listOriginal = getRenderableObjects(type);
		final List<RenderableObject> listCopy = new ArrayList<RenderableObject>(
				listOriginal);
		listOriginal.clear();

		return listCopy;
	}

}