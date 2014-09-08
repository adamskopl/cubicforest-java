package org.adamsko.cubicforest.world.object;

import org.adamsko.cubicforest.world.objectsMasters.entities.enemies.Enemy;
import org.adamsko.cubicforest.world.objectsMasters.entities.heroes.Hero;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCube;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroTool;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroToolOrange;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroToolPortal;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroToolTrap;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroToolTurret;

/**
 * 'Visitor' design pattern. 'With Default' variation. <br>
 * Interface for visitor classes visiting WorldObject classes. Every visitor
 * classes family will be derived from default abstract visitor with all
 * implementations, so not every visitor function will have to be implemented in
 * concrete classes.
 * 
 * @author adamsko
 * 
 */
public interface WorldObjectVisitor {

	/**
	 * Visitor should has access to visiting object, to perform operations on
	 * both: visited object and visitor.
	 */
	void setVisitingObject(WorldObject visitingObject);

	/**
	 * Get visiting object.
	 */
	WorldObject getVisitingObject();

	/**
	 * Default method for {@link WorldObject} derived classes, that don't have
	 * their own accept() method
	 */
	void visitWorldObject(WorldObject worldObject);

	void visitHero(Hero hero);

	void visitEnemy(Enemy enemy);

	void visitGatherCube(GatherCube gatherCube);

	// HERO TOOLS
	void visitHeroTool(HeroTool heroTool);

	void visitToolTrap(HeroToolTrap heroToolTrap);

	void visitToolOrange(HeroToolOrange heroToolOrange);

	void visitToolTurret(HeroToolTurret heroToolTurret);

	void visitToolPortal(HeroToolPortal heroToolPortal);

}