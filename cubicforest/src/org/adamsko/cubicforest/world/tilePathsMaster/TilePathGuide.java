package org.adamsko.cubicforest.world.tilePathsMaster;

import org.adamsko.cubicforest.screens.GameScreen;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.collision.handler.OrderOperationHandler;
import org.adamsko.cubicforest.world.ordersMaster.OrderOperation;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.equations.Linear;

import com.badlogic.gdx.Gdx;

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

	public static String toString(final GuideStage_e stage) {
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
	private final TilePathGuideHelper helper;
	private final TilePathsMaster master;
	private final TilesMaster tilesMaster;

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
	public TilePathGuide(final WorldObject wanderer, final TilePath path,
			final TilePathsMaster master, final TilesMaster tilesMaster) {

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
	}

	/**
	 * Start guiding object through the tiles.
	 */
	public void start() {

		if (singleTile()) {
			try {
				handleSingleTile();
			} catch (final Exception e) {
				e.printStackTrace();
			}
			return;
		}

		try {
			nextStage();
		} catch (final Exception e) {
			Gdx.app.error("TilePathGuide", "nextStage()");
			e.printStackTrace();
		}
	}

	private void handleSingleTile() throws Exception {
		processTileEvent(TileEvent.OCCUPANT_STOPS, helper.getTileHeadingTo(),
				wanderer);

		master.onPathEnd(this);
	}

	/**
	 * Check if path is composed from a single tile. At this moment first tile
	 * is popped to 'tileHeadingTo'.
	 * 
	 * @return
	 */
	private boolean singleTile() {
		return (path.isEmpty());
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
				// wanderer has reached his goal or TilePathGuide started with a
				// path composed from a single tile

				// path is empty: occupant has reached its goal
				final OrderOperationHandler orderOperationHandler = processTileEvent(
						TileEvent.OCCUPANT_STOPS, helper.getTileHeadingTo(),
						wanderer);

				master.onPathEnd(this);
				return;
			}
			final OrderOperationHandler orderOperationHandler = stageBorder();

			/* CODE DUPLICATION IN HEADING_CENTER */
			if (orderOperationHandler.getOrderOperation() == OrderOperation.ORDER_FINISH) {
				master.onPathEnd(this);
				return;
			}

			break;
		}
		case HEADING_CENTER: {
			final OrderOperationHandler orderOperationHandler = stageCenter();
			/* CODE DUPLICATION IN HEADING_BORDER */
			if (orderOperationHandler.getOrderOperation() == OrderOperation.ORDER_FINISH) {
				master.onPathEnd(this);
				return;
			}

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
		Tween.to(wanderer, helper.getTweenType(), 0.03f)
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
	private OrderOperationHandler stageBorder() throws Exception {
		// path is not empty, occupant passes tile
		final OrderOperationHandler orderOperationHandler = processTileEvent(
				TileEvent.OCCUPANT_PASSES, helper.getTileHeadingTo(), wanderer);

		wanderer.addMovePoints(-1);
		// assign tileHeadingTo to tileHeadingFrom (tileHeadingTo is
		// a tile that has been reached right now)
		helper.reassignTileFrom();
		// wanderer is heading to border between tileHeadingFrom and
		// tileHeadingTom, remove first Tile, assign it
		helper.setTileHeadingTo(path.removeFrontTile());

		return orderOperationHandler;
	}

	/**
	 * Perform operations for HEADING_CENTER state: in this stage wanderer is
	 * heading from the edge of the tileHeadingFrom to the center of
	 * TileHeadingTo.
	 * 
	 * @throws Exception
	 */
	private OrderOperationHandler stageCenter() throws Exception {

		final OrderOperationHandler orderOperationHandler = processTileEvent(
				TileEvent.OCCUPANT_ENTERS, helper.getTileHeadingTo(), wanderer);

		processTileEvent(TileEvent.OCCUPANT_LEAVES,
				helper.getTileHeadingFrom(), wanderer);

		return orderOperationHandler;
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
	public void onEvent(final int type, final BaseTween<?> source) {
		try {
			nextStage();
		} catch (final Exception e) {
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
	private OrderOperationHandler processTileEvent(final TileEvent tileEvent,
			final Tile tile, final WorldObject wanderer) throws Exception {

		final OrderOperationHandler orderOperationHandler = tilesMaster
				.getTilesEventsHandler().tileEvent(tileEvent, tile, wanderer);

		return orderOperationHandler;
	}
}
