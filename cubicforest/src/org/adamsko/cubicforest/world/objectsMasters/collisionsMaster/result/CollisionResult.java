package org.adamsko.cubicforest.world.objectsMasters.collisionsMaster.result;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.roundsMaster.GameResult;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.objectsMasters.ObjectOperation;
import org.adamsko.cubicforest.world.objectsMasters.entities.enemies.Enemy;
import org.adamsko.cubicforest.world.objectsMasters.entities.heroes.Hero;
import org.adamsko.cubicforest.world.objectsMasters.items.ItemObject;
import org.adamsko.cubicforest.world.objectsMasters.items.gatherCubes.GatherCube;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroTool;
import org.adamsko.cubicforest.world.ordersMaster.OrderOperation;
import org.adamsko.cubicforest.world.tilesMaster.Tile;

/**
 * Defines the result of one interatcion
 * 
 * @author adamsko
 * 
 */
public class CollisionResult {

	/**
	 * How the order of active object (guided by TilePathGuide right now) should
	 * be modified?
	 */
	private OrderOperation orderOperation;
	/**
	 * How the active object should be modified? Active object: object
	 * interacting witch checked object. E.g enemy - heroTool
	 */
	private ObjectOperation orderObjectOperation;

	/**
	 * How the tiled object should me modified? E.g how heroTool should me
	 * modified, when enemy is interacting with it?
	 */
	private ObjectOperation tileObjectOperation;

	/**
	 * How the tile's item should me modified? E.g how gather cube should be
	 * modified, when enemy is interacting with it?
	 */
	private ObjectOperation tileItemOperation;

	/**
	 * Modify if game is lost or won.
	 */
	private GameResult gameResult;

	/**
	 * Tile for which collision result is resolved. Needed among others to
	 * indicate tileObjectOperation value.
	 */
	private final Tile eventTile;
	/**
	 * Object interacting with a tile.
	 */
	private final WorldObject orderObject;

	private final List<Hero> toRemoveHeroes;
	private final List<Enemy> toRemoveEnemies;
	private final List<HeroTool> toRemoveHeroTools;
	private final List<GatherCube> toRemoveGatherCubes;

	public ObjectOperation getTileObjectOperation() {
		return tileObjectOperation;
	}

	public List<Hero> getRemovalsHeroes() {
		return toRemoveHeroes;
	}

	public List<Enemy> getRemovalsEnemies() {
		return toRemoveEnemies;
	}

	public List<HeroTool> getRemovalsHeroTools() {
		return toRemoveHeroTools;
	}

	public List<GatherCube> getRemovalsGatherCubes() {
		return toRemoveGatherCubes;
	}

	public CollisionResult(final Tile eventTile, final WorldObject eventObject) {

		this.eventTile = eventTile;
		this.orderObject = eventObject;

		toRemoveHeroes = new ArrayList<Hero>();
		toRemoveEnemies = new ArrayList<Enemy>();
		toRemoveHeroTools = new ArrayList<HeroTool>();
		toRemoveGatherCubes = new ArrayList<GatherCube>();

		orderOperation = OrderOperation.ORDER_CONTINUE;
		orderObjectOperation = ObjectOperation.OBJECT_NOTHING;
		tileObjectOperation = ObjectOperation.OBJECT_NOTHING;
		tileItemOperation = ObjectOperation.OBJECT_NOTHING;
		gameResult = GameResult.GAME_PLAY;
	}

	public OrderOperation getOrderOperation() {
		return orderOperation;
	}

	public void setOrderOperation(final OrderOperation orderOperation) {
		this.orderOperation = orderOperation;
	}

	public ObjectOperation getOrderObjectOperation() {
		return orderObjectOperation;
	}

	public GameResult getGameResult() {
		return gameResult;
	}

	public void setGameResult(final GameResult gameResult) {
		this.gameResult = gameResult;
	}

	/**
	 * Check if CollisionResult has values different than default.
	 * 
	 * @return
	 */
	public boolean defaultValues() {
		if (orderOperation != OrderOperation.ORDER_CONTINUE) {
			return false;
		}
		if (orderObjectOperation != ObjectOperation.OBJECT_NOTHING) {
			return false;
		}
		if (tileObjectOperation != ObjectOperation.OBJECT_NOTHING) {
			return false;
		}
		if (tileItemOperation != ObjectOperation.OBJECT_NOTHING) {
			return false;
		}
		return true;
	}

	public void remove(final Enemy enemy) {
		toRemoveEnemies.add(enemy);
		resolveRemoval(enemy);
	}

	public void remove(final Hero hero) {
		toRemoveHeroes.add(hero);
		resolveRemoval(hero);
	}

	public void remove(final HeroTool heroTool) {
		toRemoveHeroTools.add(heroTool);
		resolveRemoval(heroTool);
	}

	public void remove(final GatherCube gatherCube) {
		toRemoveGatherCubes.add(gatherCube);
		resolveRemoval(gatherCube);
	}

	/**
	 * Check if removed object has an influence on tileObjectOperation,
	 * orderObjectOperation or tileItemOperation.
	 * 
	 * @param object
	 *            object just removed
	 */
	private void resolveRemoval(final WorldObject objectRemoved) {

		switch (objectRemoved.getType()) {
		case OBJECT_ENTITY:
			if (objectRemoved == orderObject) {
				orderObjectOperation = ObjectOperation.OBJECT_REMOVE;
			}
			final WorldObject occupant = eventTile.getOccupant();
			if (occupant != null) {
				if (objectRemoved == occupant) {
					tileObjectOperation = ObjectOperation.OBJECT_REMOVE;
				}
			}
			break;
		case OBJECT_ITEM:
			final ItemObject item = (ItemObject) eventTile.getItem();
			if (item != null) {
				if (objectRemoved == item) {
					tileItemOperation = ObjectOperation.OBJECT_REMOVE;
				}
			}
			break;
		default:
		}
	}
}
