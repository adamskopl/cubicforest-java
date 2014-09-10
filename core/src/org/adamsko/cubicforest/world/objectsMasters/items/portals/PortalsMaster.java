package org.adamsko.cubicforest.world.objectsMasters.items.portals;

import java.util.List;

import org.adamsko.cubicforest.world.mapsLoader.CFMap;
import org.adamsko.cubicforest.world.mapsLoader.tiled.TiledObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectsMasterDefault;
import org.adamsko.cubicforest.world.tile.TilesMaster;

import com.badlogic.gdx.math.Vector2;

public class PortalsMaster extends WorldObjectsMasterDefault {

	public PortalsMaster(final TilesMaster tilesMaster,
			final String textureName, final int tileW, final int tileH) {
		super("PortalsMaster", tilesMaster, textureName, tileW, tileH);
	}

	@Override
	public void update(final float deltaTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadMapObjects(final CFMap cfMap) throws Exception {
		final List<Vector2> coords = cfMap
				.getObjectTypeCoords(TiledObjectType.TILED_PORTAL);

		Portal portal;
		int atlasIndex = 0;
		for (final Vector2 pos : coords) {
			portal = new Portal(atlasRows.get(0)[atlasIndex / 2],
					atlasIndex / 2, this);
			portal.setRenderVector(new Vector2(-atlasRows.get(0)[0]
					.getRegionWidth() / 2, -11));
			pos.add(new Vector2(0.5f, 0.5f));
			portal.setTilesPos(pos);
			portal.setVerticalPos(0.2f);

			portal.setName("tree");

			addObject(portal);
			atlasIndex++;
		}

	}

	@Override
	public void unloadMapObjects() throws Exception {
		removeWorldObjects();
	}

	public void portalHighlight(final Portal portal) {
		portal.setTextureRegion(atlasRows.get(1)[portal.getTexNum()]);
	}

	public void portalUnHighlight(final Portal portal) {
		portal.setTextureRegion(atlasRows.get(0)[portal.getTexNum()]);
	}

}
