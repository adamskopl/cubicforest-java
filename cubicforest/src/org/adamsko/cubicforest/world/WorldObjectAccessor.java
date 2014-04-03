package org.adamsko.cubicforest.world;

import org.adamsko.cubicforest.world.object.WorldObject;

import aurelienribon.tweenengine.TweenAccessor;

public class WorldObjectAccessor implements TweenAccessor<WorldObject> {

	public static final int TILESPOS_X = 1;
	public static final int TILESPOS_Y = 2;
	public static final int TILESPOS_XY = 3;
	public static final int HEIGHT = 4;

	@Override
	public int getValues(WorldObject target, int tweenType, float[] returnValues) {
		switch(tweenType) {
		case(TILESPOS_X): {
			returnValues[0] = target.getTilesPosX(); 
			return 1;
		}
		case(TILESPOS_Y): {
			returnValues[0] = target.getTilesPosY(); 
			return 1;
		}
		case(TILESPOS_XY): {
			returnValues[0] = target.getTilesPosX();
			returnValues[1] = target.getTilesPosY();
			return 2;
		}
		case(HEIGHT): {
			returnValues[0] = target.getVerticalPos();
			return 1;
		}		
		default: assert false; return -1;
		}
	}
	
	@Override
	public void setValues(WorldObject target, int tweenType, float[] newValues) {
		switch(tweenType) {
		case(TILESPOS_X): {
			target.setTilesPosX(newValues[0]);
			break;
		}
		case(TILESPOS_Y): {
			target.setTilesPosY(newValues[0]);
			break;
		}
		case(TILESPOS_XY): {
			target.setTilesPosX(newValues[0]);
			target.setTilesPosY(newValues[1]);
			break;
		}
		case(HEIGHT): {
			target.setHeight(newValues[0]);
		}		
		default: assert false; break;
		}
	}
}
