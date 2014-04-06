package org.adamsko.cubicforest.render.world;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.gui.GuiElement;
import org.adamsko.cubicforest.render.text.Label;
import org.adamsko.cubicforest.render.world.RenderableObjectsContainer.ROListType_e;
import org.adamsko.cubicforest.render.world.queue.RenderListMaster;
import org.adamsko.cubicforest.world.object.WorldObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer10;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

/**
 * 
 * @author adamsko
 * 
 */
public class WorldRenderer {

	OrthographicCamera cam;

	List<RenderableObjectsMaster> renderableObjectsMastersWorld;
	List<RenderableObjectsMaster> renderableObjectsMastersGui;

	RenderListMaster renderListMasterWorld;
	RenderListMaster renderListMasterGui;

	SpriteBatch batch = new SpriteBatch(5460);
	BitmapFont font = new BitmapFont();

	/**
	 * Width of the isometric tile in pixels.
	 */
	int tileWidth = 75;

	ImmediateModeRenderer10 renderer;

	FPSLogger fps = new FPSLogger();

	public WorldRenderer() {
		renderer = new ImmediateModeRenderer10();
		renderListMasterWorld = new RenderListMaster();
		renderListMasterGui = new RenderListMaster();
		CoordCalc.setTileSize(tileWidth);
		// this.cam = new OrthographicCamera(480, 320);
		this.cam = new OrthographicCamera(780, 460);
		// this.cam.position.set(0, 0, 0);
		// this.cam.position.set(0, -100, 0);
		// this.cam.position.set(240, -160, 0);
		this.cam.position.set(390, -230, 0);
		renderableObjectsMastersWorld = new ArrayList<RenderableObjectsMaster>();
		renderableObjectsMastersGui = new ArrayList<RenderableObjectsMaster>();
	}

	public void render(float deltaTime) {

		cam.update();

		batch.setProjectionMatrix(cam.combined);

		batch.begin();

		renderROMs();

		batch.end();

		// renderGrid();
//		 fps.log();
	}

	void renderGrid() {
		cam.update(false);
		renderer.begin(cam.combined, GL10.GL_POINTS);

		int amount = 20;
		Vector3 vec = new Vector3();
		for (int row = 0; row < amount; row++) {
			for (int col = 0; col < amount; col++) {
				Vector2 screenCoord = CoordCalc.tilesToRender(new Vector2(row,
						col));
				vec.set(screenCoord, 0);
				renderer.color(1.0f, 0.8f, 0.8f, 1.0f);
				renderer.vertex(vec);
			}
		}
		renderer.end();
	}

	public void dispose() {
		batch.dispose();
	}

	public void addROMWorld(RenderableObjectsMaster ROM) {
		renderableObjectsMastersWorld.add(ROM);
	}

	public void addROMGui(RenderableObjectsMaster ROM) {
		renderableObjectsMastersGui.add(ROM);
	}

	/**
	 * Prepare {@link RenderListMaster} for render: add new
	 * {@link RenderableObject} objects, update old ones, sort list.
	 */
	private void updateRenderList(
			List<RenderableObjectsMaster> renderableObjectsMasters,
			RenderListMaster renderListMaster) {
		Boolean sortNeeded = false;
		for (RenderableObjectsMaster rOM : renderableObjectsMasters) {
			List<RenderableObject> objectsUnserved = rOM
					.popRenderableObjects(ROListType_e.RO_UNSERVED);

			List<RenderableObject> objectsToRemove = rOM
					.popRenderableObjects(ROListType_e.RO_TO_REMOVE);

			if (objectsUnserved.size() != 0 || 
					objectsToRemove.size() != 0) {
				sortNeeded = true;
				renderListMaster.addRenderableObjects(objectsUnserved);
				renderListMaster.removeRenderableObjects(objectsToRemove);
			}

			/*
			 * TODO: objectsToUpdate handling. Remove them from renderList and
			 * add to the end, so they can be sorted
			 */
		}

		if (sortNeeded) {
			// renderListMaster has new objects added from ROMs: sort needed
			renderListMaster.sortRenderList();
		}
	}

	private void renderObject(RenderableObject rObj) {
		switch (rObj.getRenderType()) {
		case TYPE_WORLD:
			WorldObject wObj = (WorldObject) rObj;
			Vector2 objPos = wObj.getTilesPos();
			Vector2 renderPos = CoordCalc.tilesToRender(objPos);
			renderPos.add(rObj.getRenderVector());
			batch.draw(wObj.getTextureRegion(), renderPos.x, renderPos.y);
			break;
		case TYPE_GUI:
			GuiElement gObj = (GuiElement) rObj;
			renderPos = gObj.getScreenPos();
			renderPos.add(gObj.getRenderVector());
//			renderPos.add(new Vector2(100, 100));
			batch.draw(gObj.getTextureRegion(), renderPos.x, renderPos.y);
			break;
		default:
			break;
		}
	}

	private void renderObjectsLabels(RenderableObject rObj) {

		Vector2 renderPos = new Vector2();

		switch (rObj.getRenderType()) {
		case TYPE_WORLD:
			WorldObject wObj = (WorldObject) rObj;
			Vector2 objPos = wObj.getTilesPos();
			renderPos = CoordCalc.tilesToRender(objPos);

			break;
		case TYPE_GUI:
			GuiElement gObj = (GuiElement) rObj;
			renderPos = new Vector2(gObj.getScreenPos().x,
					gObj.getScreenPos().y);
			break;
		default:
			break;
		}

		if (rObj.hasLabels()) {
			for (Label l : rObj.getLabels()) {
				renderLabel(l, renderPos);
			}
		}

	}

	private void renderLabel(Label label, Vector2 pos) {
		font.setColor(label.getColor());
		font.setScale(label.getScale());
		pos.add(label.getVecX(), label.getVecY());
		font.draw(batch, label.getValue(), pos.x, pos.y);
	}

	private void renderROMs() {
		updateRenderList(renderableObjectsMastersWorld, renderListMasterWorld);
		updateRenderList(renderableObjectsMastersGui, renderListMasterGui);
		renderRenderList(renderListMasterWorld);
		renderRenderList(renderListMasterGui);

	}

	private void renderRenderList(RenderListMaster renderListMaster) {
		for (RenderableObject rObj : renderListMaster.gerRenderList()) {
			renderObject(rObj);
		}
		/*
		 * Labels are rendered in the end: so they are not covered by
		 * RenderableObject objects
		 */
		for (RenderableObject rObj : renderListMaster.gerRenderList()) {
			renderObjectsLabels(rObj);
		}
	}

	@SuppressWarnings("unused")
	private void renderROM(RenderableObjectsMaster ROM) {
		List<RenderableObject> rObjects = ROM
				.getRenderableObjects(ROListType_e.RO_ALL);
		for (RenderableObject rObj : rObjects) {
			renderObject(rObj);
		}
	}
}
