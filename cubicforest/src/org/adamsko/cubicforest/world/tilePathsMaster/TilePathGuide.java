package org.adamsko.cubicforest.world.tilePathsMaster;

import org.adamsko.cubicforest.screens.GameScreen;
import org.adamsko.cubicforest.world.object.WorldObject;
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

		// FIXME: onPathEnd() here... will it not cause errors?
		if (path.isEmpty()) {
			master.onPathEnd(this);
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
				sendTileEvent(TileEvent_e.OCCUPANT_STOPS,
						helper.getTileHeadingTo(), wanderer);

				master.onPathEnd(this);
				return;
			}
			stageBorder();
			break;
		}
		case HEADING_CENTER: {
			stageCenter();
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
	 * between tileHeadingFrom and TileHeadingTo.
	 * 
	 * @throws Exception
	 */
	private void stageBorder() throws Exception {
		// path is not empty, occupant passes tile
		sendTileEvent(TileEvent_e.OCCUPANT_PASSES,
				helper.getTileHeadingTo(), wanderer);

		// assign tileHeadingTo to tileHeadingFrom (tileHeadingTo is
		// a tile that has been reached right now)
		helper.reassignTileFrom();
		// wanderer is heading to border between tileHeadingFrom and
		// tileHeadingTom, remove first Tile, assign it
		helper.setTileHeadingTo(path.removeFrontTile());
	}

	/**
	 * Perform operations for HEADING_CENTER state: in this stage wanderer is
	 * heading from the edge of the tileHeadingFrom to the center of
	 * TileHeadingTo.
	 * 
	 * @throws Exception
	 */
	private void stageCenter() throws Exception {
		
		sendTileEvent(TileEvent_e.OCCUPANT_ENTERS, helper.getTileHeadingTo(),
				wanderer);

		sendTileEvent(TileEvent_e.OCCUPANT_LEAVES, helper.getTileHeadingFrom(),
				wanderer);
	}

	public WorldObject getWanderer() {
		return wanderer;
	}

	@Override
	public void onEvent(int type, BaseTween<?> source) {
		try {
			nextStage();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void sendTileEvent(TileEvent_e tileEvent, Tile tile,
			WorldObject wanderer) throws Exception {
		tilesMaster.event().tileEvent(tileEvent, tile, wanderer);
	}

	public void pathOperation(OrderOperation_e operation) {
		Gdx.app.debug(getName(), "pathOperation " + operation.toString());
	}

}
