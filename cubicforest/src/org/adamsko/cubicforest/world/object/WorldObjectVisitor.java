package org.adamsko.cubicforest.world.object;

import org.adamsko.cubicforest.world.objectsMasters.entities.enemies.Enemy;
import org.adamsko.cubicforest.world.objectsMasters.entities.heroes.Hero;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCube;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroTool;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroToolOrange;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroToolPortal;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroToolTrap;

/**
 * 'Visitor' design pattern. 'With Default' variation assumption. <br>
 * Interface for visitor classes visiting WorldObject classes. Every visitor
 * classes family will be derived from default abstract visitor with all
 * implementations, so not every visitor function will have to be implemented in
 * concrete classes.
 * 
 * @author adamsko
 * 
 */
public interface WorldObjectVisitor {

	void setVisitingObject(WorldObject visitingObject);

	WorldObject getVisitingObject();

	// ///////////////////////////////////////////////////////////////////

	void visitWorldObject(WorldObject worldObject);

	void visitHero(Hero hero);

	void visitEnemy(Enemy enemy);

	void visitGatherCube(GatherCube gatherCube);

	// HERO TOOLS
	void visitHeroTool(HeroTool heroTool);

	void visitToolTrap(HeroToolTrap heroToolTrap);

	void visitToolOrange(HeroToolOrange heroToolOrange);

	void visitToolPortal(HeroToolPortal heroToolPortal);

}
