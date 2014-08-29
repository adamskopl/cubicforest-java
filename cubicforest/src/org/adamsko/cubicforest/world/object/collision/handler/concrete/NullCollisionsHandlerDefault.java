package org.adamsko.cubicforest.world.object.collision.handler.concrete;


public class NullCollisionsHandlerDefault extends CollisionsHandlerDefault {
	private static NullCollisionsHandlerDefault instance = null;

	private NullCollisionsHandlerDefault() {
		super();
	}

	public static NullCollisionsHandlerDefault instance() {
		if (instance == null) {
			instance = new NullCollisionsHandlerDefault();
		}
		return instance;
	}

	@Override
	public boolean isNull() {
		return true;
	}
}
