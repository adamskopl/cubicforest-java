package org.adamsko.cubicforest.world.objectsMasters.entities.heroes;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.render.text.ROLabel_e;
import org.adamsko.cubicforest.render.world.RenderableObject;
import org.adamsko.cubicforest.roundsMaster.RoundsMaster;
import org.adamsko.cubicforest.world.WorldObjectsMaster;
import org.adamsko.cubicforest.world.mapsLoader.MapsLoader;
import org.adamsko.cubicforest.world.mapsLoader.converter.TiledObjectType_e;
import org.adamsko.cubicforest.world.object.WorldObject;
import org.adamsko.cubicforest.world.object.WorldObjectType_e;
import org.adamsko.cubicforest.world.objectsMasters.interactionMaster.InteractionObjectsMaster;
import org.adamsko.cubicforest.world.ordersMaster.OrderableObjectsContainer;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class HeroesMaster extends InteractionObjectsMaster implements
		WorldObjectsMaster, OrderableObjectsContainer {

	public HeroesMaster(MapsLoader mapsLoader, TilesMaster TM,
			RoundsMaster roundsMaster, String textureName, int tileW, int tileH) {
		super("HeroesMaster", mapsLoader, TM, roundsMaster, WorldObjectType_e.OBJECT_ENTITY,
				textureName, tileW, tileH);
		try {
			loadMapObjects(mapsLoader);
		} catch (Exception e) {
			Gdx.app.error("HeroesMaster", e.toString());
		}
	}

	public void handleServantTileEvent(WorldObject servant,
			TileEvent_e tileEvent) {
		switch (tileEvent) {
		case TILE_PICKED: {
			RenderableObject servantConv = (RenderableObject) servant;
			// int texNum = servantConv.getTexNum();
			// servantConv.setTextureRegion(atlasRows.get(1)[texNum]);
		}
		default: {

		}
		}
	}

	public WorldObject getTestObject() {
		return getWorldObjects().get(0);
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<WorldObject> getOrderableObjects() {
		return getWorldObjects();
	}

	@Override
	public void tileEvent(TileEvent_e evenType, Tile eventTile,
			WorldObject eventObject) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadMapObjects(MapsLoader mapsLoader) throws Exception {
		List<Vector2> coords = mapsLoader
				.getCoords(TiledObjectType_e.TILED_ENTITY_HERO);

		Hero hero;
		int atlasIndex = 0;
		for (Vector2 pos : coords) {
			hero = new Hero(atlasRows.get(0)[atlasIndex], atlasIndex);
			hero.setRenderVector(new Vector2(-atlasRows.get(0)[0]
					.getRegionWidth() / 2, -5));

			hero.setSpeed(4);

			pos.add(new Vector2(0.5f, 0.5f));
			hero.setTilesPos(pos);
			hero.setName("H" + atlasIndex);
			hero.addLabel(ROLabel_e.LABEL_NAME);
			hero.altLabelLast(Color.ORANGE, 1.0f, -10.0f, 10.0f);
			hero.setVerticalPos(0.1f);

			addObject(hero);

			if (atlasIndex == 2) {
				atlasIndex = 0;
			} else {
				atlasIndex++;
			}
		}

	}

	@Override
	public void reload(MapsLoader mapsLoader) {
		try {
			removeWorldObjects();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			loadMapObjects(mapsLoader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
