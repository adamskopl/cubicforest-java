package org.adamsko.cubicforest.mapsResolver;

import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.NullHeroTool;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroTool;
import org.adamsko.cubicforest.world.tile.NullCubicTile;
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

	private final Tile chosenTile;
	private final Vector2 chosenTilePos;
	private HeroTool chosenHeroTool;

	public OrderDecisionDefault(final boolean nullConstructor) {
		chosenTilePos = null;
		chosenTile = NullCubicTile.instance();
	}

	public OrderDecisionDefault(final Tile chosenTile) {
		this.chosenTile = chosenTile;
		this.chosenTilePos = new Vector2(chosenTile.getTilesPosX(),
				chosenTile.getTilesPosY());
		chosenHeroTool = NullHeroTool.instance();
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
