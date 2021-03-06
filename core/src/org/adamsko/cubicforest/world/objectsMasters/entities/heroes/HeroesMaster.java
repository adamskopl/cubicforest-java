package org.adamsko.cubicforest.world.objectsMasters.entities.heroes;

import java.util.List;

import org.adamsko.cubicforest.render.text.ROLabel;
import org.adamsko.cubicforest.world.mapsLoader.CFMap;
import org.adamsko.cubicforest.world.mapsLoader.tiled.TiledObjectType;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMasterDefault;
import org.adamsko.cubicforest.world.ordersMaster.OrderableObjectsContainer;
import org.adamsko.cubicforest.world.tile.TileDirection;
import org.adamsko.cubicforest.world.tile.TilesMaster;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class HeroesMaster extends WorldObjectsMasterDefault implements
		OrderableObjectsContainer {

	private int nameIndex;
	private int atlasIndex;

	public HeroesMaster(final TilesMaster tilesMaster) {
		super("HeroesMaster", "hero", tilesMaster);

	}

	@Override
	public boolean isNull() {
		return false;
	}

	public WorldObject getTestObject() {
		return getWorldObjects().get(0);
	}

	@Override
	public void update(final float deltaTime) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<WorldObject> getOrderableObjects() {
		return getWorldObjects();
	}

	@Override
	public WorldObject factoryMethod(final Vector2 tilePos) {
		final Hero hero = new Hero(this);
		hero.setTexturesManager(getTexturesManager(), WorldObjectType.HERO);

		hero.setRenderVector(new Vector2(-35.0f, -23.0f));

		hero.setSpeed(5);

		final Vector2 pos = new Vector2(tilePos);
		pos.add(new Vector2(0.5f, 0.5f));
		hero.setTilesPos(pos);
		hero.setName("H" + nameIndex);
		hero.addLabel(ROLabel.LABEL_NAME);
		hero.altLabelLast(Color.ORANGE, 1.0f, -10.0f, 10.0f);
		hero.setVerticalPos(0.3f);

		hero.tileDirection().changeDirection(
				TileDirection.randomTileDirection());

		return hero;
	};

	@Override
	public void loadMapObjects(final List<Vector2> tilePositions) {
		nameIndex = 0;
		atlasIndex = 0;
		for (final Vector2 pos : tilePositions) {

			addObject(factoryMethod(pos));

			nameIndex++;
			if (atlasIndex == 2) {
				atlasIndex = 0;
			} else {
				atlasIndex++;
			}
		}

	}

	@Override
	public void loadMapObjects(final CFMap map) throws Exception {
		final List<Vector2> tilePositions = map
				.getObjectTypeCoords(TiledObjectType.TILED_ENTITY_HERO);

		loadMapObjects(tilePositions);

	}

	@Override
	public void unloadMapObjects() {
		removeWorldObjects();
	}

}
