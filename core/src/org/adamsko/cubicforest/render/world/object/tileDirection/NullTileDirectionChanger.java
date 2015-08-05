package org.adamsko.cubicforest.render.world.object.tileDirection;

import org.adamsko.cubicforest.helpTools.CLog;
import org.adamsko.cubicforest.world.tile.TileDirection;

public class NullTileDirectionChanger extends
TileDirectionChangerDefault {

	private static NullTileDirectionChanger instance = null;

	private NullTileDirectionChanger() {
		super(null, null, null);
		CLog.addObject(this, "NullTileDirectionChanger");
		CLog.setUsage(this, true);
	}

	public static NullTileDirectionChanger instance() {
		if (instance == null) {
			instance = new NullTileDirectionChanger();
		}
		return instance;
	}

	@Override
	public boolean isNull() {
		return true;
	}

	@Override
	public void changeDirection(final TileDirection tileDirection) {
		CLog.error(this, "changeDirection");
	}

	@Override
	public TileDirection getDirection() {
		CLog.error(this, "getDirection");
		return TileDirection.NULL;
	}

}
