package org.adamsko.cubicforest.render;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.render.queue.RenderListMaster;
import org.adamsko.cubicforest.world.WorldObject;
import org.adamsko.cubicforest.world.WorldObjectsContainer;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class RenderableObjectsContainer extends WorldObjectsContainer {

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
	 * those being already in {@link RenderListMaster}. List is cleared after
	 * being served.
	 */
	private List<RenderableObject> renderableObjectsUnserved;
	protected Texture objectsTexture;
	protected TextureRegion[] atlas;

	public RenderableObjectsContainer(TilesMaster TM, String textureName,
			int tileW, int tileH) {
		super(TM); 
		renderableObjects = new ArrayList<RenderableObject>();
		renderableObjectsUnserved = new ArrayList<RenderableObject>();
		objectsTexture = new Texture(Gdx.files.internal("data/" + textureName
				+ ".png"));
		atlas = new TextureRegion(objectsTexture).split(tileW, tileH)[0];
	}

	public void addRenderableObject(RenderableObject newObject) {
		addWorldObject(newObject);
		// add newObject to other RenderableObject objects
		renderableObjects.add(newObject);
		// add newObject to RenderableObject objects, which are not in
		// RenderListMaster yet
		renderableObjectsUnserved.add(newObject);
	}
	
	public List<WorldObject> getWorldObjects() {
		return super.getWorldObjects();
	}
	
	public List<RenderableObject> getRenderableObjects() {
		return renderableObjects;
	}
	
	public List<RenderableObject> getRenderableObjectsUnserved() {
		return renderableObjectsUnserved;
	}
	
	/**
	 * Get {@link RenderableObject} objects that are not yet in
	 * {@link RenderListMaster}. Clean the list afterwards.
	 * 
	 * @return Copy of the {@link RenderableObject} objects list, with objects,
	 *         that are not in {@link RenderListMaster} yet.
	 */
	public List<RenderableObject> popRenderableObjectsUnserved() {
		// copy is made only to clear renderableObjectsToServe before return
		List<RenderableObject> serveCopy = new ArrayList<RenderableObject>(
				renderableObjectsUnserved);
		renderableObjectsUnserved.clear();
		return serveCopy;
	}
}