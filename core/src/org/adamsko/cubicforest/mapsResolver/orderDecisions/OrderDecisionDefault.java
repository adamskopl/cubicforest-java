package org.adamsko.cubicforest.mapsResolver.orderDecisions;

import org.adamsko.cubicforest.world.object.WorldObjectType;
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
	private WorldObjectType chosenHeroTool;

	public OrderDecisionDefault(final boolean nullConstructor) {
		chosenTilePos = null;
	}

	/**
	 * @param chosenTile
	 *            chosen order tile
	 * @param chosenHeroTool
	 *            chosen order hero tool. {@link WorldObjectType} NULL if no
	 *            tool choice.
	 */
	public OrderDecisionDefault(final Tile chosenTile,
			final WorldObjectType chosenHeroTool) {
		this.chosenTilePos = new Vector2(chosenTile.getTilesPosX(),
				chosenTile.getTilesPosY());
		this.chosenHeroTool = chosenHeroTool;
	}

	@Override
	public String toString() {
		String string = new String(chosenTilePos.toString());
		if (chosenHeroTool != WorldObjectType.NULL) {
			switch (chosenHeroTool) {
			case TOOLEXIT:
				string = string.concat("<Exit>");
				break;
			case TOOLTRAP:
				string = string.concat("<Trap>");
				break;
			default:
				string = string.concat("<UnknownTool>");
				break;
			}
		}
		return string;
	}

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public void setChosenHeroTool(final WorldObjectType chosenHeroTool) {
		this.chosenHeroTool = chosenHeroTool;
	}

	@Override
	public WorldObjectType getChosenHeroTool() {
		return this.chosenHeroTool;
	}

	@Override
	public boolean heroToolChosen() {
		return !(chosenHeroTool == WorldObjectType.NULL);
	}

	@Override
	public Vector2 getChosenTilePos() {
		return chosenTilePos;
	}

}
