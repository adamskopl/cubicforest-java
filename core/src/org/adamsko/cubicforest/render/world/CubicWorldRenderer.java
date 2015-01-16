package org.adamsko.cubicforest.render.world;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.gui.GuiElement;
import org.adamsko.cubicforest.render.text.Label;
import org.adamsko.cubicforest.render.world.RenderableObjectsMasterDefault.ROListType_e;
import org.adamsko.cubicforest.render.world.coordCalc.CoordCalc;
import org.adamsko.cubicforest.render.world.renderList.RenderList;
import org.adamsko.cubicforest.render.world.renderList.RenderListDefault;
import org.adamsko.cubicforest.world.object.WorldObject;

import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer20;
import com.badlogic.gdx.math.Vector2;

/**
 * 
 * @author adamsko
 * 
 */
public class CubicWorldRenderer implements GameRenderer {

	OrthographicCamera cam;

	private CoordCalc coordCalcX;

	List<RenderableObjectsMaster> renderableObjectsMastersWorld;
	List<RenderableObjectsMaster> renderableObjectsMastersGui;

	RenderList renderListMasterWorld;
	RenderList renderListMasterGui;

	SpriteBatch batch = new SpriteBatch(5460);
	BitmapFont font = new BitmapFont();

	/**
	 * Width of the isometric tile in pixels.
	 */
	int tileWidth = 75;

	ImmediateModeRenderer renderer;

	FPSLogger fps = new FPSLogger();

	public CubicWorldRenderer() {
		renderer = new ImmediateModeRenderer20(false, true, 0);

		renderListMasterWorld = new RenderListDefault("renderListWorld");
		renderListMasterGui = new RenderListDefault("renderListGui");

		this.cam = new OrthographicCamera(780, 460);
		this.cam.position.set(390, -230, 0);
		renderableObjectsMastersWorld = new ArrayList<RenderableObjectsMaster>();
		renderableObjectsMastersGui = new ArrayList<RenderableObjectsMaster>();
	}

	@Override
	public void render(final float deltaTime) {

		cam.update();

		batch.setProjectionMatrix(cam.combined);

		batch.begin();

		renderROMs();

		batch.end();

	}

	@Override
	public void setCoordCalc(final CoordCalc coordCalcX) {
		this.coordCalcX = coordCalcX;
	}

	@Override
	public void addROMWorld(final RenderableObjectsMaster ROM) {
		renderableObjectsMastersWorld.add(ROM);
	}

	@Override
	public void addROMGui(final RenderableObjectsMaster ROM) {
		renderableObjectsMastersGui.add(ROM);
	}

	@Override
	public void dispose() {
		batch.dispose();
	};

	/**
	 * Prepare {@link RenderListDefault} for render: add new
	 * {@link RenderableObject} objects, update old ones, sort list.
	 */
	private void updateList(
			final List<RenderableObjectsMaster> renderableObjectsMasters,
			final RenderList renderList) {
		Boolean sortNeeded = false;
		for (final RenderableObjectsMaster rOM : renderableObjectsMasters) {
			final List<RenderableObject> objectsUnserved = rOM
					.popRenderableObjects(ROListType_e.RO_UNSERVED);

			final List<RenderableObject> objectsToRemove = rOM
					.popRenderableObjects(ROListType_e.RO_TO_REMOVE);

			if (objectsUnserved.size() != 0 || objectsToRemove.size() != 0) {
				sortNeeded = true;
				renderList.add(objectsUnserved);
				renderList.remove(objectsToRemove);
			}

			/*
			 * TODO: objectsToUpdate handling. Remove them from renderList and
			 * add to the end, so they can be sorted
			 */
		}

		if (sortNeeded) {
			// renderListMaster has new objects added from ROMs: sort needed
			renderList.sort();
		}

		// uncomment to see if render list size. good to make sure, that list is
		// managed correctly
		// Gdx.app.debug(renderList.getName(),
		// Integer.toString(renderList.size()));
	}

	private void renderObject(final RenderableObject rObj) {
		switch (rObj.getRenderType()) {
		case TYPE_WORLD:
			final WorldObject wObj = (WorldObject) rObj;
			final Vector2 objPos = wObj.getTilesPos();
			Vector2 renderPos = coordCalcX.tilesToRender(objPos);
			renderPos.add(rObj.getRenderVector());
			batch.draw(rObj.getTextureRegion(), renderPos.x, renderPos.y);
			break;
		case TYPE_GUI:
			final GuiElement gObj = (GuiElement) rObj;
			renderPos = gObj.getScreenPos();
			renderPos.add(gObj.getRenderVector());
			batch.draw(gObj.getTextureRegion(), renderPos.x, renderPos.y);
			break;
		default:
			break;
		}
	}

	private void renderObjectsLabels(final RenderableObject rObj) {

		Vector2 renderPos = new Vector2();

		switch (rObj.getRenderType()) {
		case TYPE_WORLD:
			final WorldObject wObj = (WorldObject) rObj;
			final Vector2 objPos = wObj.getTilesPos();
			renderPos = coordCalcX.tilesToRender(objPos);

			break;
		case TYPE_GUI:
			final GuiElement gObj = (GuiElement) rObj;
			renderPos = new Vector2(gObj.getScreenPos().x,
					gObj.getScreenPos().y);
			break;
		default:
			break;
		}

		if (rObj.hasLabels()) {
			for (final Label l : rObj.getLabels()) {
				renderLabel(l, renderPos);
			}
		}

	}

	private void renderLabel(final Label label, final Vector2 pos) {
		font.setColor(label.getColor());
		font.setScale(label.getScale());
		pos.add(label.getVecX(), label.getVecY());
		font.draw(batch, label.getValue(), pos.x, pos.y);
	}

	private void renderROMs() {
		updateList(renderableObjectsMastersWorld, renderListMasterWorld);
		updateList(renderableObjectsMastersGui, renderListMasterGui);
		renderList(renderListMasterWorld);
		renderList(renderListMasterGui);
	}

	private void renderList(final RenderList renderListMaster) {
		for (final RenderableObject rObj : renderListMaster.get()) {
			renderObject(rObj);
		}
		/*
		 * Labels are rendered in the end: so they are not covered by
		 * RenderableObject objects
		 */
		for (final RenderableObject rObj : renderListMaster.get()) {
			renderObjectsLabels(rObj);
		}
	}

}
