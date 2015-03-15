package org.adamsko.cubicforest.world.objectsMasters.entities.enemies;

import java.util.List;

import org.adamsko.cubicforest.render.text.ROLabel;
import org.adamsko.cubicforest.world.mapsLoader.CFMap;
import org.adamsko.cubicforest.world.mapsLoader.tiled.TiledObjectType;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.objectsMasters.WorldObjectsMasterDefault;
import org.adamsko.cubicforest.world.ordersMaster.OrderableObjectsContainer;
import org.adamsko.cubicforest.world.tile.TileDirection;
import org.adamsko.cubicforest.world.tile.TilesMaster;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class EnemiesMaster extends WorldObjectsMasterDefault implements
		OrderableObjectsContainer {

	private int nameIndex;
	private int atlasIndex;

	public EnemiesMaster(final TilesMaster tilesMaster,
			final String textureName, final int tileW, final int tileH) {
		super("EnemiesMaster", "enemy", tilesMaster, textureName, tileW, tileH);

	}

	@Override
	public boolean isNull() {
		return false;
	};

	@Override
	public void update(final float deltaTime) {

	}

	@Override
	public List<WorldObject> getOrderableObjects() {
		return getWorldObjects();
	}

	@Override
	public WorldObject factoryMethod(final Vector2 tilePos) {
		final Enemy enemy = new Enemy(getObjectsTextureChanger(),
				atlasRows.get(0)[atlasIndex], atlasIndex, this);
		enemy.setRenderVector(new Vector2(
				-atlasRows.get(0)[0].getRegionWidth() / 2, -7));
		enemy.setTextureRegionCubic(cubicTextureRegion);
		enemy.setRenderVectorCubic(new Vector2(-40.0f, -23.0f));

		enemy.setSpeed(5);

		final Vector2 pos = new Vector2(tilePos);
		pos.add(new Vector2(0.5f, 0.5f));
		enemy.setTilesPos(pos);
		enemy.setName("E" + nameIndex);
		enemy.setVerticalPos(0.3f);
		enemy.addLabel(ROLabel.LABEL_NAME);
		enemy.altLabelLast(Color.ORANGE, 1.0f, -10.0f, 10.0f);
		enemy.tileDirection().changeDirection(
				TileDirection.randomTileDirection());
		return enemy;
	}

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
				.getObjectTypeCoords(TiledObjectType.TILED_ENTITY_ENEMY);

		loadMapObjects(tilePositions);

	}

	@Override
	public void unloadMapObjects() {
		removeWorldObjects();
	}

}
