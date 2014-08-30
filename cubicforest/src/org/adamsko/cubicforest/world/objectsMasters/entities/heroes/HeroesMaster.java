package org.adamsko.cubicforest.world.objectsMasters.entities.heroes;

import java.util.List;

import org.adamsko.cubicforest.render.text.ROLabel;
import org.adamsko.cubicforest.world.mapsLoader.CFMap;
import org.adamsko.cubicforest.world.mapsLoader.tiled.TiledObjectType;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectsContainer;
import org.adamsko.cubicforest.world.ordersMaster.OrderableObjectsContainer;
import org.adamsko.cubicforest.world.tile.TilesMaster;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class HeroesMaster extends WorldObjectsContainer implements
		OrderableObjectsContainer {

	public HeroesMaster(final TilesMaster tilesMaster,
			final String textureName, final int tileW, final int tileH) {
		super("HeroesMaster", tilesMaster, textureName, tileW, tileH);

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
	public void loadMapObjects(final CFMap map) throws Exception {
		final List<Vector2> coords = map
				.getObjectTypeCoords(TiledObjectType.TILED_ENTITY_HERO);

		Hero hero;
		int nameIndex = 0;
		int atlasIndex = 0;
		for (final Vector2 pos : coords) {
			hero = new Hero(atlasRows.get(0)[atlasIndex], atlasIndex, this);
			hero.setRenderVector(new Vector2(-atlasRows.get(0)[0]
					.getRegionWidth() / 2, -5));

			hero.setSpeed(3);

			pos.add(new Vector2(0.5f, 0.5f));
			hero.setTilesPos(pos);
			hero.setName("H" + nameIndex);
			hero.addLabel(ROLabel.LABEL_NAME);
			hero.altLabelLast(Color.ORANGE, 1.0f, -10.0f, 10.0f);
			hero.setVerticalPos(0.3f);

			addObject(hero);

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

}
