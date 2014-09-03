package org.adamsko.cubicforest.world.object;

import org.adamsko.cubicforest.render.text.ROLabel;
import org.adamsko.cubicforest.render.world.RenderableObject;
import org.adamsko.cubicforest.world.object.accessor.WorldObjectAccessorClient;
import org.adamsko.cubicforest.world.object.collision.visitors.CollisionVisitorsManager;
import org.adamsko.cubicforest.world.object.collision.visitors.manager.CollisionVisitorsManagerFactory;
import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tile.TilesMaster;
import org.adamsko.cubicforest.world.tile.propertiesIndicator.TilePropertiesIndicator;

import com.badlogic.gdx.math.Vector2;

/**
 * FIXME: the interface is to big
 * 
 * @author adamsko
 * 
 */
public interface WorldObject extends RenderableObject,
		WorldObjectAccessorClient {

	WorldObjectType getType();

	/**
	 * Return container holding this WorldObject. <br>
	 * FIXME: design flaw (?) Every class having contact with WorldObject can
	 * remove it.
	 */
	WorldObjectsMasterDefault getParentContainer();

	/**
	 * Returns {@link TilePropertiesIndicator} to check how this object affects
	 * {@link Tile} objects.
	 */
	TilePropertiesIndicator getTilePropertiesIndicator();

	/**
	 * Initialize {@link TilePropertiesIndicator} for
	 * {@link #getTilePropertiesIndicator()}
	 */
	void initTilePropertiesIndicator();

	/**
	 * Initialize {@link CollisionVisitorsManager} object working during
	 * collisions resolving. Initialization should prepare proper
	 * {@link #collision()} method work.
	 * 
	 * @param collisionVisitorsManagerFactory
	 */
	void initCollisionVisitorsManager(
			final CollisionVisitorsManagerFactory collisionVisitorsManagerFactory);

	/**
	 * Return properly set {@link CollisionVisitorsManager} object, which will
	 * handle collision with this object.
	 */
	CollisionVisitorsManager collision();

	WorldObjectState getState();

	void setState(final WorldObjectState newState);

	/**
	 * 'Accept' method from 'Visitor' design pattern. Indicate which method from
	 * {@link WorldObjectVisitor} is proper for this object.
	 */
	void accept(final WorldObjectVisitor visitor);

	/**
	 * Set tiles position handled by {@link TilesMaster}.
	 */
	void setTilesPos(final Vector2 pos);

	/**
	 * Get tiles position handled by {@link TilesMaster}.
	 */
	Vector2 getTilesPos();

	void setName(final String name);

	String getName();

	/**
	 * Set the amount of how many tiles object can move in its turn.
	 */
	void setSpeed(final int speed);

	/**
	 * Get the amount of how many tiles object can move in its turn.
	 */
	int getSpeed();

	/**
	 * Add a label of given {@link ROLabel} type. Allows to easy track of
	 * object's values.
	 * 
	 * @param type
	 *            indicates which object's parameter should be displayed
	 */
	void addLabel(final ROLabel type) throws Exception;

}
