package org.adamsko.cubicforest.roundsMaster.phaseHeroes;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.helpTools.ConditionalLog;
import org.adamsko.cubicforest.mapsResolver.orderDecisions.OrderDecisionDefault;
import org.adamsko.cubicforest.render.world.object.visualState.RenderableObjectVisualState;
import org.adamsko.cubicforest.world.object.NullCubicObject;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.objectsMasters.items.heroTools.HeroesToolsMaster;
import org.adamsko.cubicforest.world.tile.NullCubicTile;
import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tile.TilesMaster;
import org.adamsko.cubicforest.world.tile.lookController.TilesLookController;
import org.adamsko.cubicforest.world.tile.tilesSearcher.searchParameter.TilesSearchParameterFactory;

import com.badlogic.gdx.math.Vector2;

class PhaseHeroesOrdersMasterDefault implements PhaseHeroesOrdersMaster {

	private final HeroesToolsMaster heroesToolsMaster;
	private final TilesLookController tilesLookController;
	private final EnemiesHelper enemiesHelper;

	/**
	 * Tile picked for an order.
	 */
	private Tile tilePickedOrder;
	/**
	 * Is picked tile order valid.
	 */
	private boolean tileOrderValid;

	private WorldObject currentHero;

	private final TilesMaster tilesMaster;

	/**
	 * vectors containing textures coordinations (to use by
	 * {@link TilesLookController}) used to not to create {@link Vector2}
	 * objects every time when texture set is needed
	 */
	private Vector2 textureTileMovementValid, textureTileMovementInvalidChoice,
			textureTileMovementValidChoice, textureCommonRanges;

	PhaseHeroesOrdersMasterDefault(
			final TilesLookController tilesLookController,
			final TilesMaster tilesMaster,
			final HeroesToolsMaster heroesToolsMaster,
			final EnemiesHelper enemiesHelper) {
		tilePickedOrder = NullCubicTile.instance();
		tileOrderValid = false;
		this.tilesLookController = tilesLookController;
		this.tilesMaster = tilesMaster;
		this.heroesToolsMaster = heroesToolsMaster;
		this.enemiesHelper = enemiesHelper;
		// default choice: movement order
		this.currentHero = NullCubicObject.instance();

		initTextures();

		ConditionalLog.addObject(this, "PhaseHeroesOrdersMasterDefault");
		ConditionalLog.setUsage(this, true);

	}

	@Override
	public HeroesToolsMaster getHeroesToolsMaster() {
		return heroesToolsMaster;
	}

	@Override
	public void resetHighlight() {
		tilesLookController.resetTilesTextures();
	}

	@Override
	public void highlightTilesOrder() {
		resetHighlight();
		highlightHeroEnemiesRanges();
		highlightPickedTile();
	}

	@Override
	public void tilePicked(final Tile tilePickedOrder,
			final boolean tileOrderValid) {
		this.tilePickedOrder = tilePickedOrder;
		this.tileOrderValid = tileOrderValid;
	}

	@Override
	public void toolPicked(final WorldObjectType heroToolType) {
		if (tilePickedOrder.isNull() || tileOrderValid == false) {
			return;
		}
		if (heroesToolsMaster.tileContainsTool(tilePickedOrder)) {
			return;
		}
		heroesToolsMaster.setHeroToolMarkerType(heroToolType);
		heroesToolsMaster.removeHeroToolMarker();
		heroesToolsMaster.addHeroToolMarker(this.tilePickedOrder);

		highlightTilesOrder();
	}

	@Override
	public void setCurrentHero(final WorldObject currentHero) {
		this.currentHero = currentHero;
	}

	@Override
	public List<OrderDecisionDefault> getCurrentPossbileDecisions() {
		final TilesSearchParameterFactory tilesSearchParameterFactory = tilesMaster
				.getTilesSearchParameterFactory();

		final List<Tile> tilesOrderValid = tilesMaster.getTilesInRange(
				currentHero, currentHero.getSpeed(),
				tilesSearchParameterFactory.create(WorldObjectType.HERO));

		final List<OrderDecisionDefault> validOrderDecisions = new ArrayList<OrderDecisionDefault>();
		for (final Tile tile : tilesOrderValid) {
			OrderDecisionDefault orderDecision = new OrderDecisionDefault(tile,
					WorldObjectType.NULL);
			// add an order choice with no tool
			validOrderDecisions.add(orderDecision);

			if (!heroesToolsMaster.tileContainsTool(tile)) {
				for (final WorldObjectType possibleTool : heroesToolsMaster
						.getPossibleToolChoices()) {
					orderDecision = new OrderDecisionDefault(tile, possibleTool);
					// add an order with a tool choice
					validOrderDecisions.add(orderDecision);
				}
			}
		}
		return validOrderDecisions;
	}

	private void initTextures() {
		textureTileMovementValid = new Vector2(1, 1);
		textureTileMovementValidChoice = new Vector2(0, 2);
		textureTileMovementInvalidChoice = new Vector2(1, 0);
		textureCommonRanges = new Vector2(2, 0);
	}

	private void highlightPickedTile() {
		if (tilePickedOrder.isNull()) {
			return;
		}
		if (tileOrderValid) {
			tilePickedOrder.visualState().changeState(
					RenderableObjectVisualState.SELECTED);
			tilesLookController.changeTileTexture(tilePickedOrder,
					textureTileMovementValidChoice);
		} else {
			tilePickedOrder.visualState().changeState(
					RenderableObjectVisualState.SELECTED_FAIL);
			tilesLookController.changeTileTexture(tilePickedOrder,
					textureTileMovementInvalidChoice);
		}
	}

	/**
	 * Change textures of tiles being in range of current hero and enemies.
	 * Common tiles should have other texture.
	 */
	private void highlightHeroEnemiesRanges() {
		tilesLookController.highlightTilesObjectsRange(currentHero,
				textureTileMovementValid, enemiesHelper.getEnemies(),
				textureTileMovementValid, textureCommonRanges);
	}
}
