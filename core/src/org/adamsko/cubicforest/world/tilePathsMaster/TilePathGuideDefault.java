package org.adamsko.cubicforest.world.tilePathsMaster;

import org.adamsko.cubicforest.screens.GameScreen;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.collision.handler.OrderOperationHandler;
import org.adamsko.cubicforest.world.ordersMaster.OrderOperation;
import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tile.TileDirection;
import org.adamsko.cubicforest.world.tile.TilesMasterDefault.TileCollisionType;
import org.adamsko.cubicforest.world.tile.tilesEvents.TilesEventsHandler;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.equations.Linear;

import com.badlogic.gdx.Gdx;

/**
 * 
 */
public class TilePathGuideDefault implements TilePathGuide {

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

	/**
	 * Object on the path.
	 */
	private WorldObject wanderer = null;
	private TilePath path = null;
	private final TilePathGuideHelper helper;
	private TilePathsMaster master;
	private TilesEventsHandler tilesEventsHandler;

	private GuideStage_e guideStage;

	private static float tweenSpeed = 0.0f;

	public TilePathGuideDefault() {
		helper = new TilePathGuideHelper();
		// set HEADING_CENTER state, for proper nextStage() invocation
		this.guideStage = GuideStage_e.HEADING_CENTER;
	}

	public static void setTweenSpeedHigh() {
		tweenSpeed = 0.0f;
	}

	public static void setTweenSpeedLow() {
		tweenSpeed = 0.1f;
	}

	public static void setTweenSpeedVeryLow() {
		tweenSpeed = 1.0f;
	}

	/**
	 * Start guiding object through the tiles.
	 */
	@Override
	public void start(final WorldObject wanderer, final TilePath path,
			final TilePathsMaster master,
			final TilesEventsHandler tilesEventsHandler) {

		if (tilesEventsHandler.isNull()) {
			Gdx.app.error("TilePathGuideDefault::start",
					"tilesEventsHandler.isNull()");
		}

		this.wanderer = wanderer;
		this.path = path;
		this.master = master;
		this.tilesEventsHandler = tilesEventsHandler;

		/*
		 * The first tile is the tile from which wanderer starts. It will be
		 * reassigned to tileHeadingFrom in the first stageBorder() invocation
		 */
		helper.setTileHeadingTo(path.removeFrontTile());

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

	private void handleSingleTile() throws Exception {
		handleCollision(TileCollisionType.OCCUPANT_STOPS,
				helper.getTileHeadingTo(), wanderer);

		master.onPathEnd(this);
	}

	/**
	 * Check if path is composed from a single tile. At this moment first tile
	 * is popped to 'tileHeadingTo'.
	 */
	private boolean singleTile() {
		return (path.isEmpty());
	}

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
				handleCollision(TileCollisionType.OCCUPANT_STOPS,
						helper.getTileHeadingTo(), wanderer);

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
		if (helper.getTileDirection() != TileDirection.NULL) {
			wanderer.tileDirection().changeDirection(helper.getTileDirection());
		}
		startTweener();
	}

	private void startTweener() {

		Tween.to(wanderer, helper.getTweenType(), tweenSpeed)
				.target(helper.getTweenMoveTarget().x,
						helper.getTweenMoveTarget().y).ease(Linear.INOUT)
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
		final OrderOperationHandler orderOperationHandler = handleCollision(
				TileCollisionType.OCCUPANT_PASSES, helper.getTileHeadingTo(),
				wanderer);

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

		final OrderOperationHandler orderOperationHandler = handleCollision(
				TileCollisionType.OCCUPANT_ENTERS, helper.getTileHeadingTo(),
				wanderer);

		handleCollision(TileCollisionType.OCCUPANT_LEAVES,
				helper.getTileHeadingFrom(), wanderer);

		return orderOperationHandler;
	}

	/**
	 * Used in: nextStage()[OCCUPANT_STOPS], stageBorder()[OCCUPANT_PASSES],
	 * stageCenter()[OCCUPANT_ENTERS, OCCUPANT_LEAVES]
	 */
	private OrderOperationHandler handleCollision(
			final TileCollisionType tileEvent, final Tile tile,
			final WorldObject wanderer) throws Exception {

		final OrderOperationHandler orderOperationHandler = tilesEventsHandler
				.tileCollision(tileEvent, tile, wanderer);

		return orderOperationHandler;
	}
}
