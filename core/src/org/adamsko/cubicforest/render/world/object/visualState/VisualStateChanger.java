package org.adamsko.cubicforest.render.world.object.visualState;

/**
 * Changes visual state of an object: should implement all necessary operations,
 * when object's visual state is changed.
 */
public interface VisualStateChanger {

	void changeState(RenderableObjectVisualState objectVisualState);

	RenderableObjectVisualState getState();

}
