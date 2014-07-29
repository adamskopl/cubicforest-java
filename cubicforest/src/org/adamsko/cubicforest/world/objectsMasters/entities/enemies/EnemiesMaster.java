package org.adamsko.cubicforest.world.objectsMasters.entities.enemies;

import java.util.List;

import org.adamsko.cubicforest.render.text.ROLabel;
import org.adamsko.cubicforest.roundsMaster.RoundsMaster;
import org.adamsko.cubicforest.world.WorldObjectsMaster;
import org.adamsko.cubicforest.world.mapsLoader.CFMap;
import org.adamsko.cubicforest.world.mapsLoader.tiled.TiledObjectType;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.InteractionObjectsMaster;
import org.adamsko.cubicforest.world.ordersMaster.OrderableObjectsContainer;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class EnemiesMaster extends InteractionObjectsMaster implements
		WorldObjectsMaster, OrderableObjectsContainer {

	public EnemiesMaster(final TilesMaster TM, final RoundsMaster roundsMaster,
			final String textureName, final int tileW, final int tileH) {
		super("enemiesMaster", TM, WorldObjectType.OBJECT_ENTITY, textureName,
				tileW, tileH);

	}

	@Override
	public void update(final float deltaTime) {

	}

	@Override
	public List<WorldObject> getOrderableObjects() {
		return getWorldObjects();
	}

	@Override
	public void loadMapObjects(final CFMap map) throws Exception {
		final List<Vector2> coords = map
				.getObjectTypeCoords(TiledObjectType.TILED_ENTITY_ENEMY);

		Enemy enemy;
		int nameIndex = 0;
		int atlasIndex = 0;
		for (final Vector2 pos : coords) {
			enemy = new Enemy(atlasRows.get(0)[atlasIndex], atlasIndex);
			enemy.setRenderVector(new Vector2(-atlasRows.get(0)[0]
					.getRegionWidth() / 2, -7));

			enemy.setSpeed(5);

			pos.add(new Vector2(0.5f, 0.5f));
			enemy.setTilesPos(pos);
			enemy.setName("E" + nameIndex);
			enemy.setVerticalPos(0.3f);
			enemy.addLabel(ROLabel.LABEL_NAME);
			enemy.altLabelLast(Color.ORANGE, 1.0f, -10.0f, 10.0f);

			addObject(enemy);

			nameIndex++;
			if (atlasIndex == 2) {
				atlasIndex = 0;
			} else {
				atlasIndex++;
			}
		}
	}

	@Override
	public void unloadMapObjects() throws Exception {
		removeWorldObjects();
	}

	public void removeEnemy(final Enemy enemyToRemove) {
		try {
			removeObject(enemyToRemove);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

}
