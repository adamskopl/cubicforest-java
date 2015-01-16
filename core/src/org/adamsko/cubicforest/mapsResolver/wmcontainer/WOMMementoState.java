package org.adamsko.cubicforest.mapsResolver.wmcontainer;

import java.util.List;

import com.badlogic.gdx.math.Vector2;

public interface WOMMementoState {

	List<Vector2> getTilePositions();

	boolean isEqual(WOMMementoState womMementoState);

	String getName();

}
