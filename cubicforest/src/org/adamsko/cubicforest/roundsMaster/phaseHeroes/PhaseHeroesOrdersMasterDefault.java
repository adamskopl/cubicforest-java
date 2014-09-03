package org.adamsko.cubicforest.roundsMaster.phaseHeroes;

import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroesToolsMaster;
import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tile.TilesMaster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

class PhaseHeroesOrdersMasterDefault implements PhaseHeroesOrdersMaster {

	private final HeroesToolsMaster heroesToolsMaster;
	private final TilesMaster tilesMaster;

	private Tile tilePickedOrder;
	private PhaseHeroesMode phaseHeroesMode;

	/**
	 * Coordinates of the texture in image atlas of the picked {@link Tile}
	 * object. Used to revert texture before a pick.
	 */
	private final Vector2 tilePickedTexCooords;

	PhaseHeroesOrdersMasterDefault(final TilesMaster tilesMaster,
			final HeroesToolsMaster heroesToolsMaster) {
		tilePickedOrder = null;
		tilePickedTexCooords = new Vector2();
		this.tilesMaster = tilesMaster;
		this.heroesToolsMaster = heroesToolsMaster;
		// default choice: movement order
		this.phaseHeroesMode = PhaseHeroesMode.MODE_CHOICE_MOVEMENT;
	}

	@Override
	public void changePhaseHeroesMode(final PhaseHeroesMode newMode,
			final WorldObjectType heroToolType) {
		heroesToolsMaster.setHeroToolMarkerType(heroToolType);
		changePhaseHeroesMode(newMode);
	}

	@Override
	public void changePhaseHeroesMode(final PhaseHeroesMode newMode) {

		switch (newMode) {
		case MODE_CHOICE_MOVEMENT:
			newModeChoiceMovement();
			break;
		case MODE_ORDER_EXECUTION:
			newModeOrderExecution();
			break;
		case MODE_CHOICE_TOOL:
			newModeChoiceTool();
			break;

		default:
			Gdx.app.error("changePhaseHeroesMode", "unknown new mode");
			break;
		}

		this.phaseHeroesMode = newMode;
	}

	private void newModeChoiceTool() {
		switch (phaseHeroesMode) {
		case MODE_CHOICE_MOVEMENT:
			heroesToolsMaster.heroToolMarkerAdd(tilePickedOrder);
			break;
		case MODE_CHOICE_TOOL:
			heroesToolsMaster.heroToolMarkerRemove();
			heroesToolsMaster.heroToolMarkerAdd(tilePickedOrder);
			break;
		default:
			break;
		}
	}

	private void newModeChoiceMovement() {
		switch (phaseHeroesMode) {
		case MODE_CHOICE_TOOL:
			heroesToolsMaster.heroToolMarkerRemove();
			break;

		default:
			break;
		}
	}

	private void newModeOrderExecution() {
		switch (phaseHeroesMode) {
		case MODE_CHOICE_MOVEMENT:
			heroesToolsMaster.heroToolMarkerRemove();
			break;

		case MODE_CHOICE_TOOL:
			heroesToolsMaster.heroToolMarkerAccept();
			break;
		case MODE_ORDER_EXECUTION:
			Gdx.app.error("newModeOrderExecution",
					"MODE_ORDER_EXECUTION->MODE_ORDER_EXECUTION");
			break;
		default:

			break;
		}
	}

	@Override
	public void tilePicked(final Tile tilePickedOrder,
			final Boolean tileOrderValid) {
		if (this.tilePickedOrder != null) {
			unhighlightPickedTile(tileOrderValid);
		}

		this.tilePickedOrder = tilePickedOrder;
		saveTilePickedTexCoords(tileOrderValid);

		highlightPickedTile(tilePickedOrder, tileOrderValid);

		switch (phaseHeroesMode) {
		case MODE_CHOICE_MOVEMENT:
			break;

		case MODE_CHOICE_TOOL:
			addHeroToolMarker(tilePickedOrder, tileOrderValid);
			break;
		default:
			break;
		}
	}

	private void addHeroToolMarker(final Tile tilePickedOrder,
			final Boolean tileOrderValid) {

		heroesToolsMaster.heroToolMarkerRemove();

		if (tileOrderValid) {
			heroesToolsMaster.heroToolMarkerAdd(tilePickedOrder);
		}
	}

	private void saveTilePickedTexCoords(final Boolean tileOrderValid) {
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

	private void unhighlightPickedTile(final Boolean tileOrderValid) {
		tilesMaster.highlightTile(tilePickedOrder,
				(int) tilePickedTexCooords.x, (int) tilePickedTexCooords.y);

	}

	private void highlightPickedTile(final Tile tilePickedOrder,
			final Boolean tileOrderValid) {
		if (tileOrderValid) {
			tilesMaster.highlightTile(tilePickedOrder, 0, 2);
		} else {
			tilesMaster.highlightTile(tilePickedOrder, 1, 0);
		}
	}

}
