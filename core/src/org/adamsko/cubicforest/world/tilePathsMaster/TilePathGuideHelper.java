package org.adamsko.cubicforest.world.tilePathsMaster;

import org.adamsko.cubicforest.world.object.accessor.WorldObjectAccessor;
import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tile.TileDirection;
import org.adamsko.cubicforest.world.tile.TilesHelper;
import org.adamsko.cubicforest.world.tile.TilesHelper.TilesConnection_e;
import org.adamsko.cubicforest.world.tile.Vector2TileDirectionStruct;
import org.adamsko.cubicforest.world.tilePathsMaster.TilePathGuideDefault.GuideStage_e;

import com.badlogic.gdx.math.Vector2;

/**
 * Helping class containing constantly changed variables needed by
 * {@link TilePathGuideDefault}.
 * 
 * @author adamsko
 * 
 */
public class TilePathGuideHelper {

	/**
	 * Pair of tiles: from which and to which wanderers is heading right now.
	 */
	private Tile tileHeadingFrom, tileHeadingTo;
	/**
	 * type of the current Tween (e.g. POSITION_X)
	 */
	private int tweenType;
	/**
	 * Target position for moved objects: when tweener is started, moved object
	 * is heading on this position
	 */
	private final Vector2 tweenMoveTarget;
	/**
	 * Connection type between tileHeadingFrom and tileHeadingTo
	 */
	private TilesConnection_e currentConn;

	/**
	 * Tile direction for guided object: changed during recalculations.
	 */
	private TileDirection tileDirection;

	protected TilePathGuideHelper() {
		tileHeadingFrom = null;
		tileHeadingTo = null;
		tweenType = 0;
		// tweenTarget = 0.0f;
		tweenMoveTarget = new Vector2();
		currentConn = TilesConnection_e.NONE;
		this.tileDirection = TileDirection.NULL;
	}

	/**
	 * recalculate needed private fields
	 * 
	 * @throws Exception
	 */
	protected void recalculate(final GuideStage_e guideStage) throws Exception {
		switch (guideStage) {
		case HEADING_BORDER: {
			recalculateHeadingBorder();
			break;
		}
		case HEADING_CENTER: {
			recalculateHeadingCenter();
			break;
		}
		default: {
			throw new Exception("TilePathGuideHelper: unsupported guideStage");
		}
		}
	}

	/**
	 * Recalculate class fields for TilePathGuide's HEADING_BORDER stage.
	 * Invoked before recalculateHeadingCenter() for one pair of TileA and
	 * TileB.
	 * 
	 * @throws Exception
	 */
	private void recalculateHeadingBorder() throws Exception {
		currentConn = TilesHelper.getConnectionType(tileHeadingFrom,
				tileHeadingTo);

		final Vector2TileDirectionStruct edgePosition = TilesHelper
				.getPosBetween(tileHeadingFrom, tileHeadingTo);
		tileDirection = edgePosition.tileDirection;

		tweenType = WorldObjectAccessor.TILESPOS_XY;
		// based on connection type between tiles, set Tween variables
		if (currentConn == TilesConnection_e.HORIZONTAL) {
			tweenMoveTarget.x = edgePosition.vector2.x;
			tweenMoveTarget.y = edgePosition.vector2.y + 0.5f;
		} else if (currentConn == TilesConnection_e.VERTICAL) {
			tweenMoveTarget.x = edgePosition.vector2.x + 0.5f;
			tweenMoveTarget.y = edgePosition.vector2.y;
		} else if (currentConn == TilesConnection_e.PORTAL) {
			// temporary: first stage of moving, is to fly up
			tweenMoveTarget.x = tileHeadingFrom.getTilesPosX() - 2.0f;
			tweenMoveTarget.y = tileHeadingFrom.getTilesPosY() - 2.0f;
		} else {
			throw new Exception("unsupported connType "
					+ TilesHelper.toString(currentConn));
		}

	}

	/**
	 * Recalculate class fields for TilePathGuide's HEADING_CENTER stage.
	 * Invoked after recalculateHeadingBorder() for one pair of TileA and TileB.
	 * 
	 * @throws Exception
	 */
	private void recalculateHeadingCenter() throws Exception {
		switch (currentConn) {
		case HORIZONTAL: {
			tweenMoveTarget.x = tileHeadingTo.getTilesPosX() + 0.5f;
			tweenMoveTarget.y = tileHeadingTo.getTilesPosY() + 0.5f;
			break;
		}
		case VERTICAL: {
			tweenMoveTarget.x = tileHeadingTo.getTilesPosX() + 0.5f;
			tweenMoveTarget.y = tileHeadingTo.getTilesPosY() + 0.5f;
			break;
		}
		case PORTAL: {
			tweenMoveTarget.y = tileHeadingTo.getTilesPosY() + 0.5f;
			tweenMoveTarget.x = tileHeadingTo.getTilesPosX() + 0.5f;
			break;
		}
		case NONE: {
			throw new Exception("recalculateHeadingCenter(): connType == NONE");
		}
		default: {
			throw new Exception(
					"recalculateHeadingCenter(): unsupported connType");
		}
		}
	}

	protected void setTileHeadingFrom(final Tile tileHeadingFrom) {
		this.tileHeadingFrom = tileHeadingFrom;
	}

	protected void setTileHeadingTo(final Tile tileHeadingTo) {
		this.tileHeadingTo = tileHeadingTo;
	}

	/**
	 * Assign tileHeadingTo to tileHeadingFrom
	 */
	protected void reassignTileFrom() {
		tileHeadingFrom = tileHeadingTo;
	}

	protected Vector2 getTweenMoveTarget() {
		return tweenMoveTarget;
	}

	protected int getTweenType() {
		return tweenType;
	}

	protected Tile getTileHeadingFrom() {
		return tileHeadingFrom;
	}

	protected Tile getTileHeadingTo() {
		return tileHeadingTo;
	}

	/**
	 * @return current tile direction changed during recalculations
	 */
	public TileDirection getTileDirection() {
		return tileDirection;
	}

}
