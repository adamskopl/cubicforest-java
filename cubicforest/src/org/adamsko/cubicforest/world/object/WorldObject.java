package org.adamsko.cubicforest.world.object;

import org.adamsko.cubicforest.world.WorldObjectsMaster;
import org.adamsko.cubicforest.world.tilesMaster.Tile;
import org.adamsko.cubicforest.world.tilesMaster.TilesMaster.TileEvent_e;

import com.badlogic.gdx.math.Vector2;

/**
 * @author adamsko
 * 
 */
public class WorldObject {

	private WorldObjectType_e type;
	
	/*
	 * Position indicated by tiles. (0.0,0.0): uppper corner of the first tile.
	 * (2.5, 1.5): center of the field with (2, 1) coordinates
	 */
	protected Vector2 tilesPos;

	/*
	 * Vertical position of the object. Indicates order of the rendering.
	 */
	protected Float height;

	/**
	 * Long story short: WorldObject's name.
	 */
	protected String name;

	/**
	 * {@link WorldObjectsMaster} which is managing this object.
	 */
	private WorldObjectsMaster master = null;
	
	/**
	 * How many tiles object can move. 
	 */
	private int speed;
	
	/**
	 * Is this object occupying tile or leaving it free?
	 */
	private Boolean occupiesTile;

	public WorldObject(WorldObjectType_e type) {
		tilesPos = new Vector2(0.0f, 0.0f);
		height = new Float(0.0f);
		name = new String("WorldObject");
		speed = 0;
		occupiesTile = true;
	}

	/**
	 * Set {@link WorldObjectsMaster} which is managing this object.
	 * 
	 * @param master
	 *            {@link WorldObjectsMaster} of this object
	 */
	public void setMaster(WorldObjectsMaster master) {
		this.master = master;
	}

	public void setTilesPos(Vector2 pos) {
		this.tilesPos.set(pos);
	}

	public void setTilesPosX(float x) {
		this.tilesPos.set(x, this.tilesPos.y);
	}

	public void setTilesPosY(float y) {
		this.tilesPos.set(this.tilesPos.x, y);
	}

	public Vector2 getTilesPos() {
		return new Vector2(this.tilesPos);
	}

	public float getTilesPosX() {
		return this.tilesPos.x;
	}

	public float getTilesPosY() {
		return this.tilesPos.y;
	}

	public void setHeight(Float height) {
		this.height = height;
	}

	public float getHeight() {
		return height;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setOccupiesTile(Boolean occupiesTile) {
		this.occupiesTile = occupiesTile;
	}
	
	public Boolean isOccupyingTile() {
		return this.occupiesTile;
	}
	
	public WorldObjectType_e getType() {
		return type;
	}
	
	public String typeToString() {
		try {
			return WorldObjectHelper.typeToString(type);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return "TYPE_ERROR";
	}

	/**
	 * Handle {@link TileEvent_e} event of a parent {@link Tile}.
	 * 
	 * @param tileEvent
	 *            event associated with a parent {@link Tile}.
	 */
	public void handleParentTileEvent(TileEvent_e tileEvent) {
		master.handleServantTileEvent(this, tileEvent);
	}

}