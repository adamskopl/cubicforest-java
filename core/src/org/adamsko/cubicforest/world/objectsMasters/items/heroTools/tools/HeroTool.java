package org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools;

import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroToolStates_e;

public interface HeroTool extends WorldObject {

	public HeroToolStates_e getToolState();

	public int getBuildCost();

	public void changeState(final HeroToolStates_e newState);

	public void setState(final HeroToolStates_e state);

}
