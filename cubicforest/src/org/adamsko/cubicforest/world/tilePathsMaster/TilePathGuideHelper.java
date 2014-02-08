package org.adamsko.cubicforest.world.tilePathsMaster;

import org.adamsko.cubicforest.world.WorldObjectAccessor;
import org.adamsko.cubicforest.world.tilePathsMaster.TilePathGuide.GuideStage_e;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesHelper;
import org.adamsko.cubicforest.world.tilesMaster.TilesHelper.TilesConnection_e;

import com.badlogic.gdx.math.Vector2;

/**
 * Helping class containing constantly changed variables needed by
 * {@link TilePathGuide}.
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
	 * target value for current Tween
	 */
	private float tweenTarget;
	/**
	 * Connection type between tileHeadingFrom and tileHeadingTo
	 */
	private TilesConnection_e currentConn;

	protected TilePathGuideHelper() {
		tileHeadingFrom = null;
		tileHeadingTo = null;
		tweenType = 0;
		tweenTarget = 0.0f;
		currentConn = TilesConnection_e.NONE;
	}

	/**
	 * recalculate needed private fields
	 * 
	 * @throws Exception
	 */
	protected void recalculate(GuideStage_e guideStage) throws Exception {
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
		currentConn = TilesHelper
				.getConnectionType(tileHeadingFrom, tileHeadingTo);

		Vector2 edgePosition = TilesHelper.getPosBetween(tileHeadingFrom,
				tileHeadingTo);

		// based on connection type between tiles, set Tween variables
		if (currentConn == TilesConnection_e.HORIZONTAL) {
			tweenType = WorldObjectAccessor.TILESPOS_X;
			tweenTarget = edgePosition.x;
		} else if (currentConn == TilesConnection_e.VERTICAL) {
			tweenType = WorldObjectAccessor.TILESPOS_Y;
			tweenTarget = edgePosition.y;
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
			tweenTarget = tileHeadingTo.getTilesPosX() + 0.5f;
			break;
		}
		case VERTICAL: {
			tweenTarget = tileHeadingTo.getTilesPosY() + 0.5f;
			break;
		}
		case NONE: {
			throw new Exception("recalculateHeadingCenter(): connType == NONE");
		}
		default: {
			throw new Exception("recalculateHeadingCenter(): unsupported connType");
		}
		}
	}

	protected void setTileHeadingFrom(Tile tileHeadingFrom) {
		this.tileHeadingFrom = tileHeadingFrom;
	}

	protected void setTileHeadingTo(Tile tileHeadingTo) {
		this.tileHeadingTo = tileHeadingTo;
	}
	
	/**
	 * Assign tileHeadingTo to tileHeadingFrom
	 */
	protected void reassignTileFrom() {
		tileHeadingFrom = tileHeadingTo;
	}

	protected float getTweenTarget() {
		return tweenTarget;
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
	
}
