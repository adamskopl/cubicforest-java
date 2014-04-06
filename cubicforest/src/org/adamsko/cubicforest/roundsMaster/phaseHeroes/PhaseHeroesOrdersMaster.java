package org.adamsko.cubicforest.roundsMaster.phaseHeroes;

import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroToolType_e;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroesToolsMaster;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;

import com.badlogic.gdx.math.Vector2;

class PhaseHeroesOrdersMaster {

	private HeroesToolsMaster heroesToolsMaster;
	private TilesMaster tilesMaster;

	private Tile tilePickedOrder;
	/**
	 * Coordinates of the texture in image atlas of the picked {@link Tile}
	 * object. Used to revert texture before a pick.
	 */
	private Vector2 tilePickedTexCooords;

	PhaseHeroesOrdersMaster(TilesMaster tilesMaster,
			HeroesToolsMaster heroesToolsMaster) {
		tilePickedOrder = null;
		tilePickedTexCooords = new Vector2();
		this.tilesMaster = tilesMaster;
		this.heroesToolsMaster = heroesToolsMaster;
	}

	/**
	 * Handle tile picked in {@link PhaseHeroes} phase.
	 * 
	 * @param tile
	 *            tile picked for an order issue
	 * 
	 * @param tileOrderValid
	 *            picked tile is valid for an order issue
	 */
	void tilePicked(Tile tilePickedOrder, Boolean tileOrderValid) {
		if (this.tilePickedOrder != null) {
			unhighlightPickedTile(tileOrderValid);
		}

		this.tilePickedOrder = tilePickedOrder;
		saveTilePickedTexCoords(tileOrderValid);

		highlightPickedTile(tilePickedOrder, tileOrderValid);

		addHeroToolMarker(tilePickedOrder, tileOrderValid);
	}

	private void addHeroToolMarker(Tile tilePickedOrder, Boolean tileOrderValid) {
		try {
			heroesToolsMaster.removeHeroToolMarker();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (tileOrderValid) {
			heroesToolsMaster.addHeroToolMarker(tilePickedOrder,
					HeroToolType_e.TOOL_BLUE);
		}
	}

	private void saveTilePickedTexCoords(Boolean tileOrderValid) {
		if (tileOrderValid) {
			tilePickedTexCooords.set(1, 1);
		} else {
			if (tilePickedOrder.hasOccupant()) {
				tilePickedTexCooords.set(0, 1);
			} else {
				tilePickedTexCooords.set(0, 0);
			}
		}
	}

	private void unhighlightPickedTile(Boolean tileOrderValid) {
		tilesMaster.highlightTile(tilePickedOrder,
				(int) tilePickedTexCooords.x, (int) tilePickedTexCooords.y);

	}

	private void highlightPickedTile(Tile tilePickedOrder,
			Boolean tileOrderValid) {
		if (tileOrderValid) {
			tilesMaster.highlightTile(tilePickedOrder, 0, 2);
		} else {
			tilesMaster.highlightTile(tilePickedOrder, 1, 0);
		}
	}

}
