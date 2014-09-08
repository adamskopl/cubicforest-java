package org.adamsko.cubicforest.world.object.collision.visitors;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.world.object.NullCubicObject;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectVisitor;
import org.adamsko.cubicforest.world.object.collision.handler.CollisionsHandler;
import org.adamsko.cubicforest.world.objectsMasters.entities.enemies.Enemy;
import org.adamsko.cubicforest.world.objectsMasters.entities.heroes.Hero;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCube;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroTool;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroToolOrange;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroToolPortal;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroToolTrap;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroToolTurret;

import com.badlogic.gdx.Gdx;

/**
 * Default implementation of {@link WorldObjectVisitor} responsible for
 * collisions effects resolving.
 * 
 * @author adamsko
 */
public abstract class CollisionVisitorDefault implements WorldObjectVisitor,
		Nullable {

	private CollisionsHandler collisionsHandler;

	/**
	 * Object which is currently related with a specific visitor. Needed for
	 * e.g. removing this object in its visitor's methods.
	 */
	private WorldObject visitingObject;

	/**
	 * For NullCollisionVisitorDefault
	 */
	CollisionVisitorDefault() {
	}

	public CollisionVisitorDefault(final CollisionsHandler collisionsHandler) {
		if (collisionsHandler.isNull()) {
			Gdx.app.error("CollisionVisitorDefault::CollisionVisitorDefault",
					"collisionsHandler.isNull()");
		}
		this.collisionsHandler = collisionsHandler;
		this.visitingObject = NullCubicObject.instance();
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

	// HERO TOOLS
	@Override
	public void visitHeroTool(final HeroTool heroTool) {

	}

	@Override
	public void visitToolTrap(final HeroToolTrap heroToolTrap) {
		visitHeroTool(heroToolTrap);
	}

	@Override
	public void visitToolOrange(final HeroToolOrange heroToolOrange) {
		visitHeroTool(heroToolOrange);
	}

	@Override
	public void visitToolPortal(final HeroToolPortal heroToolPortal) {
		visitHeroTool(heroToolPortal);
	}

	@Override
	public void visitToolTurret(final HeroToolTurret heroToolTurret) {
		visitHeroTool(heroToolTurret);
	}

}
