package org.adamsko.cubicforest.mapsResolver.orderDecisions;

import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.NullHeroTool;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroTool;
import org.adamsko.cubicforest.world.tile.Tile;

import com.badlogic.gdx.math.Vector2;

/**
 * Single order decision performed by on object. Order consists of chosen tile
 * and chosen hero tool.
 * 
 * @author adamsko
 * 
 */
public class OrderDecisionDefault implements OrderDecision {

	private final Vector2 chosenTilePos;
	private HeroTool chosenHeroTool;

	public OrderDecisionDefault(final boolean nullConstructor) {
		chosenTilePos = null;
	}

	/**
	 * @param chosenTile
	 *            chosen order tile
	 * @param chosenHeroTool
	 *            chosen order hero tool. {@link NullHeroTool} if no tool
	 *            choice.
	 */
	public OrderDecisionDefault(final Tile chosenTile,
			final HeroTool chosenHeroTool) {
		this.chosenTilePos = new Vector2(chosenTile.getTilesPosX(),
				chosenTile.getTilesPosY());
		this.chosenHeroTool = chosenHeroTool;
	}

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public void setChosenHeroTool(final HeroTool heroTool) {
		this.chosenHeroTool = heroTool;
	}

	@Override
	public HeroTool getChosenHeroTool() {
		return chosenHeroTool;
	}

	@Override
	public Vector2 getChosenTilePos() {
		return chosenTilePos;
	}

}
