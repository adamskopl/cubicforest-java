package org.adamsko.cubicforest.render.world.object.visualState;

/**
 * Visual state for game objects. Influences object's visual appearance.
 */
public enum RenderableObjectVisualState {
	// standard look
	NORMAL,
	// when object is selected
	SELECTED,
	// second variant of selection
	SELECTED2,
	// when object is selected in a wrong way
	SELECTED_FAIL,
	// for now only for occupied tiles...
	BLOCKED,
	// e.g. tile where movement is allowed
	ALLOWED, ALLOWED2
}
