package org.adamsko.cubicforest.mapsResolver;

import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.NullHeroTool;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.tools.HeroTool;
import org.adamsko.cubicforest.world.tile.Tile;

/**
 * Single order decision performed by on object. Order consists of chosen tile
 * and chosen hero tool.
 * 
 * @author adamsko
 * 
 */
public class OrderDecision {

	private final Tile chosenTile;
	private HeroTool chosenHeroTool;

	public OrderDecision(final Tile chosenTile) {
		this.chosenTile = chosenTile;
		chosenHeroTool = NullHeroTool.instance();
	}

	public void setChosenHeroTool(final HeroTool heroTool) {
		this.chosenHeroTool = heroTool;
	}

	public HeroTool getChosenHeroTool() {
		return chosenHeroTool;
	}

	public Tile getChosenTile() {
		return chosenTile;
	}

}
