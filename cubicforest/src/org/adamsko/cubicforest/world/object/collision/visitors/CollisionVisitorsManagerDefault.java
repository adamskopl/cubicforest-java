package org.adamsko.cubicforest.world.object.collision.visitors;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectVisitor;

/**
 * @author adamsko
 * 
 */
public class CollisionVisitorsManagerDefault implements
		CollisionVisitorsManager {

	private WorldObjectVisitor collisionVisitorEnter;
	private WorldObjectVisitor collisionVisitorPass;
	private WorldObjectVisitor collisionVisitorLeave;
	private WorldObjectVisitor collisionVisitorStop;

	public CollisionVisitorsManagerDefault() {
		// set null visitors as default
		collisionVisitorEnter = NullCollisionVisitor.instance();
		collisionVisitorPass = NullCollisionVisitor.instance();
		collisionVisitorLeave = NullCollisionVisitor.instance();
		collisionVisitorStop = NullCollisionVisitor.instance();
	}

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public void setVisitingObject(final WorldObject visitingObject) {
		collisionVisitorEnter.setVisitingObject(visitingObject);
		collisionVisitorPass.setVisitingObject(visitingObject);
		collisionVisitorLeave.setVisitingObject(visitingObject);
		collisionVisitorStop.setVisitingObject(visitingObject);
	}

	public void setVisitorEnter(final WorldObjectVisitor visitor) {
		this.collisionVisitorEnter = visitor;
	}

	public void setVisitorPass(final WorldObjectVisitor visitor) {
		this.collisionVisitorPass = visitor;
	}

	public void setVisitorLeave(final WorldObjectVisitor visitor) {
		this.collisionVisitorLeave = visitor;
	}

	public void setVisitorStop(final WorldObjectVisitor visitor) {
		this.collisionVisitorStop = visitor;
	}

	@Override
	public WorldObjectVisitor visitEnter() {
		return collisionVisitorEnter;
	}

	@Override
	public WorldObjectVisitor visitPass() {
		return collisionVisitorPass;
	}

	@Override
	public WorldObjectVisitor visitLeave() {
		return collisionVisitorLeave;
	}

	@Override
	public WorldObjectVisitor visitStop() {
		return collisionVisitorStop;
	}

}
