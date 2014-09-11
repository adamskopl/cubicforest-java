package org.adamsko.cubicforest.world.objectsMasters.items.portals;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.world.mapsLoader.CFMap;
import org.adamsko.cubicforest.world.mapsLoader.tiled.TiledObjectType;
import org.adamsko.cubicforest.world.object.WorldObjectsMasterDefault;
import org.adamsko.cubicforest.world.tile.Tile;
import org.adamsko.cubicforest.world.tile.TilesContainer;
import org.adamsko.cubicforest.world.tile.TilesMaster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class PortalsMaster extends WorldObjectsMasterDefault {

	private final List<Portal> portals;
	private final TilesContainer tilesContainer;

	public PortalsMaster(final TilesMaster tilesMaster,
			final String textureName, final int tileW, final int tileH,
			final TilesContainer tilesContainer) {
		super("PortalsMaster", tilesMaster, textureName, tileW, tileH);
		portals = new ArrayList<Portal>();
		this.tilesContainer = tilesContainer;
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
			final Tile parentTile = tilesContainer.getTileOnPos(pos);
			if (parentTile.isNull()) {
				Gdx.app.error("PortalsMaster::loadMapObjects()",
						"tile.isNull()");
				continue;
			}

			portal = new CubicPortal(atlasRows.get(0)[atlasIndex / 2],
					atlasIndex / 2, this, parentTile);
			portal.setRenderVector(new Vector2(-atlasRows.get(0)[0]
					.getRegionWidth() / 2, -11));
			pos.add(new Vector2(0.5f, 0.5f));
			portal.setTilesPos(pos);
			portal.setVerticalPos(0.2f);

			portal.setName("portal");

			addObject(portal);
			portals.add(portal);
			atlasIndex++;
		}

		setTwinPortals();

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

	/**
	 * After loading portals, pair them.
	 */
	private void setTwinPortals() {
		int pairC = 0;
		if (portals.size() % 2 != 0) {
			Gdx.app.error("PortalsMaster::setTwinPortals",
					"odd number of portals");
		}
		for (final Portal portal : portals) {
			// for every second portal
			if (pairC % 2 == 1) {
				portals.get(pairC).setTwinPortal(portals.get(pairC - 1));
				portals.get(pairC - 1).setTwinPortal(portals.get(pairC));
			}
			pairC++;
		}
	}
}
