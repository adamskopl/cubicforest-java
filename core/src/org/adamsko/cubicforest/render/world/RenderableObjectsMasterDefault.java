package org.adamsko.cubicforest.render.world;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.render.cubicModel.CubicModelBuilder;
import org.adamsko.cubicforest.render.cubicModel.CubicModelBuilderDefault;
import org.adamsko.cubicforest.render.cubicModel.CubicTextureController;
import org.adamsko.cubicforest.render.world.renderList.RenderList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class RenderableObjectsMasterDefault implements RenderableObjectsMaster {

	private String name;
	private String textureName;
	private int tileW, tileH;

	/**
	 * name of the model represting objects in this master
	 */
	private String modelName;

	private CubicModelBuilder cubicModelBuilder;

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
		RO_UNSERVED,
		/**
		 * list with {@link RenderableObject} objects that are already in
		 * {@link RenderList} and need to be updated (render order recalculated)
		 */
		RO_TO_UPDATE, RO_TO_REMOVE,
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

	/**
	 * List of {@link RenderableObject}. Indicates which objects from
	 * renderableObjects objects should be updated (sorted) in
	 * {@link RenderList}. The purpose is to separate objects needing to be
	 * updated from those staying unchanged.
	 */
	private List<RenderableObject> renderableObjectsToUpdate;

	private List<RenderableObject> renderableObjectsToRemove;

	protected Texture objectsTexture;

	/**
	 * Temporary solution. Keep rows of atlas in a list.
	 */
	protected List<TextureRegion[]> atlasRows;

	public RenderableObjectsMasterDefault(final int nullConstructor) {
	}

	/**
	 * @param name
	 * @param modelName
	 *            name of the model represting objects in this master
	 * @param textureName
	 * @param tileW
	 * @param tileH
	 */
	public RenderableObjectsMasterDefault(final String name,
			final String modelName, final String textureName, final int tileW,
			final int tileH) {
		this.name = new String(name);
		this.textureName = textureName;
		this.modelName = new String(modelName);
		this.tileW = tileW;
		this.tileH = tileH;

		renderableObjects = new ArrayList<RenderableObject>();
		renderableObjectsUnserved = new ArrayList<RenderableObject>();
		renderableObjectsToRemove = new ArrayList<RenderableObject>();
		initTextures();
	}

	@Override
	public void initTextures() {
		if (this.modelName.isEmpty()) {
			// old way of generating textures
			this.objectsTexture = new Texture(Gdx.files.internal("data/"
					+ this.textureName + ".png"));

			final int rowsNum = this.objectsTexture.getHeight() / this.tileH;
			this.atlasRows = new ArrayList<TextureRegion[]>();
			for (int i = 0; i < rowsNum; i++) {
				this.atlasRows.add(new TextureRegion(this.objectsTexture)
						.split(this.tileW, this.tileH)[i]);
			}
		}
	}

	@Override
	public void initCubicModel(
			final CubicTextureController cubicTextureController) {
		this.cubicModelBuilder = new CubicModelBuilderDefault(
				cubicTextureController);
		this.cubicModelBuilder.loadModel(this.modelName);
		this.atlasRows = cubicModelBuilder.getAtlasRows();
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

	@Override
	public List<RenderableObject> popRenderableObjects(final ROListType_e type) {
		final List<RenderableObject> listOriginal = getRenderableObjects(type);
		final List<RenderableObject> listCopy = new ArrayList<RenderableObject>(
				listOriginal);
		listOriginal.clear();

		return listCopy;
	}

	@Override
	public void changeTexture(final RenderableObject object,
			final Vector2 textureCoordinates) {
		object.setTextureRegion(atlasRows.get((int) textureCoordinates.x)[(int) textureCoordinates.y]);
	}

	@Override
	public void changeTexture(final RenderableObject object,
			final int atlasRow, final int atlasCol) {
		object.setTextureRegion(atlasRows.get(atlasRow)[atlasCol]);
	}
}