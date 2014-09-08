package org.adamsko.cubicforest.world.object.accessor;

/**
 * Interface for objects used by {@link WorldObjectAccessor}
 * 
 * @author adamsko
 * 
 */
public interface WorldObjectAccessorClient {

	void setTilesPosX(final float x);

	void setTilesPosY(final float y);

	float getTilesPosX();

	float getTilesPosY();

	void setVerticalPos(final Float height);

	float getVerticalPos();

}
