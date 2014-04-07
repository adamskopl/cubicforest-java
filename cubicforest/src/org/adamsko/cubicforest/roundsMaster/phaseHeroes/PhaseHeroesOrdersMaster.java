package org.adamsko.cubicforest.roundsMaster.phaseHeroes;

import java.text.BreakIterator;

import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroTool;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroToolType_e;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroesToolsMaster;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

class PhaseHeroesOrdersMaster {

	private HeroesToolsMaster heroesToolsMaster;
	private TilesMaster tilesMaster;

	private Tile tilePickedOrder;
	private PhaseHeroesMode_e phaseHeroesMode;

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
		// default choice: movement order
		this.phaseHeroesMode = PhaseHeroesMode_e.MODE_CHOICE_MOVEMENT;
	}

	/**
	 * Same as changePhaseHeroesMode(PhaseHeroesMode_e newMode), but also set
	 * {@link HeroTool} marker type
	 * 
	 * @param newMode
	 * @param heroToolType
	 * @throws Exception
	 */
	public void changePhaseHeroesMode(PhaseHeroesMode_e newMode,
			HeroToolType_e heroToolType) throws Exception {
		heroesToolsMaster.setHeroToolMarkerType(heroToolType);
		changePhaseHeroesMode(newMode);
	}

	/**
	 * @param newMode
	 * @throws Exception
	 */
	public void changePhaseHeroesMode(PhaseHeroesMode_e newMode)
			throws Exception {

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
		
//		switch (phaseHeroesMode) {
//		case MODE_CHOICE_MOVEMENT:
//			switch (newMode) {
//			// MOVEMENT -> TOOL CHOICE
//			case MODE_CHOICE_TOOL:
//				
//				break;
//			default:
//				break;
//			}
//			break;
//		case MODE_CHOICE_TOOL:
//			switch (newMode) {
//			// TOOL CHOICE -> MOVEMENT
//			case MODE_CHOICE_MOVEMENT:
//				
//				break;
//			// TOOL CHOICE -> TOOL CHOICE
//			case MODE_CHOICE_TOOL:

//				break;
//			// TOOL CHOICE -> ORDER EXECUTION
//			case MODE_ORDER_EXECUTION:
//				break;
//			default:
//				break;
//			}
//			break;
//		default:
//			break;
//		}

		this.phaseHeroesMode = newMode;
	}

	/////////////////////////////////////////////////////////////
	//
	// SHOULD BE IN A SEPERATE CLASS
	//
	/////////////////////////////////////////////////////////////
	private void newModeChoiceTool() throws Exception {
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
	
	private void newModeChoiceMovement() throws Exception {
		switch (phaseHeroesMode) {
		case MODE_CHOICE_TOOL:
			heroesToolsMaster.heroToolMarkerRemove();
			break;

		default:
			break;
		}
	}
	
	private void newModeOrderExecution() throws Exception {
		switch (phaseHeroesMode) {
		case MODE_CHOICE_MOVEMENT:
			heroesToolsMaster.heroToolMarkerRemove();
			break;

		case MODE_CHOICE_TOOL:
			heroesToolsMaster.heroToolMarkerAccept();
			break;
		case MODE_ORDER_EXECUTION:
			Gdx.app.error("newModeOrderExecution", "MODE_ORDER_EXECUTION->MODE_ORDER_EXECUTION");
			break;
		default:
			
			break;
		}
	}
	/////////////////////////////////////////////////////////////

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

	private void addHeroToolMarker(Tile tilePickedOrder, Boolean tileOrderValid) {
		try {
			heroesToolsMaster.heroToolMarkerRemove();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (tileOrderValid) {
			heroesToolsMaster.heroToolMarkerAdd(tilePickedOrder);
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
