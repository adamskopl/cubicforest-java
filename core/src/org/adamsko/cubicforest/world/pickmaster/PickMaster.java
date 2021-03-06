package org.adamsko.cubicforest.world.pickmaster;

import java.util.ArrayList;
import java.util.List;

import org.adamsko.cubicforest.Nullable;
import org.adamsko.cubicforest.render.world.coordCalc.CoordCalc;
import org.adamsko.cubicforest.world.tile.Tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * Handles device inputs and passes them (also converted to {@link Tile}
 * positions) to interested {@link PickMasterClient} objects.
 * 
 * @author adamsko
 * 
 */
public class PickMaster implements Nullable {
	private List<PickMasterClient> clients;
	private CoordCalc coordCalc;

	PickMaster(final int nullConstructor) {
	}

	public PickMaster(final CoordCalc coordCalc) {
		clients = new ArrayList<PickMasterClient>();
		this.coordCalc = coordCalc;
	}

	@Override
	public boolean isNull() {
		return false;
	}

	public void addClient(final PickMasterClient client) {
		if (client.isNull()) {
			Gdx.app.error("PickMaster::addClient()", "client.isNull()");
		}
		clients.add(client);
	}

	public void update() {
		// justTouched() used because the game is handling only single clicks
		if (Gdx.input.justTouched()) {
			final Vector2 screenPos = new Vector2(Gdx.input.getX(),
					Gdx.input.getY());
			final Vector2 tilesPos = new Vector2(
					coordCalc.screenToTiles(screenPos));
			for (final PickMasterClient client : clients) {
				client.onInput(screenPos, tilesPos);
			}
		}
	}
}
