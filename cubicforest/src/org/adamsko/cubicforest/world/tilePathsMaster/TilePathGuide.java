package org.adamsko.cubicforest.world.tilePathsMaster;

import org.adamsko.cubicforest.screens.GameScreen;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.objectsMasters.ObjectOperation_e;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.InteractionResult;
import org.adamsko.cubicforest.world.ordersMaster.OrderOperation_e;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;

import com.badlogic.gdx.Gdx;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.equations.Linear;

/**
 * Guides {@link WorldObject} through {@link TilePath} with tweeners.
 * 
 * @author adamsko
 * 
 */
public class TilePathGuide implements TweenCallback {

	/**
	 * Stages of guiding object through the path.
	 * 
	 * @author adamsko
	 * 
	 */
	protected enum GuideStage_e {
		/**
		 * Object is heading from previous tile's border, to the next tile's
		 * center.
		 */
		HEADING_CENTER,
		/**
		 * Object is heading from previous tile's center, to the next tile's
		 * border.
		 */
		HEADING_BORDER
	}

	public static String toString(GuideStage_e stage) {
		switch (stage) {
		case HEADING_BORDER:
			return new String("HEADING_BORDER");
		case HEADING_CENTER:
			return new String("HEADING_CENTER");
		default:
			return new String("GUIDESTAGE UNKNOWN");
		}
	}

	public String getName() {
		return "TilePathGuide";
	}

	/**
	 * Object on the path.
	 */
	private WorldObject wanderer = null;
	private TilePath path = null;
	private TilePathGuideHelper helper;
	private TilePathsMaster master;
	private TilesMaster tilesMaster;

	/**
	 * Pair of tiles: from which and to which wanderers is heading right now.
	 */
	// private Tile tileHeadingFrom, tileHeadingTo;
	private GuideStage_e guideStage;

	/**
	 * 
	 * @param wanderer
	 * @param startingTile
	 * @param path
	 */
	public TilePathGuide(WorldObject wanderer, TilePath path,
			TilePathsMaster master, TilesMaster tilesMaster) {
		this.wanderer = wanderer;
		this.path = path;
		this.master = master;
		this.tilesMaster = tilesMaster;
		helper = new TilePathGuideHelper();

		/*
		 * The first tile is the tile from which wanderer starts. It will be
		 * reassigned to tileHeadingFrom in the first stageBorder() invocation
		 */
		helper.setTileHeadingTo(path.removeFrontTile());

		// set HEADING_CENTER state, for proper nextStage() invocation
		this.guideStage = GuideStage_e.HEADING_CENTER;

		if (path.isEmpty()) {
			master.onPathEnd(this, new InteractionResult());
		}
	}

	/**
	 * Start guiding object through the tiles.
	 */
	public void start() {
		try {
			nextStage();
		} catch (Exception e) {
			Gdx.app.error("TilePathGuide", "nextStage()");
			e.printStackTrace();
		}
	}

	/**
	 * @throws Exception
	 * 
	 */
	private void shiftStage() throws Exception {
		switch (guideStage) {
		case HEADING_BORDER: {
			guideStage = GuideStage_e.HEADING_CENTER;
			break;
		}
		case HEADING_CENTER: {
			guideStage = GuideStage_e.HEADING_BORDER;
			break;
		}
		default: {
			throw new Exception("shiftStage: unsupported stage");
		}
		}
	}

	/**
	 * Based on current wanderer's and path's stage, decide about the next step
	 * in completing the path.
	 * 
	 * @throws Exception
	 */
	private void nextStage() throws Exception {
		// set next stage
		shiftStage();
		switch (guideStage) {
		case HEADING_BORDER: {
			if (path.isEmpty()) {
				// wanderer has reached his goal or started with an empty path

				// path is empty: occupant has reached its goal
				InteractionResult tileEventResult = processTileEvent(
						TileEvent_e.OCCUPANT_STOPS, helper.getTileHeadingTo(),
						wanderer);
				
				if (tileEventResult.getObjectOperation() == ObjectOperation_e.OBJECT_REMOVE) {
					Gdx.app.debug("guide", "REMOVE OBJECT");
				}

				master.onPathEnd(this, tileEventResult);
				return;
			}
			InteractionResult stageResult = stageBorder();

			if (stageResult.getObjectOperation() == ObjectOperation_e.OBJECT_REMOVE) {
				Gdx.app.debug("guide", "REMOVE OBJECT");
			}

			/*
			 * TODO: help function/classes needed
			 */
			if (stageResult.getOrderOperation() == OrderOperation_e.ORDER_FINISH) {
				master.onPathEnd(this, stageResult);
				return;
			}
			break;
		}
		case HEADING_CENTER: {
			InteractionResult stageResult = stageCenter();
			break;
		}
		default: {
			Gdx.app.error("switch(guideState)", "unsupported guideState");
			break;
		}
		}
		// recalculate helper's fields before using them in Tweener
		helper.recalculate(guideStage);
		startTweener();
	}

	private void startTweener() {
		Tween.to(wanderer, helper.getTweenType(), 0.05f)
				.target(helper.getTweenTarget()).ease(Linear.INOUT)
				.setCallback(this).setCallbackTriggers(TweenCallback.COMPLETE)
				.start(GameScreen.tweenManager);
	}

	/**
	 * Before starting Tweener: Perform operations for HEADING_BORDER state: in
	 * this stage wanderer is heading from tileHeadingFrom center to the edge
	 * between tileHeadingFrom and TileHeadingTo. Tile 'PASS' event is
	 * considered.
	 * 
	 * @throws Exception
	 */
	private InteractionResult stageBorder() throws Exception {
		// path is not empty, occupant passes tile
		InteractionResult tileEventResult = processTileEvent(
				TileEvent_e.OCCUPANT_PASSES, helper.getTileHeadingTo(),
				wanderer);

		// assign tileHeadingTo to tileHeadingFrom (tileHeadingTo is
		// a tile that has been reached right now)
		helper.reassignTileFrom();
		// wanderer is heading to border between tileHeadingFrom and
		// tileHeadingTom, remove first Tile, assign it
		helper.setTileHeadingTo(path.removeFrontTile());

		return tileEventResult;
	}

	/**
	 * Perform operations for HEADING_CENTER state: in this stage wanderer is
	 * heading from the edge of the tileHeadingFrom to the center of
	 * TileHeadingTo.
	 * 
	 * @throws Exception
	 */
	private InteractionResult stageCenter() throws Exception {

		InteractionResult tileEventResult = processTileEvent(
				TileEvent_e.OCCUPANT_ENTERS, helper.getTileHeadingTo(),
				wanderer);

		InteractionResult tileEventResult2 = processTileEvent(
				TileEvent_e.OCCUPANT_LEAVES, helper.getTileHeadingFrom(),
				wanderer);

		/*
		 * TODO: which tileEventResult to return
		 */
		return tileEventResult;
	}

	public WorldObject getWanderer() {
		return wanderer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see aurelienribon.tweenengine.TweenCallback#onEvent(int,
	 * aurelienribon.tweenengine.BaseTween)
	 */
	@Override
	public void onEvent(int type, BaseTween<?> source) {
		try {
			nextStage();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Used in: nextStage()[OCCUPANT_STOPS], stageBorder()[OCCUPANT_PASSES],
	 * stageCenter()[OCCUPANT_ENTERS, OCCUPANT_LEAVES]
	 * 
	 * @param tileEvent
	 * @param tile
	 * @param wanderer
	 * @return
	 * @throws Exception
	 */
	private InteractionResult processTileEvent(TileEvent_e tileEvent,
			Tile tile, WorldObject wanderer) throws Exception {
		InteractionResult tileEventOrderResult = tilesMaster.event().tileEvent(
				tileEvent, tile, wanderer);
		return tileEventOrderResult;
	}
}
