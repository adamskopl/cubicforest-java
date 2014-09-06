package org.adamsko.cubicforest.roundsMaster.phaseHeroes;

import org.adamsko.cubicforest.world.object.NullCubicObject;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroesToolsMaster;
import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tile.lookController.TilesLookController;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class PhaseHeroesOrdersMasterDefault implements PhaseHeroesOrdersMaster {

	private final HeroesToolsMaster heroesToolsMaster;
	private final TilesLookController tilesLookController;
	private final EnemiesHelper enemiesHelper;

	private Tile tilePickedOrder;
	private PhaseHeroesMode phaseHeroesMode;
	private WorldObject currentHero;

	/**
	 * vectors containing textures coordinations (to use by
	 * {@link TilesLookController}) used to not to create {@link Vector2}
	 * objects every time when texture set is needed
	 */
	private Vector2 textureTileMovementValid, textureTileMovementInvalidChoice,
			textureTileMovementValidChoice, textureCommonRanges;

	PhaseHeroesOrdersMasterDefault(
			final TilesLookController tilesLookController,
			final HeroesToolsMaster heroesToolsMaster,
			final EnemiesHelper enemiesHelper) {
		tilePickedOrder = null;
		this.tilesLookController = tilesLookController;
		this.heroesToolsMaster = heroesToolsMaster;
		this.enemiesHelper = enemiesHelper;
		// default choice: movement order
		this.phaseHeroesMode = PhaseHeroesMode.MODE_CHOICE_MOVEMENT;
		this.currentHero = NullCubicObject.instance();

		initTextures();

	}

	@Override
	public void changePhaseHeroesMode(final PhaseHeroesMode newMode,
			final WorldObjectType heroToolType) {
		heroesToolsMaster.setHeroToolMarkerType(heroToolType);
		changePhaseHeroesMode(newMode);
	}

	@Override
	public void changePhaseHeroesMode(final PhaseHeroesMode newMode) {

		tilesLookController.resetTilesTextures();

		switch (newMode) {
		case MODE_CHOICE_MOVEMENT:
			highlightHeroEnemiesRanges();
			newModeChoiceMovement();
			break;
		case MODE_ORDER_EXECUTION:
			newModeOrderExecution();
			break;
		case MODE_CHOICE_TOOL:
			highlightHeroEnemiesRanges();
			newModeChoiceTool();
			break;

		default:
			Gdx.app.error("changePhaseHeroesMode", "unknown new mode");
			break;
		}

		this.phaseHeroesMode = newMode;
	}

	@Override
	public void tilePicked(final Tile tilePickedOrder,
			final Boolean tileOrderValid) {

		this.tilePickedOrder = tilePickedOrder;

		tilesLookController.resetTilesTextures();
		highlightHeroEnemiesRanges();
		highlightPickedTile(tilePickedOrder, tileOrderValid);

		switch (phaseHeroesMode) {
		case MODE_CHOICE_MOVEMENT:
			break;

		case MODE_CHOICE_TOOL:
			addHeroToolMarker(tilePickedOrder, tileOrderValid);
			highlightPickedTile(tilePickedOrder, tileOrderValid);
			break;
		default:
			break;
		}
	}

	@Override
	public void setCurrentHero(final WorldObject currentHero) {
		this.currentHero = currentHero;
	}

	private void initTextures() {
		textureTileMovementValid = new Vector2(1, 1);
		textureTileMovementValidChoice = new Vector2(0, 2);
		textureTileMovementInvalidChoice = new Vector2(1, 0);
		textureCommonRanges = new Vector2(2, 0);
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

	private void addHeroToolMarker(final Tile tilePickedOrder,
			final Boolean tileOrderValid) {

		heroesToolsMaster.heroToolMarkerRemove();

		if (tileOrderValid) {
			heroesToolsMaster.heroToolMarkerAdd(tilePickedOrder);
		}
	}

	private void highlightPickedTile(final Tile tilePickedOrder,
			final Boolean tileOrderValid) {
		if (tileOrderValid) {
			tilesLookController.changeTileTexture(tilePickedOrder,
					textureTileMovementValidChoice);
		} else {
			tilesLookController.changeTileTexture(tilePickedOrder,
					textureTileMovementInvalidChoice);
		}
	}

	/**
	 * Change textures of tiles being in range of current hero and enemies.
	 * Common tiles should have other texture.
	 */
	private void highlightHeroEnemiesRanges() {
		Gdx.app.debug("HIGH", "");
		tilesLookController.highlightTilesObjectsRange(currentHero,
				textureTileMovementValid, enemiesHelper.getEnemies(),
				textureTileMovementValid, textureCommonRanges);
	}

}
