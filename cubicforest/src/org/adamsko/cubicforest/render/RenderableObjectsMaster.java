package org.adamsko.cubicforest.render;

import java.util.List;

import org.adamsko.cubicforest.world.WorldObjectsMaster;

public interface RenderableObjectsMaster extends WorldObjectsMaster {

	public List<RenderableObject> getRendarbleObjects();
	
}