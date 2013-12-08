package org.adamsko.cubicforest.world;

import java.util.List;

public interface WorldObjectsMaster {
	public void update(float deltaTime);
	public List<WorldObject> getWorldObjects();
	
}
