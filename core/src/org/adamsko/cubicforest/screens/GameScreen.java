package org.adamsko.cubicforest.screens;

import org.adamsko.cubicforest.render.world.CubicWorldRenderer;
import org.adamsko.cubicforest.render.world.GameRenderer;
import org.adamsko.cubicforest.render.world.coordCalc.CoordCalc;
import org.adamsko.cubicforest.render.world.coordCalc.CoordCalcDefault;
import org.adamsko.cubicforest.world.CubicWorldBuilder;
import org.adamsko.cubicforest.world.GameWorldBuilder;
import org.adamsko.cubicforest.world.object.CubicObject;
import org.adamsko.cubicforest.world.object.accessor.WorldObjectAccessor;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMastersContainer;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;

public class GameScreen implements Screen {

	Game game;
	GameRenderer worldRenderer;
	GameWorldBuilder worldBuilder;
	CoordCalc coordCalcX;

	public static TweenManager tweenManager;

	public GameScreen(final Game game) {
		this.game = game;
	}

	private void initTween() {
		tweenManager = new TweenManager();
		Tween.registerAccessor(CubicObject.class, new WorldObjectAccessor());
	}

	@Override
	public void show() {
		Gdx.app.setLogLevel(com.badlogic.gdx.Application.LOG_DEBUG);
		initTween();

		coordCalcX = new CoordCalcDefault(75);

		worldRenderer = new CubicWorldRenderer();

		initGameBuilder(worldRenderer);

		worldRenderer.setCoordCalc(worldBuilder.getCoordCalc());

	}

	@Override
	public void render(float delta) {
		delta = Math.min(0.06f, Gdx.graphics.getDeltaTime());
		worldBuilder.update(delta);
		tweenManager.update(delta);

		Gdx.gl.glClearColor(0.2f, 0.3f, 0.4f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		worldRenderer.render(delta);
	}

	@Override
	public void hide() {
		worldRenderer.dispose();
	}

	@Override
	public void resize(final int width, final int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	private void initGameBuilder(final GameRenderer worldRenderer) {

		// the following operations should be performed in the presented order,
		// some classes are initialized partially and finished being initialized
		// after some previous classes finish their initialization

		worldBuilder = new CubicWorldBuilder();

		worldBuilder.initCoordCalc(75);

		worldBuilder.initWorldObjectsMastersContainer(worldRenderer);

		final WorldObjectsMastersContainer worldObjectsMastersContainer = worldBuilder
				.getWorldObjectsMastersContainer();

		worldBuilder.initTilePathSearcher(worldObjectsMastersContainer
				.getTilesMaster());

		worldBuilder.initCollisionVisitorsManagerFactory(
				worldObjectsMastersContainer.getGatherCubesMaster(),
				worldObjectsMastersContainer.getHeroesToolsMaster());

		worldBuilder.initMapsLoader(worldObjectsMastersContainer,
				worldBuilder.getCollisionVisitorsManagerFactory());

		worldBuilder.initRoundsMaster(worldBuilder.getMapsLoader());

		worldBuilder.initTilesMasterRoundsMaster(
				worldObjectsMastersContainer.getTilesMaster(),
				worldBuilder.getRoundsMaster(),
				worldBuilder.getCollisionVisitorsManagerFactory());

		worldBuilder.initOrdersMaster(worldObjectsMastersContainer
				.getTilesMaster());

		worldBuilder.mapsLoaderReloadWorld();

		worldBuilder.initRoundsMasterPhases(worldBuilder.getOrdersMaster(),
				worldObjectsMastersContainer, worldBuilder
						.getTilePathSearcher(), worldObjectsMastersContainer
						.getTilesMaster().getTilesLookController());

		worldBuilder.initGuiMaster(worldRenderer, worldBuilder.getMapsLoader(),
				worldObjectsMastersContainer.getGatherCubesMaster(),
				worldBuilder.getRoundsMaster());

		worldBuilder.initPickMaster(worldBuilder.getGuiMaster(),
				worldObjectsMastersContainer.getTilesMaster(),
				worldBuilder.getCoordCalc());

	}
}
