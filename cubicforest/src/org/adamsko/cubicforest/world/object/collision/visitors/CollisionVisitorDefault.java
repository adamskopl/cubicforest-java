package org.adamsko.cubicforest.world.object.collision.visitors;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.world.object.NullWorldObject;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectVisitor;
import org.adamsko.cubicforest.world.object.collision.handler.CollisionsHandler;
import org.adamsko.cubicforest.world.objectsMasters.entities.enemies.Enemy;
import org.adamsko.cubicforest.world.objectsMasters.entities.heroes.Hero;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCube;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroToolOrange;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroToolTrap;

import com.badlogic.gdx.Gdx;

public abstract class CollisionVisitorDefault implements WorldObjectVisitor,
		Nullable {

	private final CollisionsHandler collisionsHandler;

	/**
	 * Object which is actually related with a specific visitor. Needed for e.g.
	 * removing this object in its visitor's methods.
	 */
	private WorldObject visitingObject;

	public CollisionVisitorDefault(final CollisionsHandler collisionsHandler) {
		this.collisionsHandler = collisionsHandler;
		this.visitingObject = NullWorldObject.instance();
	}

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public void setVisitingObject(final WorldObject visitingObject) {
		this.visitingObject = visitingObject;
	}

	@Override
	public WorldObject getVisitingObject() {
		return visitingObject;
	}

	protected CollisionsHandler collision() {
		return collisionsHandler;
	}

	@Override
	public void visitWorldObject(final WorldObject worldObject) {
		Gdx.app.debug("VISIT", worldObject.getName());

	}

	@Override
	public void visitHero(final Hero hero) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitEnemy(final Enemy enemy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitGatherCube(final GatherCube gatherCube) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitToolTrap(final HeroToolTrap heroToolTrap) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitToolOrange(final HeroToolOrange heroToolOrange) {
		// TODO Auto-generated method stub

	}

}
