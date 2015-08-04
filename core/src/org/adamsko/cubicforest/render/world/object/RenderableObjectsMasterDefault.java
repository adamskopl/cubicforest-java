package org.adamsko.cubicforest.render.world.object;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.render.cubicModel.texturesController.CubicTextureController;
import org.adamsko.cubicforest.render.texturesManager.CTextureRegion;
import org.adamsko.cubicforest.render.texturesManager.NullTexturesManager;
import org.adamsko.cubicforest.render.texturesManager.TexturesManager;
import org.adamsko.cubicforest.render.world.objectsTextureChanger.ObjectsTextureChanger;
import org.adamsko.cubicforest.render.world.objectsTextureChanger.ObjectsTextureChangerDefault;
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

	private ObjectsTextureChanger objectsTextureChanger;
	private TexturesManager texturesManager;

	/**
	 * Temporary solution. Keep rows of atlas in a list.
	 */
	protected List<TextureRegion[]> atlasRows;

	/**
	 * Temporary texture region for an experimental cubic texture.
	 */
	protected CTextureRegion cubicTextureRegion;

	public RenderableObjectsMasterDefault(final int nullConstructor) {
		this.texturesManager = null;
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
			final String modelName, final String textureName, final int tileW,
			final int tileH) {
		this.name = new String(name);
		this.textureName = textureName;
		this.modelName = new String(modelName);
		this.tileW = tileW;
		this.tileH = tileH;

		this.texturesManager = NullTexturesManager.instance();

		renderableObjects = new ArrayList<RenderableObject>();
		renderableObjectsUnserved = new ArrayList<RenderableObject>();
		renderableObjectsToRemove = new ArrayList<RenderableObject>();
		initTextures();
	}

	@Override
	public void initTextures() {
		// old way of generating textures
		this.objectsTexture = new Texture(Gdx.files.internal("data/"
				+ this.textureName + ".png"));
		final int rowsNum = this.objectsTexture.getHeight() / this.tileH;
		this.atlasRows = new ArrayList<TextureRegion[]>();
		for (int i = 0; i < rowsNum; i++) {
			this.atlasRows.add(new TextureRegion(this.objectsTexture).split(
					this.tileW, this.tileH)[i]);
		}
	}

	@Override
	public void initCubicModel(
			final CubicTextureController cubicTextureController) {
		this.objectsTextureChanger = new ObjectsTextureChangerDefault(
				this.modelName, cubicTextureController);
		// this.cubicTextureRegion = objectsTextureChanger
		// .tempGetCubicTextureRegion();
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

	@Override
	public void changeTexture(final RenderableObject object,
			final Vector2 textureCoordinates) {
		object.setTextureRegion(atlasRows.get((int) textureCoordinates.x)[(int) textureCoordinates.y]);
	}

	@Override
	public ObjectsTextureChanger getObjectsTextureChanger() {
		return this.objectsTextureChanger;
	}

}