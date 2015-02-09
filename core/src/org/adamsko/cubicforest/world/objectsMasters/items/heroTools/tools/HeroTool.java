package org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroToolStates;

public interface HeroTool extends WorldObject {

	public HeroToolStates getToolState();

	public int getBuildCost();

	public void changeState(final HeroToolStates newState);

	public void setState(final HeroToolStates state);

}
